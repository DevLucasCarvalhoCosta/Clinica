package com.clinica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Configuração do CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define quais caminhos podem ser acessados
        registry.addMapping("/api/**") // Define que todas as rotas que começam com /api são acessíveis
                .allowedOrigins("http://localhost:8082") // Adicione o endereço do seu frontend (caso ele esteja em localhost, use a porta correspondente)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite o envio de credenciais (cookies, autenticação)
    }
}
