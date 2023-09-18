package recipemanager.projekt.recipemanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class RecipemanagerApplication {


		public static void main(String[] args) {
			SpringApplication app = new SpringApplication(RecipemanagerApplication.class);
			app.setAllowBeanDefinitionOverriding(true);
			app.run(args);
		}



	}



