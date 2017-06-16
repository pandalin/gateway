/* 
 * www.cutebear.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

package com.jvv.gateway.dal.mapper;
/* 
 * 修订记录:
 * @author Mr.Wolffy (e-mail:turalyon@moonlighting.cn) 2017-01-17 10:22 创建
 *
 */

import java.util.Date;

/**
 * 系统时间和序列生成工具 <p/>
 * 使用序列需要先创建序列(Oracle)或者sys_seq表(Mysql)<br/>
 * sys_seq表的创建sql:<br/>
 * <pre>
 *  CREATE TABLE `sys_seq` (
 *     id int(11) NOT NULL AUTO_INCREMENT,
 *     name varchar(32) DEFAULT NULL COMMENT '序列名称',
 *     raw_add_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
 *     PRIMARY KEY (id)
 *  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 * </pre>
 * <p/>
 * 使用方式：<br/>
 * <p/>
 * 在您系统中的Configuration类中，引入配置 [CutebearCommonConfiguration]
 * <br/>
 * <pre>
 * @ Configuration
 * @ ImportAutoConfiguration({ <B> CutebearCommonConfiguration.class </B>})
 * public class DalConfiguration { .....}
 * </pre>
 *
 * 然后，您就可以使用 <br/>
 * <pre>
 *     @ Autowired
 *	   private ExtraMapper extraMapper;
 *
 *	   .....
 *
 *	  //获取数据库时间
 *    extraMapper.getSysdate();
 *
 *	  //获取新的序列ID
 *    extraMapper.newSequence();
 *
 *	  //生成20的业务ID：[20160913][001][000000032]
 *    extraMapper.newId("OrderNo","001");
 *
 * </pre>
 *
 * 自动根据 dataSource Bean来区分Mysql还是Oracle
 *
 *
 * @author Mr.Wolffy (e-mail:turalyon@moonlighting.cn)
 */
public interface ExtraMapper {
	/**
	 * 获取系统时间
	 *
	 * @return
	 */
	public Date getSysdate();

	/**
	 * 获取新序列
	 *
	 * @param name
	 * @return
	 */
	public long newSequence(String name);

	/**
	 * @param seqName 序列名称
	 * @param bizCode 业务标识 默认3位，不足补0
	 * @return 返回长度未20的序列，	[yyyymmdd][bizCode][0...0][seq]
	 * @see ExtraMapper#newId(String, String, String, int)
	 */
	public String newId(String seqName, String bizCode);

	/**
	 * @param seqName 序列名称
	 * @param bizCode 业务标识 默认3位，不足补0
	 * @param len     序列长度,必须大于 8 + bizCode.length + dbCode.length + 5
	 * @return 返回指定长度的序列，	[yyyymmdd][bizCode][0...0][seq]
	 * @see ExtraMapper#newId(String, String, String, int)
	 */
	public String newId(String seqName, String bizCode, int len);

	/**
	 * @param seqName 序列名称
	 * @param bizCode 业务标识 默认3位，不足补0
	 * @param dbCode  分库分表标识，默认2位，不足补0
	 * @return 返回长度为20位的序列，	[yyyymmdd][bizCode][dbCode][0...0][seq]
	 */
	public String newId(String seqName, String bizCode, String dbCode);

	/**
	 * @param seqName 序列名称
	 * @param bizCode 业务标识 默认3位，不足补0
	 * @param dbCode  分库分表标识，默认2位，不足补0
	 * @param len     序列长度,必须大于 8 + bizCode.length + dbCode.length + 5
	 * @return 返回指定长度的序列，	[yyyymmdd][bizCode][dbCode][0...0][seq]
	 */
	public String newId(String seqName, String bizCode, String dbCode, int len);
}
