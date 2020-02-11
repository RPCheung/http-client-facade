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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.cheung.client.api.ExecutionInterceptor;
import com.cheung.client.component.HttpMethod;
import com.cheung.client.configuration.Mapper;

import net.sf.cglib.proxy.Enhancer;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public abstract class AbstractClientProxyFactory implements MethodInterceptor, ClientProxyFactory {

	protected final Map<Class<?>, Object> loadedClients = new ConcurrentHashMap<>();
	protected final Map<Class<?>, Mapper> loadedMappers = new ConcurrentHashMap<>();
	protected final List<Mapper> mappers = new CopyOnWriteArrayList<>();
	private ExecutionInterceptor interceptor;

	public AbstractClientProxyFactory(List<Mapper> mappers) throws Exception {
		this(mappers,null);
	}

	public AbstractClientProxyFactory(List<Mapper> mappers, CallbackHandler callbackHandler) throws Exception {
		transform(mappers, callbackHandler);
	}

	public void setInterceptor(ExecutionInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public ExecutionInterceptor getInterceptor() {
		return interceptor;
	}

	private void transform(List<Mapper> mappers, CallbackHandler callbackHandler) throws ClassNotFoundException {
		this.mappers.addAll(mappers);
		for (Mapper mapper : mappers) {
			Class<?> targetInterface = Thread.currentThread().getContextClassLoader().loadClass(mapper.getNamespace());

			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(targetInterface);
			if (callbackHandler == null) {
				enhancer.setCallback(this);
			}
			loadedClients.put(targetInterface, enhancer.create());
			loadedMappers.put(targetInterface, mapper);
		}
	}

	protected Object execute(Object proxy, Object[] args, MethodProxy methodProxy) throws Exception {
		
		Mapper mapper = loadedMappers.get(proxy.getClass().getInterfaces()[0]);
		if(mapper.getHttpClient() == null) {
			throw new IllegalArgumentException("the httpClientClassName can not be blank");
		}
		
		if(mapper.getBaseUrl() == null) {
			throw new IllegalArgumentException("the baseUrl can not be blank");
		}
		
		if(args.length>1) {
			throw new IllegalArgumentException("The length of args cannot be greater than 1");
		}
		
		Map<String,Mapper.Action> actions = mapper.getActions();
		
		if(!actions.containsKey(methodProxy.getSignature().getName())) {
			throw new IllegalArgumentException("cannot find id:"+methodProxy.getSignature().getName());
		}
		
		Mapper.Action action = actions.get(methodProxy.getSignature().getName());
		
		HttpClientAdapter adapter = new HttpClientAdapter(mapper);
		
		return adapter.execute(HttpMethod.valueOf(action.getActionType().toUpperCase()),action.getPath(), args.length>1?args:args[0]);
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		return execute(proxy, args, methodProxy);
	}

}
