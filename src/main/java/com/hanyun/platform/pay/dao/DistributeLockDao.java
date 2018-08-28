package com.hanyun.platform.pay.dao;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.DistributeLock;
import com.hanyun.platform.pay.domain.DistributeLockReq;




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

public interface DistributeLockDao {
    
    /**
     * 
    * @Title: updateReceiveDistributeLock 
    * @Description: 通过条件查询并更新数据获取锁信息 
    * @param  
    * @return int   
    * @throws
     */
    public int updateReceiveDistributeLock(DistributeLockReq distributeLockReq);
    
    
    /**
     * 
    * @Title: updateFreedDistributeLock 
    * @Description: 通过条件查询并释放锁信息 
    * @param  
    * @return int   
    * @throws
     */
    public int updateFreedDistributeLock(DistributeLock record);

}