/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.jvv.gateway.dal.mapper;

//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import org.apache.ibatis.annotations.Param;

import com.jvv.gateway.dal.entity.TelephoneBillPrepaidDO;
import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>telephone_bill_prepaid</tt>.
 *
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/telephone_bill_prepaid.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
 @SuppressWarnings("rawtypes")
public interface TelephoneBillPrepaidMapper {
	/**
	 *  Insert one <tt>TelephoneBillPrepaidDO</tt> object to DB table <tt>telephone_bill_prepaid</tt>, return primary key
	 *
	 * 插入数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into telephone_bill_prepaid(order_no,channel_api,phone_no,currency,amount,status,trans_date,result_code,result_message,raw_add_time,raw_update_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param telephoneBillPrepaid
	 *	@return int
	 */	 

    public int insert( @Param("telephoneBillPrepaid")TelephoneBillPrepaidDO telephoneBillPrepaid);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from telephone_bill_prepaid</tt>
	 *
	 *	@param telephoneBillPrepaid
	 *	@param start
	 *	@param pageSize
	 *	@return List<TelephoneBillPrepaidDO>
	 */	 

    public List<TelephoneBillPrepaidDO> query( @Param("telephoneBillPrepaid")TelephoneBillPrepaidDO telephoneBillPrepaid,  @Param("start")long start,  @Param("pageSize")int pageSize);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from telephone_bill_prepaid</tt>
	 *
	 *	@param telephoneBillPrepaid
	 *	@return long
	 */	 

    public long queryCount( @Param("telephoneBillPrepaid")TelephoneBillPrepaidDO telephoneBillPrepaid);

	/**
	 *  Update DB table <tt>telephone_bill_prepaid</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update telephone_bill_prepaid set order_no=? where (id = ?)</tt>
	 *
	 *	@param telephoneBillPrepaid
	 *	@return int
	 */	 

    public int update( @Param("telephoneBillPrepaid")TelephoneBillPrepaidDO telephoneBillPrepaid);

	/**
	 *  Delete records from DB table <tt>telephone_bill_prepaid</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from telephone_bill_prepaid where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 */	 

    public int delete( @Param("id")Long id);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from telephone_bill_prepaid telephoneBillPrepaid where (order_no = ?)</tt>
	 *
	 *	@param orderNo
	 *	@return TelephoneBillPrepaidDO
	 */	 

    public TelephoneBillPrepaidDO findByOrderNo( @Param("orderNo")String orderNo);

	/**
	 *  Update DB table <tt>telephone_bill_prepaid</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update telephone_bill_prepaid set channel_api=? where (id = ?)</tt>
	 *
	 *	@param telephoneBillPrepaid
	 *	@return int
	 */	 

    public int updateByOrderNo( @Param("telephoneBillPrepaid")TelephoneBillPrepaidDO telephoneBillPrepaid);

	/**
	 *  Delete records from DB table <tt>telephone_bill_prepaid</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from telephone_bill_prepaid where (order_no = ?)</tt>
	 *
	 *	@param orderNo
	 *	@return int
	 */	 

    public int deleteByOrderNo( @Param("orderNo")String orderNo);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from telephone_bill_prepaid</tt>
	 *
	 *	@param orderNoList
	 *	@return List<TelephoneBillPrepaidDO>
	 */	 

    public List<TelephoneBillPrepaidDO> findByOrderNoIn( @Param("orderNoList")List orderNoList);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from telephone_bill_prepaid telephoneBillPrepaid where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TelephoneBillPrepaidDO
	 */	 

    public TelephoneBillPrepaidDO findById( @Param("id")Long id);

	/**
	 *  Query DB table <tt>telephone_bill_prepaid</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from telephone_bill_prepaid</tt>
	 *
	 *	@param idList
	 *	@return List<TelephoneBillPrepaidDO>
	 */	 

    public List<TelephoneBillPrepaidDO> findByIdIn( @Param("idList")List idList);

}
