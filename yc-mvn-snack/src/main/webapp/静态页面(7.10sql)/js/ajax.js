//封装好的Ajax方法
 function post(url,param,callback){
	 var xhr
	 try{
		xhr=new XMLHttpRequest();
	}catch(e){
		xhr=new ActiveXObject("xxxxxxx");
	}
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4&&xhr.status==200){
			callback(xhr.responseText);
		}
	}	
	//开启连接 第三个参数 true 异步|false 同步
	xhr.open("POST",url);
	if(! (param instanceof FormData)){
		//post 提交数据的编码方式
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	}
	//发送请求 发送的请求参数
	xhr.send(param);
 }