set path_1=%cd:\=/%
set path_1=/%path_1:C:=c%
echo %path_1%
mkdir C:\maven-repo
docker-machine start default
docker image prune -f
docker run -it --rm -v "%path_1%":/usr/src/mymaven -v /c/maven-repo:/root/.m2 -w /usr/src/mymaven maven mvn clean package -Dmaven.test.skip=true
docker-compose up --build
