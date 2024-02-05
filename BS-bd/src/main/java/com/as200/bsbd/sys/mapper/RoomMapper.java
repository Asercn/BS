package com.as200.bsbd.sys.mapper;

import com.as200.bsbd.sys.entity.Room;
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
public interface RoomMapper extends BaseMapper<Room> {

    Map<String,String> getRoomInfo();

    List<Integer> getOrderFormID(@Param(value = "roomId") Integer roomId);

    List<Integer> getCustomerRoomId(@Param(value = "roomId") Integer roomId);
}
