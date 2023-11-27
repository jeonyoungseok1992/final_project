<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error-404-page</title>
<style>
    /* reset */
    *,
    *:before,
    *:after{box-sizing: border-box;}
    html,body,div,span,iframe,object,embed,h1,h2,h3,h4,h5,h6,dl,dt,dd,ul,ol,li,a,abbr,address,b,bdo,blockquote,cite,code,del,dfn,p,pre,em,i,ins,kbd,q,s,samp,strong,sub,sup,u,var,img,map,area,form,fieldset,legend,input,textarea,select,button,label,keygen,table,caption,thead,tbody,tfoot,tr,th,td,article,aside,audio,bdi,canvas,datalist,details,dialog,figcaption,figure,footer,header,main,mark,menu,menuitem,meter,nav,output,progress,section,rp,rt,ruby,summary,time,video,wbr,hr{margin: 0; padding: 0;}
    html,body{width: 100%; height: 100%;}
    html{overflow-y: scroll;}
    body{font: 1rem/1 'Roboto', 'Noto Sans KR', '.SFNSText-Regular','San Francisco','Segoe UI','Helvetica Neue','Lucida Grande',sans-serif;font-weight: 400;color: #333;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;-webkit-font-smoothing: antialiased;}
    article, aside, details, figcaption, figure, footer, header, hgroup, main, menu, nav, section, summary{display:block;}
    h1,h2,h3,h4,h5,pre,input,button,select,textarea{font: inherit;}
    input,button,select,textarea{color: inherit;-webkit-appearance: none; border-radius: 0;}
    a{color: inherit; text-decoration: none;}
    input, select, label, img, button{vertical-align: middle;}
    table{width: 100%; border-collapse: collapse; border-spacing: 0;}
    img{border:0;}
    fieldset{border:0; min-width:0; width:100%}
    ul, ul li{list-style: none;}
    ol{margin-left: 15px;}
    a,button,label{cursor: pointer;}
    button{background: transparent;   border: 0;}
    i,em,address{font-style: normal;}
    a{color: inherit; -webkit-tap-highlight-color: transparent;}
    a[href]{color: inherit; -webkit-tap-highlight-color: inherit;}
    abbr{text-decoration: none;}

    /* body style*/
    .container-404{
        display: flex;
        flex-direction: column;
    	align-items: center;
    	margin-top: 150px;
        
    }
    .etitle{
    font-size: 40px;
    font-weight: 600;
    }
    .econtent{
        font-size: 16px;
    }
    #ebutton{
    width: 300px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    }
    #ebutton button{
        width: 140px;
        height: 50px;
    }
    </style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container-404">
        <span class="etitle">404 Error</span>
        <br>
        <span class="etitle">페이지를 찾을 수 없습니다.</span>
        <br><br><br>
        <span class="econtent">입력하신 주소가 정확한지 다시 한 번 확인해주세요.</span>
        <br><br><br><br>
        <div><img src="resources/images/errorIcon.png" alt="경고사진" style="width: 100px;" ></div>
        <br><br><br><br>
        <div id="ebutton">
            <button class="btn btn-light">이전페이지</button>
            <button class="btn btn-success" style="background: #b2d8b5; border: none;">홈</button>
        </div>
    </div>
</body>
</html>