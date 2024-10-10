# Inspiration Service

## Sometimes all we need is a little inspiration
Isn't that right guys?

## Environment Variables

| Name                                   | Description                               | Example Value             |
|----------------------------------------|-------------------------------------------|---------------------------|
| ZEN_QUOTES_URL                         | The URL of the Zen Quotes API             | https://zenquotes.io/api  |
| PEXELS_URL                             | The URL of the Pexels API                 | https://api.pexels.com/v1 |
| PEXELS_API_KEY                         | The API key used to access the Pexels API | 123456789                 |
| INSPIRATION_FOR_THE_BOYS_DISCORD_TOKEN | The access token for the bot              | 123456789                 |

## Purpose of service
End goal is to grab a random quote, grab a random image, smush the quote on the image, voila. So inspiring.

## Building and Running the service
Ensure all listed environment variables are set before build/run of the service.

### Using local

1. Run `make build` to package the service into a JAR file.
2. Run `./start.sh` to run the program.

### Using Docker
The Dockerfile uses a multi-stage build. It will build the project within the container before copying the necessary files into the final image.

1. Run `docker build --tag inspiration-service .`
2. Run `docker run --interactive --tty -p 8080:8080 --env ZEN_QUOTES_URL --env PEXELS_URL --env PEXELS_API_KEY --env INSPIRATION_FOR_THE_BOYS_DISCORD_TOKEN --env SPRING_PROFILES_ACTIVE=test inspiration-service`

The service should now be accessible in localhost on port 8080.