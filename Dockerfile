FROM openjdk:14-jdk-slim

#GCC 컴파일러 설치
RUN apt-get update && \
    apt-get install -y gcc

# GCC 실행 파일 경로 환경 변수 설정
ENV PATH="/usr/bin:${PATH}"

# 실행 파일의 권한 변경
RUN chmod +x /usr/bin/gcc

# Python 설치
RUN apt-get update && \
    apt-get install -y python3

# Python 실행 파일 경로 환경 변수 설정
ENV PATH="/usr/bin/python3:${PATH}"

# Python 실행 파일에 실행 권한 부여
RUN chmod +x /usr/bin/python3

EXPOSE 8080
ARG JAR_FILE=/build/libs/SejongCodingMate-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]