package first.crmct.config;
/*
Конфигурация для доступа к скриптам JQuery канбан
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/js/**")
//                .addResourceLocations("file:/tmp/");
                .addResourceLocations("file:/Users/antonfrid/IdeaProjects/CRM-CT/src/main/resources/js/");
    }
}
