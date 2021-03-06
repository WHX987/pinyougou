// 品牌服务层
app.service('typeTemplateService',function($http){
	// 查询所有
	this.findAll = function(){
		return $http.get('../typeTemplate/findAll.do');
	}
	
	// 分页查询
	this.findPage = function(pageNum,pageSize){
		return $http.get("../typeTemplate/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize);
	}
	
	// 添加
	this.add = function(entity){
		return $http.post('../typeTemplate/add.do',entity);
	}
	
	// 修改
	this.update = function(entity){
		return $http.post('../typeTemplate/update.do',entity);
	}
	
	// 根据id查
	this.findOne = function(id){
		return $http.get('../typeTemplate/findOne.do?id='+id);
	}
	
	// 删除
	this.dele = function(ids){
		return $http.get('../typeTemplate/delete.do?ids='+ids);
	}
	
	// 条件分页查询
	this.search = function(pageNum,pageSize,searchEntity){
		return $http.post("../typeTemplate/search.do?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity);
	}
	
	
})