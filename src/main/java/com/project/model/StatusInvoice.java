package com.project.model;

import java.math.BigDecimal;

public class StatusInvoice {
	private boolean flagSuccess;
	private boolean flagRefund;
	private BigDecimal moneyRefund;

	public boolean isFlagSuccess() {
		return flagSuccess;
	}

	public void setFlagSuccess(boolean flagSuccess) {
		this.flagSuccess = flagSuccess;
	}

	public boolean isFlagRefund() {
		return flagRefund;
	}

	public void setFlagRefund(boolean flagRefund) {
		this.flagRefund = flagRefund;
	}

	public BigDecimal getMoneyRefund() {
		return moneyRefund;
	}

	public void setMoneyRefund(BigDecimal moneyRefund) {
		this.moneyRefund = moneyRefund;
	}
}