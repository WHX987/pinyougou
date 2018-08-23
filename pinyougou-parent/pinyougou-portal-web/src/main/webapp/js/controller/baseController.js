app.controller("baseController",function($scope){
	
	//分页配置
	/*$scope.paginationConf = {
		currentPage:1,  				//当前页
		totalItems:10,					//总记录数
		itemsPerPage:10,				//每页记录数
		perPageOptions:[10,20,30,40,50], //分页选项，下拉选择一页多少条记录
		onChange:function(){			//页面变更后触发的方法
			$scope.search();		//启动就会调用分页组件
		}
	};*/
	
	 //重新加载列表 数据
    $scope.reloadList=function(){
    	//切换页码  
    	$scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);	   	
    }
    
	//分页控件配置 
	$scope.paginationConf = {
         currentPage: 1,
         totalItems: 10,
         itemsPerPage: 10,
         perPageOptions: [10, 20, 30, 40, 50],
         onChange: function(){
        	 $scope.reloadList();//重新加载
     	 }
	}; 
	
	//记录品牌id的数组
	$scope.selectIds=[];
	
	$scope.updateSelection=function($event,id){
		//判断复选框勾选状态
		if($event.target.checked){
			//勾选
			$scope.selectIds.push(id);
		}else{
			//取消勾选  splice移除元素   参数一：移除元素的索引值 参数二：移除几个元素
			var index = $scope.selectIds.indexOf(id);
			
			$scope.selectIds.splice(index,1);
		}
		
	}
	
	//从json格式字符串中基于key获取对应value值并完成拼接操作
	$scope.getValueByKey=function(jsonString,key){
		//将json字符串转json对象
		var jsonArray = JSON.parse(jsonString);
		var value="";
		for(var i=0;i<jsonArray.length;i++){
			if(i>0){
				value+=","+jsonArray[i][key];
			}else{
				value+=jsonArray[i][key];
			}
			
		}
		return value;
	}
	
	
	
});