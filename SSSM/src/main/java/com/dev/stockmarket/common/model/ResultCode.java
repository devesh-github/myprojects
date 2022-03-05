package com.dev.stockmarket.common.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat (shape = JsonFormat.Shape.OBJECT)
public enum ResultCode implements Code {

    // @formatter:off

    /*
     * * Used HTTP Status Code
     *      200 : OK
     *      201 : Created
     *      400 : Bad Request
     *      401 : Unauthorized
     *      403 : Forbidden
     *      404 : Not Found
     *      500 : Internal Server Error
     *
     *
     * * Serial Number
     *      00xx : Common
     *      01xx : User
     *      02xx : Device
     *      03xx : RD (ResourceDirectory)
     *      04xx : RD Link
     *      05xx : Group
     *      06xx : ACL
     *      07xx : CI (Cloud Interface)
     *      08xx : Push
     *
     * * Sub-Serial Number
     *      x00 : Not Found.
     *      x01 : Duplicated.
     *      x02 : Invalid Param.
     *
     *  * Result Code is made by
     *      HTTP Status Code(xxx) + Serial Number(xxx)
     */

    SUCCESS                                         (2000000, "SUCCESS"),

    //COMMON
    TOKEN_VALIDATION_FAILED                         (4000002, "TOKEN_VALIDATION_FAILED"),
    FORBIDDEN                                       (4030003, "FORBIDDEN"),
    TOKEN_EXPIRED                                   (4000004, "TOKEN_EXPIRED"),
    AUTHORIZATION_FAILED                            (4000005, "AUTHORIZATION_FAILED"),

    //STOCK
    STOCK_INVALID_PARAM                              (4000102, "STOCK_INVALID_PARAM"),
    STOCK_NOT_FOUND                                  (4040100, "STOCK_NOT_FOUND"),


    UNAUTHORIZED_TOKEN                              (4010200, "UNAUTHORIZED_TOKEN"),
    SAMSUNG_ACCOUNT_UNAUTHORIZED_TOKEN              (4010201, "UNAUTHORIZED_TOKEN"),
    
    SUBSCRIPTION_NOT_FOUND                          (4040300, "SUBSCRIPTION_NOT_FOUND"),
    
    VOICE_COMMAND_INVALID                           (4000401, "VOICE_COMMAND_INVALID"),
    
    EVENT_DATA_POST_FAILED                          (4000501, "EVENT_DATA_POST_FAILED"),
    
    
    
    // Common
    INTERNAL_SERVER_ERROR                           (5000000, "INTERNAL_SERVER_ERROR"),
    BAD_REQUEST                                     (4000000, "BAD_REQUEST"),
    DB_CONNECTION_FAIL                              (5000002, "DB_CONNECTION_FAIL")

    ;
    // @@formatter:on

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
