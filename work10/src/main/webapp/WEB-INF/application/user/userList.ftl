
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr>
		<th>员工姓名</th>
		<th>电话</th>
		<th>邮箱</th>
		<th>性别</th>
		<th>生日</th>
		<th>操作</th>
	</tr>
<#if userList??>
	<#list userList as user>
	<tr>
		<td><#if user.userChName??>${user.userChName}</#if> </td>
		<td><#if user.mobilePhone??>${user.mobilePhone}</#if> </td>
		<td><#if user.email??>${user.email}</#if> </td>
		<td><#if user.userSex??>
				<#if user.userSex==1>
				 男
				</#if>
				<#if user.userSex==2>
				 女
				</#if>
				<#if user.userSex==3>
				 保密
				</#if>
		<#else>
			未知
		 </#if>
		
		 </td>
		<td><#if user.userBirthday??>${user.userBirthday?string('yyyy-MM-dd')}</#if> </td>
		<td>
			<a href="javascript:sendMail('<#if user.email??>${user.email}</#if>');" style="cursor: pointer;">发送邮件</a>
			<a href="javascript:userOper.updateUser('<#if user.userId??>${user.userId}</#if>');" style="cursor: pointer;">编辑</a>
			<a href="javascript:void(0);" style="cursor: pointer;">查看</a> <a
			href="javascript:void(0);" style="cursor: pointer;">删除</a></td>
	</tr>
	</#list>
<#else>	
	<tr>
		<td colspan="6" style="text-align: center;">暂无列表信息</td>
	</tr>
</#if>

</table>


<script type="text/javascript">
		totalCount = "<#if total??>${total}</#if>";
</script>
