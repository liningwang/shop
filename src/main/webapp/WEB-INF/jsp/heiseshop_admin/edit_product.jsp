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
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/ueditor.all.min.js"> </script>
<!--�����ֶ��������ԣ�������ie����ʱ��Ϊ��������ʧ�ܵ��±༭������ʧ��-->
<!--������ص������ļ��Ḳ������������Ŀ����ӵ��������ͣ���������������Ŀ�����õ���Ӣ�ģ�������ص����ģ�������������-->
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/lang/zh-cn/zh-cn.js"></script>
<script src="/Test/static/js/jquery.js"></script>
</head>
<body>
<script>
var imageIndex = 0;
var filestr = "";
$(document).ready(function () {
	var selectText = $(".textBox option:first");
	$.each(JSON.parse(selectText.attr("data-obj")),function(index,obj){
    	//alert(obj.secondleveltitle);
    	$(".textBox1").append("<option value="+obj.secondlevelid+">"+obj.secondleveltitle+"</option>");
   	   });
	  $(".textBox").bind("change",function(){
	    //$(".textarea").val($(this).val());
	    $(".textBox1").empty();
	    //��ȡѡ�е�optionԪ��
	    var selectText = $(".textBox").find("option:selected");
	    $.each(JSON.parse(selectText.attr("data-obj")),function(index,obj){
	    	//alert(obj.secondleveltitle);
	    	$(".textBox1").append("<option value="+obj.secondlevelid+">"+obj.secondleveltitle+"</option>");
	   	   });
	  });
	});
function submitForm(jsessionid){
	var formData = new FormData();
	var shopTitle = $("input[name=shopTitle]").val();
	var shopId = $("input[name=shopid]").val();
	var shopPrice = $("input[name=shopPrice]").val()
	var classifyid = $("select[name=classifyid]").val()
	var secondlevel = $("select[name=secondlevel]").val()
	var shopCount = $("input[name=shopCount]").val()
	var shopPicText = $("input[name=shopPicText]").val()
	shopPicText
	var fileu = filestr;
	var editor = getContent();
	var recommend="1"; 
	$("input[name=recommend]").filter(":checked").each(function(){
		recommend = $(this).attr("value");//����ʹ��this.value+',';   ����   $(this).val()+',';
	});
	formData.append("shopTitle",shopTitle);
	formData.append("shopid",shopId);
	formData.append("shopPrice",shopPrice);
	formData.append("classifyid",classifyid);
	formData.append("secondlevel",secondlevel);
	formData.append("shopCount",shopCount);
	formData.append("shopPicText",shopPicText);
	formData.append("fileu",fileu);
	formData.append("editor",editor);
	formData.append("recommend",recommend);
	$.ajax({ 
	url : "http://114.215.46.63/Test/admin/submitProduct;jsessionid="+jsessionid, 
	type : 'POST', 
	data : formData, 
	// ����jQuery��Ҫȥ�����͵�����
	processData : false, 
	// ����jQuery��Ҫȥ����Content-Type����ͷ
	contentType : false,
	beforeSend:function(){
	},
	success : function(responseStr) { 
		alert("success");
	}, 
	error : function(responseStr) { 
		alert(responseStr);
	} 
	});
}
function uploadFile1(){
	var formData = new FormData();
	formData.append("file",$("#chanpinzhutu")[0].files[0]);
	$.ajax({ 
	url : "http://114.215.46.63/Test/person/fileUpload", 
	type : 'POST', 
	data : formData, 
	// ����jQuery��Ҫȥ�����͵�����
	processData : false, 
	// ����jQuery��Ҫȥ����Content-Type����ͷ
	contentType : false,
	beforeSend:function(){
	},
	success : function(responseStr) { 
		if(imageIndex==5) {
			imageIndex = 0;
		}
		imageIndex++;
		filestr = filestr + responseStr +",";
		alert(filestr);
		$("#img"+imageIndex).attr("src",responseStr);
		$("#chanpinzhutu").val(filestr);
	}, 
	error : function(responseStr) { 
		alert(responseStr);
	} 
	});
}
//ʵ�����༭��
//����ʹ�ù�������getEditor���������ñ༭��ʵ���������ĳ���հ������øñ༭����ֱ�ӵ���UE.getEditor('editor')�����õ���ص�ʵ��
var ue = UE.getEditor('editor');


function isFocus(e){
    alert(UE.getEditor('editor').isFocus());
    UE.dom.domUtils.preventDefault(e)
}
function setblur(e){
    UE.getEditor('editor').blur();
    UE.dom.domUtils.preventDefault(e)
}
function insertHtml() {
    var value = prompt('����html����', '');
    UE.getEditor('editor').execCommand('insertHtml', value)
}
function createEditor() {
    enableBtn();
    UE.getEditor('editor');
}
function getAllHtml() {
    alert(UE.getEditor('editor').getAllHtml())
}
function getContent() {
	//�����ֻ�ģ��
	var headmy ="\n<html>\n<head>\n" 
		 +"<meta charset=\"gbk\"/>\n"
		 +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n"
		 + "<style>img{max-width:100% !important;}</style>\n"
		 +"</head>\n"
		 + "<body width=100% style=\"word-wrap:break-word; font-family:Arial\">\n"
    var content = UE.getEditor('editor').getContent();
    var end = ("\n</body>\n</html>\n");
    return headmy + content + end;
}
function getPlainTxt() {
    var arr = [];
    arr.push("ʹ��editor.getPlainTxt()�������Ի�ñ༭���Ĵ���ʽ�Ĵ��ı�����");
    arr.push("����Ϊ��");
    arr.push(UE.getEditor('editor').getPlainTxt());
    alert(arr.join('\n'))
}
function setContent(isAppendTo) {
    var arr = [];
    arr.push("ʹ��editor.setContent('��ӭʹ��ueditor')�����������ñ༭��������");
    UE.getEditor('editor').setContent('��ӭʹ��ueditor', isAppendTo);
    alert(arr.join("\n"));
}
function setDisabled() {
    UE.getEditor('editor').setDisabled('fullscreen');
    disableBtn("enable");
}

