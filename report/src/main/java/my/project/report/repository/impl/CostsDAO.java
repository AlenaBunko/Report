package my.project.report.repository.impl;

import my.project.report.model.Costs;
import my.project.report.repository.ICostsDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Repository
@Transactional
public class CostsDAO implements ICostsDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void save(Costs costs) {
        manager.persist(costs);
    }

    @Override
    public Optional<Costs> getById(Long id) {
        return Optional.of(manager.find(Costs.class, id));
    }

}
