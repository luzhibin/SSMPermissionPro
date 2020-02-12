package com.lzb.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
public class Menu {
    private Long id;

    private String text;

    private String url;

    private Menu parent;

    private Permission permission;

    private List<Menu> children = new ArrayList<>();

}