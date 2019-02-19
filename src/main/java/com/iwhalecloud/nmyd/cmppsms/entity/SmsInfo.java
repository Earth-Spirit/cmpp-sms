package com.iwhalecloud.nmyd.cmppsms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;



public class SmsInfo implements Serializable,Cloneable{
    /** 主键 */
    private Integer id ;

    /** 消息ID */
    private String msgId ;

    /** 电话号码 */
    private String phone ;

    /** 消息内容 */
    private String msg ;

    /** 创建时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate ;

    /** 状态,0待处理1成功2失败 */
    private String state ;

    /** 状态时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime stateDate;

    /** 备注 */
    private String remark ;

    public SmsInfo() {
    }

    public SmsInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getStateDate() {
        return stateDate;
    }

    public void setStateDate(LocalDateTime stateDate) {
        this.stateDate = stateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "id=" + id +
                ", msgId='" + msgId + '\'' +
                ", phone='" + phone + '\'' +
                ", msg='" + msg + '\'' +
                ", createDate=" + createDate +
                ", state='" + state + '\'' +
                ", stateDate=" + stateDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}