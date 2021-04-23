package patrika.barcode.com.model;

import androidx.annotation.NonNull;

import java.util.List;

public class DropDownList {
    /**
     * response : {"isSuccess":"true","error":null,"data":[{"centcode":"AG1","centname":"AGRA PT"},{"centcode":"AH0","centname":"AHMEDABAD RP"},{"centcode":"AJ0","centname":"AJMER RP"},{"centcode":"AL0","centname":"ALWAR RP"},{"centcode":"BA1","centname":"BANGLORE RP"},{"centcode":"BA0","centname":"BANSWARA RP"},{"centcode":"BA2","centname":"BARMER RP"},{"centcode":"BH2","centname":"BHILAI PT"},{"centcode":"BH0","centname":"BHILWARA RP"},{"centcode":"BH1","centname":"BHOPAL PT"},{"centcode":"BI0","centname":"BIKANER RP"},{"centcode":"BI1","centname":"BILASPUR PT"},{"centcode":"CH0","centname":"CHENNAI RP"},{"centcode":"SE0","centname":"CHHINDWARA PT"},{"centcode":"CO0","centname":"COIMBATORE RP"},{"centcode":"DE0","centname":"DELHI PT"},{"centcode":"GA0","centname":"GANGAPUR CITY"},{"centcode":"GW0","centname":"GWALIOR PT"},{"centcode":"HU0","centname":"HUBLI RP"},{"centcode":"NE0","centname":"INDORE NT"},{"centcode":"IN0","centname":"INDORE PT"},{"centcode":"JA1","centname":"JABALPUR PT"},{"centcode":"JA8","centname":"JAGDALPUR PT"},{"centcode":"DA0","centname":"JAIPUR DN"},{"centcode":"NE1","centname":"JAIPUR NT"},{"centcode":"JA2","centname":"JAIPUR OFFICE(ISD)"},{"centcode":"JA0","centname":"JAIPUR RP"},{"centcode":"JO0","centname":"JODHPUR RP"},{"centcode":"KH0","centname":"KHANDWA PT"},{"centcode":"KO1","centname":"KOLKATA RP"},{"centcode":"KO0","centname":"KOTA RP"},{"centcode":"MA0","centname":"MANDSAUR PT"},{"centcode":"MU1","centname":"MUMBAI OFFICE"},{"centcode":"PA0","centname":"PALI RP"},{"centcode":"RA1","centname":"RAIPUR PT"},{"centcode":"RA2","centname":"RAJGARH RP"},{"centcode":"RA0","centname":"RATLAM PT"},{"centcode":"SA1","centname":"SAGAR PT"},{"centcode":"SA0","centname":"SATNA PT"},{"centcode":"SI0","centname":"SIKAR RP"},{"centcode":"SR0","centname":"SRI GANGANAGAR RP"},{"centcode":"SU0","centname":"SURAT RP"},{"centcode":"UD0","centname":"UDAIPUR RP"},{"centcode":"UJ0","centname":"UJJAIN RP"}]}
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
         * data : [{"centcode":"AG1","centname":"AGRA PT"},{"centcode":"AH0","centname":"AHMEDABAD RP"},{"centcode":"AJ0","centname":"AJMER RP"},{"centcode":"AL0","centname":"ALWAR RP"},{"centcode":"BA1","centname":"BANGLORE RP"},{"centcode":"BA0","centname":"BANSWARA RP"},{"centcode":"BA2","centname":"BARMER RP"},{"centcode":"BH2","centname":"BHILAI PT"},{"centcode":"BH0","centname":"BHILWARA RP"},{"centcode":"BH1","centname":"BHOPAL PT"},{"centcode":"BI0","centname":"BIKANER RP"},{"centcode":"BI1","centname":"BILASPUR PT"},{"centcode":"CH0","centname":"CHENNAI RP"},{"centcode":"SE0","centname":"CHHINDWARA PT"},{"centcode":"CO0","centname":"COIMBATORE RP"},{"centcode":"DE0","centname":"DELHI PT"},{"centcode":"GA0","centname":"GANGAPUR CITY"},{"centcode":"GW0","centname":"GWALIOR PT"},{"centcode":"HU0","centname":"HUBLI RP"},{"centcode":"NE0","centname":"INDORE NT"},{"centcode":"IN0","centname":"INDORE PT"},{"centcode":"JA1","centname":"JABALPUR PT"},{"centcode":"JA8","centname":"JAGDALPUR PT"},{"centcode":"DA0","centname":"JAIPUR DN"},{"centcode":"NE1","centname":"JAIPUR NT"},{"centcode":"JA2","centname":"JAIPUR OFFICE(ISD)"},{"centcode":"JA0","centname":"JAIPUR RP"},{"centcode":"JO0","centname":"JODHPUR RP"},{"centcode":"KH0","centname":"KHANDWA PT"},{"centcode":"KO1","centname":"KOLKATA RP"},{"centcode":"KO0","centname":"KOTA RP"},{"centcode":"MA0","centname":"MANDSAUR PT"},{"centcode":"MU1","centname":"MUMBAI OFFICE"},{"centcode":"PA0","centname":"PALI RP"},{"centcode":"RA1","centname":"RAIPUR PT"},{"centcode":"RA2","centname":"RAJGARH RP"},{"centcode":"RA0","centname":"RATLAM PT"},{"centcode":"SA1","centname":"SAGAR PT"},{"centcode":"SA0","centname":"SATNA PT"},{"centcode":"SI0","centname":"SIKAR RP"},{"centcode":"SR0","centname":"SRI GANGANAGAR RP"},{"centcode":"SU0","centname":"SURAT RP"},{"centcode":"UD0","centname":"UDAIPUR RP"},{"centcode":"UJ0","centname":"UJJAIN RP"}]
         */

