SYSTEM COMPATIBILITY:
- windows-based working stations (7 ultimate sp1 64bit, 10 professional 64bit tested)
- gnu/linux-based working stations (ubuntu 14.04 lts 64bit tested)
- macOS-based working stations (macOS 10.14.1 Mojave 64 bit tested)

In order to properly run the scripty application via dockerized environment, please make sure, Your system is fully compliant with the one among given above. 

Docker toolbox for windows 7  https://download.docker.com/win/stable/DockerToolbox.exe
Docker desktop for windows 10  https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe
Docker for ubuntu https://www.digitalocean.com/community/tutorials/docker-ubuntu-16-04-ru
Docker desktop for mac  https://download.docker.com/mac/stable/Docker.dmg 

visual gui for docker
Kinematic:
for windows https://github.com/docker/kitematic/releases/download/v0.17.5/Kitematic-0.17.5-Windows.zip
for ubuntu  https://github.com/docker/kitematic/releases/download/v0.17.5/Kitematic-0.17.5-Ubuntu.zip
for macOS   https://github.com/docker/kitematic/releases/download/v0.17.5/Kitematic-0.17.5-Mac.zip

source code https://github.com/docker/kitematic/archive/v0.17.5.zip
            https://github.com/docker/kitematic/archive/v0.17.5.tar.gz
____________________________________________________________________________________________________________________

RUNNING DOCKER:

Depending on the operating system, please run one matching script file (root of project)

-  docker-compose_init.bat FOR  WINDOWS 7  VIA DOUBLE-CLICK / VIA CONSOLE
-  docker-compose_win10.bat FOR WINDOWS 10 VIA DOUBLE-CLICK / VIA CONSOLE
-  docker-compose_linux.sh FOR UBUNTU      VIA CONSOLE
-  docker-compose_mac.sh FOR MACOS         VIA CONSOLE

*Before running one of the scripts given above, please make sure, to do it from ./ (for ubuntu and macOS)
*In case of asking (build/image replacement), please choose 'y'
*localhost 8090
to stop dockerized container, please press 'ctrl+c'
to prevent file system from excessive expansion, You might want to enter following commands, after every running container's stopped:
docker-compose rm REMOVES DOCKER-COMPOSE CONTAINERS
docker images  SHOWS ALL IMAGES
docker ps -a SHOWS ALL CONTAINERS
docker rm 'docker ps -a -f status=exited -q'  FORCIBLY REMOVES STOPPED CONTAINERS
docker rm `docker ps -a -q` REMOVES ALL CONTAINERS
docker image prune -f FORCIBLY REMOVES ALL IMAGES 
docker volume prune REMOVES VOLUMES (BE CAREFULL)
