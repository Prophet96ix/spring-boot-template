# define images
IMAGE=spring-api

# stop services
docker stop $IMAGE

# clean images
docker image rm $IMAGE
docker container prune -f

# build new image
docker build -t $IMAGE .

# start services
 docker run -d -p 8080:8080 --name $IMAGE $IMAGE
