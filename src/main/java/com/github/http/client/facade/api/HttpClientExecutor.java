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

package com.github.http.client.facade.api;

public interface HttpClientExecutor<C, P0, R0,P1, R1> {
	
	C getHttpClient() throws Exception;

	R0 doPost(String path, P0 data) throws Exception;

	R1 doGet(String path, P1 parameter) throws Exception;
	
	R0 doHead(String path, P0 parameter) throws Exception;
	
	R0 doPut(String path, P0 parameter) throws Exception;
	
	R0 doDelete(String path, P0 parameter) throws Exception;
	
	R0 doConnect(String path, P0 parameter) throws Exception;
	
	R0 doOptions(String path, P0 parameter) throws Exception;
	
	R0 doTrace(String path, P0 parameter) throws Exception;
	
	R0 doPatch(String path, P0 parameter) throws Exception;

}
