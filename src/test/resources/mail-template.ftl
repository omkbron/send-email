<html>
<body style="color:#f5f5f5; font-family: Helvetica, sans-serif; font-size: 16px; background-color: #333333">
	<div style="margin-right: auto;
				margin-left: auto;
				max-width: 970px;">
		<div style="margin-bottom: 30px;">
			<h3 style="font-weight: bold;">Hi ${to},</h3>
		</div>
		<div style="background-color: #fff;
		border: 1px solid #dadada;
		padding: 5px;
		<#--max-width: 500px;-->
		margin-bottom: 40px;
		color: #333;
		-webkit-border-radius: 6px;
		-moz-border-radius: 6px;
		border-radius: 6px;
		-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .075);
		-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .075);
		box-shadow: 0 1px 2px rgba(0, 0, 0, .075);"> 
			<p>${body}</p>
			<div>
		 		<ul>
					<#list lista as elem>
						<li style="list-style: none;">${elem_index + 1}. ${elem}</li>
					</#list>
				</ul>
		 	</div>
	 	</div>
	 	<div>
	 		<span>Regards,<br/>
			${from}.</span>
	 	</div>
	 </div>
</body>
</html>