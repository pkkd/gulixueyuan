package com.atguigu.gulixueyuan.ucenter.service.impl;

import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.entity.query.QueryMember;
import com.atguigu.gulixueyuan.ucenter.mapper.MemberMapper;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 学员表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2018-12-17
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Override
    public void pageQuery(Page<Member> memberPage, QueryMember searchObj) {

        //获取查询条件
        String keyWord = searchObj.getKeyWord();
        Boolean isAvailable = searchObj.getIsAvailable();
        Date begin = searchObj.getBegin();
        Date end = searchObj.getEnd();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        //邮箱/手机/昵称/姓名
        if(!StringUtils.isEmpty(keyWord)){
            /*queryWrapper.like("EMAIL", keyWord)
                    .or()
                    .like("MOBILE", keyWord)
                    .or()
                    .like("USER_NAME", keyWord)
                    .or()
                    .like("SHOW_NAME", keyWord);*/

            queryWrapper.and(i -> i.like("EMAIL", keyWord)
                                    .or()
                                    .like("MOBILE", keyWord)
                                    .or()
                                    .like("USER_NAME", keyWord)
                                    .or()
                                    .like("SHOW_NAME", keyWord));
        }

        baseMapper.selectPage(memberPage, queryWrapper);

    }
}
