# 베이스 이미지로 Eclipse Temurin Java 17 JDK를 사용합니다.
FROM eclipse-temurin:17-jdk-alpine
# 애플리케이션 JAR 파일을 컨테이너의 루트 디렉토리에 복사합니다.
COPY ./build/libs/*SNAPSHOT.jar app.jar
# 애플리케이션을 실행하기 위한 명령을 ENTRYPOINT로 설정합니다.
ENTRYPOINT ["java", "-jar", "/app.jar"]
