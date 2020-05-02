document.addEventListener('DOMContentLoaded', function () {
    let header = new Vue({
        el: '#logout',
        data: {
            log: false,
            day: true,
            name: 'nameDay',
            header: 'headerDay',
            aut:'autDay',
            button:'buttonDay',
            cancel:'cancelNight',
            h3:'h3Day',
        }, watch: {
            day: function (e) {
                main.day = this.day;
                if(this.day){
                    this.button = 'buttonDay';
                    this.h3 = 'h3Day';
                    this.cancel = 'cancelDay'
                    this.name = 'nameDay';
                    this.header = 'headerDay';
                    this.aut = 'autDay';
                }else {
                    this.button = 'buttonNight';
                    this.cancel = 'cancelNight';
                    this.h3 = 'h3Night';
                    this.name = 'nameNight';
                    this.header = 'headerNight';
                    this.aut = 'autNight';
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