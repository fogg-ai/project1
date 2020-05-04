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

    <script src="<spring:url value="/static/js/savePhoto.js"/>"></script>

</head>
<body>

<header v-bind:class="header" id="logout">
    <div class="menu">
        <div class="icon-close">
            <img src="<spring:url value="/static/imageMainPage/close-btn.png"/>">
        </div>
        <ul>
            <li v-on:click="log=!log"><a href="#logout">Log out</a></li><!--v-bind:class="aut"-->
            <li v-on:click="upload=!upload"><a href="#upload">Upload</a></li>

            <li><input type="checkbox" class="checkbox" id="checkbox"/>
                <label id="checkboxD" v-on:click="day=!day" for="checkbox"></label></li>
        </ul>
    </div>

    <div class="background">

        <div class="icon-menu">
            <img src="<spring:url value="/static/imageMainPage/menu-ham-icon.png"/>">
        </div>
    </div>


    <span v-bind:class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>
    <%--        <h6 v-bind:class="h5">signed in as <sec:authentication property="name"/></h6>--%>

    <div v-show="upload" class="formDay">
        <form enctype="multipart/form-data" method="post" action="/upload">
            <label v-bind:class="send" for="files">Choose a photo</label>
            <input v-bind:class="send" id="files" style="display: none;" name="photo" type="file">
            <%--                <sec:csrfInput/>--%>
            <a type="submit" v-bind:class="cancel" v-on:click="upload=!upload" href="#!">Cancel</a>
            <button v-bind:class="button" type="submit">Send
            </button>
            </p>

        </form>
    </div>

    <div v-show="log" class="formDay">
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
</header>
<main id="main" v-bind:class="content">

</main>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<spring:url value="/static/js/slideMenu.js"/>"></script>
</body>
</html>
