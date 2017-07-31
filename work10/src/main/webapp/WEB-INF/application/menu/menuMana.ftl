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
	


<!-- 自己写的js -->
<script type="text/javascript" src="${c.contextPath}/static/util/menu/menuMana.js"></script>
<!-- 查询菜单目录的js -->
<script type="text/javascript" src="${c.contextPath}/static/util/menu/queryMenuTree.js"></script>
<!-- 菜单操作的js -->
<script type="text/javascript" src="${c.contextPath}/static/util/menu/menuOper.js"></script>
<!-- 点击新增时，再点击父目录时  要加载的树形结构 -->
<script type="text/javascript" src="${c.contextPath}/static/util/menu/addMenuTreeLayer.js"></script>


</head>



<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
					
					    <td width="30%">
					          所属目录 ：
					          <input type="text"  id="qry_parentMenuName" readonly="readonly">
					           <input type="hidden" id="qry_parentMenuId">
					           <button  onclick="queryMenuTree()">选择</button>
					           </td>
						<td width="30%">菜单名称：<input id="qry_menuName" type="text" ></td>
						<td width="30%">是否可用：
						<select id="qry_state"   width="100"  style="width:140px;">
						 	<option value="">-请选择-</option>
						 	<option value="1">可用</option>
						 	<option value="2">不可用</option>
						</select></td>
						
					</tr>
					<tr  height="30px">
					   <td width="30%"></td>
					   <td width="30%"></td>
						<td width="30%" style="text-align:center;">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="3" style="text-align:left;">
					    <button  onclick="menuOper.addMenu();">新增</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 数据展示部分 -->
		<tr>
			<td ><div id="menuListData" ></div></td>
		</tr>
		<tr height="50px">
			<td><div id="menuPageNumberData" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>

</body>
</html>