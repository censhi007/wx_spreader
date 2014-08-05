<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var ttype='${ttype}';
	if("login"==ttype){
		(function(o){
			if(o.data.loginCheck == "Y"){
				window.location.href="${pageContext.request.contextPath }/train/order.jsp";
			}
		})(${script});
	}
</script>
</head>
<body>

</body>
</html>