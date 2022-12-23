# Docker

## Helpful Commands
```cli
docker image ls #lists your images

docker image rm {image name} #deletes a specific image

docker ps # lists all running containers

docker ps -a # lists all containers (running and not running)

docker stop {container name or id} #stops a container

docker rm {container name} #removes the container

docker logs {container name} #shows container STDOUT logs

docker run -p {host-port}:{container-port} {image-name} # add -d if you want to run in detached mode

docker build -t {name} {Dockerfile path} # tells docker to build an image and to assign it the given name

docker-compose up # tells docker to create and run the services designated in a docker-compose.yml file, can run detached

docker-compose down # tells docker to destroy the services designated in a docker-compose.yml file
```

# Docker
There is an age old problem devopers have had to deal with since coding started: "it works on my machine". The software world is the most complex it has ever been, and odds are it will only continue to grow in complexity. Docker is a solution for simplifying your workflow and removing the issue of manualy creating environments for your application. Docker does this by providing a way of running "containerized" applications, which can be run anywhere docker is present.

# Containerization
According to Docker, a container is "a standard unit of software that packages up code and all its dependencies so the application runs quickly and reliably from one computing environment to another." This includes the necessary "code, runtime, system tools, system libraries and settings."

This is achieved by turning "images" of your application into "containers at runtime. An "Image" are the instructions for the Docker Engine that tell it how to "containerize" your application. Once the container is made, the application is hosted by the Docker Engine and made accessibile via the configurations you provide.

# Container vs Virtual Machine
A virtual machine is clone of a computer that is hosted by another machine. Each virtual machine has the entire operating system, necessary binaries and libraries, and can often times take up to 10+GBs of storage just to be operational. Also, VMs tend to be slow to boot, especially if they are more complex.

Typically a VM would be hosted by an application like Hypervisor, which itself runs on top of the physical machine doing the hosting. The mode of operation can be beneficial in some scenarios, but if you are trying to deploy an app you oftentimes don't need the bloat that comes with a VM setup. This is where container's shine

Using Docker, you only have the one host operating system interacting with the hardware, so you don't have the bloat of multiple OSs running on one machine. Docker itself runs on top of the operating system, and it is the Docker Engine that handles running the various applications you wish to deploy on the system. For example, let's say you want to deploy your application and a database to store user information: you could deploy the app in one container, the db in another, and connect them with a docker-compose.yml file. The images needed for building the application and database will be in the MB of size, not GB, saving you a significant amount of storage space over the long run. 

All that being said, a common deployment strategy you might see is a company using a VM provided by a Cloud Provider to host their containerized applications. This strategy gives you the best of both worlds.

# Docker Workflow
![Docker Workflow via Mircrosoft](life-cycle-containerized-apps-docker-cli.png)

# Dockerfile
A dockerfile is used to create an image: there are some core sections to be familiar with:
- FROM
    - tells Docker what image to use as a base
        - typically determines the underlying OS of the container and any software necessary to perform your commands/start the application
- COPY
    - tells docker what directory/file to copy into the container
        - requires you to provide a name for the copied resource in your container
- WORKDIR
    - tells Docker what to call the work directory within the container
- RUN
    - tells Docker what commands to run before starting your application
        - useful if you need to build your app before running it
- ENTRYPOINT
    - tells Docker what command to execute in order to start your application in the container

```dockerfile
FROM baseImage
COPY content/to/copy(. for all) locationOrName/of/copied/content
WORKDIR /the/workdir/path/of/copied/content
RUN commands for setup if necessary
ENTRYPOINT ["command","to","start","app"]
```

You can layer your images in order to perform more complex operations, like builing a jar file in your container and then executing it. You can assign an alias in the FROM statement in order to reference it's data later in the build process

```dockerfile
FROM builderImage as builder
COPY directory/ copiedDirectory/
RUN build command

FROM runnerImage as runner
COPY --from=builder executable.file copiedExecutable.file
ENTRYPOINT [ "command","-to","start","app" ]
```

# Docker Compose
It is common to run multiple containers in unison with each other in order to fully deploy an application. Instead of running each image individually you can create a Docker Compose file that will instruct Docker on how to deploy your containers without needing to build the containers one by one yourself

```yml
# A Docker Compose must always start with the version tag.
version: '3'

# Docker Compose works with services.
# 1 service = 1 container.
# For example, a server, a client, a database...
# We use the keyword 'services' to start to create services.
services:

  # the name of this serivce(container) is called "app"
  app:
    image: 'imageName'
    # the build section provides docker with the details needed to build the specified image
    build:
      # context tells docker where the dockerfile is: . indicates current directory
      context: .
    # container_name sets the name of the container
    container_name: app
    # ports maps the host port (left side) to the container port (right side)
    ports:
      - 8080:8080
    # depends_on is useful if you need another container to be created first
    depends_on:
      - dependency
    # you can set environment variables in the container
    environment:
      - VARIABLE=VALUE
    # you can map files in a container directory (right side) to a directory outside the container (left side)
    volumes:
      - {directory outside of container}:{directory within container}


  # this is the second container for our deployment
  dependency:

    image: 'anotherImage'
    container_name: requiredContent
    ports:
      - 1234:1234
```
