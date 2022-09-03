package com.sunfx.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Feixiang Sun
 * @date 2021/9/27 16:09
 */

@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}

