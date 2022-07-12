package com.zhdoyu.demo.user.service.impl;


import com.zhdoyu.demo.user.mapper.UserInfoMapper;
import com.zhdoyu.demo.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhdoyu.demo.model.user.UserInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-07-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
