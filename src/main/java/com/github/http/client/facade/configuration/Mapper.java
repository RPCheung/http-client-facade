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
package com.github.http.client.facade.configuration;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mapper {

	private Mapper(String httpClient, String namespace, String baseUrl, Map<String, Action> actions,ClientConfiguration clientConfiguration) {
		this.httpClient = httpClient;
		this.namespace = namespace;
		this.baseUrl = baseUrl;
		this.actions = actions;
		this.clientConfiguration = clientConfiguration;
	}

	private String httpClient;
	private String namespace;
	private String baseUrl;
	private Map<String,Action> actions;
	private ClientConfiguration clientConfiguration;

	@Data
	@Builder
	public static class Action {
		
		private String actionType;
		private String parameterType;
		private String resultType;
		private String path;
		private String contentType;
		private String defaultCharset;
		private Map<String,String> header;
		
		private Action(String actionType, String parameterType, String resultType, String path,String contentType,String defaultCharset,Map<String,String> header) {
			this.actionType = actionType;
			this.parameterType = parameterType;
			this.resultType = resultType;
			this.path = path;
			this.header = header;
			this.contentType = contentType;
			this.defaultCharset = defaultCharset;
		}

		

	}
}
