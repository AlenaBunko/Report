package my.project.report.service.impl;

import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;
import my.project.report.repository.ICostsDAO;
import my.project.report.service.ICostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class CostsService implements ICostsService {

    private final ICostsDAO costsDAO;

@Autowired
    public CostsService(ICostsDAO costsDAO) {
        this.costsDAO = costsDAO;
    }

    @Override
    public Costs createCosts(User user, String product, Long purchaseAmount, Date date, Integer warrantyPeriod) throws IOException {
        Costs costs = new Costs();
        costs.setProduct(product);
        costs.setUser(user);
        costs.setPurchaseAmount(purchaseAmount);
        costs.setDate(date);
        costs.setWarrantyPeriod(warrantyPeriod);
        costsDAO.save(costs);
        return costs;
    }

    @Override
    public Costs getById(Long id) throws CostsNotFoundException {
        return costsDAO.getById(id).orElseThrow(CostsNotFoundException::new);
    }

}
