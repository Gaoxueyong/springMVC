package com.sp.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @ClassName SysArea
 * @Description 区域管理
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月22日 上午11:17:03
 * @version 1.0.0
 */
public class SysArea {
	
    private String id;//编号

    private String parentId;//父级编号

    private String parentIds;//所有父级编号

    private String name;//名称

    private BigDecimal sort;//排序

    private String code;//区域编码

    private String type;//区域类型

    private String createBy;//创建者

    private Date createDate;//创建时间

    private String updateBy;//更新者

    private Date updateDate;//更新时间

    private String remarks;//备注信息

    private String delFlag;//删除标记 默认为0 1
    
    //虚拟字段 用于显示
    
    private String parentName;//父类名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		 this.parentName = parentName == null ? null : parentName.trim();
	}
    
    
}