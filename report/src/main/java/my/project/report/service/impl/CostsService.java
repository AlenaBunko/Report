package my.project.report.service.impl;

import my.project.report.lib.dto.CostsDTO;
import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;
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

    private final CostsDAO costsDAO;

@Autowired
    public CostsService(CostsDAO costsDAO) {
        this.costsDAO = costsDAO;
    }

    @Override
    public Costs createCosts(User user, String product, Long purchaseAmount, LocalDate date, String warrantyPeriod) throws IOException {
        Costs cost = new Costs();
        cost.setProduct(product);
        cost.setPurchaseAmount(purchaseAmount);
        cost.setDate(date);
        cost.setWarrantyPeriod(warrantyPeriod);
        costsDAO.save(cost);
        return cost;
    }

    @Override
    public Costs getById(Long id) throws CostsNotFoundException {
        return costsDAO.getById(id).orElseThrow(CostsNotFoundException::new);
    }

    @Override
    @Transactional
    public Costs updateCosts(Long costsId, String product, Long purchaseAmount, LocalDate date, String warrantyPeriod) throws IOException, CostsNotFoundException {
        Costs cost = getById(costsId);
        cost.setProduct(product);
        cost.setPurchaseAmount(purchaseAmount);
        cost.setDate(date);
        cost.setWarrantyPeriod(warrantyPeriod);
        costsDAO.update(cost);
        return cost;
    }

    @Override
    public void updateCosts(Long id, CostsDTO costsDTO) throws CostsNotFoundException {
        Optional<Costs> opt = costsDAO.getById(id);
        if (opt.isPresent()) {
            Costs cost = opt.get();
            cost.setProduct(costsDTO.getProduct());
            cost.setPurchaseAmount(costsDTO.getPurchaseAmount());
            cost.setDate(costsDTO.getDate());
            cost.setWarrantyPeriod(costsDTO.getWarrantyPeriod());
            costsDAO.update(cost);
        } else {
            throw new CostsNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        costsDAO.delete(id);
    }
}
