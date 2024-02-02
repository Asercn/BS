package com.as200.bsbd.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author as200
 * @since 2023-08-23
 */
@TableName("x_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    private String roomName;

    private Integer roomPrice;


    private String roomType;

    private String roomPicture;
}
