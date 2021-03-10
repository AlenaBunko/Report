package my.project.report.repository;

import my.project.report.model.Costs;

import java.util.Optional;

public interface ICostsDAO {

    public void save(Costs costs);

    void update(Costs costs);

    Optional<Costs> getById(Long id);

    void delete(Long id);
}
