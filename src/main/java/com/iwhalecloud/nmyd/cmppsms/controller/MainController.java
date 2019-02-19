package com.iwhalecloud.nmyd.cmppsms.controller;


import com.iwhalecloud.nmyd.cmppsms.cmpp.CMPPClient;
import com.iwhalecloud.nmyd.cmppsms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @Autowired
    SmsService smsService;

    @Autowired
    CMPPClient client;


    @RequestMapping
    public String main(ModelMap modelMap){

        modelMap.addAttribute("failCount", smsService.getFailSmsCount());
        modelMap.addAttribute("pendingCount", smsService.getPendingSmsCount());
        return "/index";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){

        modelMap.addAttribute("failCount", smsService.getFailSmsCount());
        modelMap.addAttribute("pendingCount", smsService.getPendingSmsCount());
        return "/index";
    }


    @RequestMapping("/client")
    public String ClientInfo(Model model){

        model.addAttribute("config", client.getConfig());
        model.addAttribute("status",client.isRunning());
        return "/sms/client";
    }



}
