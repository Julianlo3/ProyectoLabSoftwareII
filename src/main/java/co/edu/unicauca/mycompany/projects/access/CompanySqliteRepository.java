package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.*;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del repositorio con Sqlite
 *
 * @author Libardo, Julio
 */
public class CompanySqliteRepository implements ICompanyRepository {

    private Connection conn;

    @Override
    public boolean save(Company newCompany) {
        try {
            //Validate product
            if (newCompany == null || newCompany.getNit().isBlank() || newCompany.getName().isBlank()) {
                return false;
            }
            
            this.connect();
            this.initializeDatabase();
            String sql = "INSERT INTO company (nit, name, phone, pageWeb, sector, email, password ) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCompany.getNit());
            pstmt.setString(2, newCompany.getName());
            pstmt.setString(3, newCompany.getPhone());
            pstmt.setString(4, newCompany.getPageWeb());
            pstmt.setString(5, newCompany.getSector().toString());
            pstmt.setString(6, newCompany.getEmail());
            pstmt.setString(7, newCompany.getPassword());
            pstmt.executeUpdate();
            
            this.disconnect();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Company> listAll() {
        List<Company> companies = new ArrayList<>();
        try {
            this.connect();
            this.initializeDatabase();
            String sql = "SELECT nit, name, phone, pageWeb, sector, email, password FROM company";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Company newCompany = new Company();
                newCompany.setNit(rs.getString("nit"));
                newCompany.setName(rs.getString("name"));
                newCompany.setPhone(rs.getString("phone"));
                newCompany.setPageWeb(rs.getString("pageWeb"));
                newCompany.setSector(Sector.valueOf(rs.getString("sector").toUpperCase()));
                newCompany.setEmail(rs.getString("email"));
                newCompany.setPassword(rs.getString("password"));
                
                companies.add(newCompany);
            }
            
            this.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }
    
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:.\\\\miDatabase.db";
        String url = "jdbc:sqlite:C:\\Users\\lopez\\OneDrive\\Escritorio\\Cosas\\Uni\\2025 -1\\L. Software II\\Proyecto\\AcademicProjects\\DataBase\\MyDataBase.db";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void initializeDatabase () {
    String sql = "CREATE TABLE IF NOT EXISTS company (" +
                    "nit TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "phone TEXT, " +
                    "pageWeb TEXT, " +
                    "sector TEXT, " +
                    "email TEXT, " +
                    "password TEXT" +
                    ")";
    try 
    {
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System. out.println ("Database initialized successfully.");
    }
        catch (SQLException e) {
        e.printStackTrace () ;
        }
    }
}
