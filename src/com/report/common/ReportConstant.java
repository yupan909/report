package com.report.common;
/**
 * 常量类
 * @author Administrator
 *
 */
public class ReportConstant {
	
	/**
	 * 草稿状态
	 */
	public static final String STATUS_CG = "0";
	/**
	 * 待通过状态（普通用户） || 待审核（管理员）
	 */
	public static final String STATUS_BLZ = "1";
	/**
	 * 待提交状态（普通用户） || 审核未通过（管理员）
	 */
	public static final String STATUS_NOPASS = "2";
	/**
	 * 已通过（普通用户） || 审核已通过（管理员）
	 */
	public static final String STATUS_PASS = "3";
	
	
	/**
	 * 管理员用户名
	 */
	public static final String ADMIN = "admin";
}
