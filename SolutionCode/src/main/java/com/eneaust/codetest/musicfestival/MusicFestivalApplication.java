package com.eneaust.codetest.musicfestival;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
public class MusicFestivalApplication {
	static Logger logger = LoggerFactory.getLogger(MusicFestivalApplication.class);

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		SpringApplication.run(MusicFestivalApplication.class, args);

	}

	@Bean(name = "musicFestRestTemp")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
