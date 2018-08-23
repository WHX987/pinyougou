app.controller('specificationController',function($scope,$controller,specificationService){
	
	// 继承，并注入$$controller
	$controller('baseController',{$scope:$scope})
	
	//保存品牌数据
	$scope.save=function(){
		
		specificationService.add($scope.entity).success(
			function(response){
				if(response.success){
					$scope.search();//成功，重新查询数据
				}else{
					alert(response.message);
				}
			}
		);
	}
	
	// 条件查询
	$scope.searchEntity = {}  // 初始化searchEntity
	
	// 查询结果分页展示
	$scope.search = function(page,rows){
		specificationService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows; //当前页数据
				$scope.paginationConf.totalItems = response.total; // 总记录数
				}		
			)
			
	}
	
	//定义组合实体对象，封装规格和规格选项数据
	$scope.entity={specification:{},specificationOptions:[]};
	
	//添加行
	$scope.addRow=function(){
		$scope.entity.specificationOptions.push({});
	}
	
	//删除行
	$scope.deleRow=function(index){
		$scope.entity.specificationOptions.splice(index,1);
	}
		
});