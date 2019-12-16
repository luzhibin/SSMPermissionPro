package com.lzb.pojo;

import lombok.ToString;

@ToString
public class Permission {
    private Long pid;

    private String pname;

    private String presource;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPresource() {
        return presource;
    }

    public void setPresource(String presource) {
        this.presource = presource == null ? null : presource.trim();
    }
}