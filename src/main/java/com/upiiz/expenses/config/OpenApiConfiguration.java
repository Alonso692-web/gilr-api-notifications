package com.upiiz.expenses.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notifications API",
                description = "Documentacion de la API para notificaciones",
                version = "1.0.0",
                contact = @Contact(
                        name = "Gerardo Isaac Luna Rodarte",
                        email = "glunarl2000@alumno.ipn.mx",
                        url = "http://localhost:8081/contacto"
                ),
                license = @License(),
                termsOfService = "Derechos reservados"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Servidor en Produccion",
                        url = "https://wcbdf-adl-api-expenses.onrender.com"
                )
        },
        tags = {
                @Tag(
                        name = "Notifications",
                        description = "Endpoints para las notificaciones"
                )
        }
)
public class OpenApiConfiguration {

}
