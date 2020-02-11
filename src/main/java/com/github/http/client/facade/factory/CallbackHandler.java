/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2016-2026 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2020年2月9日
 *******************************************************************************/


package com.github.http.client.facade.factory;

import net.sf.cglib.proxy.MethodInterceptor;;

@FunctionalInterface
public interface CallbackHandler extends MethodInterceptor {
}
