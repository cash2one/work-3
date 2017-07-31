<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<!-- layer -->
<script type="text/javascript" src="${c.contextPath}/static/jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/layer/layer.min.js"></script>
<!-- zTree -->
<link rel="stylesheet" href="${c.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">	
<script type="text/javascript" src="${c.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
	


<script type="text/javascript" src="${c.contextPath}/static/util/org/orgMana.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/org/queryOrgTree.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/org/orgOper.js"></script>
<script type="text/javascript" src="${c.contextPath}/static/util/org/AddOrgTreeLayer.js"></script>


<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
					
					    <td width="30%">
					           组织：<input type="text"  readonly="readonly" id="qry_orgName"><!-- 选中的节点的名称 -->
					           <input type="hidden" id="qry_orgId"><!-- 当前节点的id -->
					           <button  onclick="queryOrgTree();">选择</button>
					     </td>
						
						<td width="30%">是否可用：
						<select id="state"  width="100"  style="width:140px;">
						     <option value="">请选择</option>
						      <option value="1">可用</option>
						       <option value="2">不可用</option>
						</select></td>
						
					</tr>
					<tr  height="30px">
					   <td width="30%"></td>
					  
						<td width="30%" style="text-align:center;">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="3" style="text-align:left;">
					    <button  onclick="orgOper.addOrg();">新增</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 条件查询部分结束 -->
		<!-- 展示数据部分开始
		    请求服务端把数据查询出来，展示到orgList.ftl页面
		    通过ajax  返回值类型：html/text  -orgList.ftl页面
		    
		    $("#loadOrgListData").html();
		 -->
		<tr>
			 <td><div id="loadOrgListData"></div></td>
		</tr>
		<!-- 展示数据部分结束 -->
		
		<!-- 分页查询的页码部分 -->
		<tr height="50px">
			<td><div id="loadOrgNumber" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>
	<script type="text/javascript">
		var path = '${c.contextPath}';
	</script>
</body>
</html>