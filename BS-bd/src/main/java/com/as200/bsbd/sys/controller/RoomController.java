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
    // 查询所有房间
    // 测试方法
    @GetMapping("/all")
    public Result<List<Room>> getAllRoom(){
        List<Room> data = roomService.list();
        return Result.success(data, "查询成功");
    }
    // 查询房间
    @GetMapping("/list")
    public Result<Map<String, Object>> selectRoom(@RequestParam(value = "roomName", required = false) String roomName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(roomName), Room::getRoomName, roomName);
        Page<Room> page = new Page<>(pageNo, pageSize);
        roomService.page(page, wrapper);
        Map<String, Object> data = new HashMap();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return Result.success(data,"查询成功");
    }



}
