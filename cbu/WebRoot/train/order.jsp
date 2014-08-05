<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/tld/c-rt.tld" prefix="c" %>
<%
	Object ps=session.getAttribute("pssgers");
	if(ps==null){		
		Object tl=org.springframework.config.WebConfig.getBean("tr_login");
		((test.travel.action.Login)tl).getPssgers(request,response);
	}
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>订票</title>
<script type='text/javascript' src='${pageContext.request.contextPath }/train/js/jquery-1.10.2.js'></script>
<style type="text/css">
#t_tips{
	display:inline-block;
	height:28px;
	clear:both;
}
#content {
	clear:both;
	text-align: center;
	margin:0px;
	WIDTH:750px;
	display:inline-block;
}
#header{	
	font-size:12px;
}
.d_tb {
	margin-top: 2%;
	margin-left: 3%;
	border: 1px solid #A8BED4;
	border-collapse: collapse;
	width: 90%;
}
.d_tb td,.d_tb th {
	border: 1px solid #A8BED4;
	font: 12px 宋体;
	margin: 0px;
}

.d_tb th {
	background-color: #DAE6F6;
	text-align: center;
	vertical-align: middle;
	height: 30px;
	font: bold 14px 宋体;
	padding: 5px;
}

.td_c {
	text-align: center;
}

.d_tb td {
	height: 28px;
}

.title {
	text-align:left;
	background-image: url(../css/default/titleBg.gif);
	background-position: 0px 0px;
	background-repeat: repeat-x;
	height: 39px;
	color: #DAE6F6;
	font-weight: bold;
	padding-top: 10px;
	padding-left: 10px;
	font-size: 14px;
	font-wight: bold;
}
</style>
<script type="text/javascript">
	var s=null;
	var fo=(function(){
		var that={};
		that.put=function(k,v){
			that[k]=v;
		}
		that.get=function(k){
			return that[k];
		}
		that.setTN=function(a){
			that.put("c",(a||[]));
		}
		that.cc=function(){//检查车次
			var c=that.get("c");
			if(c.length==0){
				$("tr[station_train_code]").addClass("showT").show();
				return;
			}
			for(var i=0;i<c.length;i++){
				var n=c[i];
				$("tr[station_train_code="+n+"]").addClass("showT").show();
			}
		}
		that.setTN([]);
		return that;
	})();

	
	function draw(tr,s,e){
		$("#t_tips").html("");
		$("#content").html("");//清除面板内的所有内容
		var tb=$("<table/>").attr("id","data_tb").addClass("d_tb").appendTo("#content");
		tb.append(dTr());//添加表头
		for(var i=s;i<e && i<tr.length;i++){
			var t=dTr(tr[i],0,i);
			tb.append(t);
		}
		fo.cc();
		$("#t_tips").html($(".showT").length+"趟车次");
		$(".oticket").click(o_ticket);
	}
	
	var TA={
			length:0,
			push:function(k,v){
				var o={
					title:k,
					num:v
				};
				TA[TA.length]=o;
				TA.length++;
				return TA;
			}
	}
	
	TA.push("商务座","swz_num").push("一等座","zy_num").push("二等座","ze_num").push("特等座","tz_num")
	.push("软卧","rw_num").push("硬卧","yw_num").push("软座","rz_num").push("软座","yz_num").push("无座","wz_num");
	//画行
	function dTr(o,xh,index){		
		if(!o){//画表头
			return $("<tr><th width='6%'>车次</th><th width='15%'>出发</th><th width='10%'>达到</th><th width='57%'>坐席情况</th><th width='12%'>操作</th></tr>");
		}
		var rid=getEId("_ticket");
		var to=o['queryLeftNewDTO'];
		var ht="";
		for(var i=0;i<TA.length;i++){
			var oi=TA[i];
			var num=to[oi['num']];
			if(!isNaN(num)||num==="有"){
				ht=ht+oi['title']+":有票"+(!isNaN(num)?"("+num+")":"")+"<br/>";
			}
		}
		var nt=ht=="";
		ht=ht==""?"无票":ht;
		
		var tr=$("<tr/>").attr(to).attr("id",rid).attr({secretStr:o.secretStr,
			title:"始发站："+to.start_station_name+",途径："+to.from_station_name+"，至："+to.to_station_name
		}).append($("<td/>").text(to.station_train_code)).append($("<td/>").text(to.start_time)).append($("<td/>").text(to.arrive_time)).
		append($("<td align='left' style='padding-left:15px;'/>").html(ht)).append($("<td/>").html(nt?"无票":"<a href='#' class='oticket' ticket='"+rid+"' >预定</a>"));
		return tr;
	}
	
	window['$trains_q']=function(){
		var s=$("#s_station").val();
		var e=$("#e_station").val();
		var t=$("#t_time").val();
		var rs=$("#rs").is(":checked");
		//_jc_save_fromStation=%u676D%u5DDE%2CHZH; _jc_save_toStation=%u957F%u6C99%2CCSQ; _jc_save_fromDate=2014-03-09; _jc_save_toDate=2014-03-07; _jc_save_wfdc_flag=dc
		//var c=document.coockie;
		document.cookie="_jc_save_fromStation=%u676D%u5DDE%2C"+s+";path=/cb/train";
		document.cookie="_jc_save_toStation=%u957F%u6C99%2"+e+";path=/cb/train";
		document.cookie="_jc_save_fromDate="+t+";path=/cb/train";
		document.cookie="_jc_save_toDate="+t+";path=/cb/train";
		document.cookie="_jc_save_wfdc_flag=dc;path=/cb/train";
		$.ajax({
			url:"${pageContext.request.contextPath }/train/query.tto?s="+s+"&e="+e+"&t="+t+"&rs="+(rs?1:0),
			dataType:"json",
			success:function(msg){
				if(msg && msg.status){
					draw(msg.data||[],0,(msg.data||[]).length);
				}
			}
		});
	}
