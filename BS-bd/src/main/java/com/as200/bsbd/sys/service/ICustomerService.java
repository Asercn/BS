package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
public interface ICustomerService extends IService<Customer> {

    Integer getTotal();

    Object getRoomName(String roomName, String customerName, String customerPhone, Long pageNo, Long pageSize);
}
