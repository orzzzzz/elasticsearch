package com.icinfo.springmvc.elasticsearch.model;

import io.searchbox.annotations.JestId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：虚拟news 搜索文章
 */
public class News {
    @JestId
    private int id;
    private String title;
    private String content;
    private String author;
    private int age;
    private BigDecimal money;
    private String creator;
    private Date createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
