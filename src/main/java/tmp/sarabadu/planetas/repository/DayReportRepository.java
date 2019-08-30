package tmp.sarabadu.planetas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tmp.sarabadu.planetas.model.DayReport;

@Repository
public interface DayReportRepository extends CrudRepository<DayReport, Long> {

}
