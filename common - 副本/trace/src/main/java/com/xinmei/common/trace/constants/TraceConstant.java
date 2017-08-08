package com.xinmei.common.trace.constants;

/**
 * @ClassName: com.xinmei.common.trace.constants.TraceConstant
 * @Description: trace常量类
 * @Author xbzhu
 * @Date 2017年03月03日 9:57
 */
public class TraceConstant {


    public static final String TYPE_CROSS_THREAD_CALLED="CROSS.THREAD.called";

    public static final String TRACE_INVOKE_METHOD="invoke.info";
    public static final String TYPE_SOFA_RPC_CLIENT = "SOFA-CLIENT";

    public static final String TYPE_SOFA_RPC_SERVER = "SOFA-SERVER";

    public static final String EVENT_SOFA_RPC_REQUEST = "SOFA-CLIENT.request";

    public static final String EVENT_SOFA_RPC_INVOKE_APP = "SOFA-CLIENT.appName";
    public static final String EVENT_SOFA_RPC_TRACE_ID = "SOFA.traceId";
    public static final String EVENT_SOFA_RPC_INVOKE_SERVICE_NAME = "SOFA.serviceName";

    public static final String EVENT_SOFA_RPC_INVOKE_TARGET_URL = "SOFA.targetURL";

    public static final String EVENT_SOFA_RPC_INVOKE_CALL_URL = "SOFA.callURL";
    public static final String EVENT_SOFA_RPC_INVOKE_CALL_METHOD = "SOFA.method";


    public static final String EVENT_SOFA_RPC_COST_TIME = "SOFA.costTime";

    public static final String EVENT_SOFA_RPC_RESULT = "SOFA.result";

    public static final String EVENT_REST_INVOKE_REQUEST="rest.request";

    public static final String EVENT_REST_INVOKE_HEADER="rest.header";

    public static final String EVENT_REST_INVOKE_RESPONSE="rest.response";

    public static final String EVENT_REST_INVOKE_RESPONSE_STATUS="rest.response.status";


    public static final String EVENT_APP_NAME = "APP.appName";
    public static final String EVENT_APP_VERSION = "APP.appVersion";
    public static final String EVENT_APP_MODULE = "APP.model";
    public static final String EVENT_APP_REQUEST = "APP.request";
    public static final String EVENT_APP_RESPONSE = "APP.response";
    public static final String EVENT_APP_SUCCESS = "APP.success";
    public static final String EVENT_APP_LOGIN_USER = "APP.loginUser";
    public static final String EVENT_APP_OS_TYPE = "APP.osType";

    public static final String EVENT_APP_REMOTE_CALL_REQUEST = "APP.call.request";
    public static final String EVENT_APP_REMOTE_CALL_TOKEN = "APP.call.token";
    public static final String EVENT_APP_REMOTE_CALL_RESPONSE = "APP.call.result";

    /**
     * 发生业务错误时，用于携带业务信息
     */
    public static final String ERROR_USER = "err.user";
    public static final String BIZ_INFO="biz";
    public static final String BIZ_AUTHOR="author";
    public static final String ERROR_TIME="err.time";
    public static final String ERROR_NOTICE_SWITCH="notice.switch";
    public static final String ERROR_INFO="error.info";

    public static final String REQUEST_ID="requestId";


    public static final String TRACE_HTTP_REQUEST_KEY="trace";
    public static final String TRACE_HTTP_REQUEST_VALUE="cat";


    public static final String CURRENT_ENV_PROFILE="env.profile";

}
