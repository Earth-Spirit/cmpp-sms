package com.iwhalecloud.nmyd.cmppsms.mapper;


import com.iwhalecloud.nmyd.cmppsms.entity.SmsInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface SmsMapper {

    List<String> getTables();


    int detele(@Param(value = "ids") List<Integer> ids);

    int updateState(@Param(value = "ids") List<Integer> ids, @Param(value = "state") int state);
    @Select("select seq_sms_info_id.nextval from dual")
    Integer generateId();

    @Select("select * from sms_info where state = #{state}")
    List<SmsInfo> getSmsInfo(@Param(value = "state") int state);

    List<SmsInfo> getList(Map<String, Object> conditions);

    int getSmsCount(@Param(value = "state") int state);


    boolean save(SmsInfo smsInfo);

    int updateBatch(@Param(value = "smsInfos") List<SmsInfo> smsInfos);

    List<HashMap<String, Object>> getstatistics(@Param(value = "state") int state);


}