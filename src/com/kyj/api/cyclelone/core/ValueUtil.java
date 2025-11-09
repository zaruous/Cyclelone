/**
 * 
 */
package com.kyj.api.cyclelone.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 */
public class ValueUtil {

	public static String toString(Exception e) {
		return toString(null, e);
	}

	/**
	 * 에러 메세지 상세화
	 *
	 * @param title 메세지 타이틀
	 * @param e
	 * @return
	 */
	public static String toString(String title, Throwable e) {
		if (e == null)
			return "[warnning] Exception is null";

		String errMsg = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (PrintWriter printWriter = new PrintWriter(out, true)) {
			if (title != null)
				printWriter.write("#############  ".concat(title).concat("  ##############\n"));
			e.printStackTrace(printWriter);
			errMsg = out.toString("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		return errMsg;
	}
}
