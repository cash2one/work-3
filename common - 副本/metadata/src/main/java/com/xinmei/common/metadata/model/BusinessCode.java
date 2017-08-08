package com.xinmei.common.metadata.model;



/**
 * Created by Toby on 2016/11/23.
 * 两码一号模型
 */
public class BusinessCode {

    //发起端两码一号
    private InitiatorBusinessCode initiatorCodeBusinessCode;
    //调用端两码一号
    private CallerBusinessCode callerCode;
    //被调用端两码一号
    private CalleeBusinessCode calleeCode;

    public static BusinessCode initiator(String productCode,String transactionNum,String eventCode){
        BusinessCode businessCode=new BusinessCode();
        InitiatorBusinessCode initiatorBusinessCode=new InitiatorBusinessCode();
        initiatorBusinessCode.setProductCode(productCode);
        initiatorBusinessCode.setEventCode(eventCode);
        initiatorBusinessCode.setTransactionNum(transactionNum);
        businessCode.setInitiatorCodeBusinessCode(initiatorBusinessCode);
        CallerBusinessCode callerBusinessCode=new CallerBusinessCode();
        callerBusinessCode.setEventCode(eventCode);
        callerBusinessCode.setProductCode(productCode);
        businessCode.setCallerCode(callerBusinessCode);
        CalleeBusinessCode calleeBusinessCode = new CalleeBusinessCode();
        calleeBusinessCode.setEventCode(eventCode);
        businessCode.setCalleeCode(calleeBusinessCode);
        return businessCode;
    }

    public void caller(String eventCode){
        CallerBusinessCode callerBusinessCode=new CallerBusinessCode();
        callerBusinessCode.setEventCode(this.initiatorCodeBusinessCode.eventCode);
        callerBusinessCode.setProductCode(this.initiatorCodeBusinessCode.productCode);
        this.callerCode = callerBusinessCode;
        CalleeBusinessCode calleeBusinessCode = new CalleeBusinessCode();
        calleeBusinessCode.setEventCode(eventCode);
        this.calleeCode = calleeBusinessCode;
    }

    public void caller(String productCode,String eventCode){
        this.callerCode.setEventCode(this.calleeCode.eventCode);
        this.callerCode.setProductCode(productCode);
        this.calleeCode.setEventCode(eventCode);
    }

    public static BusinessCode callee(String businessCodeStr, String eventCode) {
        BusinessCode businessCode=new BusinessCode();
        String[] codeArray=businessCodeStr.split("\\|");
        InitiatorBusinessCode initiatorCodeBusinessCode =new InitiatorBusinessCode();
        initiatorCodeBusinessCode.setTransactionNum(codeArray[0]);
        initiatorCodeBusinessCode.setProductCode(codeArray[1]);
        initiatorCodeBusinessCode.setEventCode(codeArray[2]);
        businessCode.setInitiatorCodeBusinessCode(initiatorCodeBusinessCode);
        CallerBusinessCode callerBusinessCode=new CallerBusinessCode();
        callerBusinessCode.setProductCode(codeArray[3]);
        callerBusinessCode.setEventCode(codeArray[4]);
        businessCode.setCallerCode(callerBusinessCode);
        CalleeBusinessCode calleeBusinessCode=new CalleeBusinessCode();
        calleeBusinessCode.setEventCode(eventCode);
        businessCode.setCalleeCode(calleeBusinessCode);
        return businessCode;
    }

    @Override
    public String toString() {
        StringBuilder buffer=new StringBuilder();
        buffer.append(initiatorCodeBusinessCode).append("|")
                .append(callerCode).append("|").append(calleeCode);
        return buffer.toString();
    }


    public CalleeBusinessCode getCalleeCode() {
        return calleeCode;
    }

    public void setCalleeCode(CalleeBusinessCode calleeCode) {
        this.calleeCode = calleeCode;
    }

    public CallerBusinessCode getCallerCode() {
        return callerCode;
    }

    public void setCallerCode(CallerBusinessCode callerCode) {
        this.callerCode = callerCode;
    }

    public InitiatorBusinessCode getInitiatorCodeBusinessCode() {
        return initiatorCodeBusinessCode;
    }

    public void setInitiatorCodeBusinessCode(InitiatorBusinessCode initiatorCodeBusinessCode) {
        this.initiatorCodeBusinessCode = initiatorCodeBusinessCode;
    }


    public static class InitiatorBusinessCode {

        private String transactionNum;
        private String productCode;
        private String eventCode;

        @Override
        public String toString() {
            StringBuilder buffer=new StringBuilder();
            buffer.append(transactionNum).append("|")
                    .append(productCode).append("|").append(eventCode);
            return buffer.toString();
        }

        public String getEventCode() {
            return eventCode;
        }

        public void setEventCode(String eventCode) {
            this.eventCode = eventCode;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getTransactionNum() {
            return transactionNum;
        }

        public void setTransactionNum(String transactionNum) {
            this.transactionNum = transactionNum;
        }


    }

    public static class CallerBusinessCode{
        private String productCode;
        private String eventCode;

        @Override
        public String toString() {
            StringBuilder buffer=new StringBuilder();
            buffer.append(productCode).append("|").append(eventCode);
            return buffer.toString();
        }

        public String getEventCode() {
            return eventCode;
        }

        public void setEventCode(String eventCode) {
            this.eventCode = eventCode;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }
    }

    private static class CalleeBusinessCode{
        private String eventCode;

        @Override
        public String toString() {
            return eventCode;
        }

        public String getEventCode() {
            return eventCode;
        }

        public void setEventCode(String eventCode) {
            this.eventCode = eventCode;
        }
    }
}
