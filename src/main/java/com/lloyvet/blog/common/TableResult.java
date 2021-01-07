package com.lloyvet.blog.common;

import lombok.Data;

/**
 * @author zihao Shen
 */
@Data
public class TableResult extends ResultObj {

    private Integer count;

    public static TableResult tableOk(Object data, Integer count) {
        TableResult result = new TableResult();
        result.setCode(0);
        result.setMsg("ok");
        result.setCount(count);
        result.setData(data);
        return result;
    }
}
