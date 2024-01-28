package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.mapper.MenuMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IMenuService menuService;
    @Autowired
    private MenuMapper menuMapper;

    @ApiOperation("查询所有列表")
    @GetMapping
    public Result<List<Menu>> getAllMenu(){
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList, "查询成功");
    }

    @ApiOperation("新增菜单")
    @PostMapping
    public Result<?> addMenu(@RequestBody Menu menu) {
        // 获取父级菜单
        Menu parentMenu = getParentMenu(menu.getParentId());
        if (menu.getParentId().equals(0)) {
            // 如果父级为0，则设置Component为Layout，Path为/拼接上name
            menu.setComponent("Layout");
            menu.setPath('/' + menu.getName());

        } else if(parentMenu != null) {
            // 如果父级不为0，且有父级则根据父级的路径拼接上文件名
            menu.setComponent(parentMenu.getName() + '/' + menu.getName());
            menu.setPath(menu.getName());
        } else {
            return Result.fail("父级菜单不存在");
        }
        Map<String, String> data = new HashMap<>();
        String filePath = menu.getComponent() + ".vue";
        data.put("filePath", filePath);
        menuService.save(menu);
        return Result.success(data,"新增成功");
    }

    private Menu getParentMenu(Integer parentId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getMenuId, parentId);
        wrapper.select(Menu::getParentId, Menu::getComponent, Menu::getPath, Menu::getName);
        return menuMapper.selectOne(wrapper);
    }
}
