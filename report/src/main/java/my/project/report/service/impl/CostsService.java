package my.project.report.service.impl;

import my.project.report.lib.dto.CostsDTO;
import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;
import my.project.report.repository.ICostsDAO;
import my.project.report.repository.impl.CostsDAO;
import my.project.report.service.ICostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CostsService implements ICostsService {

    private final ICostsDAO costsDAO;

@Autowired
    public CostsService(ICostsDAO costsDAO) {
        this.costsDAO = costsDAO;
    }

    @Override
    public Costs createCosts(User user, String product, Long purchaseAmount, LocalDate date, Integer warrantyPeriod) throws IOException {
        Costs costs = new Costs();
        costs.setProduct(product);
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

    @Override
    @Transactional
    public Costs updateCosts(Long id, String product, Long purchaseAmount, LocalDate date, Integer warrantyPeriod) throws IOException, CostsNotFoundException {
        Costs costs = getById(id);
        costs.setProduct(product);
        costs.setPurchaseAmount(purchaseAmount);
        costs.setDate(date);
        costs.setWarrantyPeriod(warrantyPeriod);
        costsDAO.update(costs);
        return costs;
    }

    @Override
    public void updateCosts(Long id, CostsDTO costsDTO) throws CostsNotFoundException {
        Optional<Costs> opt = costsDAO.getById(id);
        if (opt.isPresent()) {
            Costs costs = opt.get();
            costs.setProduct(costsDTO.getProduct());
            costs.setPurchaseAmount(costsDTO.getPurchaseAmount());
            costs.setDate(costsDTO.getDate());
            costs.setWarrantyPeriod(costsDTO.getWarrantyPeriod());
            costsDAO.update(costs);
        } else {
            throw new CostsNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        costsDAO.delete(id);
    }
}
