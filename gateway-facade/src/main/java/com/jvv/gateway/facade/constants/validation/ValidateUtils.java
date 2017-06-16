/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 23:20 创建
 *
 */
package com.jvv.gateway.facade.constants.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author turalyon@jinvovo.com
 */
public enum ValidateUtils {
	INSTANCE {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		@Override
		public Validator getValidator() {
			return factory.getValidator();
		}
	};
	public abstract Validator getValidator();
}
