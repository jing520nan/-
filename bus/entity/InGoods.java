package com.yeqifu.bus.entity;

import cn.hutool.core.lang.Chain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yeqifu.sys.common.SpringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author manan
 * @date 2021/5/24-18:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_ingoods")
public class InGoods implements Serializable {
    private static  final  long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    private  Date ingoodstime;

    private  String operateperson;

    private  Integer number;

    private  String remark;

    private  Integer providerid;

    private  Integer goodsid;

    private  Integer inarea;

    private String ingoodsnum;



//    供应商名称

    @TableField(exist = false)
    private String providername;

    /*
    * 商品名称
    */

    @TableField(exist = false)
    private  String goodsname;

    @TableField(exist = false)
    private  String size;


}
