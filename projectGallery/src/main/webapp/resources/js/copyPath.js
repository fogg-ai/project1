document.addEventListener('DOMContentLoaded', function () {
    try {
        let copyEmailBtn = document.querySelector('.buttonCopy');
        copyEmailBtn.addEventListener('click', function (event) {

            let emailLink = document.querySelector('#path');
            let range = document.createRange();
            range.selectNode(emailLink);
            window.getSelection().addRange(range);

            try {
                document.execCommand('copy');
            } catch (err) {
                console.log('Oops, unable to copy');
            }

            window.getSelection().removeAllRanges();
        });
    }catch (e) {

    }
}, false);