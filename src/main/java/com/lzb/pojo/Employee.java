package com.lzb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter@ToString
public class Employee {
    private Long id;

    private String username;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  //将数据库时间戳取出来转成JSON后做一个日期格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd") //表单提交
    private Date inputtime;

    private String tel;

    private String email;

    private Boolean state;

    private Boolean admin;

    private Department department;

    private List<Role> roles = new ArrayList<>();
}