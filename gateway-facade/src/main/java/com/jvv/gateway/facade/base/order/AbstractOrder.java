/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:23 创建
 *
 */
package com.jvv.gateway.facade.base.order;

import com.jvv.gateway.facade.constants.validation.ValidateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Set;

/**
 * 应该放到公共的模块里面，不应该每个系统都定义
 *
 * @author turalyon@jinvovo.com
 */
public abstract class AbstractOrder implements Serializable {

	/**
	 * 校验Order
	 *
	 * @param groups
	 */
	public void check(Class<?>... groups) {
		Set<ConstraintViolation<AbstractOrder>> constraintViolations = ValidateUtils.INSTANCE.getValidator()
				.validate(this, groups);
		validate(constraintViolations);
	}

	protected <T> void validate(Set<ConstraintViolation<T>> constraintViolations) {
		IllegalArgumentException exception = null;
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			StringBuilder infoBuilder = new StringBuilder();
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				infoBuilder.append(constraintViolation.getPropertyPath().toString()).append("=:")
						.append(constraintViolation.getMessage()).append(";");
			}
			exception = new IllegalArgumentException(infoBuilder.toString());
		}
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
