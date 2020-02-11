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

import com.github.http.client.facade.configuration.Mapper;

public interface ClientProxyFactory {
	
	<T> T getClient(Class<T> targetInterface);

	boolean isRegistered(Class<?> targetInterface);
	
	void registerClient(Mapper mapper, CallbackHandler callbackHandler)throws ClassNotFoundException;
	
	<T> T removeClient(Class<T> targetInterface);
}
