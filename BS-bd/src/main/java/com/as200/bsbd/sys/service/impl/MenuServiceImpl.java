package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.BsBdApplication;
import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.entity.Role;
import com.as200.bsbd.sys.entity.RoleMenu;
import com.as200.bsbd.sys.mapper.MenuMapper;
import com.as200.bsbd.sys.mapper.RoleMenuMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
@Log4j2
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public void deleteMenu(Integer menuId) {
        // 因为目录有子目录，所以要先获取子目录
        List<Menu> childMenus = getChildMenus(menuId);

        // 删除子目录及其关联数据
        for (Menu childMenu : childMenus) {
            // 删除关联表数据
            LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RoleMenu::getMenuId, childMenu.getMenuId());
            roleMenuMapper.delete(wrapper);

            // 删除子目录数据
            this.baseMapper.deleteById(childMenu.getMenuId());
        }

        // 最后删除菜单本身及其关联数据
        // 删除关联表数据
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getMenuId, menuId);
        roleMenuMapper.delete(wrapper);

        // 删除菜单数据
        this.baseMapper.deleteById(menuId);
    }

    @Value("${bs-config.web-url}")
    private String url;
    @Override

    public void createMenu(Menu menu) {
//        String filePath = "F:\\Project\\BS\\BS-web\\src\\views\\";
        String filePath = url;
        if (menu.getParentId() == 0 && menu.getComponent().equals("Layout")) {
            filePath += menu.getName().toString();
        } else {
            filePath += menu.getComponent().toString();
        }
        File vueFile = new File(filePath + ".vue");
        try {
            // 创建文件夹
            vueFile.mkdirs();
            // 创建文件
            if (vueFile.createNewFile()) {
                FileWriter writer = new FileWriter(vueFile);
                writer.write(generateVueComponent(menu.getName()));
                writer.close();
//                    System.out.println("Vue文件创建成功：" +
                log.error("Vue文件创建成功：" +  vueFile.getAbsolutePath());
            } else {
//                    System.out.println("Vue文件已存在：" + vueFile.getAbsolutePath());
                log.error("Vue is exist" +  vueFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateVueComponent(String menuName) {
        // 生成Vue组件的内容
        return "<template>\n" +
                "  <div>\n" +
                "    <h1>" + menuName + "</h1>\n" +
                "    <!-- Your Vue component content here -->\n" +
                "  </div>\n" +
                "</template>\n" +
                "\n" +
                "<script>\n" +
                "export default {\n" +
                "  // Vue component logic here\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<style scoped>\n" +
                "/* Your component styles here */\n" +
                "</style>";
    }

    private List<Menu> getChildMenus(Integer menuId) {
        // 使用查询操作根据 parentId 获取所有子目录
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, menuId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Menu> getAllMenu() {
        // 一级菜单
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Menu::getParentId, 0);
        wrapper.orderByAsc(Menu::getNumber);
        List<Menu> menuList = this.list(wrapper);

        setMenuChildren(menuList);
        return menuList;
    }

    private void setMenuChildren(List<Menu> menuList){
        if (menuList != null) {
            for (Menu menu : menuList) {
                LambdaQueryWrapper<Menu> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(Menu::getParentId, menu.getMenuId());
                wrapper1.orderByAsc(Menu::getNumber);
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
