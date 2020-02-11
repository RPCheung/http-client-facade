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

import java.util.List;

public interface MapperReader {
	
	  List<Mapper> loadMapper(List<String> filePaths) throws Exception;
	  
	  void generateMapperXML(String path);
	  
	  void generateMappeJSON(String path);

}
