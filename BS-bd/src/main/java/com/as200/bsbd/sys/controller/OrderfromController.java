package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Orderfrom;
import com.as200.bsbd.sys.service.IOrderfromService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2023-12-12
 */
@RestController
@RequestMapping("/orderfrom")
@CrossOrigin
public class OrderfromController {
    @Autowired
    private IOrderfromService orderfromService;

    @ApiOperation("获取所有的订单信息")
    @GetMapping("/all")
    public Result<?> getAllOrderform() {
        List<Orderfrom> data = orderfromService.list();
        return Result.success(data,"查询成功");
    }

//    @PostMapping("/add")
//    public Result<?> addOrder() {
//
//    }

}
