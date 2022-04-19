package com.yeqifu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.InGoods;
import com.yeqifu.bus.entity.Provider;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.service.IngoodsService;
import com.yeqifu.bus.vo.IngoodsVo;
import com.yeqifu.bus.vo.InportVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author manan
 * @date 2021/5/25-14:55
 */

@RestController
@RequestMapping("/ingoods")
public class IngoodsController {
    @Autowired
    private IngoodsService ingoodsService;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private IGoodsService goodsService;

    /**
     *添加入库信息
     * @param id
     * @param inarea
     * @param number
     * @param remark
     * @return
     */

   @RequestMapping("addIngoods")
    public ResultObj addIngoods(Integer id,  Integer inarea, Integer number, String remark){
       try{
           ingoodsService.addIngoods(id, inarea, number, remark);
           return ResultObj.INGOODS_SUCCESS;
       } catch (Exception e){
           e.printStackTrace();
           return  ResultObj.INGOODS_ERROR;
       }
   }
    /**
     * 查询商品入库
     * @param ingoodsVo
     * @return
     */
    @RequestMapping("loadAllIngoods")
    public DataGridView loadAllIngoods(IngoodsVo ingoodsVo){
        IPage<InGoods> page =new Page<>(ingoodsVo.getPage(),ingoodsVo.getLimit());
        QueryWrapper<InGoods> queryWrapper = new QueryWrapper<InGoods>();
        //对供应进行查询
        queryWrapper.eq(ingoodsVo.getProviderid()!=null&&ingoodsVo.getProviderid()!=0,"providerid",ingoodsVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(ingoodsVo.getGoodsid()!=null&&ingoodsVo.getGoodsid()!=0,"goodsid",ingoodsVo.getGoodsid());
        //对时间进行查询
        queryWrapper.eq(ingoodsVo.getStartTime()!=null,"ingoodstime",ingoodsVo.getStartTime());
        queryWrapper.eq(ingoodsVo.getEndTime()!=null,"ingoodstime",ingoodsVo.getEndTime());

        //通过进货时间进行排序
        queryWrapper.orderByDesc("ingoodstime");
        IPage<InGoods> page1 = ingoodsService.page(page,queryWrapper);
        List<InGoods> records = page1.getRecords();
        for (InGoods ingoods : records){
            Provider provider=providerService.getById(ingoods.getProviderid());
            if (provider!=null){
                ingoods.setProvidername(provider.getProvidername());

            }
            Goods goods = goodsService.getById(ingoods.getGoodsid());
            if (goods!=null){
                ingoods.setGoodsname(goods.getGoodsname());
                //shezhishangpinguige
                ingoods.setSize(goods.getSize());
            }
        }
        return  new DataGridView(page1.getTotal(),page1.getRecords());

    }

//    /**
//     * 添加入库商品
//     * @param ingoodsVo
//     * @return
//     */
//
//    @RequestMapping("addIngoods")
//    public ResultObj addIngoods(IngoodsVo ingoodsVo){
//        try {
//            //获得当前系统用户
//            User user = (User) WebUtils.getSession().getAttribute("user");
//            //设置操作人
//            ingoodsVo.setOperateperson(user.getName());
//            //设置进货时间
//            ingoodsVo.setIngoodstime(new Date());
//            ingoodsService.save(ingoodsVo);
//            return ResultObj.ADD_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultObj.ADD_ERROR;
//        }
//    }
    /**
     * 更新进货商品
     * @param ingoodsVo
     * @return
     */
    @RequestMapping("updateIngoods")
    public ResultObj updateIngoods(IngoodsVo ingoodsVo){
        try {
            ingoodsService.updateById(ingoodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }
    /**
     * 删除入库信息
     * @param  id
     * @return
     */

    @RequestMapping("deleteIngoods")
    public  ResultObj deleteIngoods(Integer id) {
        try{
            ingoodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;

        }
    }


}
