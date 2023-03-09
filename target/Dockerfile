FROM maven as build 
WORKDIR /demo
COPY . .
RUN mvn install

FROM openjdk:11.0
WORKDIR /demo
COPY --from=build /demo/target/demo.jar /demo/
EXPOSE 8080
CMD ["java","-jar","demo.jar"]
