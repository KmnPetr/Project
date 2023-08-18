package com.example.project;

import com.example.project.kafka.Producer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProjectApplication {


	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(ProjectApplication.class, args);

		//запуск потока отсылки сообщений в кафку
		Producer kafkaProducer = applicationContext.getBean(Producer.class);
		kafkaProducer.sendMessagesToKafka();
	}

	//это для перемапивания DTO
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
