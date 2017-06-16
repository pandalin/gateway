/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 08:41 创建
 *
 */
package com.jvv.gateway.common.redis;

/**
 * @author turalyon@jinvovo.com
 */
public class RedisProperties {
	private boolean enable = true;
	/**
	 * 必填：访问地址
	 */
	private String host = "localhost";
	/**
	 * 必填：访问端口
	 */
	private int port = 6379;
	/**
	 * 可选：应用缓存空间名，必须注意，为避免与其它应用发生冲突，建议采用默认名，自定义命名空间一定要特别注意。
	 */
	private String namespace = "cache_appName";
	/**
	 * 可选：创建socket连接的超时时间
	 */
	private int timeOut = 5000;

	/**
	 * 可选：基于注解的Spring CacheManager，设置缓存的过期时间。默认为0，即不会过期 如果是使用RedisTemplate来显示读写缓存的，需要自己调用expire方法设置每个key的过期时间 单位秒
	 */
	private int expireTime = 0;
	/**
	 * 可选：连接池配置
	 */
	private Pool pool = new Pool();

	public static class Pool {
		/**
		 * 最大连接数
		 */
		private int maxTotal = 500;
		/**
		 * 最大空闲连接
		 */
		private int maxIdle = 8;
		/**
		 * 最大等待连接时间
		 */
		private int maxWait = 500;

		public int getMaxTotal() {
			return maxTotal;
		}

		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}

		public int getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public int getMaxWait() {
			return maxWait;
		}

		public void setMaxWait(int maxWait) {
			this.maxWait = maxWait;
		}
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}
}
