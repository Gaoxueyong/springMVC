package com.sp.entity;

import java.util.Date;
/**
 * ****************************************************************
 *    http://blog.csdn.net/fenfenguai  
 *    Package: com.sp.entity
 *    Filename: SysProject.java
 *    Description:  项目信息
 *    @author: Gaoxueyong
 *    @version: 1.0.0
 *    Create at: 2017年2月7日 上午10:48:29
 *    Revision:
 *    2017年2月7日 上午10:48:29- first revision
 *
 ****************************************************************
 */
public class SysProject {
	
    private String id;  //编号

    private String name;//名称

    private String type;//类型

    private String createBy;//创建人

    private Date createDate;//创建时间

    private String updateBy;//修改人

    private Date updateDate;//修改时间

    private String remarks;//备注

    private String delFlag;//删除标记 默认为0  删除为1

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}