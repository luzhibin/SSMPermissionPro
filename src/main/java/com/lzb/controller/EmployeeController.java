package com.lzb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzb.pojo.*;
import com.lzb.service.EmployeeService;
import com.lzb.service.RoleService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by luzhibin on 2019/11/9 15:09
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }
    private final RoleService roleService;

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(QueryVo vo){
        /*调用业务层查询员工*/
        PageListRes pageListRes = employeeService.getEmployee(vo);
        return pageListRes;
    }

    /**
     *     /*当我们在控制器方法写了 @RequiresPermissions,Shiro在访问时,
     *     就会判断有没有该权限,如果没有,就不会执行对应方法*/
    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee(){
        return "employee";
    }

    /*employe.jsp的下拉列表*/
    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList(){
        return roleService.roleList();
    }

    /*添加员工操作*/
    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee){
        System.out.println("----------------------------------------------");
        System.out.println(employee);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.saveEmployee(employee);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }

    /*接收更新员工请求*/
    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
        public AjaxRes updateEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            ajaxRes.setMsg("更新信息成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新信息失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*接收离职(删除)操作请求*/
    @RequestMapping("/updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateState(id);
            ajaxRes.setMsg("更新信息成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新信息失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*捕获无权限的异常，跳转到无权限的页面*/
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws Exception {  /*handlerMethod:发生异常的方法*/
        /*判断当前的请求是不是JSON请求(通过ResponseBody来判断是否是ajax请求 json数据)
        如果是，则返回json给客户端 让浏览器做跳转*/
        /*获取方法上的注解*/
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null){
            /*有responseBody注解，说明是Ajax请求*/
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("无权限操作");
            /*转成json字符串*/
            String value = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(value);
        }else{
            //重定向
            response.sendRedirect("nopermission.jsp");
        }
    }

    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse httpServletResponse) {
        System.out.println("开始了Excel的下载。。");
        /*1.从数据库中取列表数据*/
        QueryVo queryVo = new QueryVo();
        queryVo.setPage(1);
        queryVo.setRows(1000);
        PageListRes pageListRes = employeeService.getEmployee(queryVo);
        List<Employee> employees = (List<Employee>) pageListRes.getRows();
        /*2.创建Excel，写到Excel中*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("员工数据");
        //创建一行，角标从0开始
        HSSFRow row = sheet.createRow(0);
        //设置第0行对应的列
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("用户名");
        row.createCell(2).setCellValue("入职日期");
        row.createCell(3).setCellValue("联系电话");
        row.createCell(4).setCellValue("电子邮箱");

        HSSFRow employeeRow = null;
        //遍历所有员工设置数据表格
        for (int i=0;i<employees.size();i++){
            Employee employee = employees.get(i);
            employeeRow = sheet.createRow(i + 1);
            employeeRow.createCell(0).setCellValue(employee.getId());
            employeeRow.createCell(1).setCellValue(employee.getUsername());
            if (employee.getInputtime()!=null){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(employee.getInputtime());
                employeeRow.createCell(2).setCellValue(format);
            }else{
                employeeRow.createCell(2).setCellValue("");
            }
            employeeRow.createCell(3).setCellValue(employee.getTel());
            employeeRow.createCell(4).setCellValue(employee.getEmail());
        }
        /*3.响应给浏览器*/
        try {
            String fileName = new String("员工数据.xls".getBytes(StandardCharsets.UTF_8),"iso8859-1");
            httpServletResponse.setHeader("content-Disposition","attachment;filename="+fileName);
            workbook.write(httpServletResponse.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*下载模板*/
    @RequestMapping("/downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        FileInputStream fileInputStream = null;
        try {
            String fileName = new String("ExcelTpl.xls".getBytes(StandardCharsets.UTF_8),"iso8859-1");
            httpServletResponse.setHeader("content-Disposition","attachment;filename="+fileName);
            //获取文件路径
            String realPath = httpServletRequest.getSession().getServletContext().getRealPath("static/ExcelTml.xls");
            fileInputStream = new FileInputStream(realPath);
            IOUtils.copy(fileInputStream,httpServletResponse.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*上传Excel处理*/
    /*在MVC配置文件中配置文件上传解析器*/
    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            ajaxRes.setMsg("上传成功");
            ajaxRes.setSuccess(true);
            /*通过上传的文件流创建工作簿*/
            HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
            //通过角标获取sheet
            HSSFSheet sheet = workbook.getSheetAt(0);
            /*获取最大行号*/
            int lastRowNum = sheet.getLastRowNum();
            System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGG-------"+lastRowNum);
            /*遍历所有行，要从第一行开始遍历*/
            Row employeeRow = null;

            for (int i=1;i<=lastRowNum;i++){
                Employee employee = new Employee();
                //根据角标获取这一行
                employeeRow = sheet.getRow(i);
                //获取每一列
                String username = this.getCellValue(employeeRow.getCell(0)).toString();
                employee.setUsername(username);
                employee.setInputtime((Date) this.getCellValue(employeeRow.getCell(1)));
                employee.setTel(this.getCellValue(employeeRow.getCell(2)).toString());
                employee.setEmail((String) this.getCellValue(employeeRow.getCell(3)));
                employee.setState(true);
                employee.setAdmin(false);
                employee.setPassword(this.getCellValue(employeeRow.getCell(4)).toString());
                employeeService.saveEmployee(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxRes.setMsg("上传失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    private Object getCellValue(Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }

}
