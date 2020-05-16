document.addEventListener('DOMContentLoaded', function () {

    let dropArea = document.getElementById('drop-area');
    let form = document.getElementById('formPhoto');

    ;['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false)
    })
    function preventDefaults (e) {
        e.preventDefault()
        e.stopPropagation()
    }
    ;['dragenter', 'dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, highlight, false)

    })
    ;['dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, unhighlight, false);
    })
    function highlight(e) {
        dropArea.classList.add('highlight')


    }
    function unhighlight(e) {
        dropArea.classList.remove('highlight');
    }

    dropArea.addEventListener('drop', handleDrop, false)
    function handleDrop(e) {
        let dt = e.dataTransfer
        let files = dt.files
        handleFiles(files)

    }

    function handleFiles(files) {
        ([...files]).forEach(uploadFile)
    }

    function uploadFile(file) {
        let url = 'http://localhost:8080/rest/photo';
        let formData = new FormData()
        formData.append('file', file)
        fetch(url, {
            method: 'POST',
            body: formData
        })
            .then(() => { console.log("nice")})
            .catch(() => { console.log("bad") })
        window.location.reload()
    }
}, false);
