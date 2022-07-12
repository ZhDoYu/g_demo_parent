package com.zhdoyu.demo.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhdoyu.demo.model.wechat.Menu;
import com.zhdoyu.demo.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author zdy
 * @since 2022-07-12
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findMenuOneInfo();

    List<MenuVo> findMenuInfo();

    void syncMenu();

    void removeMenu();
}
