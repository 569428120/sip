package com.sip.user.model;

public class AuthorityModel {
    public static final String AUTHORITY_TYPE_SYSTEM = "system";
    public static final String AUTHORITY_TYPE_MENU = "menu";
    public static final String AUTHORITY_TYPE_MENU_ITEM = "menuItem";
    public static final String AUTHORITY_TYPE_OPERATION = "operation";

    private Long parentId;
    private String authorityType;
    private String name;
    private String path;
    private String icon;
    private String level;
    private Boolean exact;
    private String description;
}
