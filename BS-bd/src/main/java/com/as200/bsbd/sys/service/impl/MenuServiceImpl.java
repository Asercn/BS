package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.entity.Role;
import com.as200.bsbd.sys.mapper.MenuMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-10-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getAllMenu() {
        // 一级菜单
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Menu::getParentId, 0);
        List<Menu> menuList = this.list(wrapper);

        setMenuChildren(menuList);
        return menuList;
    }

    private void setMenuChildren(List<Menu> menuList){
        if (menuList != null) {
            for (Menu menu : menuList) {
                LambdaQueryWrapper<Menu> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(Menu::getParentId, menu.getMenuId());
                List<Menu> list1 = this.list(wrapper1);
                menu.setChildren(list1);
                // 递归
                setMenuChildren(list1);
            }
        }
    }

    @Override
    public List<Menu> getMenuListByUserId(Integer userId) {
        // 一级菜单
        List<Menu> menuList = this.baseMapper.getMenuByUserId(userId, 0);
        // 子菜单
        setMenuChildrenByUserId(userId, menuList);
        return menuList;
    }

    private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
        if (menuList != null){
            for (Menu menu: menuList){
                List<Menu> subMenuList = this.baseMapper.getMenuByUserId(userId, menu.getMenuId());
                menu.setChildren(subMenuList);
                // 递归
                setMenuChildrenByUserId(userId, subMenuList);
            }
        }
    }
}
