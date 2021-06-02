FROM openjdk:11-jdk 

ADD build/libs/connessioni.jar connessioni.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "connessioni.jar"]