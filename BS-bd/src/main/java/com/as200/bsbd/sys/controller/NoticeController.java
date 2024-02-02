package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Notice;
import com.as200.bsbd.sys.service.INoticeService;
import com.as200.bsbd.sys.service.impl.NoticeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2024-02-02
 */
@RestController
@RequestMapping("/notice")
@CrossOrigin
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @ApiOperation("分页获取所有的公告")
    @GetMapping
    public Result<?> getAllNotice(@RequestParam(value = "pageNo", required = true)Integer pageNo,
                                  @RequestParam(value = "pageSize", required = true)Integer pageSize) {

        Page<Notice> page = new Page<>(pageNo, pageSize);
        noticeService.page(page);
        Map<String, Object> data = new HashMap<>();
        data.put("noticeInfo",page.getRecords());
        data.put("total", page.getTotal());
        data.put("current", page.getCurrent());
        return Result.success(data, "查询成功");
    }

    @ApiOperation("首页获取通知")
    @GetMapping("/dashboard")
    public Result<?> getNoticeForDashboard() {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        // 查询没有隐藏的公告
        wrapper.eq(Notice::getHidden, "0");
        return Result.success(noticeService.list(wrapper));
    }


    @ApiOperation("根据ID查询公告")
    @GetMapping("/{noticeId}")
    public Result<?> getNoticeById(@PathVariable(value = "noticeId") Integer noticeId) {
        return Result.success(noticeService.getById(noticeId));
    }

    @ApiOperation("删除公告")
    @DeleteMapping("/{noticeId}")
    public Result<?> deletedNoticeById(@PathVariable(value = "noticeId") Integer noticeId) {
        return Result.success(noticeService.removeById(noticeId), "成功删除");
    }

    @ApiOperation("新增公告")
    @PostMapping
    public Result<?> addNotice(@RequestBody Notice notice) {
        return  Result.success(noticeService.save(notice), "发布成功");
    }

    @ApiOperation("修改公告")
    @PutMapping
    public Result<?> updateNotice(@RequestBody Notice notice) {
        return Result.success(noticeService.updateById(notice), "修改成功");
    }

}
