package com.zhdoyu.demo.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhdoyu.demo.model.activity.CouponInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.model.activity.CouponUse;
import com.zhdoyu.demo.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-11
 */
public interface CouponInfoService extends IService<CouponInfo> {

    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);
}
