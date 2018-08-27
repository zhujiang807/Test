<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="jquery.min.js"></script>
</head>
<script type="text/javascript">
</script>
<body style="display: none;">
	<div id="datas"></div>
	<div id="teamsCommentData"
		style="width:1020px;text-align:center;display: none;margin-bottom: 50px; display: block;"></div>
	<div
		style="margin-top:30px;width:1020px;float:content;font-size: 20px;">
		<a href="#" onclick="showTeamsComment()">更多</a>
	</div>
	<table>
		<tr>
			<td colspan="1"></td>
		</tr>
	</table>

	首页 | 个人中心：个人信息 私信 | 战队中心：我的战队 加入的战队 | 活动中心：苹果店 最新公告 或者 | 个人中心 | 战队中心 |
	活动中心 首页 | 个人信息 私信 | 我的战队 加入的战队 | 苹果店 最新公告 | | 战队查看 战队比赛 | 或者 | | | 首页 |
	个人信息 私信 | 我的战队 加入的战队 | 苹果店 最新公告 | | 战队查看 战队比赛 |
</body>
</html>
