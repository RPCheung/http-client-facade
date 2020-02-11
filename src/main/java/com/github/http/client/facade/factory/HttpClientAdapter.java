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

import com.github.http.client.facade.api.HttpClientExecutor;
import com.github.http.client.facade.component.ClientType;
import com.github.http.client.facade.component.HttpMethod;
import com.github.http.client.facade.configuration.Mapper;

class HttpClientAdapter {

	@SuppressWarnings("rawtypes")
	private HttpClientExecutor executor;

	private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	public HttpClientAdapter(Mapper mapper) throws Exception {
		init(mapper);
	}

	private void init(Mapper mapper) throws Exception {
		ClientType clientType = ClientType.valueOf(mapper.getHttpClient());
		executor = createClientImpl(clientType.getClassName(), mapper);
	}

	@SuppressWarnings("rawtypes")
	private HttpClientExecutor createClientImpl(String className, Mapper mapper) throws Exception {
		return HttpClientExecutor.class
				.cast(classLoader.loadClass(className).getConstructor(Mapper.class).newInstance(mapper));
	}

	@SuppressWarnings("unchecked")
	Object execute(HttpMethod method, String path, Object parameter) throws Exception {
		switch (method) {
			case GET: {
				return executor.doGet(path, parameter);
			}
			case POST: {
				return executor.doPost(path, parameter);
			}
			case PUT: {
				return executor.doPut(path, parameter);
			}
			case CONNECT: {
				return executor.doConnect(path, parameter);
			}
			case DELETE: {
				return executor.doDelete(path, parameter);
			}
			case HEAD: {
				return executor.doHead(path, parameter);
			}
			case OPTIONS: {
				return executor.doOptions(path, parameter);
			}
			case PATCH: {
				return executor.doPatch(path, parameter);
			}
			case TRACE: {
				return executor.doTrace(path, parameter);
			}
			default: {
				throw new UnsupportedOperationException("unknown method");
			}
		}

	}
}
