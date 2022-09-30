# stop services
docker-compose -f docker-compose.yml down

# clean host
docker container prune -f

# start services
docker-compose -f docker-compose.yml up -d
