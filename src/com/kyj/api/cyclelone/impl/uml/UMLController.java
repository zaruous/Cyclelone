/**
 * 
 */
package com.kyj.api.cyclelone.impl.uml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;
import net.sourceforge.plantuml.SourceStringReader;

/**
 * 
 */
public class UMLController {
	File dir = new File("stdlib");
	public void getUML(@NotNull Context ctx) throws IOException {
		
		String content = ctx.formParam("content");
		
		byte[] arr = generate(content);
		
		ctx.res().addHeader("Content-Type", "image/png");
		ctx.result(arr);
	}
	
	byte[] generate(String content) throws IOException {
		File file = new File(dir, "getInvLot.puml");
//		Files.newBufferedReader(file.toPath());
		String plantUMLSource = Files.readString(file.getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
		// 2. 출력 파일 경로 설정
//		File outputFilePath = new File(dir, file.getName().replace(".puml", ".png"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try  {
			// 3. PlantUML SourceStringReader 생성
			SourceStringReader reader = new SourceStringReader(plantUMLSource);

			// 4. 다이어그램 렌더링 및 파일로 저장
			// null은 DescOption 객체로, 기본 옵션을 사용함을 의미합니다.
			reader.outputImage(out);

//			System.out.println("PlantUML 다이어그램이 성공적으로 생성되었습니다: " + outputFilePath);
		} catch (IOException e) {
			System.err.println("다이어그램 생성 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	

}
