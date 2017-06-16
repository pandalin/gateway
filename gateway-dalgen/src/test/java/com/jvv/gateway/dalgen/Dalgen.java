/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 21:42 创建
 *
 */
package com.jvv.gateway.dalgen;

import middlegen.AbstractDalgenTask;

/**
 * @author turalyon@jinvovo.com
 */
public class Dalgen extends AbstractDalgenTask {
	@Override
	protected void init() {
		//具体配置看 README.md
		setAppName("gateway");
		setDalPackage("com.jvv.gateway.dal");
		setDalModule("dal");
		setDaoPackage("mapper");
		setDoPackage("entity");
		setDoSuffix("DO");
		setExtendsEntity("Entity");
		setDataBaseInfo("jdbc:mysql://127.0.0.1:3306/test", "root", "111111");

		//设置为空，使用customer-dalgen/src/main/templates，或者指定templates路径；
		//如果 templates模板不修改，这里直接注释掉即可
		//		setTemplatesPath("");

		setUseJavaType(true);
		setUseJdbcType(true);
		setUseTypeHandler(true);
		//生成DO对象、Mapper接口和Mapping.xml 以及接口实现
		setGenerate(GENERATE_DO + GENERATE_DAO + GENERATE_MAPPER + GENERATE_IMPL);
		setFindRelationship(false);
		setIgnorePropertiesInDO("id,rawAddTime,rawUpdateTime");
		//多个table逗号分隔

		//		String tables = "test1,test2";
		String tables = "telephone_bill_prepaid";
		setTables(tables);
	}

	public static void main(String[] args) {
		//		PropertyConfigurator.configure("log4j.properties");

		Dalgen dalgen = new Dalgen();
		dalgen.start();
	}
}
