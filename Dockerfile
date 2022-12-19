FROM maven:3 as builder
WORKDIR .
COPY . .
RUN mvn -f ./pom.xml clean package

FROM tomcat:9
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT"]
COPY --from=builder ./target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]