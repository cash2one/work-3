package com.trust.privilege.model.complex.excel;
/***
 * @ClassName: ExcelDataUserAndRoleAndGroup 
 * @Description: Excel数据。用户，角色，角色组的Model
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月27日 上午11:53:23
 */
public class ExcelDataUserAndRoleAndGroup extends ExcelDataBase{
	private String realName;			//用户真实姓名
	private String userName ;			//用户名
	private String userDesc	;			//用户描述
	private String memeberType;			//证件类型
	private String idCard	;			//身份证号
	private String phoneNumber;			//手机号
	private String groupName;			//用户组名
	private String groupDesc;			//用户组描述
	private String Parent_Group_Id;		//当前组的父用户组
	
	
	/**公有属性的取值赋值方法*/
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getParent_Group_Id() {
		return Parent_Group_Id;
	}
	public void setParent_Group_Id(String parent_Group_Id) {
		Parent_Group_Id = parent_Group_Id;
	}
	public String getMemeberType() {
		return memeberType;
	}
	public void setMemeberType(String memeberType) {
		this.memeberType = memeberType;
	}
	
	/**无参构造器*/
	public ExcelDataUserAndRoleAndGroup(){}
	
	/**有参构造器*/
	public ExcelDataUserAndRoleAndGroup(String roleName, String roleDesc, String systemPlatFormCd, String realName,
			String userName, String userDesc, String memeberType, String idCard, String phoneNumber, String groupName,
			String groupDesc, String parent_Group_Id) {
		super(roleName, roleDesc, systemPlatFormCd);
		this.realName = realName;
		this.userName = userName;
		this.userDesc = userDesc;
		this.memeberType = memeberType;
		this.idCard = idCard;
		this.phoneNumber = phoneNumber;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
		Parent_Group_Id = parent_Group_Id;
	}
	
	
}
