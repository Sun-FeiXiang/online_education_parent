package com.sunfx.servicebase.execptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Feixiang Sun
 * @date 2021/8/18 15:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class EduException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
