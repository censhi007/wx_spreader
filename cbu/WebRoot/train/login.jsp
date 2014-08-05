<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/c-rt.tld" prefix="c" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>登陆</title>
<style type="text/css">
*{
	margin:0px auto;
	padding:0px;
}
.cet{
	text-align:center;
}

.ltb{
	margin-top: 2%;
	border: 1px solid #A8BED4;
	border-collapse: collapse;
	width: 280px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/train/js/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(document).ready(function($){
		$("#vcode").blur(function(){
			var that=$(this);
			var vc=that.val();
			if(!vc || vc.length!=4)return false;
			$.ajax({
				url:"${pageContext.request.contextPath }/train/ckvc.tto?vcode="+vc,
				dataType:"json",
				success:function(msg){
					if(msg&&msg.data!='N'){
						$("#randtip").text("√");
						$("#s").click();
					}else{
						$("#randtip").text("×");
					}
				}
			})
		});
		$("#s").click(function(){
			var date=new Date();
			date.setTime(date.getTime+30*24*3600*1000);
			document.cookie="t_luname="+$("#uname").val()+";path=/cb/train;domain=127.0.0.1;expire="+date.toGMTString();
			if($("#randtip").text()!="√"){
				alert("验证码不正确!");
				return false;
			}
			if(!$("#uname").val()||!$("#psd").val()){
				alert("必须输入用户名和密码！");
				return false;
			}
			$("#sform").submit();
		});
		var cs=document.cookie;
		var css=cs.split(";");
		for(var i=0;i<css.length;i++){
			var c=css[i];
			var sc=c.split("=");
			if(sc[0]==="t_luname"){
				$("#uname").val(sc[1]);
				break;
			}
		}
	});
</script>
</head>
<body>
<div class="cet">
<form action="${pageContext.request.contextPath }/train/login.tto" id="sform" method="post">
	<table class="ltb">
		<tr height="32px">
			<td width="85px" align="right"> 姓名：</td><td colspan=2 width="180px" align="left"><input type="text" name="uname" id="uname"/></td>
		</tr>
		<tr height="32px">
			<td align="right"> 密码：</td><td  colspan=2 align="left"><input type="password" name="psd" id="psd"/></td>
		</tr>
		
		<tr height="32">
			<td align="right"> 验证码：</td><td width="60px" align="left"><input type="text" name="vcode" id="vcode" size="6" /></td>
			<td width="120px">
				<a href="javascript:vi.src='${pageContext.request.contextPath }/train/vc.tto?sid='+Math.random();return false;" title='点击刷新'>	<img alt="验证码" id="vi" name="vi" src="${pageContext.request.contextPath }/train/vc.tto" width="78" height="26"/></a>
				<label id="randtip"></label>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				<input type="button" name="s" id="s" value="登陆" width="75px" style="width:75px;margin-right:22px"/>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>