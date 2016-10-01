package com.ysu.zyw.tc.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/account")
public class TcAccountController {

    @RequestMapping(value = "/go2signup")
    public ModelAndView go2signup() {
        return new ModelAndView("/WEB-INF/templates/account/signup.ftl");
    }

    @RequestMapping(value = "/go2signin")
    public ModelAndView go2signin() {
        return new ModelAndView("/WEB-INF/templates/account/signin.ftl");
    }

}