</script>
</head>
<body>
<div class="main">
<table>
	<tr>
		<td>选择乘车人:</td>
		<td id="pssger">
			<c:forEach var="ps" items="${pssgers }">
			<input type="checkbox" value="${ps.id }" class="ps_ck" code="${ps.code }" email="${ps.email }"/> ${ps.name } &nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
		</td>
		<td>
			<input type="button" value="提交" id="sendpss"/>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>始发站:</td><td><input type="text" id="s_station" value="HZH" size="10"/></td>
		<td>终点站：</td><td><input type="text" id="e_station" value="CSQ" size="10"/></td>
		<td>日期：</td><td><input type="date" id="t_time" value="2014-03-10"/></td>
		<td>后台运行<input type="checkbox" id="rs" name="rs"/></td>
		<td>
			<input type="button" value="查询" id="query_but"/>
		</td>
	</tr>
</table>
<div id="t_tips"></div>
<div id="content">
</div>
</div>
<script type="text/javascript">
$(document).ready(function($){
	$("#query_but").click(function(){
			window.$trains_q();
			if($("#rs").is(":checked")){
				$(this).val("选择了后台运行");
				$(this).attr("disabled","disabled");
			}			
	});
	$("#sendpss").click(function(){
		
	});
});
function getEId(fix){
	return (fix+Math.random()).replace(".","");
}
function getAttrs(obj){
	var rs=[];
	obj.each(function(){
		var that=this;
		var attr=this.attributes;
		if(attr){
			var o={log:function(){
				var ms='{';
				for(var e in this){
					if(this.hasOwnProperty(e) && typeof this[e]!="function"){
						ms+=(e+":"+this[e]+",");
					}
				}
				if(ms!="{"){
					ms=ms.substring(0,ms.length()-1);
				}
				ms+="}";
				console.log(ms);
			}};
			for(var i=0;i<attr.length;i++){
				var n=attr[i];
				o[n.nodeName]=n.nodeValue;
			}
			rs.push(o);
		}
	});
	return rs;
}
function o_ticket(){
	var that=$(this);
	var ticket=that.attr("ticket");
	var tk=$("#"+ticket);
	var os=getAttrs(tk);
	os=os?os[0]:{};
	
	
}
</script>
</body>
</html>