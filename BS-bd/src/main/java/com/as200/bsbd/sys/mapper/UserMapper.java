package com.as200.bsbd.sys.mapper;

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
 * @since 2023-08-17
 */
public interface UserMapper extends BaseMapper<User> {
    public List<String> getRoleNameByUserId(Integer userid);
    public List<String> getUserInfoOrByUserID(@Param("userID") Integer userID,
                                      @Param("pageNo") Long offset,
                                      @Param("pageSize") Long pageSize);
}
