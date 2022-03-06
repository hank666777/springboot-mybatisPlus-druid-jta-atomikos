package com.example.atomikos.service.primary.vo;

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
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("u_id")
    private String uId;

    @TableField("u_name")
    private String uName;

    @TableField("u_desc")
    private String uDesc;

    @TableField("version")
    private Integer version;

    @TableField("status")
    private String status;

    @TableField("create_date")
    private LocalDate createDate;

    @TableField("update_date")
    private LocalDate updateDate;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuDesc() {
        return uDesc;
    }

    public void setuDesc(String uDesc) {
        this.uDesc = uDesc;
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
        return "Users{" +
        "uId=" + uId +
        ", uName=" + uName +
        ", uDesc=" + uDesc +
        ", version=" + version +
        ", status=" + status +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
