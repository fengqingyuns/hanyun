package com.hanyun.platform.pay.service.impl;

import java.util.Date;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.dao.DistributeLockDao;
import com.hanyun.platform.pay.dao.DistributeLockHisDao;
import com.hanyun.platform.pay.domain.DistributeLock;
import com.hanyun.platform.pay.domain.DistributeLockHis;
import com.hanyun.platform.pay.domain.DistributeLockReq;
import com.hanyun.platform.pay.service.DistributeLockService;

/**
 * @author wangjie@hanyun.com
 * @date 2016年9月6日 上午11:46:37
 */
@Service("DistributeLockService")
public class DistributeLockServiceImpl implements DistributeLockService {
	private static Logger LOGGER=LoggerFactory.getLogger(DistributeLockServiceImpl.class) ;

	@Resource
	private DistributeLockDao distributeLockDao;
	@Resource
	private DistributeLockHisDao distributeLockHisDao;

	/**
	 * 
	 * Title: receiveDistributeLock 
	 * Description:获取锁
	 * @param dl
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean receiveDistributeLock(String businesskey, String operator, String ip, int effectiveTime) {
		LOGGER.info("请求数据 businesskey:{},operator:{},ip:{},effectiveTime:{}",businesskey,operator,ip,effectiveTime);
		Date effectiveTime1 = DateCalcUtil.addHours(new Date(),-effectiveTime);		

		DistributeLockReq distributeLockReq = new DistributeLockReq();
		distributeLockReq.setBusinessKey(businesskey);
		distributeLockReq.setOperator(operator);
		distributeLockReq.setOperatorIp(ip);
		distributeLockReq.setStatus(1);
		distributeLockReq.setEffectiveTime(effectiveTime1);
		
		int count = distributeLockDao.updateReceiveDistributeLock(distributeLockReq);
		if (count > 0) {
			DistributeLockHis distributeLockHis = new DistributeLockHis(); 
			distributeLockHis.setBusinessKey(businesskey);
			distributeLockHis.setReleaseType(0);
			distributeLockHis.setOperator(operator);
			distributeLockHis.setOperatorIp(ip);			
			int addCount = distributeLockHisDao.addDistributeLockHis(distributeLockHis);
			LOGGER.info("插入条数 addCount:{}",addCount);
			return true;
		}
		return false;
	}

	/**
	 * Title: freedDistributeLock 
	 * Description:
	 * @param business_key
	 * @param operator
	 * @param ip
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean freedDistributeLock(String business_key, String operator) {
		DistributeLock dl = new DistributeLock();
		dl.setBusinessKey(business_key);
		dl.setOperator(operator);
		dl.setStatus(0);
		int count = distributeLockDao.updateFreedDistributeLock(dl);
		if (count > 0) {
			DistributeLockHis distributeLockHis = new DistributeLockHis(); 
			distributeLockHis.setBusinessKey(business_key);
			distributeLockHis.setOperator(operator);
			distributeLockHis.setReleaseType(1);//正常释放
			int updateCount = distributeLockHisDao.updateDistributeLockHis(distributeLockHis);
			return true;
		}
		return false;
	}

}
