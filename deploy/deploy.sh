# define images
SPRING=spring_api
NGINX=nginx_proxy

# stop services
docker-compose rm -sv -f $SPRING
docker-compose rm -sv -f $NGINX

# clean images
docker image rm $SPRING
docker image rm $NGINX
docker container prune -f

# pull new images
docker build -t $SPRING ../.
docker build -t $NGINX nginx/.

# start services
docker-compose -f docker-compose.yml up -d
