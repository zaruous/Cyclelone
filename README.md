Cyclelone
소개
Cyclelone은 Javalin 프레임워크를 사용하여 개발된 경량 Java 웹 애플리케이션입니다. 이 프로젝트는 RESTful API를 통해 두 가지 주요 기능을 제공합니다.

UML 다이어그램 생성: PlantUML 소스 코드를 입력받아 PNG 이미지 형식의 다이어그램을 생성합니다.

음악 생성 API 연동: Suno와 같은 외부 음악 생성 API와 연동하여 프록시 역할을 수행합니다.

주요 기능
동적 핸들러 라우팅: urlmapping.json 파일을 통해 API 경로, 컨트롤러 클래스, 메서드 등을 동적으로 매핑합니다. 이를 통해 라우팅 규칙을 코드 수정 없이 쉽게 변경할 수 있습니다.

PlantUML 연동: UMLController는 PlantUML 라이브러리를 사용하여 사용자가 제공한 텍스트 기반 UML 정의를 이미지로 변환합니다. 예를 들어, getInvLot.puml과 같은 소스 파일은 getInvLot.png와 같은 시퀀스 다이어그램 이미지로 변환될 수 있습니다.

Suno API 프록시: SunoController는 SunoRequest 객체를 받아 Suno API에 요청을 전달하는 프록시 엔드포인트 역할을 합니다.

기술 스택
언어: Java 21

빌드 도구: Maven

웹 프레임워크: Javalin

주요 라이브러리:

net.sourceforge.plantuml:plantuml-mit: UML 다이어그램 생성

com.fasterxml.jackson.core:jackson-databind: JSON 데이터 처리

org.apache.httpcomponents:httpclient: HTTP 요청 처리

org.junit.jupiter: 테스트

시작하기
1. 필수 환경

Java Development Kit (JDK) 21 이상

Maven

2. 프로젝트 빌드

Bash

mvn clean install
3. 애플리케이션 실행
프로젝트의 메인 클래스인 com.kyj.api.cyclelone.Starter를 실행합니다.

Bash

java -jar target/cyclelone-0.0.1-SNAPSHOT.jar
애플리케이션은 기본적으로 11022 포트에서 시작됩니다.

API 엔드포인트
urlmapping.json에 정의된 주요 엔드포인트는 다음과 같습니다:

경로	메서드	설명
/uml	POST	UML 생성 컨텐츠를 받아 UML 다이어그램 이미지를 반환합니다.
/suno	POST	Suno API를 통해 음악을 생성하기 위한 프록시 역할을 수행합니다. <추후 작성 예정>

Sheets로 내보내기
예제
stdlib/getInvLot.puml에 정의된 시퀀스 다이어그램은 getInvLot.png와 같은 이미지로 생성됩니다.

getInvLot 시퀀스 다이어그램 예시
