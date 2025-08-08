package com.kyj.api.cyclelone.core;

import java.util.List;

public record MethodInfo (
	 String name,
	    String method,
	    String description,
	    List<ParameterInfo> parameters,
	    List<ResponseInfo> responses
){}
