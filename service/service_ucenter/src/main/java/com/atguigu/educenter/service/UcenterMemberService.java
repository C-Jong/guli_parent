package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-25
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //会员登录
    String login(LoginVo loginVo);

    //会员注册
    void register(RegisterVo registerVo);

    //根据微信id查询用户
    UcenterMember getByOpenid(String openid);
}
