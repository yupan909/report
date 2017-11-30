/**
* 公共js
*/
var Public = {
		
		/**
		 * 提示框 1：成功 2：失败 3：警告
		 */
		alert:function(type,msg,fn){
			var icon = 1; //成功
			if(2==type){ //失败
				icon = 2;
			}else if(3==type){ //警告
				icon = 7;
			}
			
			layer.open({
				type:0,
				content:'<div style="font-size:20px;">'+msg+'</div>',
				icon: icon,
				btn: ['确定'],
			  	yes: fn,
			  	cancel: fn
			}); 
		},
		
		/**
		 * 获取url地址栏指定的参数
		 * @param name
		 */
		urlParam:function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null){
				return unescape(r[2]);
			}else{
				 return null; //返回参数值
			} 
		},
		
		/**
		 * 回显数据
		 */
		editData:function(url){
			var id =$("#id").val();
			if(id == "" || id == null || typeof(id) == "undefined"){
				id = Public.urlParam("id");
			}
			$.ajax({
				cache: true,
				type:"post",
				url:url,
				data:{id:id},
				dataType:"json",
				async:false,
				success:function(data){
					if(""==data || null==data || typeof(data)=="undefined"){
						Public.alert(2,"回显数据失败！");
						return false;
					}
					for(var keys in data){
						var value = data[keys];
	                    var $key = $("#"+keys);
	                    if($key.is("select")){
							$key.find("option[value='"+value+"']").attr("selected",true);						
						}else if($key.attr("type")=="radio" || $key.attr("type")=="checkbox"){
							$("input[name="+keys+"][value="+value+"]").attr("checked",true);
						}else{
							if($key.is("span") || $key.is("font")){
								$key.html(value);
							}else{
								$key.val(value);
							}
						}
					}
				}
			});
		},
		
		/**
		 * 回显数据
		 */
		editDataForSub:function(url){
			var id =$("#id").val();
			if(id == "" || id == null || typeof(id) == "undefined"){
				id = Public.urlParam("id");
			}
			$.ajax({
				cache: true,
				type:"post",
				url:url,
				data:{id:id},
				dataType:"json",
				async:false,
				success:function(data){
					if(""==data || null==data || typeof(data)=="undefined"){
						Public.alert(2,"回显数据失败！");
						return false;
					}
					
					//回显主表
					var sub = data.subProject;
					for(var keys in sub){
						var value = sub[keys];
						var $key = $("#"+keys);
						if($key.is("select")){
							$key.find("option[value='"+value+"']").attr("selected",true);						
						}else if($key.attr("type")=="radio" || $key.attr("type")=="checkbox"){
							$("input[name="+keys+"][value='"+value+"']").attr("checked",true);
						}else if($("[name="+keys+"]").attr("type")=="checkbox"){
							var vals = value.split("|");
							for(var i=0; i<vals.length; i++){
								$("input[name="+keys+"][value='"+vals[i]+"']").attr("checked",true);
							}
						}else{
							if($key.is("span") || $key.is("font")){
								if(keys=="xb"){
									if(value=="1"){
										value = "男";
									}else if(value=="2"){
										value = "女";
									}
								}
								$key.html(value);
							}else{
								$key.val(value);
							}
						}
					}
					
					//回显子表
					var partiesInfo = data.partiesInfo;
					Public.editDataChild(partiesInfo, $("#party"));
					
					var expectAchievements = data.expectAchievements;
					Public.editDataChild(expectAchievements, $("#ach-expect"));
					
					var realAchievements = data.realAchievements;
					Public.editDataChild(realAchievements, $("#ach-real"));
					
				}
			});
		},
		
		//回显子表
		editDataChild:function(data, dataDIV){
			for(var i=0; i<data.length; i++){
				var oneData = data[i];
				var nowLine; //表示当前行
				if(i==0){ //第一行
					nowLine = $(dataDIV).children("div:first"); //第一行
				}else{
					//新建一行
					var firstDiv = "<div class='row'>" + $(dataDIV).children("div:first").html() + "</div>";
					$(dataDIV).append(firstDiv);
					//初始化新增行
					var newDiv = $(dataDIV).children("div:last");
					$("input[type=text]",newDiv).val("");
					$("span.glyphicon",newDiv).removeClass("glyphicon-plus").addClass("glyphicon-minus").attr("onclick","Public.delLine(this);");
				
					nowLine = newDiv; //赋值新行
				}
				
				//回显数据
				for(var key in oneData){
					var $key = $("[name="+key+"]",nowLine);
					var value = oneData[key];
					if($key.is("select")){
						$key.find("option").removeAttr("selected");
						$key.find("option[value='"+value+"']").attr("selected",true);						
					}else{
						if($key.is("span") || $key.is("font")){
							$key.html(value);
						}else{
							$key.val(value);
						}
					}
				 }
			 }	
		},
		
		//新增行
		addLine:function(obj){
			var div = $(obj).parent().parent().parent("div");
			//第一行
			var firstDiv = "<div class='row'>" + $(div).children("div:first").html() + "</div>";
			$(div).append(firstDiv);
			//初始化新增行
			var newDiv = $(div).children("div:last");
			$("input[type=text]",newDiv).val("");
			$("span.glyphicon",newDiv).removeClass("glyphicon-plus").addClass("glyphicon-minus").attr("onclick","Public.delLine(this);");
		},
		
		//删除行
		delLine:function(obj){
			layer.confirm('您确定要删除该行吗?', {icon: 3, title:'提示'}, function(index){
				$(obj).parent().parent("div.row").remove();
				layer.close(index);
			});
		},
		
		/**
		 * 保存表单
		 * 参数1：表单ID
		 * 参数2：提交路径
		 * type：提交或保存
		**/
		saveData:function(frm,action,type){
			var bool = false;
			try{
				$.ajax({
					cache: true,
					type:'post',
					url:action,
					data:$("#"+frm).serialize(),
					dataType:"json",
					async:false,
					success:function(data){
						if(""!=data && null!=data && typeof(data)!="undefined"){
							if("1"==data.msg){
								$("#id").val(data.id); //回显id
								if("submit"!=type){
									Public.alert(1,"保存成功"); 
								}
								bool = true;
							}else{
								if("submit"!=type){
									Public.alert(2,"保存失败");   
								}
								bool = false;
							}
						}else{
							if("submit"!=type){
								Public.alert(2,"保存时出现异常！");  
							}
							bool = false;
						}
					},
					error:function(data){
						Public.alert(2,"请求出现异常！");   
						bool = false;
					}		
				});
			}catch(e){
			}
			return bool;
		}
}