function setEnabled() {
    UE.getEditor('editor').setEnabled();
    enableBtn();
}

function getText() {
    //��������ťʱ�༭�����Ѿ�ʧȥ�˽��㣬���ֱ����getText������õ����ݣ�����Ҫ��ѡ������Ȼ��ȡ������
    var range = UE.getEditor('editor').selection.getRange();
    range.select();
    var txt = UE.getEditor('editor').selection.getText();
    alert(txt)
}

function getContentTxt() {
    var arr = [];
    arr.push("ʹ��editor.getContentTxt()�������Ի�ñ༭���Ĵ��ı�����");
    arr.push("�༭���Ĵ��ı�����Ϊ��");
    arr.push(UE.getEditor('editor').getContentTxt());
    alert(arr.join("\n"));
}
function hasContent() {
    var arr = [];
    arr.push("ʹ��editor.hasContents()�����жϱ༭�����Ƿ�������");
    arr.push("�жϽ��Ϊ��");
    arr.push(UE.getEditor('editor').hasContents());
    alert(arr.join("\n"));
}
function setFocus() {
    UE.getEditor('editor').focus();
}
function deleteEditor() {
    disableBtn();
    UE.getEditor('editor').destroy();
}
function disableBtn(str) {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        if (btn.id == str) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        } else {
            btn.setAttribute("disabled", "true");
        }
    }
}
function enableBtn() {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
    }
}

function getLocalData () {
    alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
}

function clearLocalData () {
    UE.getEditor('editor').execCommand( "clearlocaldata" );
    alert("����ղݸ���")
}
</script>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="add"></i><em>�༭/��Ӳ�Ʒ</em></span>
    <span class="modular fr"><a href="product_list.html" class="pt-link-btn">��Ʒ�б�</a></span>
  </div>
  <table class="list-style">
  <tr>
   <td style="text-align:right;width:15%;">��Ʒid</td>
   <td><input type="text" class="textBox length-long" name="shopid" value="${product.shopid}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;width:15%;">��Ʒ���ƣ�</td>
    <td><input type="text" class="textBox length-long" name="shopTitle" value="${product.shoptitle}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;">��Ʒһ�����ࣺ</td>
    <td>
     <select class="textBox" name="classifyid">
     <c:forEach items="${category}" var="cate">
      <option value="${cate.stairId}" data-obj=${cate.secondLevelList}>${cate.stairTitle}</option>
      </c:forEach>
     </select>
    </td>
   </tr>
      <tr>
    <td style="text-align:right;">��Ʒ�������ࣺ</td>
    <td>
     <select class="textBox1" name="secondlevel">
     </select>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">�г��ۣ�</td>
    <td>
     <input type="text" class="textBox length-short" name="shopPrice" value="${product.shopprice}"/>
     <span>Ԫ</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">��Ա�ۣ�</td>
    <td>
     <input type="text" class="textBox length-short"/>
     <span>Ԫ</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">��棺</td>
    <td>
     <input type="text" class="textBox length-short" name="shopCount" value="${product.shopCount}"/>
     <span>��</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">�Ƽ�����</td>
    <td>
     <input type="checkbox" name="recommend" id="jingpin" value="1"/>
     <label for="jingpin" >�Ƽ�</label>
     <input type="checkbox" name="recommend" id="xinpin" value="0"/>
     <label for="xinpin">��ͨ��Ʒ</label>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">��Ʒ��ͼ��</td>
    <td>
     <input type="file" id="chanpinzhutu" class="hide" name="fileu"
     onchange="uploadFile1()"/>
     <label for="chanpinzhutu" class="labelBtn2">�����ϴ�(���ѡ��N��)</label>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <td>
     <img id="img1" src="#" width="80" height="80"/>
     <img id="img2" src="#" width="80" height="80"/>
     <img id="img3" src="#" width="80" height="80"/>
     <img id="img4" src="#" width="80" height="80"/>
     <img id="img5" src="#" width="80" height="80"/>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">ʹ�����в�Ʒ���飺</td>
    <td><input type="text" class="textBox length-long" name="shopPicText" value="${product.shopgraphicdetails}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;">��Ʒ���飺</td>
    <td>
    	<script id="editor" type="text/plain"
				style="width:100%;height:30%;"></script>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <%String s = (String)session.getId(); //��ȡsession ID��  %>
    <td><input type="button" value="��������Ʒ" class="tdBtn" onclick="submitForm('<%=s%>')"/></td>
   </tr>
  </table>
  </form>
 </div>
</body>
</html>