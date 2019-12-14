<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${APP_PATH}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${APP_PATH}/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

function goDelete(baseId ){
	if(window.confirm("确定要删除数据吗?")){
		location.href="/basic/delete?id="+baseId;
	}
}
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click">
        	<a href="${APP_PATH}/basic/goAddOrUpdate">
	        	<span>
	        		<img src="${APP_PATH}/images/t01.png" />
	        	</span>
	        		添加
        	</a>
        </li>
        <li class="click">
        <span><img src="${APP_PATH}/images/t02.png" /></span>
        	修改</li>
        <li><span><img src="${APP_PATH}/images/t03.png" /></span>删除</li>
        <li><span><img src="${APP_PATH}/images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="${APP_PATH}/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>数据编号<i class="sort"><img src="${APP_PATH}/images/px.gif" /></i></th>
        <th>数据名称</th>
        <th>父节点</th>
        <th>描述</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${list }" var="bd">
        	<c:if test="${empty bd.parentId }">
        		<!-- 显示所有的大类 -->
		        <tr>
			        <td><input name="" type="checkbox" value="" /></td>
			        <td>${bd.baseId }</td>
			        <td style="color:red;font-weight: bold;">${bd.baseName }</td>
			        <td>${bd.parentId }</td>
			        <td>${bd.baseDesc }</td>
			        <td><a href="${APP_PATH}/basic/goAddOrUpdate?baseId=${bd.baseId }" class="tablelink">修改</a>
			        	<a href="javascript:void(0)" onclick="goDelete(${bd.baseId })" class="tablelink"> 删除</a>
			        </td>
		        </tr> 
		        <!-- 将所有的数据再循环一遍 取出parentId = bd.parentId的数据 -->
		        <c:forEach items="${list }" var="small">
		        	<c:if test="${small.parentId eq bd.baseId }">
		        		<tr>
					        <td><input name="" type="checkbox" value="" /></td>
					        <td style="padding-left: 20px;">${small.baseId }</td>
					        <td style="padding-left: 20px;">${small.baseName }</td>
					        <td>${small.parentId }</td>
					        <td>${small.baseDesc }</td>
					        <td><a href="${APP_PATH}/basic/goAddOrUpdate?baseId=${small.baseId }" class="tablelink">修改</a>
					        	<a href="javascript:void(0)" onclick="goDelete(${small.baseId })" class="tablelink"> 删除</a>
					        </td>
				        </tr> 
		        	</c:if>
		        </c:forEach>
	        </c:if>
      	 </c:forEach>
        </tbody>
    </table>
    
   
    
    
    <%--<div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${APP_PATH}/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>--%>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
