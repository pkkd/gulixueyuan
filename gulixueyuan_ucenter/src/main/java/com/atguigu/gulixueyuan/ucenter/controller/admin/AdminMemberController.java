package com.atguigu.gulixueyuan.ucenter.controller.admin;


import com.atguigu.entity.PageResult;
import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.entity.query.QueryMember;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/admin/ucenter/member")
public class AdminMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "返回所有学员列表")
    @GetMapping
    public R list(){

        List<Member> list = memberService.list(null);
        return R.ok().data(list);
    }

    @ApiOperation(value = "根据id删除学员")
    @DeleteMapping(value = "{memberId}")
    public R deleteById(
            @ApiParam(name = "memberId", value = "学员ID", required = true)
            @PathVariable Long memberId){

        memberService.removeById(memberId);
        return R.ok();
    }

    @ApiModelProperty(value = "分页查询列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "queryMap", value = "查询条件", required = false)
            @RequestBody QueryMember searchObj){


        Page<Member> memberPage = new Page<>(page, limit);
        memberService.pageQuery(memberPage, searchObj);
        List<Member> records = memberPage.getRecords();
        long total = memberPage.getTotal();
        PageResult<Member> pageResult = new PageResult<>(total, records);

        return R.ok().data(pageResult);
    }

}

