package br.com.compassuol.pb.challenge.msauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MsAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthorizationApplication.class, args);
	}

}
