package com.simple.apigateway;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class Prefilter implements GlobalFilter {

	final Logger logger = LoggerFactory.getLogger(Prefilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("It's a pre-filter");
		
		String reqPath = exchange.getRequest().getPath().toString();
		logger.info("Req Path : " + reqPath);
		
		HttpHeaders headers = exchange.getRequest().getHeaders();
		
		Set<String> headerNames = headers.keySet();
		headerNames.forEach((headerName)-> {
			logger.info("Header Name :" + headers.getFirst(headerName));
		}
		);
		
		return chain.filter(exchange);
	}

}
