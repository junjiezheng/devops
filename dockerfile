FROM daocloud.io/library/java:8u40-jdk
COPY devops.jar /usr/local/
WORKDIR /usr/local
CMD java -jar devops.jar