package cn.tomsnail.snail.core.log.ls;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "FileAppender", category = "Core", elementType = "appender", printObject = true)
public class FileAppender extends AbstractAppender{

	
	protected FileAppender(String name, Filter filter, Layout<? extends Serializable> layout,
			boolean ignoreExceptions) {
		super(name, filter, layout, ignoreExceptions);
	}


	@Override
	public void append(LogEvent event) {
		System.out.println(">>>>>>"+new String(getLayout().toByteArray(event)));
	}
	
	
	@PluginFactory
    public static FileAppender createAppender(@PluginAttribute("name") String name,
                                              @PluginElement("Filter") final Filter filter,
                                              @PluginElement("Layout") Layout<? extends Serializable> layout,
                                              @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
       
        return new FileAppender(name, filter, layout, ignoreExceptions);
    }

}
