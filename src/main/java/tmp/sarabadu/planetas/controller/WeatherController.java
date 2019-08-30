package tmp.sarabadu.planetas.controller;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tmp.sarabadu.planetas.model.DayReport;
import tmp.sarabadu.planetas.service.DayReportService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	@Autowired
	DayReportService serv;

	@GetMapping
	public ResponseEntity<DayReport> getDay(@RequestParam Long dia) {
		try {
			return new ResponseEntity<>(serv.getDay(dia), HttpStatus.OK);
		} catch (RelationNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
