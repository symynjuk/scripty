FROM java:8

MAINTAINER VADYM POLTORATSKY "vadjan87heavymetall@gmail.com"

WORKDIR /

ADD target/scripty-0.0.1-SNAPSHOT.jar scripty.jar

EXPOSE 8090

#CMD java -jar scripty.jar
ENTRYPOINT ["java","-jar","scripty.jar"]
