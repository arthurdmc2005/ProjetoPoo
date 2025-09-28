
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY dist/Barbearia.jar .
CMD ["java", "-jar", "Barbearia.jar"]

CMD ["/bin/sh"]
