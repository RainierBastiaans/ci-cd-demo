FROM maven:3 as builder
WORKDIR .
COPY . .
RUN set -x
RUN apt update && apt install sudo
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN sudo apt install ./google-chrome-stable_current_amd64.deb
RUN google-chrome --version
RUN mvn -f ./pom.xml clean package

FROM tomcat:9
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT"]
COPY --from=builder ./target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
