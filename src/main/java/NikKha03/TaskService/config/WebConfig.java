//package NikKha03.TaskService.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
//
//    @Value("#{'${app-env.allowed-origins}'.split(',')}")
//    private List<String> allowedOrigins;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        logger.info("allowedOrigins: " + allowedOrigins);
//
//        registry.addMapping("/**")
//                .allowedOrigins(String.valueOf(allowedOrigins))
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//        ;
//    }
//
//}
//
