package com.ultrapower.webservice.bean;

/**
 * 话题实体bean
 * 
 * @author wsh
 * 
 */
public class Topic {
	private int tid;// 话题编号
	private String tcontent;// 话题内容
	private String createtime;// 创建时间
	private int viewcount;// 查看数
	private int replycount;// 回帖数
	private boolean status;// 状态（false有敏感词，true没有敏感词）
	private String sensitive;//敏感词
	private	 String emoition; //情感正负面	
	private String emotionword;//情感词
	
	private String deptname;//部门名称
	private String ext_2;//扩展字段2
	/**
	 * 无参构造
	 */
	public Topic() {
		super();
	}

	/**
	 * 有参构造
	 * @param tid 微博编号
	 * @param tcontent 微博内容
	 * @param createtime 创建时间
	 * @param viewcount 浏览次数
	 * @param replycount 回复次数
	 * @param status 审核是否有敏感字
	 * @param sensitive 敏感字
	 * @param emotion 情感状态
	 */
	public Topic(int tid, String tcontent, String createtime, int viewcount,
			int replycount, boolean status,String sensitive,String emotion,String emtionword,String deptname,String ext_2) {
		super();
		this.tcontent = tcontent;
		this.createtime = createtime;
		this.viewcount = viewcount;
		this.replycount = replycount;
		this.status = status;
		this.sensitive=sensitive;
		this.emoition=emotion;
		this.emotionword=emtionword;
		this.deptname=deptname;
		this.ext_2=ext_2;
	}

	/**
	 * get,set方法
	 * 
	 * @return
	 */

	public String getTcontent() {
		return tcontent;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSensitive() {
		return sensitive;
	}

	public void setSensitive(String sensitive) {
		this.sensitive = sensitive;
	}

	public String getEmoition() {
		return emoition;
	}

	public void setEmoition(String emoition) {
		this.emoition = emoition;
	}

	public String getEmotionword() {
		return emotionword;
	}

	public void setEmotionword(String emotionword) {
		this.emotionword = emotionword;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getExt_2() {
		return ext_2;
	}

	public void setExt_2(String ext_2) {
		this.ext_2 = ext_2;
	}

	
	
}
