package tmp.sarabadu.planetas;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.init.InitJob;
import tmp.sarabadu.planetas.model.TotalReport;

@Slf4j
@SpringBootApplication
public class PlanetasApplication {

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
