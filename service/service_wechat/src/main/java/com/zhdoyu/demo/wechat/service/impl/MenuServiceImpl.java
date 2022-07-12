package com.zhdoyu.demo.wechat.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhdoyu.demo.exception.GgktException;
import com.zhdoyu.demo.model.wechat.Menu;
import com.zhdoyu.demo.vo.wechat.MenuVo;
import com.zhdoyu.demo.wechat.mapper.MenuMapper;
import com.zhdoyu.demo.wechat.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-07-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<Menu> findMenuOneInfo() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<Menu> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> finalMenuList = new ArrayList<>();
        List<Menu> menuList= baseMapper.selectList(null);
        List<Menu> oneMenuList = menuList.stream().filter(menu -> menu.getParentId().longValue() == 0).collect(Collectors.toList());
        for(Menu oneMenu : oneMenuList) {
            MenuVo oneMenuVo = new MenuVo();
            BeanUtils.copyProperties(oneMenu, oneMenuVo);

            List<Menu> twoMenuList = menuList.stream()
                    .filter(menu -> menu.getParentId().longValue() == oneMenu.getId())
                    .collect(Collectors.toList());
            List<MenuVo> children = new ArrayList<>();
            for (Menu twoMenu : twoMenuList) {
                MenuVo twoMenuVo = new MenuVo();
                BeanUtils.copyProperties(twoMenu, twoMenuVo);
                children.add(twoMenuVo);
            }
            oneMenuVo.setChildren(children);
            finalMenuList.add(oneMenuVo);
        }
        return finalMenuList;
    }

    //同步公众号菜单
    @Override
    public void syncMenu() {
        //获取所有菜单数据
        List<MenuVo> menuVoList = this.findMenuInfo();
        //封装button里面结构，数组格式
        JSONArray buttonList = new JSONArray();
        for (MenuVo oneMenuVo:menuVoList) {
            //json对象  一级菜单
            JSONObject one = new JSONObject();
            one.put("name",oneMenuVo.getName());
            //json数组   二级菜单
            JSONArray subButton = new JSONArray();
            for (MenuVo twoMenuVo:oneMenuVo.getChildren()) {
                JSONObject view = new JSONObject();
                view.put("type", twoMenuVo.getType());
                if(twoMenuVo.getType().equals("view")) {
                    view.put("name", twoMenuVo.getName());
                    view.put("url", "http://gdemo.free.idcfengye.com/#"
                            +twoMenuVo.getUrl());
                } else {
                    view.put("name", twoMenuVo.getName());
                    view.put("key", twoMenuVo.getMeunKey());
                }
                subButton.add(view);
            }
            one.put("sub_button",subButton);
            buttonList.add(one);
        }
        //封装最外层button部分
        JSONObject button = new JSONObject();
        button.put("button",buttonList);

        try {
            String menuId =
                    this.wxMpService.getMenuService().menuCreate(button.toJSONString());
            System.out.println("menuId"+menuId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new GgktException(20001,"公众号菜单同步失败");
        }
    }

    @SneakyThrows
    @Override
    public void removeMenu() {
        wxMpService.getMenuService().menuDelete();
    }
}
