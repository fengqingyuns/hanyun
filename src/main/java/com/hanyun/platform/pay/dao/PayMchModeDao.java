package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.vo.paymode.PayMchModeReq;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * </pre>
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface PayMchModeDao {
    
    public MchActualPayModeInfo selectOnlinePayType(MchActualPayModeInfo query);
    
    public List<MchActualPayModeInfo> selectMchAllOnlinePayType(String brandId);

    public int insertPayMchMode(PayMchModeReq payMchModeReq);
        
    public int insertByAvailstatus(PayMchModeReq payMchModeReq);

    public int updatePayMchMode(PayMchModeReq payMchModeReq);

    public int updatemchavailstatus(PayMchModeReq payMchModeReq);

    public int getCountByBrandIdAndPayType(PayMchModeReq payMchModeReq);
    
    public List<PayMchMode> getPayMchModeByBrandId(PayMchModeReq payMchModeReq);

    
}