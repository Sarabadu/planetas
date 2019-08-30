package tmp.sarabadu.planetas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import tmp.sarabadu.planetas.enums.WeatherEnum;

@Data
@Builder
@Entity
public class DayReport {

	@Id
	private long day;

	private WeatherEnum weather;

	@Transient
	@JsonIgnore
	private SpaceObject ferengi;
	@Transient
	@JsonIgnore
	private SpaceObject betasoide;
	@Transient
	@JsonIgnore
	private SpaceObject vulcano;
	private Double rainIndex;

	public DayReport() {
		super();
	}

	public DayReport(long day, WeatherEnum weather, SpaceObject ferengi, SpaceObject betasoide, SpaceObject vulcano,
			Double rainIndex) {
		super();
		this.day = day;
		this.weather = weather;
		this.ferengi = ferengi;
		this.betasoide = betasoide;
		this.vulcano = vulcano;
		this.rainIndex = rainIndex;
	}

}
