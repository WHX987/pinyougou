package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("brand")
public class BrandController {
	
	@Reference
	private BrandService brandService;
	
	@RequestMapping("findAll.do")
	public List<TbBrand> fiandAll(){
		return brandService.findAll();
	}
	
	
	@RequestMapping("findPage.do")
	public PageResult findPage(Integer pageNum,Integer pageSize) {
		System.out.println(brandService.findPage(pageNum,pageSize));
		return brandService.findPage(pageNum,pageSize);
	}
	
	@RequestMapping("add")
	public Result add(@RequestBody TbBrand brand) {
		try {
			brandService.add(brand);
			return new Result(true, "新增成功");
		}catch (Exception e) {
			return new Result(false, "新增失败");
		}
	}
	
	@RequestMapping("findOne")
	public TbBrand findOne(Long id) {
		return brandService.findOne(id);
	}
	
	@RequestMapping("update")
	public Result update(@RequestBody TbBrand brand) {
		try {
			brandService.update(brand);
			return new Result(true, "修改成功");
		}catch (Exception e) {
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping("delete")
	public Result update(Long [] ids) {
		try {
			brandService.delete(ids);
			return new Result(true, "删除成功");
		}catch (Exception e) {
			return new Result(false, "删除失败");
		}
	}
	
	// 条件分页查询
	@RequestMapping("search")
	public PageResult search(@RequestBody TbBrand brand,Integer pageNum, Integer pageSize) {
		return brandService.findPage(brand,pageNum, pageSize);
	}
	
	// 品牌查询
	@RequestMapping("selectOptionList")
	public List<Map> selectOptionList(){
		return brandService.selectOptionList();
	}
	
}
