<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/demo.css">
	<script type="text/javascript" src="${c.contextPath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/easyui/jquery.easyui.min.js"></script>

	<!-- ztree -->
	<link rel="stylesheet" href="${c.contextPath}/static/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${c.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${c.contextPath}/static/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
	<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit.js"></script>-->
	<SCRIPT type="text/javascript">
		
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			<#if menuList??>
				<#list  menuList  as menu>
					{ id:1, pId:0,isParent:false, name:<#if menu.menuName??>'${menu.menuName}'</#if>, url:<#if menu.menuPath??>'${menu.menuPath}'</#if>, target:"right"},
				</#list>
	</#if>
			 
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	
	</SCRIPT>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;background:#B3DFDA;"">
	<!-- 树形结构的菜单栏 -->

	
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
	
	
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<!-- 锚链接  -->
		<iframe name="right" width="100%" height="100%"></iframe>
	</div>
</body>
</html>