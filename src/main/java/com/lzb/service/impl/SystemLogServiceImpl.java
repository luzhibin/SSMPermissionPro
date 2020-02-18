package com.lzb.service.impl;

import com.lzb.mapper.SystemlogMapper;
import com.lzb.pojo.Systemlog;
import com.lzb.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luzhibin on 2020/2/14 22:49
 */
@Service("SystemLogService")
@Transactional
public class SystemLogServiceImpl implements SystemLogService {

    private final SystemlogMapper systemlogMapper;

    @Autowired
    public SystemLogServiceImpl(SystemlogMapper systemlogMapper) {
        this.systemlogMapper = systemlogMapper;
    }

    @Override
    public void insertSystemLog(Systemlog systemlog) {
        systemlogMapper.insert(systemlog);
    }
}
