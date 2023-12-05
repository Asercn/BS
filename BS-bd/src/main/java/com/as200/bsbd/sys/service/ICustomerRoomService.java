package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.CustomerRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
public interface ICustomerRoomService extends IService<CustomerRoom> {


    Object getboRoomList();
}
