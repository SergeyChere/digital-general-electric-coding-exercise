FROM openjdk:11
ADD /out/artifacts/digital_general_electric_test_task_jar/digital-general-electric-test-task.jar main.jar
ENTRYPOINT ["java", "-jar", "main.jar"]