        private String isSuccess;
        private Object error;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * centcode : AG1
             * centname : AGRA PT
             */

            public String centcode;
            public String centname;

            public String getCentcode() {
                return centcode;
            }

            public void setCentcode(String centcode) {
                this.centcode = centcode;
            }

            public String getCentname() {
                return centname;
            }

            public void setCentname(String centname) {
                this.centname = centname;
            }
        }
    }


    /**
     * response : {"isSuccess":"true","error":null,"data":[{"centcode":"AG1","centname":"AGRA PT"},{"centcode":"AH0","centname":"AHMEDABAD RP"},{"centcode":"AJ0","centname":"AJMER RP"},{"centcode":"AL0","centname":"ALWAR RP"},{"centcode":"BA1","centname":"BANGLORE RP"},{"centcode":"BA0","centname":"BANSWARA RP"},{"centcode":"BA2","centname":"BARMER RP"},{"centcode":"BH2","centname":"BHILAI PT"},{"centcode":"BH0","centname":"BHILWARA RP"},{"centcode":"BH1","centname":"BHOPAL PT"},{"centcode":"BI0","centname":"BIKANER RP"},{"centcode":"BI1","centname":"BILASPUR PT"},{"centcode":"CH0","centname":"CHENNAI RP"},{"centcode":"SE0","centname":"CHHINDWARA PT"},{"centcode":"CO0","centname":"COIMBATORE RP"},{"centcode":"DE0","centname":"DELHI PT"},{"centcode":"GA0","centname":"GANGAPUR CITY"},{"centcode":"GW0","centname":"GWALIOR PT"},{"centcode":"HU0","centname":"HUBLI RP"},{"centcode":"NE0","centname":"INDORE NT"},{"centcode":"IN0","centname":"INDORE PT"},{"centcode":"JA1","centname":"JABALPUR PT"},{"centcode":"JA8","centname":"JAGDALPUR PT"},{"centcode":"DA0","centname":"JAIPUR DN"},{"centcode":"NE1","centname":"JAIPUR NT"},{"centcode":"JA2","centname":"JAIPUR OFFICE(ISD)"},{"centcode":"JA0","centname":"JAIPUR RP"},{"centcode":"JO0","centname":"JODHPUR RP"},{"centcode":"KH0","centname":"KHANDWA PT"},{"centcode":"KO1","centname":"KOLKATA RP"},{"centcode":"KO0","centname":"KOTA RP"},{"centcode":"MA0","centname":"MANDSAUR PT"},{"centcode":"MU1","centname":"MUMBAI OFFICE"},{"centcode":"PA0","centname":"PALI RP"},{"centcode":"RA1","centname":"RAIPUR PT"},{"centcode":"RA2","centname":"RAJGARH RP"},{"centcode":"RA0","centname":"RATLAM PT"},{"centcode":"SA1","centname":"SAGAR PT"},{"centcode":"SA0","centname":"SATNA PT"},{"centcode":"SI0","centname":"SIKAR RP"},{"centcode":"SR0","centname":"SRI GANGANAGAR RP"},{"centcode":"SU0","centname":"SURAT RP"},{"centcode":"UD0","centname":"UDAIPUR RP"},{"centcode":"UJ0","centname":"UJJAIN RP"}]}
     */


}
