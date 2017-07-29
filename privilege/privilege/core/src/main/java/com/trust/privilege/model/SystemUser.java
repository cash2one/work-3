package com.trust.privilege.model;

import java.util.Date;

public class SystemUser {
    private String systemUserId;

    private String userName;

    private String password;

    private Date createDt;

    private Date updateDt;

    private String stateCd;

    private String userDesc;

    private String createUserId;

    private String idCard;

    private String telNumber;

    private String reserve5;

    private String memberType;

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId == null ? null : systemUserId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber == null ? null : telNumber.trim();
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5 == null ? null : reserve5.trim();
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType == null ? null : memberType.trim();
    }

	@Override
	public String toString() {
		return "SystemUser [systemUserId=" + systemUserId + ", userName="
				+ userName + ", password=" + password + ", createDt="
				+ createDt + ", updateDt=" + updateDt + ", stateCd=" + stateCd
				+ ", userDesc=" + userDesc + ", createUserId=" + createUserId
				+ ", idCard=" + idCard + ", telNumber=" + telNumber
				+ ", reserve5=" + reserve5 + ", memberType=" + memberType + "]";
	}

	public SystemUser() {
		super();
	}
    
}