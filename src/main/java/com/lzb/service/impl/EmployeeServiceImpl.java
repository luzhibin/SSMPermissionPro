package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.EmployeeMapper;
import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.pojo.Role;
import com.lzb.service.EmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 0:26
 */
@Service("employeeService")
@Transactional  //事务
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public PageListRes getEmployee(QueryVo vo) {
        /*分页查询,后面做高级查询时把值传进来*/
        Page<Employee> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Employee> employees = employeeMapper.selectAll(vo);
        /*封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    @Override   //添加员工
    public void saveEmployee(Employee employee){
        String password = employee.getPassword();
        String username = employee.getUsername();
        /*对密码进行MD5加密，使用用户名作为盐值，散列2次*/
        Md5Hash md5Hash = new Md5Hash(password, username, 2);
        employee.setPassword(md5Hash.toString());
        /*保存员工*/
        employeeMapper.insert(employee);
        /*保存员工与角色的关系*/
        for (Role role:employee.getRoles()){
/*            System.out.println("==================================================");
            System.out.println(role);
            System.out.println(employee.getRoles());*/
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(),role.getRid());
        }
    }

    /**
     * 更新员工
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee){
        /*打破员工与角色的关系*/
        employeeMapper.deleteRoleRel(employee.getId());
        /*更新员工*/
        employeeMapper.updateByPrimaryKey(employee);
        /*重新建立员工与角色的关系*/
        for (Role role:employee.getRoles()){
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(),role.getRid());
        }
    }

    @Override //修改员工离职状态
    public void updateState(Long id){
        employeeMapper.updateState(id);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeMapper.getEmployeeByUsername(username);

    }

    /*根据用户的id查询角色表的rnum*/
    @Override
    public List<String> getRolesById(Long id) {
        return employeeMapper.getRolesById(id);
    }

    /*根据用户的id查询权限  资源的名称*/
    @Override
    public List<String> getPermissionById(Long id) {
        return employeeMapper.getPermissionById(id);
    }
}
