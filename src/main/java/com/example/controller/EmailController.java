package com.example.controller;

import com.example.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @Author：zhaozg
 * @Date：2024/4/19 13:36
 * @Desc：
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/sendTemplateEmail")
    public void sendTemplateEmail(String code) {
        // 处理邮件模板
        Context context = new Context();
        context.setVariable("username", "张三");
        String template = templateEngine.process("email", context);
        String ccMail = "18336816041@163.com";
        try {
            MailUtil.sendMail("359353493@qq.com", ccMail, template);
            log.info("发送成功！");
        } catch (Exception e) {
            log.info("发送失败！");
            e.printStackTrace();
        }
    }
}
