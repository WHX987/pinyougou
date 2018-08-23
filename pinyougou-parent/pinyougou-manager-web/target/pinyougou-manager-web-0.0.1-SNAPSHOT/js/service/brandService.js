// 品牌服务层
app.service('brandService',function($http){
	// 查询所有
	this.findAll = function(){
		return $http.get('../brand/findAll.do');
	}
	
	// 分页查询
	this.findPage = function(pageNum,pageSize){
		return $http.get("../brand/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize);
	}
	
	// 添加
	this.add = function(entity){
		return $http.post('../brand/add.do',entity);
	}
	
	// 保存
	this.update = function(entity){
		return $http.post('../brand/update.do',entity);
	}
	
	// 根据id查
	this.findOne = function(id){
		return $http.get('../brand/findOne.do?id='+id);
	}
	
	// 删除
	this.dele = function(ids){
		return $http.get('../brand/delete.do?ids='+ids);
	}
	
	// 条件分页查询
	this.search = function(page,rows,searchEntity){
		return $http.post("../brand/search.do?pageNum="+page+"&pageSize="+rows,searchEntity);
	}
	
	// 品牌查询
	this.selectOptionList = function(){
		return $http.get('../brand/selectOptionList.do');
	}
})