package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2023-10-28
 */
@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    @Autowired
    private IMenuService iMenuService;


    @ApiOperation("查询所有列表")
    @GetMapping
    public Result<List<Menu>> getAllMenu(){
        List<Menu> menuList = iMenuService.getAllMenu();
        return Result.success(menuList, "查询成功");
    }
}
