CREATE TABLE sms_info(
    Id INT NOT NULL   COMMENT '主键' ,
    msg_Id VARCHAR(32)    COMMENT '消息ID' ,
    phone VARCHAR(32)    COMMENT '电话号码' ,
    msg VARCHAR(3120)    COMMENT '消息内容' ,
    create_date DATETIME    COMMENT '创建时间' ,
    state VARCHAR(32)    COMMENT '状态,0待处理1成功2失败' ,
    state_date DATETIME    COMMENT '状态时间' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    PRIMARY KEY (Id)
) COMMENT = ' 短信发送记录表';