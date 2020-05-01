
let log = new Vue({
    el: '#log',
    data: {
        reg: false,
        log: false
    },
    watch: {
        reg: function (val) {
            if (this.reg === true) {
                this.log = false;
            }
            if (this.log === true) {
                this.reg = false;
            }
        },
        log: function (val) {
            if (this.log === true) {
                this.reg = false;
            }
            if (this.reg === true) {
                this.log = false;
            }
        }
    }
});


