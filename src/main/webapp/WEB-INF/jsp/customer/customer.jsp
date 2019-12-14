<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>无标题文档</title>
        <link href="${APP_PATH}/css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${APP_PATH}/js/jquery.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {


            });

            function deleteCustomer(id) {
                if (confirm("确定要删除该用户吗?")) {
                    var param={};
                    param.id = id;
                    /*location.href = "${APP_PATH}/customer/delete?id=" + id;*/
                    $.ajax({
                        type : 'post',
                        url :  '${APP_PATH}/customer/delete',
                        data : param,
                        dataType : 'json',
                        success : function (data) {
                            if(data == true){
                                window.location.href='${APP_PATH}/customer/query';
                            }else {
                                window.alert("无法删除客户");
                            }
                        }
                    });
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
            <shiro:hasAnyRoles name="业务员,操作员">
                <li class="click">
                    <a href="${APP_PATH}/customer/goAddOrUpdate">
                        <span><img src="${APP_PATH}/images/t01.png"/></span>添加
                    </a>
                </li>
            </shiro:hasAnyRoles>
            <li class="click"><span><img src="${APP_PATH}/images/t02.png"/></span>修改</li>
            <li><span><img src="${APP_PATH}/images/t03.png"/></span>删除</li>
            <li><span><img src="${APP_PATH}/images/t04.png"/></span>统计</li>
        </ul>


        <ul class="toolbar1">
            <li><span><img src="${APP_PATH}/images/t05.png"/></span>设置</li>
        </ul>

    </div>


    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>客户编号<i class="sort"><img src="${APP_PATH}/images/px.gif"/></i></th>
            <th>客户姓名</th>
            <th>地址</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>电话</th>
            <th>备注</th>
            <th>业务员</th>
            <th>常用区间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list }" var="c">
            <tr>
                <td><input name="" type="checkbox" value=""/></td>
                <td>${c.customer.customerId }</td>
                <td>${c.customer.customerName }</td>
                <td>${c.customer.address }</td>
                <td>
                    <c:choose>
                        <c:when test="${c.customer.cSex eq '0'}">
                            男
                        </c:when>
                        <c:otherwise>
                            女
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${c.customer.email }</td>
                <td>${c.customer.mobilePhone }</td>
                <td>${c.customer.remark }</td>
                <td>${c.realName }</td>
                <td>${c.commonInterval }</td>
                <td>

                    <a href="${APP_PATH}/customer/goAddOrUpdate?id=${c.customer.customerId }" class="tablelink">修改</a>
                    <a href="javascript:void(0)" onclick="deleteCustomer(${c.customer.customerId })" class="tablelink">
                        删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>


</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<div style="display:none">
    <script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script>
</div>
</body>
</html>
