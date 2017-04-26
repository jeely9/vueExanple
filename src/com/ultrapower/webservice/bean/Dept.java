package com.ultrapower.webservice.bean;

public class Dept {
	private int id;//部门编号
	private String gerentuanduiId;//团队账号/个人账号ID
	private String zhanghaoId;//账号ID
	private String department;//归属部门
	private String email;//邮箱名称
	private String position;//职位
	private String employeeName;//员工名称
	
	public Dept() {
		super();
	}

	public Dept(int id, String gerentuanduiId, String zhanghaoId,
			String department, String email, String position,
			String employeeName) {
		super();
		this.id = id;
		this.gerentuanduiId = gerentuanduiId;
		this.zhanghaoId = zhanghaoId;
		this.department = department;
		this.email = email;
		this.position = position;
		this.employeeName = employeeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGerentuanduiId() {
		return gerentuanduiId;
	}

	public void setGerentuanduiId(String gerentuanduiId) {
		this.gerentuanduiId = gerentuanduiId;
	}

	public String getZhanghaoId() {
		return zhanghaoId;
	}

	public void setZhanghaoId(String zhanghaoId) {
		this.zhanghaoId = zhanghaoId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
