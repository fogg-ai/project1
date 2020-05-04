document.addEventListener('DOMContentLoaded', function () {
    let header = new Vue({
        el: '#logout',
        data: {
            log: false,
            upload: false,
            day: true,
            name: 'nameDay',
            header: 'headerDay',
            aut:'autDay',
            button:'buttonDay',
            cancel:'cancelDay',
            h3:'h3Day',
            h5:'h5Day',
            send:'sendDay',
        }, watch: {
            day: function (e) {
                main.day = this.day;
                if(this.day){
                    this.send = 'sendDay';
                    this.button = 'buttonDay';
                    this.h3 = 'h3Day';
                    this.cancel = 'cancelDay'
                    this.name = 'nameDay';
                    this.header = 'headerDay';
                    this.aut = 'autDay';
                    this.h5 = 'h5Day'
                }else {
                    this.send = 'sendNight';
                    this.button = 'buttonNight';
                    this.cancel = 'cancelNight';
                    this.h3 = 'h3Night';
                    this.name = 'nameNight';
                    this.header = 'headerNight';
                    this.aut = 'autNight';
                    this.h5 = 'h5Night'
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
        }
    })


    let main = new Vue({
        el: '#main',
        data: {
            day: true,
            content: 'contentDay',
        }, watch: {
            day: function (e) {
                if(header.day){
                    console.log(main.day);
                    this.content = 'contentDay';
                }else {
                    console.log(main.day);
                    this.content = 'contentNight';
                }

            }
        }
    })
}, false);