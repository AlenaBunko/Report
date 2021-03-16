package my.project.report.service;

import my.project.report.lib.dto.CostsDTO;
import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;

import java.io.IOException;
import java.time.LocalDate;

public interface ICostsService {

    public Costs createCosts (User user, String product, Long purchaseAmount, LocalDate date,Integer warrantyPeriod) throws IOException;

    Costs getById(Long id) throws CostsNotFoundException;

    Costs updateCosts (Long costsId, String product, Long purchaseAmount, LocalDate date,Integer warrantyPeriod) throws  IOException, CostsNotFoundException;

    void updateCosts (Long id, CostsDTO costsDTO) throws CostsNotFoundException;

    void delete (Long id);
}
