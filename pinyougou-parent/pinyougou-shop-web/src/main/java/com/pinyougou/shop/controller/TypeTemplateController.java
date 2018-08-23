package com.pinyougou.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("typeTemplate")
public class TypeTemplateController {
	
	
	@Reference
	private TypeTemplateService typeTemplateService;
	
	@RequestMapping("findAll.do")
	public List<TbTypeTemplate> fiandAll(){
		return typeTemplateService.findAll();
	}
	
	
	@RequestMapping("findPage.do")
	public PageResult findPage(Integer pageNum,Integer pageSize) {
		System.out.println(typeTemplateService.findPage(pageNum,pageSize));
		return typeTemplateService.findPage(pageNum,pageSize);
	}
	
	@RequestMapping("add")
	public Result add(@RequestBody TbTypeTemplate typeTemplate) {
		try {
			typeTemplateService.add(typeTemplate);
			return new Result(true, "新增成功");
		}catch (Exception e) {
			return new Result(false, "新增失败");
		}
	}
	
	@RequestMapping("findOne")
	public TbTypeTemplate findOne(Long id) {
		return typeTemplateService.findOne(id);
	}
	
	@RequestMapping("update")
	public Result update(@RequestBody TbTypeTemplate typeTemplate) {
		try {
			typeTemplateService.update(typeTemplate);
			return new Result(true, "修改成功");
		}catch (Exception e) {
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping("delete")
	public Result update(Long [] ids) {
		try {
			typeTemplateService.delete(ids);
			return new Result(true, "删除成功");
		}catch (Exception e) {
			return new Result(false, "删除失败");
		}
	}
	
	// 条件分页查询
	@RequestMapping("search")
	public PageResult search(@RequestBody TbTypeTemplate typeTemplate,Integer pageNum, Integer pageSize) {
		return typeTemplateService.findPage(typeTemplate,pageNum, pageSize);
	}
	
	@RequestMapping("findSpecList")
	public List<Map> findSpecList(Long id){
		return typeTemplateService.findSpecList(id);
	}
}
