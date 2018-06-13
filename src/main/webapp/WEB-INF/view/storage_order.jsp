<%--
  Created by IntelliJ IDEA.
  User: Za
  Date: 2018/5/25
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="commons/global.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <%@ include file="commons/admincss.jsp" %>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <%@ include file="commons/adminmenu.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">入库单</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="/storage_order/add"><i class="fa fa-user-plus fa-3x"></i> </a>
                        <span class="text-center text-info">${msg}</span>
                    </div>

                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>物料名</th>
                                <th>数量</th>
                                <th>单位</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="one" items="${storage_order}" varStatus="status">
                                <tr class="odd gradeX">
                                    <td>${status.index+1}</td>
                                    <td>${one.getProductName()}</td>
                                    <td>${one.getAmount()}</td>
                                    <td>${one.getUnit()}</td>
                                    <td>${one.getStatus()}</td>
                                    <td>${one.getCreated()}</td>
                                    <td>
                                        <c:if test="${one.getStatus()=='1'}">
                                            <a href="/storage_order/change/${one.getId()}">修改数量</a>
                                        </c:if>
                                        <c:if test="${one.getStatus()=='2'}">修改数量</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${one.getStatus()=='1'}">
                                        <a href="/storage_order/delete/${one.getId()}">删除</a>
                                        </c:if>
                                        <c:if test="${one.getStatus()=='2'}">删除</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${one.getStatus()=='1'}">
                                            <a href="/storage_order/check/${one.getId()}">审核</a>
                                        </c:if>
                                        <c:if test="${one.getStatus()=='2'}">
                                            <a href="/storage_order/cancle/${one.getId()}">取消审核</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<%@ include file="commons/adminjs.jsp" %>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

</body>
</html>
