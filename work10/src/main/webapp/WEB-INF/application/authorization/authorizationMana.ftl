<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 项目名称 -->
<script type="text/javascript">
	var path = '${c.contextPath}';//pageContext.request.contextPath
</script>
<!-- jquery插件 -->
<script type="text/javascript" src="${c.contextPath}/static/jquery/jquery-1.11.0.js"></script>
<!-- layer 插件 -->
<script type="text/javascript" src="${c.contextPath}/static/layer/layer.min.js"></script>
<!-- 树形结构 zTree -->
<link rel="stylesheet" href="${c.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">	
<script type="text/javascript" src="${c.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
	
<script type="text/javascript">
	var path = '${c.contextPath}';
</script>
<!-- 自己的js -->
<script type="text/javascript" src="${c.contextPath}/static/util/authorization/authorizationMana.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/authorization/authorizationOrg.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/authorization/authorizationUser.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/authorization/authorizationMenu.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/authorization/menuTreeLayer.js"></script>

</head>



<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
					<td width="30%">角色：
						<select id="sys_role"   width="100"  style="width:140px;">
						</select></td>
					   	<td width="30%">授权类型：
						<select id="type"   width="100"  style="width:140px;">
						 	<option value="">-请选择-</option>
						 	<option value="1">组织</option>
						 	<option value="2">个人</option>
						 	<option value="3">菜单</option>
						</select></td>
						
					</tr>
					<tr  height="30px">
					   <td width="30%"></td>
					   <td width="30%"></td>
						<td width="30%" style="text-align:center;">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="3" style="text-align:left;">
					    <button  onclick="menuTreeLayer()">授权菜单</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 数据展示部分 -->
		<tr>
			<td ><div id="authorizationListData" ></div></td>
		</tr>
		<!-- 页码 -->
		<tr height="50px">
			<td><div id="authorizationPageNumberData" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>

</body>
</html>