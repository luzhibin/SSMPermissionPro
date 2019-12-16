package com.lzb.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 1:08
 */
//返回给前端一个列表结果页
@Setter@Getter
public class PageListRes {
    private Long total;     //总数
    private List<?> rows = new ArrayList<>();

}
