package com.iwhalecloud.nmyd.cmppsms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iwhalecloud.nmyd.cmppsms.cmpp.CMPPClient;
import com.iwhalecloud.nmyd.cmppsms.context.SpringContextUtil;
import com.iwhalecloud.nmyd.cmppsms.entity.SmsInfo;
import com.iwhalecloud.nmyd.cmppsms.mapper.SmsMapper;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SmsService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CMPPClient client;

    @Autowired
    private SmsMapper smsMapper;

    /**
     * 根据状态获取短信数量，-1为获取全部数量
     * @param state
     * @return
     */
    public int getSmsCount(int state){

        return smsMapper.getSmsCount(state);
    }


    public int delete(List<Integer> ids){
        return smsMapper.detele(ids);
    };

    public int updateState(List<Integer> ids,int state){
        return smsMapper.updateState(ids,state);
    }
    /**
     * 获取失败短信记录数
     * @return
     */
    public int getFailSmsCount(){
        return smsMapper.getSmsCount(2);
    }

    /**
     * 获取待处理短信记录数
     * @return
     */
    public int getPendingSmsCount(){
        return smsMapper.getSmsCount(0);
    }


    /**
     * 按天统计短信处理数量
     * @return
     */
    public List getstatistics(){
        return smsMapper.getstatistics(-1);
    }


    /**
     * 获取分页数据
     * @param pageNum
     * @param pageSize
     * @param conditions
     * @return
     */
    public List<SmsInfo> page(int pageNum, int pageSize, Map conditions){

        PageHelper.startPage(pageNum, pageSize);

        List<SmsInfo> smsInfos = smsMapper.getList(conditions);
        return smsInfos;
    }

    /**
     * 发送短信
     * @param phone
     * @param msg
     * @return
     */
    public String sendSms(String phone, String msg){
        Map result = client.sendSms(phone, msg);

        SmsInfo smsInfo = new SmsInfo(smsMapper.generateId());
        smsInfo.setPhone(phone);
        smsInfo.setMsg(msg);
        Integer retCode = MapUtils.getInteger(result,"retCode");
        if(retCode.intValue() == 0){

            smsInfo.setState("1");

        }else{
            smsInfo.setState("2");

        }
        smsInfo.setMsgId(MapUtils.getLong(result, "msgId").toString());
        smsInfo.setRemark(MapUtils.getString(result,"msg"));
        smsInfo.setStateDate(LocalDateTime.now());
        smsMapper.save(smsInfo);

        return result.toString();
    }

    /**
     * 处理短信表中待发送短信记录
     */
    public void dealSms(){

        List<SmsInfo> smsInfoList = smsMapper.getSmsInfo(0);
        log.info("get sms info size = {}", smsInfoList.size());

        if (smsInfoList == null || smsInfoList.size() == 0){
            return;
        }

        for(SmsInfo smsInfo : smsInfoList){

            Map result = client.sendSms(smsInfo.getPhone(), smsInfo.getMsg());

            smsInfo.setMsgId(MapUtils.getLong(result, "msgId").toString());

            Integer retCode = MapUtils.getInteger(result,"retCode");
            if(retCode.intValue() == 0){

                smsInfo.setState("1");

            }else{
                smsInfo.setState("2");

            }
            smsInfo.setRemark(MapUtils.getString(result,"msg"));
            smsInfo.setStateDate(LocalDateTime.now());

        }
        smsMapper.updateBatch(smsInfoList);
    }




}
