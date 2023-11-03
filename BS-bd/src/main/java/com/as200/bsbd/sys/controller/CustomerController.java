package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Customer;
import com.as200.bsbd.sys.entity.CustomerRoom;
import com.as200.bsbd.sys.entity.Room;
import com.as200.bsbd.sys.service.ICustomerRoomService;
import com.as200.bsbd.sys.service.ICustomerService;
import com.as200.bsbd.sys.service.IRoomService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private ICustomerService customerService;


    @GetMapping
    @ApiOperation("获取所有用户信息")
    public Result<?> getCustomer(){
        List<Customer> customer = customerService.list();
        return Result.success(customer, "查询成功");
    }
    @GetMapping("/list")
    @ApiOperation("分页获取用户信息，可以根据用户姓名，手机号，房间号进行查询")
    public Result<Map<String, Object>> custormerRoomList(@RequestParam(value = "pageNo") Long pageNo,
                                                         @RequestParam(value = "pageSize") Long pageSize,
                                                         @RequestParam(value = "customerName", required = false)String customerName,
                                                         @RequestParam(value = "customerPhone", required = false)String customerPhone,
                                                         @RequestParam(value = "roomName", required = false) String roomName){
        Map<String, Object> data = new HashMap<>();
        data.put("customerInfo", customerService.getRoomName(roomName,customerName,customerPhone,pageNo,pageSize));//根据房间查信息
        data.put("total", customerService.getCustomerRoomTotal(customerName, customerPhone, roomName));  // 获取行数
        return Result.success(data, "查询成功");
    }

    @PostMapping
    @ApiOperation("添加用户")
    public Result<?> addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        Map<String, Object> data = new HashMap<>();
        data.put("customer", customerService.getById(customer.getId()));
        return Result.success(data, "客户信息添加成功");
    }
}
