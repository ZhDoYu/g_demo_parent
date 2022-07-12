package com.zhdoyu.demo.order.service.impl;

import com.zhdoyu.demo.model.order.OrderDetail;
import com.zhdoyu.demo.order.mapper.OrderDetailMapper;
import com.zhdoyu.demo.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-07-11
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
