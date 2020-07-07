package com.keepjoy.core;

public enum KeepJoyConstant {

    //MultipleReturn
    RESPONSETYPE_REDIRECT("redirect"),

    //大型模块
    MODULE_SECURITY("core"),
    MODULE_WEIXIN("weixin"),
    MODULE_WORKFLOW("workflow"),
    MODULE_MONITOR("monitor"),

    //文件后缀
    PDF("pdf"),
    TXT("txt"),
    XLS("xls"),XLSX("xlsx"),
    DOC("doc"),DOCX("docx"),
    HTML("html"),
    JPG("jpg"),JPEG("jpeg"),PNG("png"),GIF("gif"),
    CSS("css"),
    JS("js"),MAP("map"),
    TTF("ttf"),WOFF("woff"),WOFF2("woff2"),EOT("eot"),
    SVG("svg"),


    //content-type
    CONTENT_TYPE_PDF("application/pdf"),
    CONTENT_TYPE_EXCEL("application/vnd.ms-excel"),
    CONTENT_TYPE_HTML("text/html"),
    CONTENT_TYPE_PNG("image/png"),
    CONTENT_TYPE_JPG("image/jpg"),
    CONTENT_TYPE_GIF("image/gif"),
    CONTENT_TYPE_CSS("text/css"),
    CONTENT_TYPE_JS("text/javascript"),
    CONTENT_TYPE_DOC("application/msword"),
    CONTENT_TYPE_TTF("application/x-font-ttf"),
    CONTENT_TYPE_WOFF("application/x-font-woff"),
    CONTENT_TYPE_SVG("image/svg+xml"),
    CONTENT_TYPE_JSON("text/json");

    private String value;
    KeepJoyConstant(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }
}
