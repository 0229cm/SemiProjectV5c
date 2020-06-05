<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>구글 리캡챠</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script>
        function onSubmit() {
            if (grecaptcha.getResponse() == "") {
                alert("자동가입방지 확인 필요!!");
                return false;
            } else {
                alert("자동가입방지 성공 확인!!");
                document.getElementById("g-recaptcha").value = grecaptcha.getResponse();
                return true;
            }
        }
    </script>
</head>
<body>
<h1>구글 reCaptcha : checkbox V2</h1>

<form method="post" id="loginfrm" action="checkcaptcha.jsp"
      onsubmit="return onSubmit()">
    <div>아이디 : <input type="text" id="userid"
                                name="userid"></div>
    <div>비밀번호 : <input type="password" id="passwd"
                                name="passwd"></div>
    <input type = "hidden" name="g-recaptcha" id="g-recaptcha"/>
    <div class="g-recaptcha" type="submit"
         data-sitekey="6LfPlQAVAAAAAML_OMoHboglVAcShiPaGb_yum38"></div>
    <div><button type="submit">로그인하기</button></div>

</form>

</body>
</html>
