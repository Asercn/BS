package com.as200.bsbd.sys.controller;

import com.alibaba.fastjson2.util.DateUtils;
import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Orderfrom;
import com.as200.bsbd.sys.service.IOrderfromService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import freemarker.template.utility.DateUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("增加交易记录")
    @PostMapping("/add")
    public Result<?> addOrder(@RequestBody Orderfrom orderfrom) {
        orderfrom.setId(null);
        orderfromService.save(orderfrom);
        return Result.success("增加成功");

    }

    @ApiOperation("按页获取订单信息")
    @GetMapping("/getorder")
    public Result<?> getOrderfrom(@RequestParam(value = "pageNo")Integer pageNo,
                                  @RequestParam(value = "pageSize")Integer pageSize,
                                  @RequestParam(value = "orderNumber", required = false)Integer orderNumber,
                                  @RequestParam(value = "date", required = false) String date) {
        LambdaQueryWrapper<Orderfrom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(orderNumber != null, Orderfrom::getOrderNumber, orderNumber);
        if (date != null) {
            LocalDate date1 = LocalDate.parse(date);
            wrapper.ge(date1 != null, Orderfrom::getDate, date1)
                    .lt(date1 != null, Orderfrom::getDate, date1.plusDays(1));
        }
        wrapper.orderByDesc(Orderfrom::getId);
        Page<Orderfrom> page = new Page<>(pageNo, pageSize);
        orderfromService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());

        return Result.success(data, "查询成功");
    }

}
