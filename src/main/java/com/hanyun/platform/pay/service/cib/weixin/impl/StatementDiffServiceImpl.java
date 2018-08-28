package com.hanyun.platform.pay.service.cib.weixin.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hanyun.platform.pay.dao.StatementDiffDao;
import com.hanyun.platform.pay.domain.StatementDiff;
import com.hanyun.platform.pay.service.cib.weixin.StatementDiffService;
@Service
public class StatementDiffServiceImpl implements StatementDiffService{
	@Resource
	private StatementDiffDao statementDiffDao;
	/**
	 * 新增对账差异提报
	 */
	@Override
	public void addStatementDiffSubmit(StatementDiff statementDiff) {
		statementDiff.setDiffStatus(Integer.valueOf(0));
		statementDiff.setReportTime(new Date());
		statementDiff.setCreateTime(new Date());
		statementDiffDao.insertStatementDiffSubmit(statementDiff);
	}
}
