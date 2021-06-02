FROM openjdk:11-jdk 

ADD build/libs/enigmi-seguiti.jar enigmi-seguiti.jar

ENTRYPOINT ["java", "-jar", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "enigmi-seguiti.jar"]
