package com.yeqifu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Customer;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.Salesout;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.ISalesService;
import com.yeqifu.bus.service.ISalesoutService;
import com.yeqifu.bus.vo.SalesVo;
import com.yeqifu.bus.vo.SalesoutVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author manan
 * @date 2021/5/29-21:02
 */
@RestController
@RequestMapping("/salesout")
public class SalesoutController {

    @Autowired
    private ISalesoutService salesoutService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * @param  id
     * @param  number
     * @param  remark
     * @param  outarea
     * @return
     *
     */

    @RequestMapping("addOutgoods")
    public ResultObj addOutgoods(Integer id, Integer number, String remark,Integer outarea ){
        try{
            salesoutService.addOutgoods(id,number,remark,outarea);
            return ResultObj.OUTGOODS_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OUTGOODS_ERROR;
        }
    }

    /**
     * @param salesoutVo
     * @return
     */

    @RequestMapping("loadAllOutSales")
    public DataGridView loadAllOutSales(SalesoutVo salesoutVo){
        IPage<Salesout> page = new Page<Salesout>(salesoutVo.getPage(),salesoutVo.getLimit());
        QueryWrapper<Salesout> queryWrapper = new QueryWrapper<Salesout>();
        queryWrapper.eq(salesoutVo.getCustomerid()!=null&&salesoutVo.getCustomerid()!=0,"customerid",salesoutVo.getCustomerid());
        queryWrapper.eq(salesoutVo.getGoodsid()!=null&&salesoutVo.getGoodsid()!=0,"goodsid",salesoutVo.getGoodsid());
        queryWrapper.ge(salesoutVo.getStartTime()!=null,"salesouttime",salesoutVo.getStartTime());
        queryWrapper.le(salesoutVo.getEndTime()!=null,"salesouttime",salesoutVo.getEndTime());


        queryWrapper.orderByDesc("salesouttime");
        IPage<Salesout> page1 = salesoutService.page(page,queryWrapper);
        List<Salesout> records = page1.getRecords();
        for (Salesout salesout : records){
            System.out.println("============================");
            Customer customer = customerService.getById(salesout.getCustomerid());
            if (customer!=null){
                salesout.setCustomername(customer.getCustomername());
            }
            Goods goods = goodsService.getById(salesout.getGoodsid());
            if (goods!=null){
                salesout.setGoodsname(goods.getGoodsname());
                salesout.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 删除商品出库信息
     * @param id
     * @return
     */
    @RequestMapping("deleteSalesout")
    public ResultObj deleteSalesout(Integer id){
        try {
            salesoutService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 更新商品销售信息
     * @param salesoutVo
     * @return
     */
    @RequestMapping("updateSalesout")
    public ResultObj updateSales(SalesoutVo salesoutVo){
        try {
            salesoutService.updateById(salesoutVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }
}
