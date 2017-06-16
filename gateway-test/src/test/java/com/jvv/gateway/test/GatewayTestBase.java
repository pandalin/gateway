/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:18 创建
 *
 */
package com.jvv.gateway.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author turalyon@jinvovo.com
 */
@ContextConfiguration(locations = { "classpath*:/spring/spring-*.xml" })
@ComponentScan({"com.jvv.gateway.*"})
public abstract class GatewayTestBase extends AbstractJUnit4SpringContextTests {
}
