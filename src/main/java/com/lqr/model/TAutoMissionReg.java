package com.lqr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_automissionreg")
public class TAutoMissionReg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自动任务表主键
	 */
	private String regKey;
	/**
	 * 自动任务执行时间, 格式:HH:MI:SS; 例如：20:30:00，iscycle=1的时候不判断此处
	 */
	private String execTime;
	/**
	 * cycle=1的时候，根据此配置为分钟，每小时执行一次
	 */
	private String execPerMinu;
	/**
	 * 自动任务名字
	 */
	private String regName;
	/**
	 * 自动任务类名, 例如: com.xx.xx.xxx
	 */
	private String regClass;
	/**
	 * 执行方法, 非构造函数, 方法不能带参数
	 */
	private String regMethod;
	/**
	 * 注册时间
	 */
	private Date regDate;
	/**
	 * 是否循环执行: 0-否，1-是, 默认：0
	 */
	private String isCycle;
	/**
	 * 节假日是否执行：0-否，1-执行，默认：1
	 * 思路：若为否， 判断当前日期是否是节假日再执行。
	 */
	private String isHoliday;	
	/**
	 * 是否有效, 0-无效, 1-有效
	 */
	private String isActive;
	/**
	 * 备注
	 */
	private String remark;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "regKey", unique = false, nullable = false, length = 60)
	public String getRegKey() {
		return regKey;
	}
	public void setRegKey(String regKey) {
		this.regKey = regKey;
	}
	@Column(name = "execTime", nullable = true, length = 8)
	public String getExecTime() {
		return execTime;
	}
	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	@Column(name = "execPerMinu", nullable = true, length = 2)
	public String getExecPerMinu() {
		return execPerMinu;
	}
	public void setExecPerMinu(String execPerMinu) {
		this.execPerMinu = execPerMinu;
	}
	@Column(name = "regName", nullable = false, length = 100)
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	@Column(name = "regClass", nullable = false, length = 200)
	public String getRegClass() {
		return regClass;
	}
	public void setRegClass(String regClass) {
		this.regClass = regClass;
	}
	@Column(name = "regMethod", nullable = false, length = 100)
	public String getRegMethod() {
		return regMethod;
	}
	public void setRegMethod(String regMethod) {
		this.regMethod = regMethod;
	}
	@Column(name = "regDate", nullable = true)
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Column(name = "isActive", nullable = false, length = 1)
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name = "isCycle", nullable = false, length = 1)
	public String getIsCycle() {
		return isCycle;
	}
	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}
	@Column(name = "isHoliday", nullable = true, length = 1)
	public String getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}
	@Column(name = "remark", nullable = true, length = 1000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
