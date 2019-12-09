package com.lzb.service.impl;

import com.lzb.mapper.DepartmentMapper;
import com.lzb.pojo.Department;
import com.lzb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/8 15:54
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> getDepartmentList(){
        return departmentMapper.selectAll();
    }
}
