mkdir "%UserProfile%\.m2"
docker image prune -f
docker run -it --rm -v "%cd%":/usr/src/mymaven -v "%UserProfile%\.m2":/root/.m2 -w /usr/src/mymaven maven mvn clean package -Dmaven.test.skip=true
docker-compose up --build
