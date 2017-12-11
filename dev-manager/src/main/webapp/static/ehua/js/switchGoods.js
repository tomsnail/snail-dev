/**
 * Created by Administrator on 2017/3/6.
 */
$(function () {
    switchGoods();
})
function switchGoods () {
    $("#checkAll").click(function () {
        var isChecked=$(this)[0].checked;
        $("table :checkbox").each(function () {
            $(this).prop("checked",isChecked);
        })
    })
}