package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class CompanyService implements ICompanyService {

    private ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Company> getAllCompanies() {
        return repository.listAll();
    }

    @Override
    public boolean saveCompany(Company newCompany) {
        return repository.save(newCompany);
    }

}
