package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.grouppojo.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("specification")
public class SpecificationController {
	
	@Reference
	private SpecificationService specificationService;
	
	/**
	 * 分页查询 
	 */
	@RequestMapping("search")
	public PageResult search(@RequestBody TbSpecification specification,Integer pageNum,Integer pageSize) {
		return specificationService.findPage(specification,pageNum,pageSize);
	}
	
	// 添加
	@RequestMapping("add")
	public Result add(@RequestBody Specification specification) {
		try {
			specificationService.add(specification);
			return new Result(true, "新增成功");
		}catch (Exception e) {
			return new Result(false, "新增失败");
		}
	}
	
	@RequestMapping("selectOptionList")
	public List<Map> selectOptionList(){
		return specificationService.selectOptionList();
	}
}
