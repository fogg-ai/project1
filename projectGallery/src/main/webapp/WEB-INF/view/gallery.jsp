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
</head>
<body>

    <header v-bind:class="header" id="logout">
        <span  v-bind:class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>
        <a v-bind:class="aut" v-on:click="log=!log" href="#logout">Log out</a>

        <input type="checkbox" class="checkbox" id="checkbox"/>
        <label id="checkboxD" v-on:click="day=!day" for="checkbox"></label>


        <div v-show="log" class="formDay">

            <form class="modal" action="/logout" method="POST">
                <h3 v-bind:class="h3">Нou definitely want to go out?</h3>
                <br/>
                <div class="modal-content">
                    <sec:csrfInput/>
                </div>

                <div class="fieldSubmit">
                    <a type="submit" class="cancelDay" v-on:click="log=!log" href="#!">Cancel</a>
                    <button class="buttonDay" type="submit">Log out
                    </button>
                </div>
            </form>
        </div>
    </header>
    <main id="main" v-bind:class="content">контент</main>

</body>
</html>
