package com.hanyun.platform.pay.service.cib.weixin;

import com.hanyun.platform.pay.domain.StatementDiff;

public interface StatementDiffService {
	/**
	 * 新增对账差异提报
	 * @param statementDiff
	 */
	public void addStatementDiffSubmit(StatementDiff statementDiff);
}
