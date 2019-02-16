package com.atguigu.gulixueyuan.ucenter.service;

import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.entity.query.QueryMember;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学员表 服务类
 * </p>
 *
 * @author Helen
 * @since 2018-12-17
 */
public interface MemberService extends IService<Member> {

    void pageQuery(Page<Member> memberPage, QueryMember searchObj);
}
