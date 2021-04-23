package patrika.barcode.com.model;

public class UpdateData {

    /**
     * response : {"isSuccess":"true","error":null,"data":"Record Updated Successfully!!"}
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
         * data : Record Updated Successfully!!
         */

        private String isSuccess;
        private Object error;
        private String data;

        public String getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess) {
            this.isSuccess = isSuccess;
        }

        public Object getError() {
            return error;
        }

        public void setError(Object error) {
            this.error = error;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
