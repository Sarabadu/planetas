package tmp.sarabadu.planetas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.init.InitJob;
import tmp.sarabadu.planetas.model.DayReport;
import tmp.sarabadu.planetas.model.Planet;
import tmp.sarabadu.planetas.model.SolarSystem;
import tmp.sarabadu.planetas.model.SpaceObject;
import tmp.sarabadu.planetas.model.Star;
import tmp.sarabadu.planetas.model.TotalReport;
import tmp.sarabadu.planetas.service.WeatherService;

@Slf4j
@SpringBootApplication
public class PlanetasApplication  {

	@Autowired
	InitJob initJob;
	
	public static void main(String[] args) {
		SpringApplication.run(PlanetasApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		TotalReport report = initJob.init();
		logger.info("==============Reporte Final===============");
		logger.info("Dias de Sequia: {}", report.getPeriods().get(WeatherEnum.SEQUIA).size());
		logger.info("Dias de Lluvia: {}", report.getPeriods().get(WeatherEnum.LLUVIA).size());
		logger.info("Dias de Optimo: {}", report.getPeriods().get(WeatherEnum.OPTIMO).size());
		logger.info("Dia Pico Maximo de Lluvia: {}", report.getMaxLluviaDay());
		logger.info("Pico Maximo de Lluvia: {}", report.getMaxLluviaIndex());
	}
}
