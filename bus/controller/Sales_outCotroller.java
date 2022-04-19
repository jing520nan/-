package com.yeqifu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Customer;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.Salesback;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.ISalesbackService;
import com.yeqifu.bus.vo.SalesbackVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * @author manan
 * @date 2021/4/20-17:29
 */
//@RestController
//@RequestMapping("/sales_out")
//public class Sales_outCotroller {
//    @Autowired
//    private ISales_outService sales_outService;
//
//    @Autowired
//    private ICustomerService customerService;
//
//    @Autowired
//    private IGoodsService goodsService;
//
//}
