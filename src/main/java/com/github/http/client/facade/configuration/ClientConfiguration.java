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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientConfiguration {
	private int socketTimeout;
	private int connectTimeout;
}
