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

package com.github.http.client.facade.configuration.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.http.client.facade.configuration.ClientConfiguration;
import com.github.http.client.facade.configuration.Mapper;
import com.github.http.client.facade.configuration.MapperReader;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;



public class MapperReaderImpl implements MapperReader {

	@Override
	public List<Mapper> loadMapper(List<String> filePaths) throws Exception {
		List<Mapper> mappers = new ArrayList<>();
		for (String filePath : filePaths) {
			mappers.add(loadSingleMapper(filePath));
		}
		return new CopyOnWriteArrayList<>(mappers);
	}

	private Mapper loadSingleMapper(String filePath) throws Exception {
		InputStream filePathStream = null;

		Mapper mapper = Mapper.builder().build();

		ClientConfiguration configuration = ClientConfiguration.builder().build();

		Map<String, Mapper.Action> actions = new ConcurrentHashMap<String, Mapper.Action>();

		try {
			filePathStream = new FileInputStream(filePath);
			SAXReader reader = new SAXReader();
			Document document = reader.read(filePathStream);
			Element root = document.getRootElement();

			// init mapper
			mapper.setNamespace(root.selectSingleNode("/client/@namespace").getStringValue());
			mapper.setBaseUrl(root.selectSingleNode("/client/@baseUrl").getStringValue());
			mapper.setHttpClient(root.selectSingleNode("/client/@httpClient").getStringValue());
			mapper.setClientConfiguration(configuration);
			mapper.setActions(actions);

			// init ClientConfiguration
			Node configurationNode = root.selectSingleNode("/client/configurations");
			String socketTimeout = configurationNode.selectSingleNode("socketTimeout/@value").getStringValue();
			if ("".equals(socketTimeout) || socketTimeout == null) {
				configuration.setSocketTimeout(30000);
			} else {
				configuration.setSocketTimeout(Integer.parseInt(socketTimeout));
			}
			
			String connectTimeout = configurationNode.selectSingleNode("connectTimeout/@value").getStringValue();
			if ("".equals(connectTimeout) || connectTimeout == null) {
				configuration.setConnectTimeout(30000);
			} else {
				configuration.setConnectTimeout(Integer.parseInt(socketTimeout));
			}

			// init actions
			List<Node> actionNodes = root.selectNodes("/client/action");
			for (Node node : actionNodes) {
				Map<String, String> headers = new ConcurrentHashMap<>();

				actions.put(node.selectSingleNode("@id").getStringValue(),
						Mapper.Action.builder().actionType(node.selectSingleNode("@method").getStringValue())
								.path(node.selectSingleNode("@path").getStringValue())
								.resultType(node.selectSingleNode("@resultType").getStringValue()).header(headers)
								.contentType(node.selectSingleNode("@contentType").getStringValue())
								.defaultCharset(node.selectSingleNode("@defaultCharset").getStringValue())
								.parameterType(node.selectSingleNode("@parameterType").getStringValue()).build());

				List<Node> headerNodes = root.selectNodes("/client/action/headers/header");
				for (Node header : headerNodes) {
					headers.put(header.selectSingleNode("@key").getStringValue(),
							header.selectSingleNode("@value").getStringValue());
				}

			}
		} finally {
			if (filePathStream != null) {
				filePathStream.close();
			}
		}
		return mapper;
	}

	@Override
	public void generateMapperXML(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void generateMappeJSON(String path) {
		// TODO Auto-generated method stub

	}

}
