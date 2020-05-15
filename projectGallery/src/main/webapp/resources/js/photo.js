document.addEventListener('DOMContentLoaded', function () {

    if (localStorage.getItem('day') === null) {
        localStorage.setItem('day', true);
    }



    let header = new Vue({
        el: '#logout',
        data: {
            log: false,
            upload: false,
            day: localStorage.getItem('day'),
            name: null,
            headerHtml: null,
            aut: null,
            button: null,
            cancel: null,
            h3: null,
            send: null,
            form: null,
        }, watch: {
            day: function (e) {
                localStorage.setItem('day', this.day);
                main.day = this.day;
                footerGal.day = this.day;
                if (this.day) {
                    this.form = 'formDay';
                    this.send = 'sendDay';
                    this.button = 'buttonDay';
                    this.h3 = 'h3Day';
                    this.cancel = 'cancelDay'
                    this.name = 'nameDay';
                    this.headerHtml = 'headerDay';
                    this.aut = 'autDay';

                }
                if (!this.day) {
                    this.form = 'formNight';
                    this.send = 'sendNight';
                    this.button = 'buttonNight';
                    this.cancel = 'cancelNight';
                    this.h3 = 'h3Night';
                    this.name = 'nameNight';
                    this.headerHtml = 'headerNight';
                    this.aut = 'autNight';

                }


            },
            upload: function (val) {
                if (this.upload === true) {
                    this.log = false;
                }
                if (this.log === true) {
                    this.upload = false;
                }
            },
            log: function (val) {
                if (this.log === true) {
                    this.upload = false;
                }
                if (this.upload === true) {
                    this.log = false;
                }
            }
        },
        mounted() {
            localStorage.setItem('day', this.day);

            if (localStorage.getItem('day') === 'true') {
                this.form = 'formDay';
                this.send = 'sendDay';
                this.button = 'buttonDay';
                this.h3 = 'h3Day';
                this.cancel = 'cancelDay'
                this.name = 'nameDay';
                this.headerHtml = 'headerDay';
                this.aut = 'autDay';

            }
            if (localStorage.getItem('day') === 'false') {
                this.form = 'formNight';
                this.send = 'sendNight';
                this.button = 'buttonNight';
                this.cancel = 'cancelNight';
                this.h3 = 'h3Night';
                this.name = 'nameNight';
                this.headerHtml = 'headerNight';
                this.aut = 'autNight';

            }


        }
    })

    let main = new Vue({
        el: '#main',
        data: {
            day: true,
            content: null,
            path: null,
        }, watch: {
            day: function (e) {
                if (this.day) {
                    this.content = 'contentDay';
                    this.path = 'pathDay';
                } else {
                    this.content = 'contentNight';
                    this.path = 'pathNight';
                }
            }
        },
        mounted() {
            if (localStorage.getItem('day') === 'true') {
                this.content = 'contentDay';
                this.path = 'pathDay';
            } else if (localStorage.getItem('day') === 'false') {
                this.content = 'contentNight';
                this.path = 'pathNight';
            }

        }
    })
    let footerGal = new Vue({
        el:"#footerGal",
        data: {
            day: true,
            info: null
        }, watch: {
            day: function (e) {

                if (this.day) {
                    this.info = 'infoDay';
                } else {
                    this.info = 'infoNight';
                }

            }
        },
        mounted() {
            if (localStorage.getItem('day') === 'true') {
                this.info = 'infoDay';
            } else if (localStorage.getItem('day') === 'false') {
                this.info = 'infoNight';
            }
        }
    })
}, false);