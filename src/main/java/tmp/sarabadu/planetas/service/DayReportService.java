package tmp.sarabadu.planetas.service;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmp.sarabadu.planetas.model.DayReport;
import tmp.sarabadu.planetas.repository.DayReportRepository;

@Service
public class DayReportService {
	@Autowired
	DayReportRepository dayReportDao;

	public void saveAll(List<DayReport> reports) {
		dayReportDao.saveAll(reports);
	}

	public DayReport getDay(Long dia) throws RelationNotFoundException {
		Optional<DayReport> optDay = dayReportDao.findById(dia);
		return optDay.orElseThrow(() -> new RelationNotFoundException());
	}
}
