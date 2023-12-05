package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.CustomerRoom;
import com.as200.bsbd.sys.mapper.CustomerRoomMapper;
import com.as200.bsbd.sys.service.ICustomerRoomService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
@Service
public class CustomerRoomServiceImpl extends ServiceImpl<CustomerRoomMapper, CustomerRoom> implements ICustomerRoomService {


    @Override
    public Object getboRoomList() {
        // 找出end_date>now_date的房间列表,并返回
        LambdaQueryWrapper<CustomerRoom> wrapper = new LambdaQueryWrapper();
        wrapper.gt(CustomerRoom::getEndDate, java.time.LocalDate.now());
        return this.baseMapper.selectList(wrapper);
    }
}
