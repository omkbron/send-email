<html>
<head>
	<style type="text/css">
		body {
			color: #f5f5f5;
			font-family: Helvetica, sans-serif;
			font-size: 16px;
			background-color: #333333;
		}
		
		.container {
			margin-right: auto;
			margin-left: auto;
			max-width: 970px;
		}
		
		.header {
			margin-bottom: 30px;
		}
		
		h3 {
			font-weight: bold;
		}
		
		.white-panel {
			background-color: #fff;
			border: 1px solid #dadada;
			padding: 5px; 
			margin-bottom: 40px;
			color: #333;
			-webkit-border-radius: 6px;
			-moz-border-radius: 6px;
			border-radius: 6px;
			-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .075);
			-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .075);
			box-shadow: 0 1px 2px rgba(0, 0, 0, .075);
		}
		
		li {
			list-style: none;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h3>Hello ${to},</h3>
		</div>
		<div class="white-panel"> 
			<p>${body}</p>
			<div>
		 		<ul>
					<#list lista as elem>
						<li>${elem_index + 1}. ${elem}</li>
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