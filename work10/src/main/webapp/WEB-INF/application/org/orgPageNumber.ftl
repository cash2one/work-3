<!-- 评论分页 -->
<#if total??>
	<#if (total > 0)>
		<div >
			<div >
			<!--  current=1时，startIndex = -1  后台会报错
			  不显示：上一页
			 -->
				<#if current != 1>
					<!-- 后台current从1开始计算 1234,前台从0开始 0123  
					  current=1时，startIndex = -1
					 -->
					<a  href="javascript:loadOrgReturnNumber(${current}-2)">上一页</a>
				</#if>
				<#if startPage != 1>
					<a  href="javascript:loadOrgReturnNumber(0)">1</a>
				</#if>
				<#if page == 1><!-- 总的页数只有一页 -->
					<a  href="javascript:loadOrgReturnNumber(0)" >1</a>
				<#else><!-- page不等于1页 -->
					<#if startPage != 1>
						<span>...</span>
					</#if>
					<#list startPage..endPage as index>   <!-- for(int i = startPage;i<=endPage;i++){}  -->
						<#if current == index>
							<a style="color:blue;font-size:16px;"  href="javascript:void(0)" >${index}</a><!--   -->
						<#else>
							<a  href="javascript:loadOrgReturnNumber(${index}-1)">${index}</a>
						</#if>
					</#list>
					<#if endPage != page><!--   page = 20 页 endPage =6    -->
						<span>...</span><!-- 1 2 3 4 5 6…… 20 -->
						<a  href="javascript:loadOrgReturnNumber(${page}-1)">${page}</a>
					</#if>
				</#if>
				<#if page != current>
					<a  href="javascript:loadOrgReturnNumber(${current})">下一页</a>
				</#if>
			</div>
		</div>	
	<#else>
		<div >
			<div >
				<a  href="javascript:void(0)">上一页</a>
				<a  href="javascript:void(0)" >1</a>
				<a  href="javascript:void(0)">下一页</a>
			</div>
		</div>
	</#if>
<#else>
	<div >
		<div >
			<a  href="javascript:void(0)">上一页</a>
			<a  href="javascript:void(0)" >1</a>
			<a  href="javascript:void(0)">下一页</a>
		</div>
	</div>
</#if>
