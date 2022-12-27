# this bash script will be used to create my sports docker image
# I will use this script instead of manually entering in commands
# so I don't accidentally make a spelling mistake

docker build -t esuminski/sports:stdout .
docker push esuminski/sports:stdout