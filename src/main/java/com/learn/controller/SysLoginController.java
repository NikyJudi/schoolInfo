package com.learn.controller;

import com.learn.entity.SysUserEntity;
import com.learn.service.SysUserService;
import com.learn.utils.R;
import com.learn.utils.ShiroUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 登录相关
 *
 *
 *
 * 10#1:15:31
 */
@Controller
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;
    //验证码，这里没有实现
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录，采用shiro登录框架，从login.html中的url(sys/login)转到这里，
     * shiro框架会自动调用shiro下的UserRealm。java进行登录名和密码以及验证码的验证
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public R login(String username, String password, String captcha) throws IOException {
// 
// 
        try {
        	//获取当前登录用户的信息，使用Apache Shiro安全框架
            Subject subject = ShiroUtils.getSubject();
            //sha256加密，toHex()字符串转成16进制
            password = new Sha256Hash(password).toHex();
            //把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //提交认证信息，之后自动调用UserRealm与数据库中的信息进行比较
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        } catch (LockedAccountException e) {
            return R.error(e.getMessage());
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        //返回数据，放在hashmap中，默认放的是"code", 0
        return R.ok();
    }


    /**
     * 注册框架
     */
    @ResponseBody
    @RequestMapping(value = "/sys/reg", method = RequestMethod.POST)
    public R reg(String username, String password, String captcha) throws IOException {
 

        //sha256加密
        SysUserEntity user = new SysUserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(1);
        user.setType("1");

        List<Long> roles = new ArrayList<>();
        roles.add(1L);

        user.setRoleIdList(roles);

        this.sysUserService.save(user);

        return R.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

}
