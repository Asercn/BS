package com.as200.bsbd.sys.mapper;

import com.as200.bsbd.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author as200
 * @since 2023-08-17
 */
public interface UserMapper extends BaseMapper<User> {
//    public List<String> getRoleNameByUserId(Integer userid);
    public String getRoleNameByUserId(Integer userid);
    public List<String> getUserInfo(@Param("pageNo") Long offset,
                                    @Param("pageSize") Long pageSize,
                                    @Param("username") String userName);

    Map<String, Object> getUserInfoByID(@Param(value = "userID") Integer userID);
}
