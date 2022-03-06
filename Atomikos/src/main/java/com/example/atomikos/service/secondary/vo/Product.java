package com.example.atomikos.service.secondary.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
@NoArgsConstructor
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("p_id")
    private String pId;

    @TableField("p_name")
    private String pName;

    @TableField("p_desc")
    private String pDesc;

    @TableField("version")
    private Integer version;

    @TableField("status")
    private String status;

    @TableField("create_date")
    private LocalDate createDate;

    @TableField("update_date")
    private LocalDate updateDate;


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Product{" +
        "pId=" + pId +
        ", pName=" + pName +
        ", pDesc=" + pDesc +
        ", version=" + version +
        ", status=" + status +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
