package patrika.barcode.com.model;

public class LoginResponse {

    /**
     * response : {"isSuccess":"true","error":null,"data":{"compcode":"RP001,FF001,VN001","username":"ADMIN","userid":"AD0"}}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * isSuccess : true
         * error : null
         * data : {"compcode":"RP001,FF001,VN001","username":"ADMIN","userid":"AD0"}
         */

        private String isSuccess;
        private String error;
        private DataBean data;

        public String getIsSuccess()
        {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess)
        {
            this.isSuccess = isSuccess;
        }

        public String getError()
        {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public DataBean getData()
        {
            return data;
        }

        public void setData(DataBean data)
        {
            this.data = data;
        }

        public static class DataBean {
            /**
             * compcode : RP001,FF001,VN001
             * username : ADMIN
             * userid : AD0
             */
            private String compcode;
            private String username;
            private String userid;

            public String getCompcode() {
                return compcode;
            }

            public void setCompcode(String compcode) {
                this.compcode = compcode;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
