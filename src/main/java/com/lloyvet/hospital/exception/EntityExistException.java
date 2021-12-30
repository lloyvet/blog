package com.lloyvet.hospital.exception;


/**
 * 实体类存在异常
 * @author zihao Shen
 */
public class EntityExistException extends RuntimeException{

    public EntityExistException(String entity, String field, String val) {
        super(EntityExistException.generateMessage(entity, field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return entity + "[" + field + "]: " + val + " 已存在";
    }

    public EntityExistException(String message) {
        super(message);
    }
}
