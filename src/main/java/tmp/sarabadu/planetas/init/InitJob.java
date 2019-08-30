package tmp.sarabadu.planetas.init;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.DayReport;
import tmp.sarabadu.planetas.model.PeriodsReport;
import tmp.sarabadu.planetas.model.Planet;
import tmp.sarabadu.planetas.model.SolarSystem;
import tmp.sarabadu.planetas.model.SpaceObject;
import tmp.sarabadu.planetas.model.Star;
import tmp.sarabadu.planetas.model.TotalReport;
import tmp.sarabadu.planetas.service.WeatherService;


@Component
public class InitJob {

	private WeatherService weatherServ;
	private Integer yearsQty;
	private Integer daysPerYear;
	
		
	public InitJob(@Autowired WeatherService weatherServ, 
			       @Value("${init.year.qty:10}") Integer yearsQty, 
			       @Value("${init.year.days:365}") Integer daysPerYear) {
		super();
		this.weatherServ = weatherServ;
		this.yearsQty = yearsQty;
		this.daysPerYear = daysPerYear;
	}

	public TotalReport init() {
		
		SpaceObject sun = new Star(0., 0.);
		List<Planet> planets = new ArrayList<Planet>();
		
		planets.add(new Planet(sun,0,500,1));
		planets.add(new Planet(sun,0,2000,3));
		planets.add(new Planet(sun,0,1000,-5));
		
		SolarSystem solarSys = new SolarSystem(sun, planets);
		
		
		List<DayReport> repotList = generateDailyReport(solarSys);
	
		TotalReport totalReport = new TotalReport(WeatherEnum.values());
		DayReport lastReport = null;
				
		
		for (DayReport dayReport : repotList) {

			if (weatherChanged(lastReport, dayReport)) {
				List<DayReport> dayList = new ArrayList<DayReport>();
				dayList.add(dayReport);
				
				totalReport.getPeriods()
						   .get(dayReport.getWeather()).add(dayList);
						   
				
			} else {
				List<List<DayReport>> listPeriods = totalReport.getPeriods().get(dayReport.getWeather());
				List<DayReport> dayList = listPeriods.get(listPeriods.size()-1);
				dayList.add(dayReport);
			}
			
			if (totalReport.getMaxLluviaIndex() == null || totalReport.getMaxLluviaIndex() < dayReport.getRainIndex()) {
				totalReport.setMaxLluviaDay(dayReport.getDay());
				totalReport.setMaxLluviaIndex(dayReport.getRainIndex());
			}
			lastReport = dayReport;
			
		}
		return totalReport;
	}

	private boolean weatherChanged(DayReport lastReport, DayReport dayReport) {
		return lastReport == null || !lastReport.getWeather().equals(dayReport.getWeather());
	}

	private List<DayReport> generateDailyReport(SolarSystem solarSys) {
		
		return Stream.iterate(solarSys, p -> p.advance(1))
									  .limit(yearsQty * daysPerYear)
									  .map(s2 ->  DayReport.builder()
												  .weather(weatherServ.getWeather(s2))
												  .rainIndex(weatherServ.getLluviaIndex(s2))
												  .day(s2.getDay())
												  .ferengi(s2.getPlanets().get(0))
												  .betasoide(s2.getPlanets().get(1))
												  .vulcano(s2.getPlanets().get(2))
												  .build()
												  
									  )
									  
									  .collect(Collectors.toList());
		 
	}
}
