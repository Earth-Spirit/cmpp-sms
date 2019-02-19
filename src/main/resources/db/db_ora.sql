CREATE TABLE sms_info(
    Id INT NOT NULL,
    msg_Id NVARCHAR2(32),
    phone NVARCHAR2(32),
    msg VARCHAR2(3120),
    create_date DATE default sysdate,
    state NVARCHAR2(32),
    state_date DATE,
    remark VARCHAR2(1024),
    PRIMARY KEY (Id)
);

COMMENT ON TABLE sms_info IS '短信发送记录表';
COMMENT ON COLUMN sms_info.Id IS '主键';
COMMENT ON COLUMN sms_info.msg_Id IS '消息ID';
COMMENT ON COLUMN sms_info.phone IS '电话号码';
COMMENT ON COLUMN sms_info.msg IS '消息内容';
COMMENT ON COLUMN sms_info.create_date IS '创建时间';
COMMENT ON COLUMN sms_info.state IS '状态,0待处理1成功2失败';
COMMENT ON COLUMN sms_info.state_date IS '状态时间';
COMMENT ON COLUMN sms_info.remark IS '备注';


-- Create sequence
create sequence seq_sms_info_id  --Sequence实例名
minvalue 1                                --最小值，可以设置为0
maxvalue 2147483647                       --最大值
start with 1                              --从1开始计数
increment by 1                            --每次加几个
cache 20;