package com.zhdoyu.demo.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.model.order.OrderInfo;
import com.zhdoyu.demo.vo.order.OrderInfoQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-11
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Map<String,Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);
}
