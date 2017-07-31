<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<!-- jquery -->
<script type="text/javascript" src="${c.contextPath}/static/jquery/jquery-1.11.0.js"></script>
<!-- layer -->
<script type="text/javascript" src="${c.contextPath}/static/layer/layer.min.js"></script>

<script type="text/javascript" src="${c.contextPath}/static/util/role/roleMana.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/role/roleOper.js"></script>


<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
						<td width="50%">角色名称：<input type="text" id="qry_roleName" ></td>
						<td width="50%">是否可以：
						<select id="qry_roleState"   width="300px"  style="width:140px;">
						 	<option value="">-请选择-</option>
						 	<option value="1">可用</option>
						 	<option value="2">不可用</option>
						</select></td>
						
					</tr>
					<tr  height="30px">
					   <td width="40%"><div id="" height="40px" style="text-align:center;"></div></td>
					  
					   <td align="right">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="2" style="text-align:left;">
					    <button  onclick="roleOper.addRole();">新增</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 角色列表 -->
		<tr>
			<td ><div id="roleListDataContainer" ></div></td>
		</tr>
		<!-- 页码 -->
		<tr height="50px" align="center">
			<td ><div id="rolePageNumberContainer" ></div></td>
		</tr>
	</table>

</body>
<script type="text/javascript">
   var  path = '${c.contextPath}';
</script>
</html>