package com.as200.bsbd.sys.mapper;

import com.as200.bsbd.sys.entity.Customer;
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
 * @since 2023-08-23
 */
public interface CustomerMapper extends BaseMapper<Customer> {


    Integer getCustomerRoomTotal(@Param("customerName") String customerName,
                                 @Param("customerPhone") String customerPhone,
                                 @Param("roomName") String roomName);

    List<Map<String ,String>> getRoomNamebyCustomer(@Param("roomName") String roomName,
                                                    @Param("customerName") String customerName,
                                                    @Param("customerPhone") String customerPhone,
                                                    @Param("offset") Long offset,
                                                    @Param("pageSize") Long pageSize);
}
