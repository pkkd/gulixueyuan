package com.atguigu.gulixueyuan.ucenter.controller;


import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学员表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2018-12-17
 */
@Api(description = "学员管理")
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "注册学员",
                    notes = "updateTime, createTime 无需添加。这是前台系统用户的注册功能，前提是用户已经接收了手机验证码"
    )
    @PostMapping
    public R register(
            @ApiParam(name = "member", value = "学员对象，这是json", required = true)
            @RequestBody Member member){

        memberService.save(member);

        return R.ok();
    }
}

