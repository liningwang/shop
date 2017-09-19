<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�����б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet"
	type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>
<script type="text/javascript">  
       var page = 0;
        function go(url) {  
        	page = page + 1;
        	 $.get(url+"?page="+page+"&rows=20",function(data,status){
        		 $(".wrap").html(data);
        	    }); 
        }  
        function pre(url) {  
        	page = page - 1;
        	alert(page);
      		if(page < 0) {
      			return;
      		}
        	 $.get(url+"?page="+page+"&rows=20",function(data,status){
        		 $(".wrap").html(data);
        	    }); 
        } 
        function deleteOrder(orderNum){
        	if(confirm("ȷ�� ɾ����")) {
           		 $.get("http://114.215.46.63/Test/admin/deleteOrder?orderNum="+orderNum,function(data,status){
            		  alert("���ݣ�" + data + "\n״̬��" + status);
           		 });
        	}
        };
        function setAllCheck(){
        	
        	$(".check").each(function(){
        		//.attr('checked):   //���汾1.6+����:��checked����undefined�� ;1.5-����:true��false
        		//���ﲻ����
        		//$("#cb1").attr("checked",true);����ֻ�е�һ��������
        		//alert($(this).is(":checked"));
        		if($(this).is(":checked")){
        			$(this).prop("checked", false);
        		} else {
        			$(this).prop("checked",true);
        		}
        	});
        	
        }
        function sendPro(jsessionid){
        	var value="";
        	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//����ʹ��this.value+',';   ����   $(this).val()+',';
        	});
        	 $.post("http://114.215.46.63/Test/admin/changeMoreOrder;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
        		 alert("���ݣ�" + data + "\n״̬��" + status + " value " + data.progressValue );
       			$(".check").filter(":checked").parents("tr").children(".wstatus").children().text("���ջ�");
      		 });
        }
        function getAllCheck(jsessionid){
        	var value="";
        	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//����ʹ��this.value+',';   ����   $(this).val()+',';
        	});
        	 $.post("http://114.215.46.63/Test/admin/deleteMoreOrder;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
        		 alert("���ݣ�" + data + "\n״̬��" + status + " value " + data.progressValue );
       			$(".check").filter(":checked").parents("tr").remove();
      		 });
        }
    </script>
</head>
<body class="mybody">
	<div class="wrap">
		<div class="page-title">
			<span class="modular fl"><i class="order"></i><em>�����б�</em></span>
		</div>
		<div class="operate">
			<form action="/Test/admin/searchOrder" method="post">
				<img src="/Test/static/images/icon_search.gif" /> 
					<input type="text" name="content"
					class="textBox length-long" placeholder="���붩����Ż��ռ�������..." /> 
					<select name="orderType" class="inline-select">
						<option value="1">δ����</option>
						<option value="2">�Ѹ���</option>
					</select> 
				<input type="submit" value="��ѯ" class="tdBtn" />
			</form>
		</div>
		<table class="list-style Interlaced">
			<tr>
				<th>�������</th>
				<th>�µ�ʱ��</th>
				<th>�ռ���</th>
				<th>�������</th>
				<th>����״̬</th>
				<th>����</th>
			</tr>
			<c:forEach items="${products}" var="pro">
				<tr>
					<td><input type="checkbox" class="check" value="${pro.ordernum}"/> 
					<a href="order_detail?orderNum=${pro.ordernum}">${pro.ordernum}</a>
					</td>
					<td class="center"><span class="block">DeatGhost</span> <span
						class="block">${pro.orderTime}</span></td>
					<td width="450"><span class="block">${pro.username}[${pro.usertelephone}]</span>
						<address>${pro.userprovince}${pro.usercity}${pro.userdistrict}${pro.userdetailedaddress}</address>
					</td>
					<td class="center"><span><i>��</i><b>${pro.sumofSalesPrice}</b></span>
					</td>
					<td class="center wstatus"><c:if test="${pro.orderType == 1}">
							<span>δ����</span>
						</c:if> <c:if test="${pro.orderType == 2}">
							<span>������</span>
						</c:if> <c:if test="${pro.orderType == 3}">
							<span>���ջ�</span>
						</c:if> <c:if test="${pro.orderType == 4}">
							<span>������</span>
						</c:if> <c:if test="${pro.orderType == 5}">
							<span>�����</span>
						</c:if></td>
					<td class="center"><a href="order_detail?orderNum=${pro.ordernum}"
						class="inline-block" title="�鿴����"><img
							src="/Test/static/images/icon_view.gif" /></a> <a
						class="inline-block" title="ɾ������"><img
							src="/Test/static/images/icon_trash.gif" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- BatchOperation -->
		<div style="overflow: hidden;">
			<!-- Operation -->
			<%String s = (String)session.getId(); //��ȡsession ID��  %>
			<div class="BatchOperation fl">
				<input type="checkbox" id="del" onclick="setAllCheck()"/> <label for="del"
					class="btnStyle middle">ȫѡ</label> 
					 <input type="button" value="���"
					class="btnStyle" onclick="sendPro('<%=s%>')"/> 
					<input type="button" value="ɾ������"
					class="btnStyle" onclick="getAllCheck('<%=s%>')"/>
			</div>
			<!-- turn page -->
			<div class="turnPage center fr">
				<c:choose>
					<c:when test="${page>=1}">
						<a
							href="orders?page=${page-1}&rows=10">��һҳ</a>
					</c:when>
					<c:otherwise>
						<a>��ҳ</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page<=pageNum}">
						<a
							href="orders?page=${page+1}&rows=10">��һҳ</a>
					</c:when>
					<c:otherwise>
						<a>���һҳ</a>
					</c:otherwise>
				</c:choose>
				<a href="orders?page=0&rows=10">��һҳ</a> 
				<a href="orders?page=${pageNum}&rows=10">���һҳ</a>
			</div>
		</div>
	</div>
</body>
</html>