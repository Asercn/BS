package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.Customer;
import com.as200.bsbd.sys.mapper.CustomerMapper;
import com.as200.bsbd.sys.service.ICustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    CustomerRoomServiceImpl customerRoomService;

    @Override
    public Object getRoomName(String roomName, String customerName, String customerPhone, Long pageNo, Long pageSize) {
        Long offset = (pageNo-1)*pageSize;
        return this.baseMapper.getRoomNamebyCustomer(roomName,customerName, customerPhone, offset,pageSize);
    }

    @Override
    public Object getCustomerRoomTotal(String customerName, String customerPhone, String roomName) {
        return this.baseMapper.getCustomerRoomTotal(customerName, customerPhone, roomName);
    }
}
