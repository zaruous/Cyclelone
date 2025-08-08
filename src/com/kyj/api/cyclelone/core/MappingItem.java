/**
 * 
 */
package com.kyj.api.cyclelone.core;

import java.util.List;

/**
 * 
 */
public record MappingItem(
		int version, 
		String path, 
		String controller, 
		String description, 
		List<MethodInfo> methods) {
}