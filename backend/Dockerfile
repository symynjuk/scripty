# Scripty
#
# VERSION 0.0.1

FROM java:8

LABEL maintainer="vadjan87heavymetall@gmail.com"
LABEL Decsription="Scripty - co.inventorsoft Academy autumn 2018 project"

WORKDIR /

ADD target/scripty-0.0.1-SNAPSHOT.jar scripty.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","scripty.jar"]
