package com.lzb.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by luzhibin on 2019/12/11 17:03
 */
@Getter
@Setter
@ToString
public class QueryVo {
    private int page;
    private int rows;
    private String keyword;
}
