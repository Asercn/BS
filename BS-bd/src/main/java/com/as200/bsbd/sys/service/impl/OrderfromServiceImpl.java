package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.Orderfrom;
import com.as200.bsbd.sys.mapper.OrderfromMapper;
import com.as200.bsbd.sys.service.IOrderfromService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-12-12
 */
@Service
public class OrderfromServiceImpl extends ServiceImpl<OrderfromMapper, Orderfrom> implements IOrderfromService {

    @Override
    public BigDecimal getTodayIncome() {
        // 获取当天的日期
        LocalDate today = LocalDate.now();
        LocalDateTime startTime = today.atStartOfDay();
        LocalDateTime endTime = today.atTime(23,59,59);

        // 获取当日的收入之和
        LambdaQueryWrapper<Orderfrom> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Orderfrom::getIncome);
        wrapper.between(Orderfrom::getDate, startTime,endTime);

        // 计算今日收入之和
        BigDecimal dailyIncome = this.list(wrapper).stream()
                .map(Orderfrom::getIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return dailyIncome;
    }

    @Override
    public BigDecimal getMonthIncome() {
        // 获取当天的日期
        LocalDate today = LocalDate.now();
        // 获取本月开始的时间
        LocalDateTime startOfMonth = LocalDateTime.of(today.withDayOfMonth(1), LocalTime.MIN);

        // 获取本月结束的时间
        LocalDate nextMonth = today.plusMonths(1);
        LocalDateTime endOfMonth = LocalDateTime.of(nextMonth.withDayOfMonth(1).minusDays(1), LocalTime.MAX);

        // 获取当月的收入
        LambdaQueryWrapper<Orderfrom> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Orderfrom::getIncome);
        wrapper.between(Orderfrom::getDate, startOfMonth,endOfMonth);

        // 计算当月收入之和
        BigDecimal monthIncome = this.list(wrapper).stream()
                .map(Orderfrom::getIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return monthIncome;
    }

    @Override
    public BigDecimal getYearIncome() {
        // 获取当天的日期
        LocalDate currentTime = LocalDate.now();
        LocalDateTime startYear = LocalDateTime.of(currentTime.withDayOfYear(1), java.time.LocalTime.MIN);
        LocalDateTime endYear = LocalDateTime.of(currentTime.withDayOfYear(currentTime.lengthOfYear()), java.time.LocalTime.MAX);

        // 获取当日的收入之和
        LambdaQueryWrapper<Orderfrom> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Orderfrom::getIncome);
        wrapper.between(Orderfrom::getDate, startYear,endYear);

        // 计算今日收入之和
        BigDecimal YearIncome = this.list(wrapper).stream()
                .map(Orderfrom::getIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return YearIncome;
    }
}
