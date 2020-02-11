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
 * Created on 2020年2月10日
 *******************************************************************************/

package com.github.http.client.facade.api;


import com.github.http.client.facade.configuration.Mapper;

public abstract class AbstractClientImpl<C,P0,R0,P1,R1> implements HttpClientExecutor<C,P0,R0,P1,R1> {
	
	private C c;
	protected Mapper mapper;
	
	public AbstractClientImpl(Mapper mapper,C c) {
		this.c = c;
		this.mapper = mapper;
	}
	
	@Override
	public C getHttpClient() throws Exception{
		return c;
	}
	
	@Override
	public R0 doHead(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public R0 doPut(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public R0 doDelete(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public R0 doConnect(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public R0 doOptions(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public R0 doTrace(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public R0 doPatch(String path, P0 parameter) throws Exception {
		throw new UnsupportedOperationException();
	}

}
