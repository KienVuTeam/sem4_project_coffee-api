package com.project.model;

public class RecoverInfo {
	private boolean flagSuccess;
	private boolean flagSendding;
	private String emailRecover;
	private String linkRecover;
	private String timeExpired;
	private String msgRecover;
	//
	public boolean isFlagSuccess() {
		return flagSuccess;
	}
	public void setFlagSuccess(boolean flagSuccess) {
		this.flagSuccess = flagSuccess;
	}
	public boolean isFlagSendding() {
		return flagSendding;
	}
	public void setFlagSendding(boolean flagSendding) {
		this.flagSendding = flagSendding;
	}
	public String getEmailRecover() {
		return emailRecover;
	}
	public void setEmailRecover(String emailRecover) {
		this.emailRecover = emailRecover;
	}
	public String getLinkRecover() {
		return linkRecover;
	}
	public void setLinkRecover(String linkRecover) {
		this.linkRecover = linkRecover;
	}
	public String getTimeExpired() {
		return timeExpired;
	}
	public void setTimeExpired(String timeExpired) {
		this.timeExpired = timeExpired;
	}
	public String getMsgRecover() {
		return msgRecover;
	}
	public void setMsgRecover(String msgRecover) {
		this.msgRecover = msgRecover;
	}
}
