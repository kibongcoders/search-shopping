FROM amazoncorretto:17
RUN mkdir /app
ADD /build/libs/search-shopping-0.0.1-SNAPSHOT.jar /app
CMD [ "java", "-jar", "/app/search-shopping-0.0.1-SNAPSHOT.jar" ]