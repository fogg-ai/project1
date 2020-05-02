<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <title>Gallery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="<spring:url value="/static/styles/style.css"/>" rel="stylesheet"/>
    <script src="<spring:url value="/static/js/vueJs.js"/>"> </script>
</head>
<body>


<header id="log">
    <span class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>
    <a class="aut" v-on:click="reg=!reg" href="#reg">Sing up</a>
    <a class="aut" v-on:click="log=!log" href="#login">Sing in</a>
    <div class="form">
        <form class="modal" action="/login" v-show="log" method="POST">
            <div class="modal-content">

                <div class="field">
                    <label>Login:<input class="fieldL" name="login" required/></label>
                </div>
                <div class="field">
                    <label>Password:<input name="password" class="fieldP" required type="password"/></label>
                    <sec:csrfInput/>
                </div>
                <div class="field">
                    <input type="checkbox" class="fieldCh" id="horns" name="rememberMe"/>
                    <label for="horns">Remember?</label>
                </div>
            </div>

            <div class="fieldSubmit">
                <a type="submit" class="cancel" v-on:click="log=!log" href="#!">Cancel</a>
                <button type="submit" >Sing in
                </button>
            </div>
            <c:if test="${param.error == 'notentry'}">
                Repeat the entry, the data is incorrect.
            </c:if>
        </form>


    </div>
    <div class="form reg">
        <form class="modal" action="/register" v-show="reg" method="POST">
            <div class="modal-content">

                <div class="field">
                    <label>Login:<input class="fieldL" name="login" required/></label>
                </div >
                <div class="field">
                    <label>Password:<input class="fieldP" name="password" required type="password"/></label>
                </div>
                <div class="field" >
                    <label>Email:<input class="fieldE" name="email" required type="email"/></label>
                    <input type="hidden" name="role" value="ROLE_USER"/>
                    <sec:csrfInput/>
                </div>
            </div>

            <div class="field">
                <a type="submit" class="cancel" v-on:click="reg=!reg" href="#!">Cancel</a>
                <button type="submit" >Sing up
                </button>
            </div>
        </form>
    </div>

</header>
<main>
    <div class="content">
        <div class="fullscreen-bg">
            <div class="overlay">
            </div>
            <video loop="" muted="" autoplay=""  class="fullscreen-bg__video">
                <source src="<spring:url value="/static/imageMainPage/1.mp4"/>"  type="video/mp4">
                <source src="<spring:url value="/static/imageMainPage/1.mp4"/>"  type="video/webm">
            </video>
        </div>
    </div>


</main>
<footer>
    <span class="info">© 2020 PersonGallery</span>
    <script src="<spring:url value="/static/js/index.js"/>"> </script>
</footer>
</body>
</html>