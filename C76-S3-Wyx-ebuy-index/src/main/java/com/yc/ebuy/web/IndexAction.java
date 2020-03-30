package com.yc.ebuy.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yc.ebuy.bean.EasybuyUser;
import com.yc.ebuy.biz.BizException;
import com.yc.ebuy.biz.UserBiz;

@SessionAttributes("loginedUser")
@RestController
public class IndexAction {

	@Resource
	IEbuyBackAction eba;
	
	@Resource
	UserBiz ubiz;

	/*@ModelAttribute
	public void init() {
		
	}*/
	
	@GetMapping({ "/", "index", "index.html" })
	public ModelAndView index(ModelAndView mav) {
		// 通过远程服务调用方式获取分类信息
		mav.addObject("pclist", eba.getPc());
		mav.addObject("nlist", eba.getNews());
		mav.addObject("hplist", eba.getHotP());
		mav.setViewName("index");
		return mav;
	}
	
	@GetMapping("tologin")
	public ModelAndView tologin(ModelAndView mav) {
		mav.setViewName("Login");
		return mav;
	}

	@PostMapping("login")
	public ModelAndView login(EasybuyUser user, ModelAndView mav) {
		try {
			EasybuyUser dbuser = ubiz.login(user);
			// 将用户对象添加到会话
			mav.addObject("loginedUser", dbuser);
			//mav.setViewName("index");
			return index(mav);
		} catch (BizException e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
			mav.setViewName("Login");
		}
		return mav;
	}
	
	@GetMapping("product/{id}")
	public ModelAndView product(@PathVariable("id") int id, ModelAndView mav) {
		//要展示的商品
		mav.addObject("product", eba.product());
		//商品分类
		mav.addObject("pclist", eba.getPc());
		mav.setViewName("product");
		return mav;
	}

}