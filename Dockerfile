FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /usr/src

RUN apt-get update && apt-get -y upgrade

# Install maven and make to build the service inside the docker container
RUN apt-get -y install maven
RUN apt-get -y install make

COPY . .

RUN make build

# Remove all files other than the packaged .jar file
RUN rm -rf src/
RUN rm -rf target/
RUN rm Makefile
RUN rm pom.xml

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /usr/src

# Copy the packaged jar file from the build stage to the final image
COPY --from=build /usr/src/ ./

# Copy the start script to run the program
COPY start.sh ./

# Give container rwx permissions for the start script
RUN chmod 777 start.sh

EXPOSE 8080
CMD ["./start.sh"]