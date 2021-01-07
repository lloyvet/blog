package com.lloyvet.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zihao Shen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    private Integer code = 200;
    private String msg = "";
    private Object data;

    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultObj ok() {
        ResultObj result = new ResultObj();
        result.setCode(200);
        result.setMsg("OK");
        return result;
    }
    public static ResultObj ok(Object data) {
        ResultObj result = new ResultObj();
        result.setCode(200);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

}
