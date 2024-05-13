FROM azul/zulu-openjdk:21.0.3-jdk as build

WORKDIR /usr/share
RUN set -x && \
    apt-get update && apt-get -y upgrade --no-install-recommends && \
    apt-get -y --no-install-recommends install curl && \
    curl -O https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -xvf apache-maven-*-bin.tar.gz  && \
    rm apache-maven-*-bin.tar.gz && \
    mv apache-maven-* maven && \
    ln -s /usr/share/maven/bin/mvn /bin/

WORKDIR /micronaut

ADD pom.xml .
RUN mvn install -DskipTests

ADD src src
RUN mvn package -DskipTests

FROM azul/zulu-openjdk:21-jre
WORKDIR /micronaut

COPY --from=build /micronaut/target/micronaut.jar ./

CMD ["java", "-jar", "micronaut.jar"]

EXPOSE 8080
