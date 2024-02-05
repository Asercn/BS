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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
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

    @ApiOperation("查询所有房间信息")
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

    @ApiOperation("用户查询的接口")
    @GetMapping("/user")
    public Result<?> userGetRoom(@RequestParam(value = "pageNo")Long pageNo,
                                 @RequestParam(value = "pageSize")Long pageSize,
                                 @RequestParam(value = "roomType", required = false)String roomType,
                                 @RequestParam(value = "roomPrice", required = false)Integer roomPrice) {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        Page<Room> page = new Page<>(pageNo,pageSize);
        wrapper.like(StringUtils.hasLength(roomType), Room::getRoomType, roomType);

        if (roomPrice != null && roomPrice != 0) {
            wrapper.le(Room::getRoomPrice,roomPrice);
        }
        roomService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("roomData", page.getRecords());
        data.put("total", page.getTotal());
        data.put("current",page.getCurrent());
        return Result.success(data, "查询成功");
    }

    @ApiOperation("根据三个选项查询开房记录")
    @GetMapping("/list")
    public Result<Map<String, Object>> selectRoom(@RequestParam(value = "roomName", required = false) String roomName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(roomName), Room::getRoomName, roomName);
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
    public Result<Map<String, String>> RoomInfo(){
        Map<String, String> data = roomService.getRoomInfo();
        return Result.success(data,"查询成功");
    }

    // 新增房间
    @ApiOperation("新增房间")
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
        roomService.removeRoom(roomId);
        return Result.success("删除成功");
    }

    @Value("${bs-config.path}")
    private String Path;

    @ApiOperation("上传图片")
    @PostMapping("/upImage")
    public Result<?> upImage(@RequestParam(value = "file", required = false)MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()){
            return Result.fail("文件上传失败");
        }
        // 获取传过来的文件名字
        String Originalfilename = file.getOriginalFilename();
        // 防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String filename = System.currentTimeMillis()+"."+Originalfilename.substring(Originalfilename.lastIndexOf(".")+1);
        // 设置保存地址
        String path = Path;
        File dest = new File(path+filename);
        // 判断文件夹是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {

            // 上传文件
            file.transferTo(dest);
            return Result.success(filename, "文件"+ filename +"上传成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
