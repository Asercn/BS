package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.CustomerRoom;
import com.as200.bsbd.sys.service.ICustomerRoomService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
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
@RequestMapping("/customerRoom")
@CrossOrigin
public class CustomerRoomController {

    @Autowired
    private ICustomerRoomService customerRoomService;

    // 根据房间ID查询开房的信息
    @GetMapping("/{roomId}")
    public Result<?> getCustomerRoomById(@PathVariable(value = "roomId")Integer roomId){
        LambdaQueryWrapper<CustomerRoom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(roomId != null, CustomerRoom::getRoomId, roomId);
        wrapper.orderByDesc(CustomerRoom::getEndDate);
        Map<String,Object> data = new HashMap<>();
        data.put("customerRoom",customerRoomService.list(wrapper));
        return Result.success(data,"查询成功");
    }


    @PostMapping
    public Result<?> addCustomerRoom(@RequestBody CustomerRoom customerRoom){
        // 校准时间
        customerRoom.setStartDate(customerRoom.getStartDate().plusDays(1));
        customerRoom.setEndDate(customerRoom.getEndDate().plusDays(1));
        customerRoomService.save(customerRoom);
        return Result.success("订单成功");
    }
}
