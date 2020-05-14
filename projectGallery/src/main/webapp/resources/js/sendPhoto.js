document.addEventListener('DOMContentLoaded', function () {
    let element = document.querySelector("#files");
    let formElem = document.querySelector("#formPhoto");
    element.addEventListener('change',function (e) {
        formElem.submit();
    })
}, false);