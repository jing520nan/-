package com.yeqifu.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.CORBA.IDLType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author manan
 * @date 2021/4/20-20:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_salesout")

public class Salesout implements  Serializable {

        private static final long serialVersionUID=1L;

        @TableId(value = "id",type = IdType.AUTO)
        private  Integer id;

        private String operateperson;

        private Integer number;

        private Date salesouttime;

        private String remark;

        private Integer outarea;

        private double outprice;

        private String salesoutnum;

        private Integer customerid;

        private Integer goodsid;




        /**
         * 客户姓名
         */
        @TableField(exist = false)
        private String customername;

        /**
         * 商品名称
         */
        @TableField(exist = false)
        private String goodsname;

        /**
         * 商品规格
         */
        @TableField(exist = false)
        private String size;



}
