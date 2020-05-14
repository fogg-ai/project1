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
    <script src="<spring:url value="/static/js/vueJs.js"/>"></script>
    <script src="<spring:url value="/static/js/slideMenu.js"/>"></script>
</head>
<body>


<header id="log">
    <div class="menu">

        <div class="icon-close">
            <img src="<spring:url value="/static/imageMainPage/close-btn.png"/>">
        </div>

        <ul>
            <sec:authorize access="isAnonymous()">
                <li v-on:click="reg=!reg"><a  href="#reg">Sing up</a></li>
                <li v-on:click="log=!log"><a  href="#login">Sing in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <li class="link"><a href="/gallery">Gallery</a></li>
            </sec:authorize>
        </ul>
    </div>

    <div class="background">

        <div class="icon-menu">
            <img src="<spring:url value="/static/imageMainPage/menu-ham-icon.png"/>">
        </div>
    </div>


    <span class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>

    <div class="form">
        <form:form modelAttribute="userGalleryDto" class="modal" action="/login" v-show="log" method="POST">
            <div class="modal-content">

                <div class="field">
                    <label>Login:<input class="fieldL" name="login" required/></label>
                </div>
                <div class="field">
                    <label>Password:<input name="password"  class="fieldP" required type="password"/></label>
                    <sec:csrfInput/>
                </div>
                <div class="field">
                    <input type="checkbox" class="fieldCh" id="horns" name="rememberMe"/>
                    <label for="horns">Remember?</label>
                </div>
            </div>

            <div class="fieldSubmit">
                <a type="submit" class="cancel" v-on:click="log=!log" href="#!">Cancel</a>
                <button type="submit">Sing in
                </button>
            </div>
            <c:if test="${param.error == 'notentry'}">
                Repeat the entry, the data is incorrect.
            </c:if>
        </form:form>


    </div>


    <div class="form reg">
        <form:form modelAttribute="userGalleryDto" class="modal" action="/register" v-show="reg" method="POST">
            <div class="modal-content">

                <div class="field">
                    <form:label path="login">Login:</form:label>
                    <form:input path="login" required="required" class="fieldR"/>
                    <h5><form:errors path="login" class="helper-text" data-error="wrong" data-success="right"/></h5>
                </div>
                <div class="field">
                    <form:label path="password">Password:<form:input maxlength="16" path="password" type="password" required="required" class="fieldP"/></form:label>
                    <h5><form:errors path="password"  class="helper-text" data-error="wrong" data-success="right"/></h5>
                </div>
                <div class="field">
                    <form:label path="email">Email:<form:input path="email" type="email" required="required" class="fieldE"/></form:label>
                    <h5><form:errors path="email" class="helper-text" data-error="wrong" data-success="right"/></h5>

                    <sec:csrfInput/>
                </div>
            </div>

            <div class="field">
                <a type="submit" class="cancel" v-on:click="reg=!reg" href="#!">Cancel</a>
                <button type="submit">Sing up
                </button>
            </div>
            ${errorLogin}
        </form:form>
    </div>

</header>
<main>
    <div class="content">
        <div class="fullscreen-bg">
            <div class="overlay">
            </div>
            <video loop="" muted="" autoplay="" class="fullscreen-bg__video">
                <source src="<spring:url value="/static/imageMainPage/1.mp4"/>" type="video/mp4">
                <source src="<spring:url value="/static/imageMainPage/1.mp4"/>" type="video/webm">
            </video>
           
        </div>
    </div>


</main>
<footer>
    <span class="info"> 2020 PersonGallery</span> <!--©-->
    <script src="<spring:url value="/static/js/index.js"/>"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="<spring:url value="/static/js/slideMenu.js"/>"></script>
</footer>
</body>
</html>