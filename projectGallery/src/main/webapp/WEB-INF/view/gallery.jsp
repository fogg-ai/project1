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

    <script src="<spring:url value="/static/js/sendPhoto.js"/>"></script>


</head>
<body>

</div>

<div class="drop-area">
    <header id="logout">
        <div v-bind:class="headerHtml">
            <div class="menu">
                <div class="icon-close">
                    <img src="<spring:url value="/static/imageMainPage/close-btn.png"/>">
                </div>
                <ul>
                    <li v-on:click="log=!log"><a href="#logout">Log out</a></li><!--v-bind:class="aut"-->
                    <li v-on:click="upload=!upload"><a href="#upload">Upload</a></li>

                    <li><input type="checkbox" class="checkbox" id="checkbox" v-model="day"/>
                        <label id="checkboxD" for="checkbox"></label></li>
                    <li><br/></li>
                    <li></li>
                    <li></li>
                    <li><span class="mem">${size}/${sizeMax}</span></li>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li class="liHref"><a href="<spring:url value="/buyplace"/>">More place</a></li>
                    </sec:authorize>
                </ul>
            </div>

            <div class="background">

                <div class="icon-menu">
                    <img src="<spring:url value="/static/imageMainPage/menu-ham-icon.png"/>">
                </div>
            </div>


            <span v-bind:class="name">𝒫𝑒𝓇𝓈𝑜𝓃 𝒢𝒶𝓁𝓁𝑒𝓇𝓎</span>


            <div v-show="upload" v-bind:class="form">

                <form enctype="multipart/form-data" id="formPhoto" method="post" action="/upload">
                    <label v-bind:class="send" for="files">Choose a photo</label>
                    <input v-bind:class="send" id="files"
                           class="fileArea" name="photo" type="file" multiple>
                    <a type="submit" v-bind:class="cancel" v-on:click="upload=!upload" href="#!">Cancel</a>
                    </p>

                </form>

            </div>


            <div v-show="log" v-bind:class="form">
                <form class="modal" action="/logout" method="POST">
                    <h3 v-bind:class="h3">Нou definitely want to go out?</h3>
                    <br/>
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
                    <c:if test='${!pathOpenPhoto.equals("There is no such photo")}'>http://localhost:8080</c:if>${pathOpenPhoto}</a>
                <button title="Click to copy" class="buttonCopy"><h1>⎘</h1></button>
                </span>
                </c:if>
            </div>
            <c:forEach items="${pathListPhotoMin}" var="item1" varStatus="loop1">
            <span class="holder">
                    <img src="<spring:url value="${item1}" />">
                <c:forEach items="${pathList}" var="item" varStatus="loop">
                    <c:if test="${loop.count == loop1.count}">
                        <div class="block">
                            <h2>
                                <a class="infoImage" title="Delete"
                                   href="/deletePhoto?path=<spring:url value="${item}"/>">🗑</a>
                                <a class="infoImage" title="View" href="<spring:url value="${item}"/>">ᐈ</a>
                                <a class="infoImage" title="Share this"
                                   href="/gallery/?path=<spring:url value="${item}"/>">ᕮᕭ</a>
                                <a class="infoImage download" title="Download"
                                   href="/download?path=<spring:url value="${item}"/>">&#10506;</a>
                            </h2>
                        </div>
                    </c:if>
                </c:forEach>
            </span>
            </c:forEach>

        </div>

        ${message}
        <div class="clearly">
        </div>

    </main>

</div>
<div id="footerGal">
    <span v-bind:class="info"> 2020 PersonGallery</span> <!--©-->
</div>

</div>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<spring:url value="/static/js/slideMenu.js"/>"></script>
<script src="<spring:url value="/static/js/dragAndDrop.js"/>"></script>
</body>
</html>
