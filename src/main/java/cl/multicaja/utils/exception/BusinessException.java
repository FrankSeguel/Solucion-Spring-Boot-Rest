/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.multicaja.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author fseguel
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
    
    public BusinessException(String message) {
        super(message);
        logger.debug(message);
    }

    public BusinessException(String message, Throwable t) {
        super(message, t);
        logger.debug(message,t);
    }
}
