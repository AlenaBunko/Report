package my.project.report.repository;

import my.project.report.model.Costs;

import java.util.Optional;

public interface ICostsDAO {

    public void save(Costs costs);

    Optional<Costs> getById(Long id);

}
