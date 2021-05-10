FROM openjdk:11-slim
LABEL maintainer="Lukas Milasauskas 'lukas@milasauskas.com'"
WORKDIR /app
COPY build/libs libs/
COPY build/resources resources/
COPY build/classes classes/
ENTRYPOINT ["java", "-Xms256m", "-Xmx2048m", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.mif.pipeline.SamplePipelineApplication"]
EXPOSE 8080
