package com.guyulei.emarket.bean;

/**
 * Created by A on 2017/8/25.
 */

public class AppInfo {

    /**
     * des : 产品介绍：有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、
     * downloadUrl : app/com.youyuan.yyhl/com.youyuan.yyhl.apk
     * iconUrl : app/com.youyuan.yyhl/icon.jpg
     * id : 1525490
     * name : 有缘网
     * packageName : com.youyuan.yyhl
     * size : 3876203
     * stars : 4
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int    id;
    private String name;
    private String packageName;
    private int    size;
    private int    stars;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
