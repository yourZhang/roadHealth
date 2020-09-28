var Mixins = {
    data: {
        baseUrl: "",
        awInt: 1,
        qiniuAddr: "http://qh6x6sf0q.hd-bkt.clouddn.com/"
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
                that.baseUrl = "http://127.0.0.1:8086/health-mobile/"
            } else {
                that.baseUrl = ""
            }
        }
    }
}