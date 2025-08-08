package com.kyj.api.cyclelone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import net.sourceforge.plantuml.SourceStringReader;

public class UmlTest {
	
	
	

	public static void main(String[] args) throws IOException {
		new UmlTest().test();
	}

	File dir = new File("stdlib");

	public void test() throws IOException {
		// 1. UML 다이어그램

		File file = new File(dir, "getInvLot.puml");
//		Files.newBufferedReader(file.toPath());
		String plantUMLSource = Files.readString(file.getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
		// 2. 출력 파일 경로 설정
		File outputFilePath = new File(dir, file.getName().replace(".puml", ".png"));

		try (OutputStream fos = new FileOutputStream(outputFilePath)) {
			// 3. PlantUML SourceStringReader 생성
			SourceStringReader reader = new SourceStringReader(plantUMLSource);

			// 4. 다이어그램 렌더링 및 파일로 저장
			// null은 DescOption 객체로, 기본 옵션을 사용함을 의미합니다.
			reader.outputImage(fos);

			System.out.println("PlantUML 다이어그램이 성공적으로 생성되었습니다: " + outputFilePath);
		} catch (IOException e) {
			System.err.println("다이어그램 생성 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
