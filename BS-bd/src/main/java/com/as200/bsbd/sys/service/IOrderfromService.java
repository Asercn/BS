package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.Orderfrom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-12-12
 */
public interface IOrderfromService extends IService<Orderfrom> {
    BigDecimal getTodayIncome();

    BigDecimal getMonthIncome();

    BigDecimal getYearIncome();
}
