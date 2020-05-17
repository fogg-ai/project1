document.addEventListener('DOMContentLoaded', function () {

    let dropArea = document.querySelector('.drop-area');
    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false);
    })

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    ['dragenter', 'dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, highlight, false);


    });
    ['dragleave','drop','dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, unhighlight, false);
    })


    function highlight(e) {
        if (e.target.getAttribute('class') === "headerDay"
            || e.target.getAttribute('class') === "headerNight"){
            dropArea.classList.remove('highlight');
        }else {
            dropArea.classList.add('highlight');
        }
    }

    function unhighlight(e) {
        console.log(e.target)
        if (e.target.getAttribute('class') === "contentDay"
            || e.target.getAttribute('class') === "contentNight"
            || e.target.getAttribute('class') === "clearly"){
            dropArea.classList.remove('highlight');
        }

    }

    dropArea.addEventListener('drop', handleDrop, false)

    function handleDrop(e) {
        let dt = e.dataTransfer;
        let files = dt.files;
        handleFiles(files);

    }

    function handleFiles(files) {
        ([...files]).forEach(uploadFile);
    }

    function uploadFile(file) {
        let url = 'http://localhost:8080/rest/photo';
        let formData = new FormData()
        formData.append('file', file)
        fetch(url, {
            method: 'POST',
            body: formData
        })
            .then(() => {
                window.location.reload();
            })
            .catch(() => {
                console.log("bad")

                window.location.reload();
            })
    }
}, false);
