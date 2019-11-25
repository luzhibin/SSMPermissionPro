package com.lzb.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 1:08
 */
@Setter@Getter
public class PageListRes {
    private Long total;
    private List<Employee> rows = new ArrayList<>();

}
