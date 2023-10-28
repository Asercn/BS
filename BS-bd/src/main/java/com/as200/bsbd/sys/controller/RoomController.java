package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.CustomerRoom;
import com.as200.bsbd.sys.entity.Room;
import com.as200.bsbd.sys.service.IRoleService;
import com.as200.bsbd.sys.service.IRoomService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/room")
@CrossOrigin  // 跨域
public class RoomController {
    @Autowired
    private IRoomService roomService;
    // 查询所有房间信息
    @GetMapping("/all")
    public Result<Map<String, Object>> getAllRoom(@RequestParam(value = "pageNo")Long pageNo,
                                         @RequestParam(value = "pageSize")Long pageSize){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        Page<Room> page = new Page<>(pageNo, pageSize);
        roomService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("roomData",page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data, "查询成功");
    }
    // 查询房间
    @GetMapping("/list")
    public Result<Map<String, Object>> selectRoom(@RequestParam(value = "roomName", required = false) String roomName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(roomName), Room::getRoomName, roomName);
        wrapper.orderByAsc(Room::getRoomName);
        Page<Room> page = new Page<>(pageNo, pageSize);
        roomService.page(page, wrapper);
        Map<String, Object> data = new HashMap();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return Result.success(data,"查询成功");
    }


    // 查询开出去的房间数量，没开出去的房间数量
    @GetMapping("/info")
    public Result<Map<String, Object>> RoomInfo(){
        Map<String, Object> data = new HashMap<>();
        data.put("tableData", roomService.getRoomInfo());
        return Result.success(data,"查询成功");
    }

    // 新增房间
    @PostMapping
    public Result<List<Room>> addRoom(@RequestBody Room room){
        roomService.save(room);
        return Result.success("增加成功");
    }

    // 修改房间信息
    @PutMapping
    public Result<?> updateRoom(@RequestBody Room room){
        roomService.updateById(room);
        return Result.success("修改成功");
    }

    // 根据房间ID获取房间信息
    @GetMapping("/{roomId}")
    public Result<Room> getRoomById(@PathVariable(value = "roomId") Integer roomId){
        Room room = roomService.getById(roomId);
        return Result.success(room);
    }

    // 根据房间ID删除房间
    @DeleteMapping("/{roomId}")
    public Result<?> deleteRoom(@PathVariable(value = "roomId") Integer roomId){
        roomService.removeById(roomId);
        return Result.success("删除成功");
    }


}
