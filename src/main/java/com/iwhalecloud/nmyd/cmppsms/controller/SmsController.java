package com.iwhalecloud.nmyd.cmppsms.controller;


import com.github.pagehelper.PageInfo;
import com.iwhalecloud.nmyd.cmppsms.entity.SmsInfo;
import com.iwhalecloud.nmyd.cmppsms.mapper.SmsMapper;
import com.iwhalecloud.nmyd.cmppsms.service.SmsService;
import com.iwhalecloud.nmyd.cmppsms.vo.BootstrapTableVo;
import oracle.jdbc.proxy.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/sms")
public class SmsController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SmsService smsService;

    @Autowired
    SmsMapper smsMapper;

    @RequestMapping("send")
    @ResponseBody
    public String sendSms(@RequestParam String phones, @RequestParam String msg){

        log.info("phone is {}, msg is {}", phones, msg);

        return smsService.sendSms(phones, msg);
    }

    @RequestMapping("")
    public String sendSms(){
        return "/sms/send";
    }


    @RequestMapping("/list")
    public String list(){
        return "/sms/list";
    }

    @RequestMapping("getFailSmsCount")
    @ResponseBody
    public int getSmsCount(){

        return smsService.getSmsCount(2);
    }

    @RequestMapping("getstatistics")
    @ResponseBody
    public List getstatistics(){
        return smsService.getstatistics();
    }



    @RequestMapping("/page")
    @ResponseBody
    public BootstrapTableVo<SmsInfo> page(@RequestBody Map<String, Object>params){

        Map conditions = new HashMap();

        conditions =(Map) params.get("conditions");

        int pageNumber = (int)params.get("pageNumber");
        int pageSize = (int)params.get("pageSize");

        List<SmsInfo> smsInfos = smsService.page(pageNumber, pageSize, conditions);
        PageInfo<SmsInfo> pageInfo = new PageInfo<>(smsInfos);

        BootstrapTableVo<SmsInfo> tableData = new BootstrapTableVo<SmsInfo>();
        tableData.setTotal(pageInfo.getTotal());
        tableData.setRows(pageInfo.getList());
        return tableData;
    }


    @GetMapping("/test")
    public String test(){

        return "/test";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam("ids[]") List<Integer> ids){

        return smsService.delete(ids);
    }

    @PostMapping("/updateState")
    @ResponseBody
    public int updateState(@RequestParam("ids[]") Integer[] ids){


        return  smsService.updateState(Arrays.asList(ids),5);
    }
}
