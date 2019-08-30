package tmp.sarabadu.planetas.init;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.TotalReport;
import tmp.sarabadu.planetas.service.WeatherService;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application.properties")
public class InitJobTest {

	@Autowired
	WeatherService serv;

	@Test
	public void test_one_day() throws Exception {

		InitJob job = new InitJob(serv, 1, 1);
		TotalReport rep = job.init();

		assertThat(rep.getPeriods().get(WeatherEnum.SEQUIA).size(), equalTo(1));
		assertThat(rep.getPeriods().get(WeatherEnum.LLUVIA), empty());
		assertThat(rep.getPeriods().get(WeatherEnum.OPTIMO), empty());
		assertThat(rep.getPeriods().get(WeatherEnum.NORMAL), empty());

	}

	@Test
	public void test_two_days() {
		InitJob job = new InitJob(serv, 1, 2);
		TotalReport rep = job.init();

		assertThat(rep.getPeriods().get(WeatherEnum.SEQUIA).size(), equalTo(1));
		assertThat(rep.getPeriods().get(WeatherEnum.LLUVIA), empty());
		assertThat(rep.getPeriods().get(WeatherEnum.OPTIMO), empty());
		assertThat(rep.getPeriods().get(WeatherEnum.NORMAL).size(), equalTo(1));
	}

	@Test
	public void test_first_rain() {
		InitJob job = new InitJob(serv, 1, 24);
		TotalReport rep = job.init();

		logger.info("reporte {}", rep);

		assertThat(rep.getPeriods().get(WeatherEnum.SEQUIA).size(), equalTo(1));
		assertThat(rep.getPeriods().get(WeatherEnum.LLUVIA).size(), equalTo(1));
		assertThat(rep.getPeriods().get(WeatherEnum.OPTIMO), empty());
		assertThat(rep.getPeriods().get(WeatherEnum.NORMAL).size(), equalTo(1));
		assertThat(rep.getPeriods().get(WeatherEnum.NORMAL).get(0).size(), greaterThan(1));

	}
}
