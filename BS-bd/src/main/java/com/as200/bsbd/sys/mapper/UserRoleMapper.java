package com.as200.bsbd.sys.mapper;

import com.as200.bsbd.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author as200
 * @since 2023-08-21
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    void removeByUserID(@Param(value = "userID") Integer userID);
}
