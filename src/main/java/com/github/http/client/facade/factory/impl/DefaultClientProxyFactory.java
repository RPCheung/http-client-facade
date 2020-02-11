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


package com.github.http.client.facade.factory.impl;

import java.util.List;


import com.github.http.client.facade.configuration.Mapper;
import com.github.http.client.facade.factory.AbstractClientProxyFactory;
import com.github.http.client.facade.factory.CallbackHandler;
import net.sf.cglib.proxy.Enhancer;

public class DefaultClientProxyFactory extends AbstractClientProxyFactory {

	public DefaultClientProxyFactory(List<Mapper> mappers) throws Exception {
		super(mappers);
	}

	@Override
	public <T> T getClient(Class<T> targetInterface) {
		if(loadedClients.containsKey(targetInterface)) {
			return targetInterface.cast(loadedClients.get(targetInterface));
		}
		throw new IllegalArgumentException("cannot find "+targetInterface+" client");
	}

	@Override
	public boolean isRegistered(Class<?> targetInterface) {
		return loadedClients.containsKey(targetInterface);
	}

	@Override
	public void registerClient(Mapper mapper, CallbackHandler callbackHandler) throws ClassNotFoundException {
		
		Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(mapper.getNamespace());

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		if (callbackHandler == null) {
			enhancer.setCallback(this);
		}
		loadedClients.put(clazz, enhancer.create());
	}

	@Override
	public <T> T removeClient(Class<T> targetInterface) {
		return targetInterface.cast(loadedClients.remove(targetInterface));
	}

}
