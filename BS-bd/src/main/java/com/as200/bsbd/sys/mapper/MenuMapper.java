package com.as200.bsbd.sys.mapper;

import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author as200
 * @since 2023-10-28
 */
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> getMenuByUserId(@Param("userId") Integer userId,
                                      @Param("pid") Integer pid);

}
