package org.learnj.spring.boot.web.rest.exception;

import org.learnj.spring.boot.web.rest.error.CardError;

/**
 * @author Loster on 5/7/17.
 */
public class EntityNotExistException extends ValidationException {

    public EntityNotExistException(String entityName) {
        super(CardError.ENTITY_NOT_EXIST.getCode(), "实体" + entityName + "不存在");
    }
}
