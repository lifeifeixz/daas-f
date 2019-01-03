/**
 * Created by flysLi on 2018/12/26.
 *
 * 工具
 */

function URLUtil() {
}
URLUtil.getParameter = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

function StrUtil() {
}
StrUtil.isNull = function (str) {
    return str == null ? true : false;
}
StrUtil.isNotEmpty = function (str) {
    return !StrUtil.isNull(str) && str.length > 0 ? true : false;
}
StrUtil.isNotBlank = function (str) {
    return str != null && str.trim().length > 0;
}

