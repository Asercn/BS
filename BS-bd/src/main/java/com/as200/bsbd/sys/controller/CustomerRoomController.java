package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.CustomerRoom;
import com.as200.bsbd.sys.service.ICustomerRoomService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
 * @since 2023-08-23
 */
@RestController
@RequestMapping("/customerRoom")
@CrossOrigin
public class CustomerRoomController {

    @Autowired
    private ICustomerRoomService customerRoomService;

    // 根据房间ID查询开房的信息
    @ApiOperation("根据房间ID查询最新的开房的信息")
    @GetMapping("/{roomId}")
    public Result<?> getCustomerRoomByRoomId(@PathVariable(value = "roomId")Integer roomId){
        LambdaQueryWrapper<CustomerRoom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(roomId != null, CustomerRoom::getRoomId, roomId);
        wrapper.orderByDesc(CustomerRoom::getEndDate);
        List<CustomerRoom> list = customerRoomService.list(wrapper);
        CustomerRoom data = list.isEmpty() ? null : list.get(0);
        return Result.success(data,"查询成功");
    }

    @GetMapping("/boroomList")
    @ApiOperation("获取到已经开的房间的列表,返回房间的ID信息")
    public Result<?> getBoRoomList(){
        Map<String, Object> data = new HashMap();
        data.put("boroomList", customerRoomService.getboRoomList());
        return Result.success(data,"查询成功");
    }


    @ApiOperation("增加开房记录订单")
    @PostMapping
    public Result<?> addCustomerRoom(@RequestBody CustomerRoom customerRoom){
        // 纠正一些错误
        customerRoom.setId(null);
        // 校准时间
        customerRoom.setStartDate(customerRoom.getStartDate().plusDays(1));
        customerRoom.setEndDate(customerRoom.getEndDate().plusDays(1));
        customerRoomService.save(customerRoom);
        return Result.success("订单成功");
    }


    @PutMapping
    @ApiOperation("根据开房的订单号来退房")
    public Result<?> outRoom(@RequestBody CustomerRoom customerRoom){
        LocalDate currentDate = LocalDate.now();
        if (customerRoom.getStartDate() != null && customerRoom.getStartDate().compareTo(currentDate) > 0) {
            customerRoom.setStartDate(currentDate); // 补丁2
        } else {
            customerRoom.setStartDate(null); // 补丁1
        }
        customerRoom.setEndDate(currentDate);
        customerRoomService.updateById(customerRoom);
        return Result.success("退房成功");
    }
}
