FROM maven as build 
WORKDIR /demo
COPY . .
RUN mvn install:install-file "-Dfile=your-artifact-1.0.jar" "-DpomFile=your-pom.xml" "-Dsources=src.jar" "-Djavadoc=apidocs.jar" "-DgroupId=org.some.group" "-DartifactId=your-artifact" "-Dversion=1.0" "-Dpackaging=jar" "-Dclassifier=sources" "-DgeneratePom=true" "-DcreateChecksum=true"

FROM openjdk:11.0
WORKDIR /demo
COPY --from=build /demo/target/demo.jar /demo/
EXPOSE 8080
CMD ["java","-jar","demo.jar"]
