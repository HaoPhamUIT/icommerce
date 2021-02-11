package vn.phh.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "vn.phh.product.repository.product",
        mongoTemplateRef = "productTemplate")
public class ProductMongoConfig {
}
