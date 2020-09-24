var Mixins = {
    data: {
        baseUrl: "",
        awInt: 1
    },
    created() {
        //预处理
        this.handleBaseUrl();
    },
    mounted() {

    },
    methods: {
        //预处理
        handleBaseUrl() {
            let that = this;
            if (that.awInt == 1) {
                that.baseUrl = "http://127.0.0.1:8082/health-oms/"
            }else{
                that.baseUrl = ""
            }
        }
    }
}