/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDao;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDataDao;
import com.thinkgem.jeesite.modules.cms.dao.CategoryDao;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.ArticleData;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.utils.CreateHtmlUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.zkjd.ehua.common.utils.DataUtil;
import com.zkjd.ehua.common.utils.MQUtil;
import com.zkjd.ehua.common.utils.service.QiniuService;

/**
 * 文章Service
 * @author ThinkGem
 * @version 2013-05-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleService extends CrudService<ArticleDao, Article> {
	
	//private static final String DOMAIN = DataUtil.getSysConfig("STATIC_FILE", "103003");//"http://on3v335rm.bkt.clouddn.com/";

	@Autowired
	private ArticleDataDao articleDataDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private QiniuService qiniuService;
	
	@Transactional(readOnly = false)
	public Page<Article> findPage(Page<Article> page, Article article, boolean isDataScopeFilter) {
		// 更新过期的权重，间隔为“6”个小时
		Date updateExpiredWeightDate =  (Date)CacheUtils.get("updateExpiredWeightDateByArticle");
		if (updateExpiredWeightDate == null || (updateExpiredWeightDate != null 
				&& updateExpiredWeightDate.getTime() < new Date().getTime())){
			dao.updateExpiredWeight(article);
			CacheUtils.put("updateExpiredWeightDateByArticle", DateUtils.addHours(new Date(), 6));
		}
//		DetachedCriteria dc = dao.createDetachedCriteria();
//		dc.createAlias("category", "category");
//		dc.createAlias("category.site", "category.site");
		if (article.getCategory()!=null && StringUtils.isNotBlank(article.getCategory().getId()) && !Category.isRoot(article.getCategory().getId())){
			Category category = categoryDao.get(article.getCategory().getId());
			if (category==null){
				category = new Category();
			}
			category.setParentIds(category.getId());
			category.setSite(category.getSite());
			article.setCategory(category);
		}
		else{
			article.setCategory(new Category());
		}
//		if (StringUtils.isBlank(page.getOrderBy())){
//			page.setOrderBy("a.weight,a.update_date desc");
//		}
//		return dao.find(page, dc);
	//	article.getSqlMap().put("dsf", dataScopeFilter(article.getCurrentUser(), "o", "u"));
		return super.findPage(page, article);
		
	}

	@Transactional(readOnly = false)
	public void save(Article article,HttpServletRequest request) {
		if (article.getArticleData().getContent()!=null){
			article.getArticleData().setContent(StringEscapeUtils.unescapeHtml4(
					article.getArticleData().getContent()));
		}
		// 如果没有审核权限，则将当前内容改为待审核状态
//		if (!UserUtils.getSubject().isPermitted("cms:article:audit")){
//			article.setDelFlag(Article.DEL_FLAG_AUDIT);
//		}
		// 如果栏目不需要审核，则将该内容设为发布状态
//		if (article.getCategory()!=null&&StringUtils.isNotBlank(article.getCategory().getId())){
//			Category category = categoryDao.get(article.getCategory().getId());
//			if (!Global.YES.equals(category.getIsAudit())){
//				article.setDelFlag(Article.DEL_FLAG_NORMAL);
//			}
//		}
		article.setUpdateBy(UserUtils.getUser());
		article.setUpdateDate(new Date());
        if (StringUtils.isNotBlank(article.getViewConfig())){
            article.setViewConfig(StringEscapeUtils.unescapeHtml4(article.getViewConfig()));
        }
        
        String isStatic = article.getIsStatic();
        
        
        
        if(!StringUtils.isBlank(isStatic)&&isStatic.equals("1")){
        	
        	HashMap<String,Object> data = new HashMap<String,Object>(); 
        	data.put("title", article.getTitle());
        	data.put("dataTime", com.thinkgem.jeesite.common.utils.DateUtils.formatDateTime(article.getPublishDate()));
        	data.put("author", article.getAuthor());
        	data.put("source", article.getArticleData().getCopyfrom());
        	data.put("context", article.getArticleData().getContent());
        	String domainUrl = DataUtil.getSysConfig("system.domain.url", "103006");
        	String apiUrl = DataUtil.getSysConfig("system.api.url", "103006");
        	String staticfileUrl = DataUtil.getSysConfig("STATIC_FILE", "103003");
        	data.put("domainUrl", domainUrl);
        	data.put("apiUrl", apiUrl);
        	data.put("staticfileUrl", staticfileUrl);
        	data.put("className", article.getCategory().getName());
        	data.put("classification_id", article.getCategory().getId());
        	data.put("keywords", article.getKeywords());
        	String fileName = "";
        	String staticUrl = "";
        	if(StringUtils.isBlank(article.getStaticUrl())){
        		fileName = "/"+System.currentTimeMillis()+".html";
        		article.setStaticName(fileName.replace("/", "").replace(".html", ""));
        		data.put("staticName", article.getStaticName());
        		staticUrl = CreateHtmlUtil.createHTML(request, data, article.getStaticTemplate(), fileName);
        	}else{
        		fileName = article.getStaticUrl().split("files")[1];
        		article.setStaticName(fileName.replace("/", "").replace(".html", ""));
        		data.put("staticName", article.getStaticName());
        		staticUrl = CreateHtmlUtil.createHTML(request, data, article.getStaticTemplate(), fileName);
        	}        	
        	qiniuService.upload(staticUrl, fileName, "staticfiles");
        	article.setStaticUrl(DataUtil.getSysConfig("STATIC_FILE", "103003")+"/files"+fileName);
        	
        }
        
        ArticleData articleData = new ArticleData();;
		if (StringUtils.isBlank(article.getId())){
			article.preInsert();
			articleData = article.getArticleData();
			articleData.setId(article.getId());
			dao.insert(article);
			articleDataDao.insert(articleData);
		}else{
			article.preUpdate();
			articleData = article.getArticleData();
			articleData.setId(article.getId());
			dao.update(article);
			articleDataDao.update(article.getArticleData());
		}
		MQUtil.sendNodeMsg("300007");
	}
	
	@Transactional(readOnly = false)
	public void delete(Article article, Boolean isRe) {
//		dao.updateDelFlag(id, isRe!=null&&isRe?Article.DEL_FLAG_NORMAL:Article.DEL_FLAG_DELETE);
		// 使用下面方法，以便更新索引。
		//Article article = dao.get(id);
		//article.setDelFlag(isRe!=null&&isRe?Article.DEL_FLAG_NORMAL:Article.DEL_FLAG_DELETE);
		//dao.insert(article);
		if(!StringUtils.isBlank(article.getIsStatic())&&article.getIsStatic().equals("1")){
			qiniuService.delete(article.getStaticUrl().replace(DataUtil.getSysConfig("STATIC_FILE", "103003"), ""), "staticfiles");
		}
		super.delete(article);
		MQUtil.sendNodeMsg("300007");
	}
	
	/**
	 * 通过编号获取内容标题
	 * @return new Object[]{栏目Id,文章Id,文章标题}
	 */
	public List<Object[]> findByIds(String ids) {
		if(ids == null){
			return new ArrayList<Object[]>();
		}
		List<Object[]> list = Lists.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		Article e = null;
		for(int i=0;(idss.length-i)>0;i++){
			e = dao.get(idss[i]);
			list.add(new Object[]{e.getCategory().getId(),e.getId(),StringUtils.abbr(e.getTitle(),50)});
		}
		return list;
	}
	
	/**
	 * 点击数加一
	 */
	@Transactional(readOnly = false)
	public void updateHitsAddOne(String id) {
		dao.updateHitsAddOne(id);
	}
	
	/**
	 * 更新索引
	 */
	public void createIndex(){
		//dao.createIndex();
	}
	
	/**
	 * 全文检索
	 */
	//FIXME 暂不提供检索功能
	public Page<Article> search(Page<Article> page, String q, String categoryId, String beginDate, String endDate){
		
		// 设置查询条件
//		BooleanQuery query = dao.getFullTextQuery(q, "title","keywords","description","articleData.content");
//		
//		// 设置过滤条件
//		List<BooleanClause> bcList = Lists.newArrayList();
//
//		bcList.add(new BooleanClause(new TermQuery(new Term(Article.FIELD_DEL_FLAG, Article.DEL_FLAG_NORMAL)), Occur.MUST));
//		if (StringUtils.isNotBlank(categoryId)){
//			bcList.add(new BooleanClause(new TermQuery(new Term("category.ids", categoryId)), Occur.MUST));
//		}
//		
//		if (StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate)) {   
//			bcList.add(new BooleanClause(new TermRangeQuery("updateDate", beginDate.replaceAll("-", ""),
//					endDate.replaceAll("-", ""), true, true), Occur.MUST));
//		}   
		
		//BooleanQuery queryFilter = dao.getFullTextQuery((BooleanClause[])bcList.toArray(new BooleanClause[bcList.size()]));

//		System.out.println(queryFilter);
		
		// 设置排序（默认相识度排序）
		//FIXME 暂时不提供lucene检索
		//Sort sort = null;//new Sort(new SortField("updateDate", SortField.DOC, true));
		// 全文检索
		//dao.search(page, query, queryFilter, sort);
		// 关键字高亮
		//dao.keywordsHighlight(query, page.getList(), 30, "title");
		//dao.keywordsHighlight(query, page.getList(), 130, "description","articleData.content");
		
		return page;
	}
	
}
