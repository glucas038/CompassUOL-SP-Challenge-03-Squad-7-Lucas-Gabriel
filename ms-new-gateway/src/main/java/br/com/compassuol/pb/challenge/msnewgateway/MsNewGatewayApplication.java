package br.com.compassuol.pb.challenge.msnewgateway;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class MsNewGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNewGatewayApplication.class, args);
	}
/*
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("ms-products/**").uri("lb://ms-products"))
				.route("ms-authorization", r -> r.path("ms-authorization/**")
						.filters(f -> f.modifyRequestBody(String.class, String.class,
								(exchange, s) -> {
									ServerHttpRequest request = exchange.getRequest();
									HttpHeaders headers = request.getHeaders();
									headers.set("Sensitive-Headers", "Cookie, Set-Cookie");
									return Mono.just(s);
								}))
						.uri("lb://ms-authorization"))
				.build();
	}

 */
}
