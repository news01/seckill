<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script src="https://cdn.bootcss.com/jquery/2.0.0/jquery.min.js"></script>
<!--  -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- jquery的cookie操作插件 -->
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jquery倒时器插件 -->
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<script type="text/javascript" src="/static/seckill.js"></script>
<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			 seckill.detail.init({
				seckillId : ${seckill.id},
				startTime : ${seckill.startTime.time},
				endTime : ${seckill.endTime.time}
			
		});
		
	});
</script>
</html>
