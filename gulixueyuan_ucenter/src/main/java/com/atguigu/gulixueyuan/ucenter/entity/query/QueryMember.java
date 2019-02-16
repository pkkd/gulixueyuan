package com.atguigu.gulixueyuan.ucenter.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author helen
 * @since 2018/12/18
 */
@Data
@ApiModel(value ="Member查询对象")
public class QueryMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮箱/手机/昵称/姓名")
    private String keyWord;

    @ApiModelProperty(value = "是否可用 1正常  0冻结")
    private Boolean isAvailable;

    @ApiModelProperty(value = "注册开始时间", example = "2018-01-01 10:10:10")
    private Date begin;

    @ApiModelProperty(value = "注册结束时间", example = "2018-01-01 10:10:10")
    private Date end;

}
