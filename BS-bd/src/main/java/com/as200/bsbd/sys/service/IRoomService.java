package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
public interface IRoomService extends IService<Room> {

    Map<String, String> getRoomInfo();

    void removeRoom(Integer roomId);
}
