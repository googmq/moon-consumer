FROM tomcat:tomcat:9.0.83-jdk17-corretto-al2

MAINTAINER d@mail.com

ADD ./target/xx.war /usr/local/tomcat/webapps/

EXPOSE 8080

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]