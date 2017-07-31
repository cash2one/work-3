
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr>
		<th>菜单名称</th>
		<th>是否可用</th>
		<th>创建时间</th>
		<th>操作</th>
	</tr>


	

<#if menusList??>
	<#list menusList as menu>
	<tr>
		<td><#if menu.menuName??>${menu.menuName}</#if> </td>
		<td>
		
			<#if  menu.isPublish??>
				<#if  menu.isPublish==1>
				可用
				</#if>
			<#else>
				不可用
			</#if>
			</td>
		<td><#if menu.createDate??>${menu.createDate?string("yyyy-MM-dd HH:mm:ss")} </#if></td>
		<td><a href="javascript:menuOper.updateOrQuery('<#if menu.menuId??>${menu.menuId}</#if>','up')" style="cursor: pointer;">编辑</a>
			<a href="javascript:menuOper.updateOrQuery('<#if menu.menuId??>${menu.menuId}</#if>','query');" style="cursor: pointer;">查看</a> <a
			href="javascript:menuOper.deleteMenu('<#if menu.menuId??>${menu.menuId}</#if>');" style="cursor: pointer;">删除</a></td>
	</tr>
	</#list>
<#else>
	<tr>
		<td colspan="4" style="text-align: center;">暂无列表信息</td>
	</tr>
</#if>
</table>


<script type="text/javascript">
		totalCount = "<#if total??>${total}</#if>"; //取出总的记录数
</script>
