package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.error.CardError;

/**
 * @author Loster on 5/7/17.
 */
public class MissingParameterException extends InvalidParameterException {

    public MissingParameterException(String parameterName) {
        super(CardError.MISSING_PARAMETER.getCode(), "参数" + parameterName + "不能为空");
    }
}
