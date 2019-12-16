package com.lzb.pojo;

import lombok.ToString;

@ToString
public class Role {
    private Long rid;

    private String rnum;

    private String rname;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum == null ? null : rnum.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }
}