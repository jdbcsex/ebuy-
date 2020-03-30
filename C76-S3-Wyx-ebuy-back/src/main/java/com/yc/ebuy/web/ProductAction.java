package com.yc.ebuy.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yc.ebuy.bean.EasybuyProduct;
import com.yc.ebuy.bean.EasybuyProductExample;
import com.yc.ebuy.dao.EasybuyProductMapper;

@RestController
public class ProductAction {

	@Resource
	private EasybuyProductMapper pm;

	/**
	 * 查询所有的商品分类信息
	 *	// 127.0.0.1:8002/getPc
	 */
	@GetMapping("getHotP")
	public List<EasybuyProduct> getHotP(){
		EasybuyProductExample pce = new EasybuyProductExample();
		pce.setOrderByClause("stock desc");
		PageHelper.startPage(1, 10);
		List<EasybuyProduct> list = pm.selectByExample(pce);
		return list;
	}
	//商品详情页面
	@GetMapping("product/{id}")
	public EasybuyProduct product(@PathVariable("id") int id){
		return pm.selectByPrimaryKey(id);
	}

}