/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.jvv.gateway.dal.mapper;

//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.jvv.gateway.dal.entity.UserMessageRecordLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>user_message_record_log</tt>.
 *
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/user_message_record_log.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
 @SuppressWarnings("rawtypes")
public interface UserMessageRecordLogMapper {
	/**
	 *  Insert one <tt>UserMessageRecordLogDO</tt> object to DB table <tt>user_message_record_log</tt>, return primary key
	 *
	 * 插入数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into user_message_record_log(id,log_id,send_time,send_content,receive_phone,send_phone,msg_type,is_sucess,msg_channel,user_id,createtime,result_code,result_message) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param userMessageRecordLog
	 *	@return int
	 */	 

    public int insert( @Param("userMessageRecordLog")UserMessageRecordLogDO userMessageRecordLog);

	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from user_message_record_log</tt>
	 *
	 *	@param userMessageRecordLog
	 *	@param start
	 *	@param pageSize
	 *	@return List<UserMessageRecordLogDO>
	 */	 

    public List<UserMessageRecordLogDO> query( @Param("userMessageRecordLog")UserMessageRecordLogDO userMessageRecordLog,  @Param("start")long start,  @Param("pageSize")int pageSize);

	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from user_message_record_log</tt>
	 *
	 *	@param userMessageRecordLog
	 *	@return long
	 */	 

    public long queryCount( @Param("userMessageRecordLog")UserMessageRecordLogDO userMessageRecordLog);

	/**
	 *  Update DB table <tt>user_message_record_log</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update user_message_record_log set log_id=? where (id = ?)</tt>
	 *
	 *	@param userMessageRecordLog
	 *	@return int
	 */	 

    public int update( @Param("userMessageRecordLog")UserMessageRecordLogDO userMessageRecordLog);

	/**
	 *  Delete records from DB table <tt>user_message_record_log</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from user_message_record_log where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 */	 

    public int delete( @Param("id")Long id);

	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from user_message_record_log userMessageRecordLog where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return UserMessageRecordLogDO
	 */	 

    public UserMessageRecordLogDO findById( @Param("id")Long id);

	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from user_message_record_log</tt>
	 *
	 *	@param idList
	 *	@return List<UserMessageRecordLogDO>
	 */	 

    public List<UserMessageRecordLogDO> findByIdIn( @Param("idList")List idList);
	
	/**
	 * 统计渠道发送短信量
	 * @param beginDate
	 * @param endDate
	 * @param channelType
	 * @param success
	 * @return
	 */
	int querySmsRecordByChannel (@Param ("beginDate") String beginDate,
	                                             @Param ("endDate") String endDate,
	                                             @Param ("channelType") String channelType,
	                                             @Param ("success") Integer success);
	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from user_message_record_log userMessageRecordLog where (log_id = ?)</tt>
	 *
	 *	@param logId
	 *	@return UserMessageRecordLogDO
	 */	 

    public UserMessageRecordLogDO findByLogId( @Param("logId")String logId);

	/**
	 *  Update DB table <tt>user_message_record_log</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update user_message_record_log set send_time=? where (id = ?)</tt>
	 *
	 *	@param userMessageRecordLog
	 *	@return int
	 */	 

    public int updateByLogId( @Param("userMessageRecordLog")UserMessageRecordLogDO userMessageRecordLog);

	/**
	 *  Delete records from DB table <tt>user_message_record_log</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from user_message_record_log where (log_id = ?)</tt>
	 *
	 *	@param logId
	 *	@return int
	 */	 

    public int deleteByLogId( @Param("logId")String logId);

	/**
	 *  Query DB table <tt>user_message_record_log</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from user_message_record_log</tt>
	 *
	 *	@param logIdList
	 *	@return List<UserMessageRecordLogDO>
	 */	 

    public List<UserMessageRecordLogDO> findByLogIdIn( @Param("logIdList")List logIdList);

}