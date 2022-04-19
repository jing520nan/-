package com.yeqifu.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.CORBA.IDLType;

import java.io.Serializable;
import java.util.Date;
/**
 * @author manan
 * @date 2021/4/20-20:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("bus_salesoutH")
@TableName("bus_salesoutMES")
public class SalesoutMES implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "saleMESid",type = IdType.AUTO)
    private  Integer saleMESid;

    private  Integer saleHid;

    private  Integer goodsid;

    private  Integer number;

    private  double saleoutprice;

    private  String paytype;

    private  String remark;

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
