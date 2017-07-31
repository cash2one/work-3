<!-- 该页面能得到total -->
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr>
		<th>角色名称</th>
		<th>角色状态</th>
		<th>创建时间</th>
		<th>操作</th>
	</tr>


	

<#if rolesList??>
	<#list rolesList as role>
	<tr>
		<td><#if role.roleName??>${role.roleName}</#if> </td>
		<td><#if role.state??>
				<#if role.state==1>
					可用
				<#else>
					不可用
				</#if>
		</#if></td>
		<td><#if role.createdDate??>${role.createdDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
		<td><a href="javascript:roleOper.queryOrUp('<#if role.roleId??>${role.roleId}</#if>','up');" style="cursor: pointer;">编辑</a>
			<a href="javascript:roleOper.queryOrUp('<#if role.roleId??>${role.roleId}</#if>','query');" style="cursor: pointer;">查看</a> <a
			href="javascript:roleOper.deleteRole('<#if role.roleId??>${role.roleId}</#if>')" style="cursor: pointer;">删除</a></td>
	</tr>
	</#list>
<#else>
	<tr>
		<td colspan="4" style="text-align: center;">暂无列表信息</td>
	</tr>
</#if>

	

</table>


<script type="text/javascript">
		totalCount = "<#if total??>${total}</#if>"; 
</script>
