<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 插件   在前 -->
	<script type="text/javascript" src="${c.contextPath}/static/jquery/jquery-1.11.0.js"></script>
	<!--layer-->
	<script type="text/javascript" src="${c.contextPath}/static/layer/layer.min.js"></script>
	<!-- zTree -->
	<link rel="stylesheet" href="${c.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">	
	<script type="text/javascript" src="${c.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
	<!--jquery.form.js  插件  ajax提交表单的插件  -->
	<script type="text/javascript" src="${c.contextPath}/static/form-master/jquery.form.js"></script>
	
	<!-- 自己在后 -->

	<script type="text/javascript" src="${c.contextPath}/static/util/user/userMana.js"></script>
	
	<script type="text/javascript" src="${c.contextPath}/static/util/user/queryOrgTree.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/user/exportUser.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/user/importUser.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/user/sendMail.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/user/userOper.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/area/getArea.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/util/user/AddOrgTreeLayer.js"></script>
	
</head>



<body style="background-color: #DCDCDC;">
	
	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
					
					    <td width="30%">
					           所属组织：<input type="text"  readonly="readonly" id="qry_orgName">
					           <input type="hidden" id="qry_orgId">
					           <button  onclick="queryOrgTree();">选择</button>
					           </td>
						<td width="30%">员工姓名：<input type="text" id="qry_userCHName" ></td>
						<td width="30%">电话号码：
							<input type="text" id="qry_telphonenumber" ></td>
					
						
					</tr>
					<tr  height="30px">
					   <td width="30%">省份:
					   	<select style="width: 50%" id="qry_provinceId">
					   		<option value="">-请选择-</option>
					   	</select>
					   </td>
					   <td width="30%">
					   地市：<select style="width: 50%" id="qry_cityId">
					   	<option value="">-请选择-</option>
					   </select>
					   </td>
					    <td width="30%">
					   县区：<select style="width: 50%" id="qry_countryId">
					   	<option value="">-请选择-</option>
					   </select>
					   </td>
						
					</tr>
						<tr  height="30px">
					    <td width="30%" style="text-align:left;" colspan="2">
					    <button  onclick="userOper.addUser();">新增</button>
					     <button  onclick="importUser();">导入</button>
					      <button  onclick="exportUser();">导出</button>
					    </td>
					    
					    <td width="30%" style="text-align:center;" >
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 用户列表部分 -->
		<tr>
			<td ><div id="userlistDataContainner" ></div></td>
		</tr>
		<!-- 页码部分 -->
		<tr height="50px">
			<td><div id="userPageNumberContainner" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>
  <script type="text/javascript" >
    var path = '${c.contextPath}';
  </script>
</body>
</html>