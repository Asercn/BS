package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.Room;
import com.as200.bsbd.sys.mapper.RoomMapper;
import com.as200.bsbd.sys.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Map<String, String> getRoomInfo() {
        return this.baseMapper.getRoomInfo();
    }
}
