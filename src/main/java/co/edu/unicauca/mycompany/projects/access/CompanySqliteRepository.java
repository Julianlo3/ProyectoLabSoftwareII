package co.edu.unicauca.mycompany.projects.access;

import java.util.List;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;

/**
 * Implementación del repositorio con Sqlite
 *
 * @author Libardo, Julio
 */



public class CompanySqliteRepository implements ICompanyRepository {
    private final String url = "jdbc:sqlite:mycompany.db"; // Cambia el nombre de la base de datos si es necesario

    public CompanySqliteRepository(){
            initializeDatabase();
    }
 
    private void initializeDatabase() {
   
    }

    @Override
    public boolean save(Company newCompany) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Company> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
