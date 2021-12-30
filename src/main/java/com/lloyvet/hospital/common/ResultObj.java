package com.lloyvet.hospital.common;

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

    public static final ResultObj IS_LOGIN =new ResultObj(200,"已登陆");
    public static final ResultObj UN_LOGIN =new ResultObj(-1,"未登陆");

    public static final ResultObj IS_LOGINUP =new ResultObj(200,"注册成功");
    public static final ResultObj UN_LOGINUP =new ResultObj(-1,"注册失败");


    public static final ResultObj DELETE_SUCCESS = new ResultObj(200,"删除成功");
    public static final ResultObj DELETE_ERROR =  new ResultObj(-1,"删除失败") ;

    public static final ResultObj MEDICAL_SUCCESS = new ResultObj(200,"就医成功");
    public static final ResultObj MEDICAL_ERROR =  new ResultObj(-1,"就医失败") ;

    public static final ResultObj APPOINT_SUCCESS = new ResultObj(200,"预约成功");
    public static final ResultObj APPOINT_ERROR =  new ResultObj(-1,"预约失败") ;

    public static final ResultObj doctor_SUCCESS = new ResultObj(200,"就医成功");
    public static final ResultObj doctor_ERROR =  new ResultObj(-1,"就医失败") ;

    public static final ResultObj ADD_SUCCESS = new ResultObj(200,"添加成功");
    public static final ResultObj ADD_ERROR =  new ResultObj(-1,"添加失败") ;

    public static final ResultObj CODE_ERROR =  new ResultObj(-1,"添加失败") ;

    public static final ResultObj ADDPERSON_ERROR = new ResultObj(-1,"添加失败,该身份证号已存在");
    public static final ResultObj UPDATEPERSON_ERROR = new ResultObj(-1,"更新失败,该身份证号已存在");

    public static final ResultObj ADDDRUGS_SUCCESS = new ResultObj(200,"添加成功");
    public static final ResultObj ADDDRUGS_ERROR =  new ResultObj(-1,"添加失败,可能以存在此类药品请前往修改") ;
    public static final ResultObj UPDATEDRUGS_ERROR = new ResultObj(-1,"更新失败,可能以存在此类药品请前往修改") ;;

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(200,"更新成功");
    public static final ResultObj UPDATE_ERROR =  new ResultObj(-1,"更新失败") ;
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(200,"分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(-1,"分配失败");
    public static final ResultObj RESET_SUCCESS = new ResultObj(200,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(-1,"重置成功");


}
