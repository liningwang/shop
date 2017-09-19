<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ʒ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>
</head>
<body>
<script>
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
function getAllCheck(jsessionid){
	var value="";
	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//����ʹ��this.value+',';   ����   $(this).val()+',';
	});
	 $.post("http://114.215.46.63/Test/admin/deleteMoreProduct;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
		 alert("���ݣ�" + data + "\n״̬��" + status + " value " + data.progressValue );
			$(".check").filter(":checked").parents("tr").remove();
		 });
}  
</script>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i></i><em>��Ʒ�б�</em></span>
    <span class="modular fr"><a href="addProduct?shopid=0" class="pt-link-btn">+�����Ʒ</a></span>
  </div>
  <div class="operate">
   <form>
    <select class="inline-select">
     <option>A����</option>
     <option>B����</option>
    </select>
    <input type="text" class="textBox length-long" placeholder="�����Ʒ����..."/>
    <input type="button" value="��ѯ" class="tdBtn"/>
   </form>
  </div>
  <table class="list-style Interlaced">
   <tr>
    <th>ID���</th>
    <th>��Ʒ</th>
    <th>����</th>
    <th>�г���</th>
    <th>��Ա��</th>
    <th>���</th>
    <th>�Ƽ���Ʒ</th>
    <th>����</th>
   </tr>
   <c:forEach items="${product}" var="pro">
    <tr>
    <td>
     <span>
     <input type="checkbox" class="middle children-checkbox check" value="${pro.shopid}"/>
     <i>${pro.shopid}</i>
     </span>
    </td>
    <td class="center pic-area"><img src="#" class="thumbnail"/></td>
    <td class="td-name">
      <span class="ellipsis td-name block">${pro.shoptitle}</span>
    </td>
    <td class="center">
     <span>
      <i>��</i>
      <em>${pro.shopprice}</em>
     </span>
    </td>
    <td class="center">
     <span>
      <i>��</i>
      <em>${pro.shopprice}</em>
     </span>
    </td>
    <td class="center">
     <span>
      <em>${pro.shopCount}</em>
      <i>��</i>
     </span>
    </td>
    <c:if test="${pro.recommend == 1}">
		 <td class="center"><img src="/Test/static/images/yes.gif"/></td>
	</c:if>
    <c:if test="${pro.recommend == 0}">
    	<td class="center"><img src="/Test/static/images/no.gif"/></td>
    </c:if>
    <td class="center">
    <a href="addProduct?shopid=${pro.shopid}" title="�༭"><img src="/Test/static/images/icon_edit.gif"/></a>
    <a title="ɾ��" href="deleteProduct?shopId=${pro.shopid}"><img src="/Test/static/images/icon_drop.gif"/></a>
    </td>
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
					<input type="button" value="����ɾ��" class="btnStyle" onclick="getAllCheck('<%=s%>')"/>
			</div>
			<!-- turn page -->
			<div class="turnPage center fr">
				<c:choose>
					<c:when test="${page>=1}">
						<a
							href="productList?page=${page-1}&rows=10">��һҳ</a>
					</c:when>
					<c:otherwise>
						<a>��ҳ</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page<=pageNum}">
						<a
							href="productList?page=${page+1}&rows=10">��һҳ</a>
					</c:when>
					<c:otherwise>
						<a>���һҳ</a>
					</c:otherwise>
				</c:choose>
				<a href="productList?page=0&rows=10">��һҳ</a> 
				<a href="productList?page=${pageNum}&rows=10">���һҳ</a>
			</div>
		</div>
 </div>
</body>
</html>