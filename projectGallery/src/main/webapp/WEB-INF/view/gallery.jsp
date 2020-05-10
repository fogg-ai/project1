<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:v-on="http://www.w3.org/1999/xhtml"
      xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <title>Photo</title>
    <link href="<spring:url value="/static/styles/photo.css"/>" rel="stylesheet"/>
    <script src="<spring:url value="/static/js/vueJs.js"/>"></script>
    <script src="<spring:url value="/static/js/photo.js"/>"></script>

    <script src="<spring:url value="/static/js/copyPath.js"/>"></script>

</head>
<body>
<header id="logout">
    <div v-bind:class="headerHtml">
        <div class="menu">
            <div class="icon-close">
                <img src="<spring:url value="/static/imageMainPage/close-btn.png"/>">
            </div>
            <ul>
                <%--                <li><h6 class="h5Night">Signed in as <sec:authentication property="name"/></h6></li>--%>
                <li v-on:click="log=!log"><a href="#logout">Log out</a></li><!--v-bind:class="aut"-->
                <li v-on:click="upload=!upload"><a href="#upload">Upload</a></li>

                <li><input type="checkbox" class="checkbox" id="checkbox"/><!-- v-model="checked"-->
                    <label id="checkboxD" v-on:click="day=!day" for="checkbox"></label></li>
                <li><br/></li>
                <li></li>
                <li></li>
                <li><span class="mem">${size}/1000</span></li>

            </ul>
        </div>

        <div class="background">

            <div class="icon-menu">
                <img src="<spring:url value="/static/imageMainPage/menu-ham-icon.png"/>">
            </div>
        </div>


        <span v-bind:class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>
        <%--        --%>

        <div v-show="upload" v-bind:class="form">
            <form enctype="multipart/form-data" method="post" action="/upload">
                <label v-bind:class="send" for="files">Choose a photo</label>
                <input v-bind:class="send" id="files" class="fileArea" name="photo" type="file">
                <%--                <sec:csrfInput/>--%>
                <a type="submit" v-bind:class="cancel" v-on:click="upload=!upload" href="#!">Cancel</a>
                <button v-bind:class="button" type="submit">Send
                </button>
                </p>

            </form>
        </div>

        <div v-show="log" v-bind:class="form">
            <form class="modal" action="/logout" method="POST">
                <h3 v-bind:class="h3">Нou definitely want to go out?</h3>
                <br/>
                <div class="modal-content">
                    <%--                <sec:csrfInput/>--%>
                </div>

                <div class="fieldSubmit">
                    <a type="submit" v-bind:class="cancel" v-on:click="log=!log" href="#!">Cancel</a>
                    <button v-bind:class="button" type="submit">Log out
                    </button>
                </div>
            </form>
        </div>
    </div>
</header>

<main id="main" v-bind:class="content">
    <div class="pusto">
    </div>
    <div class="allPhoto">
        <div class="pathOpenPhoto">
            <c:if test="${param.path != null}">
                <span class="blockPhoto">
                <a v-bind:class="path" id="path" href="<spring:url value="${pathOpenPhoto}"/>">
                    http://localhost:8080${pathOpenPhoto}</a>
                <button title="Click to copy" class="buttonCopy"><h1>⎘</h1></button>
                </span>
            </c:if>
        </div>
        <c:forEach items="${pathList}" var="item" varStatus="loop">
            <span class="holder">
                <img src="<spring:url value="${item}" />">
                <div class="block">
                    <h2>
                        <a class="infoImage" title="Delete"
                           href="/deletePhoto?path=<spring:url value="${item}"/>"/>🗑</a>
                        <a class="infoImage" title="View" href="<spring:url value="${item}"/>">ᐈ</a>
                        <a class="infoImage" title="Share this"
                           href="/gallery/?path=<spring:url value="${item}"/>"/>ᕮᕭ</a>
                        <a class="infoImage" title="Download"
                           href="/download?path=<spring:url value="${item}"/>"/>&#10506;</a>
                    </h2>
                </div>
            </span>
        </c:forEach>

    </div>

    ${message}
</main>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<spring:url value="/static/js/slideMenu.js"/>"></script>

</body>
</html>
