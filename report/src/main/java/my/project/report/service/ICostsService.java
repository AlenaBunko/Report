package my.project.report.service;

import my.project.report.lib.exception.CostsNotFoundException;
import my.project.report.model.Costs;
import my.project.report.model.User;

import java.io.IOException;
import java.util.Date;

public interface ICostsService {

    public Costs createCosts (User user, String product, Long purchaseAmount, Date date, Integer warrantyPeriod) throws IOException;

    Costs getById(Long id) throws CostsNotFoundException;

}
