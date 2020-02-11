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
package com.github.http.client.facade.component;

public enum ClientType {
	APACHE_HTTP_CLIENT("com.cheung.client.impl.ApacheHttpClient"),
	OK_HTTP("");
	
	private String className;
	ClientType(String className){
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
}
