package com.itheima.code;

import java.util.Date;

/**
 * @Auther: manlou-lu.guangrong
 * @Date: 2018/10/30 09:04
 * @Description: God bless no bugs!!!
 */
public class RoleResDto {
    private String roleId;

    private String name;

    private Integer industry;

    private String description;

    private Date createTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
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
}
