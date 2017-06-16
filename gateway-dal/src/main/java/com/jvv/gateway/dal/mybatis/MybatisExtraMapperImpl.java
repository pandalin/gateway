/* 
 * www.cutebear.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

package com.jvv.gateway.dal.mybatis;
/* 
 * 修订记录:
 * @author Mr.Wolffy (e-mail:turalyon@moonlighting.cn) 2017-01-17 10:23 创建
 *
 */

import com.google.common.collect.Maps;
import com.jvv.gateway.dal.entity.ExtraDO;
import com.jvv.gateway.dal.mapper.ExtraMapper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Mr.Wolffy (e-mail:turalyon@moonlighting.cn)
 */
@Service("extraMapper")
public class MybatisExtraMapperImpl extends SqlSessionDaoSupport implements ExtraMapper {
	
	/**
	 * 同步数据库的时间
	 */
	public static final long UPDATE_INTERVAL_TIME = 10 * 60 * 1000;
	
	public static int default_id_length = 20;
	
	//创建session连接实例
	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 实体缓存
	 */
	private volatile long timeInterval = 0;
	private volatile long lastUpdateTime = -1;
	
	private volatile Map<Key, String> seqFlagMap = Maps.newConcurrentMap();
	
	@PostConstruct
	private void init() {
		getSysdate();
	}
	
	/**
	 * 获取数据库时间
	 *
	 * @return
	 */
	public Date getSysdate() {
		return getSysdate(0);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public long newSequence(String name) {
		ExtraDO dbDo = new ExtraDO();
		dbDo.setName(name);
		sqlSessionTemplate.insert("MYSQL-SEQ-NEXTVAL", dbDo);
		return dbDo.getId();
	}
	
	public Date getSysdate(long delay) {
		final long appTime = new Date().getTime();
		if (appTime - lastUpdateTime > UPDATE_INTERVAL_TIME) {
			timeInterval = getSysdate1().getTime() - new Date().getTime();
			lastUpdateTime = appTime;
		}
		Date sysDate = new Date(appTime + timeInterval + delay);
		return sysDate;
	}
	
	public String newId(String seqName, String bizCode) {
		return newId(seqName, bizCode, null);
	}
	
	public String newId(String seqName, String bizCode, int len) {
		return newId(seqName, bizCode, null, len);
	}
	
	public String newId(String seqName, String bizCode, String dbCode) {
		return newId(seqName, bizCode, dbCode, default_id_length);
	}
	
	public String newId(String seqName, String bizCode, String dbCode, int len) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = dateFormat.format(getSysdate());

		String sysSeq = date + getSeqFlag(bizCode, dbCode);
		long dbSeq = newSequence(seqName);
		String dbSeqsString = StringUtils.leftPad(String.valueOf(dbSeq), len - 8, "0");
		return sysSeq + StringUtils.right(dbSeqsString, len - sysSeq.length());
	}
	
	private String getSeqFlag(String bizCode, String dbCode) {
		Key key = new Key(bizCode, dbCode);
		String sqlFlag = seqFlagMap.get(key);
		if (sqlFlag == null) {
			sqlFlag = (bizCode == null ? "" : StringUtils.leftPad(bizCode, 3, '0'))
					  + (dbCode == null ? "" : StringUtils.leftPad(dbCode, 2, '0'));
			seqFlagMap.put(key, sqlFlag);
		}
		return sqlFlag;
	}
	
	private Date getSysdate1() {
		return sqlSessionTemplate.selectOne("COMMON-GET-SYSDATE");
	}
	
	private class Key {
		private String bizCode;
		private String dbCode;
		
		public Key(String bizCode, String dbCode) {
			this.bizCode = bizCode;
			this.dbCode = dbCode;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			
			Key key = (Key) o;
			
			if (bizCode != null ? !bizCode.equals(key.bizCode) : key.bizCode != null)
				return false;
			return dbCode != null ? dbCode.equals(key.dbCode) : key.dbCode == null;
			
		}
		
		@Override
		public int hashCode() {
			int result = bizCode != null ? bizCode.hashCode() : 0;
			result = 31 * result + (dbCode != null ? dbCode.hashCode() : 0);
			return result;
		}
	}
}
