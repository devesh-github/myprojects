package com.dev.stockmarket.common.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.stockmarket.common.exception.SssmRestException;
import com.dev.stockmarket.common.model.SssmAPIResponse;
import com.dev.stockmarket.common.model.ResultCode;


@ControllerAdvice
public class DefaultExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    private HttpHeaders generateContentTypeHeaders(HttpServletRequest req) {

        HttpHeaders responseHeaders = new HttpHeaders();

        log.debug("content-type : {}", req.getContentType());
        if (StringUtils.containsIgnoreCase(req.getContentType(), "multipart/form-data")) {
            responseHeaders.add("Content-Type", MediaType.TEXT_PLAIN_VALUE);
        } else {
            responseHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        }

        return responseHeaders;

    }

    @ExceptionHandler (SssmRestException.class)
    public ResponseEntity<SssmAPIResponse<String>> handleRestException(SssmRestException ex, HttpServletRequest req) throws IOException {

        log.error("RestException error : code [{}] , message [{}]", ex.getErrorCode(), ex.getMessage());

        log.debug("Exception Message ", ex);

        SssmAPIResponse<String> failResponse = null;

        failResponse = new SssmAPIResponse<String>(ex.getErrorCode(), ex.getMessage());

        int httpStatusCode = HttpStatus.BAD_REQUEST.value();

        if (ex.getErrorCode() != null) {
            httpStatusCode = Integer.valueOf(String.valueOf(ex.getErrorCode().getCode()).substring(0, 3));
        }

        return new ResponseEntity<SssmAPIResponse<String>>(failResponse, generateContentTypeHeaders(req), HttpStatus.valueOf(httpStatusCode));

    }
    
    @ExceptionHandler ({TypeMismatchException.class})
    public ResponseEntity<SssmAPIResponse<String>> handleMismatchException(TypeMismatchException ex, HttpServletRequest req) throws IOException {

        log.error("RestException error : code [{}] , message [{}]", ex.getErrorCode(), ex.getMessage());

        log.debug("Exception Message ", ex);

        SssmAPIResponse<String> failResponse = null;

        failResponse = new SssmAPIResponse<String>(ResultCode.BAD_REQUEST);

        if (ex.getCause() != null) {
            failResponse.setData(ex.getCause().getMessage());
        } else {
            failResponse.setData(ex.getMessage());
        }


        return new ResponseEntity<SssmAPIResponse<String>>(failResponse, generateContentTypeHeaders(req), HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler (HttpMessageNotReadableException.class)
    public ResponseEntity<SssmAPIResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest req) throws IOException {


        log.error("RestException error : message [{}]", ex.getMessage());

        log.debug("Exception Message ", ex);
        

        SssmAPIResponse<String> failResponse = null;

        failResponse = new SssmAPIResponse<String>(ResultCode.BAD_REQUEST);

        if (ex.getMostSpecificCause() != null) {
            failResponse.setData(ex.getCause().getMessage());
        } else {
            failResponse.setData(ex.getMessage());
        }


        return new ResponseEntity<SssmAPIResponse<String>>(failResponse, generateContentTypeHeaders(req), HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler (MissingServletRequestParameterException.class)
    public ResponseEntity<SssmAPIResponse<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest req) throws IOException {

        log.error("MissingServletRequestParameterException {}", ex);

        SssmAPIResponse<String> failResponse = null;

        failResponse = new SssmAPIResponse<String>(ResultCode.BAD_REQUEST);

        if (ex.getCause() != null) {
            failResponse.setData(ex.getCause().getMessage());
        } else {
            failResponse.setData(ex.getMessage());
        }

        return new ResponseEntity<SssmAPIResponse<String>>(failResponse, generateContentTypeHeaders(req), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler (Exception.class)
    public ResponseEntity<SssmAPIResponse<String>> handleException(Exception ex, HttpServletRequest req) throws IOException {

        log.error("UNKNOWN EXCEPTION", ex);

        SssmAPIResponse<String> failResponse = null;

        failResponse = new SssmAPIResponse<String>(ResultCode.INTERNAL_SERVER_ERROR);

        if (ex.getCause() != null) {
            failResponse.setData(ex.getCause().getMessage());
        } else {
            failResponse.setData(ex.getMessage());
        }

        return new ResponseEntity<SssmAPIResponse<String>>(failResponse, generateContentTypeHeaders(req), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
