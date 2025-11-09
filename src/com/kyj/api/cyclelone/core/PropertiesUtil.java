/**
 * 
 */
package com.kyj.api.cyclelone.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class PropertiesUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

	public static Properties of(Map<String, Object> init) {
		Properties properties = new Properties();
		properties.putAll(init);
		return properties;
	}

	public static Properties createOrLoad(Class<?> clazz) {
		return createOrLoad(clazz, (Properties) null);
	}

	public static Properties createOrLoad(Class<?> clazz, Supplier<Properties> emptyInit) {
		return createOrLoad(clazz.getSimpleName(), emptyInit);
	}

	public static Properties createOrLoad(String name, Supplier<Properties> emptyInit) {
		Properties properties = new Properties();
		File file = new File(".config/." + name + ".properties");
		if (file.exists()) {
			try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
				properties.load(in);
			} catch (Exception e) {
				LOGGER.error(ValueUtil.toString(e));
			}
		} else {
			// 초기 저장.
			try {
				save(name, emptyInit == null ? properties : emptyInit.get());
			} catch (Exception e) {
				LOGGER.error(ValueUtil.toString(e));
			}
		}
		return properties;
	}

	public static Properties createOrLoad(Class<?> clazz, Properties emptyInit) {
		Properties properties = new Properties();
		File file = new File(".config/." + clazz.getSimpleName() + ".properties");
		if (file.exists()) {
			try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
				properties.load(in);
			} catch (Exception e) {
				LOGGER.error(ValueUtil.toString(e));
			}
		} else {
			// 초기 저장.
			try {
				save(clazz, emptyInit == null ? properties : emptyInit);
			} catch (Exception e) {
				LOGGER.error(ValueUtil.toString(e));
			}
		}
		return properties;
	}

	public static void save(Class<?> clazz, Properties properties, Consumer<Exception> onError) {
		try {
			save(clazz.getSimpleName(), properties);
		} catch (Exception e) {
			onError.accept(e);
		}
	};

	/**
	 * @param clazz
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public static Properties save(Class<?> clazz, Properties properties) throws Exception {
		return save(clazz.getSimpleName(), properties);
	}

	public static Properties save(String name, Properties properties) throws Exception {
		File file = new File(".config/." + name + ".properties");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
			properties.store(out, name);
		}
		return properties;
	}
}
