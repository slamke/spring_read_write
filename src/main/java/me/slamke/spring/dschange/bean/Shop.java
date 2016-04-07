package me.slamke.spring.dschange.bean;

import java.util.Date;

public class Shop {

    /** 主键id */
    private Integer id;

    /** 外部平台店铺id */
    private String shopId;

    /** 店铺所属类目id */
    private String catId;

    /** 卖家昵称 */
    private String nick;

    /** 店铺标题 */
    private String title;

    /** 店铺描述 */
    private String description;

    /** 开店时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date updateTime;

    private boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String toString(){
        return "Shop={" +
                "id = " + id + "," +
                "shopId = " + shopId + "," +
                "catId = " + catId + "," +
                "nick = " + nick + "," +
                "title = " + title + "," +
                "description = " + description + "," +
                "createTime = " + createTime + "," +
                "updateTime = " + updateTime +
                "}";
    }
}
