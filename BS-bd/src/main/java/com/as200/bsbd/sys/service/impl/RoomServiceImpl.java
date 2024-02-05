package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.Orderfrom;
import com.as200.bsbd.sys.entity.Room;
import com.as200.bsbd.sys.mapper.RoomMapper;
import com.as200.bsbd.sys.service.ICustomerRoomService;
import com.as200.bsbd.sys.service.IOrderfromService;
import com.as200.bsbd.sys.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Autowired
    private IOrderfromService orderfromService;
    @Autowired
    private ICustomerRoomService customerRoomService;

    @Override
    public Map<String, String> getRoomInfo() {
        return this.baseMapper.getRoomInfo();
    }

    @Override
    public void removeRoom(Integer roomId) {
        // 通过房间ID查询到开房记录中的订单号，然后再执行删除
        List<Integer> orderFormIDs = this.baseMapper.getOrderFormID(roomId);
        // 删除订单记录前要获取订单记录的ID
        for (Integer orderformID : orderFormIDs) {  // 删除相关的订单记录
            orderfromService.removeById(orderformID);
        }
        // 删除开房记录中的数据时要先删除订单记录中的相关数据
        List<Integer> customerRoomIDs = this.baseMapper.getCustomerRoomId(roomId);
        for (Integer customerRoomID : customerRoomIDs) {
            customerRoomService.removeById(customerRoomID);
        }
        // 删除房间信息前要删除开房记录中相关的房间数据
        this.baseMapper.deleteById(roomId);
    }
}
