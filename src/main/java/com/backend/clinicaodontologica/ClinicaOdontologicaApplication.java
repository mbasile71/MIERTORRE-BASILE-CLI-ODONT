package com.backend.clinicaodontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	private static Logger logger = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		createDb();
		logger.info("ClinicaOdontologica is now running...");
	}

	//Pedimos a la libreria que nos disponibilice un ModelMapper
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}


	private static void createDb() {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/bdcodontol;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
