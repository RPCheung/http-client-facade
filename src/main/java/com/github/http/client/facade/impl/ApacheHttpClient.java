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


package com.github.http.client.facade.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.cheung.client.api.AbstractClientImpl;
import com.cheung.client.configuration.Mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApacheHttpClient extends AbstractClientImpl<CloseableHttpClient,JSONObject,JSONObject,String,String>{
	
	public ApacheHttpClient(Mapper mapper) {
		super(mapper,HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                .setSocketTimeout(mapper.getClientConfiguration().getSocketTimeout())
                .build()).build());
	}

	public JSONObject doPost(String path, JSONObject data) throws Exception {
		
		return null;
	}

	public String doGet(String path, String parameter) throws Exception {
		log.info(parameter);
		String url = null;
		if(this.mapper.getBaseUrl().endsWith("/")) {
			url = this.mapper.getBaseUrl()+path;
		}else {
			url = this.mapper.getBaseUrl()+"/"+path;
		}
		URIBuilder uriBuilder = new URIBuilder(url);
		HttpGet get = new HttpGet(uriBuilder.build());
		CloseableHttpResponse response = this.getHttpClient().execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        log.info(statusCode+"");
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "utf-8");
	}
}
