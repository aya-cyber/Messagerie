package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Employe;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

/**
 *
 * @author HP
 */
public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
        try {
            String req = "INSERT INTO employe (nom, prenom) VALUES (?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Employe o) {
        try {
            String req = "UPDATE employe SET nom = ?, prenom = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setInt(3, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Employe o) {
        try {
            String req = "DELETE FROM employe WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Employe findById(int id) {
        Employe employe = null;
        try {
            String req = "SELECT * FROM employe WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employe;
    }

    @Override
    public List<Employe> findAll() {
        List<Employe> employes = new ArrayList<>();
        try {
            String req = "SELECT * FROM employe";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employes.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employes;
    }
}
