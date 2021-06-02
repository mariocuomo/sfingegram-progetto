FROM openjdk:16-jdk 

ADD build/libs/enigmi.jar enigmi.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "enigmi.jar"]
