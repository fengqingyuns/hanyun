package com.hanyun.platform.pay.dao;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.DistributeLockHis;



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

public interface DistributeLockHisDao {
    /**
     * 
    * @Title: addDistributeLockHis 
    * @Description: 新增分布式锁记录 
    * @param  
    * @return int   
    * @throws
     */
    public int addDistributeLockHis(DistributeLockHis record);
    
    /**
     * 
    * @Title: updateDistributeLockHis 
    * @Description: 修改分布式锁记录 
    * @param  
    * @return int   
    * @throws
     */
    public int updateDistributeLockHis(DistributeLockHis record);

}