<html>
<head>
	<#include "style.css">
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