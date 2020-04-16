package br.com.compasso.votacao.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.compasso.votacao.entity.Associate;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket votationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compasso.votacao"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Associate.class)
				 .globalOperationParameters(
	                        Arrays.asList(
	                                new ParameterBuilder()
	                                    .name("Authorization")
	                                    .description("Header para Token JWT")
	                                    .modelRef(new ModelRef("string"))
	                                    .parameterType("header")
	                                    .required(false)
	                                    .build()));
	}
}
