package ma.projet.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Employe;
import ma.projet.beans.Message;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

/**
 *
 * @author HP
 */
public class MessageService implements IDao<Message> {

    private EmployeService es;

    public MessageService() {
        es = new EmployeService();
    }

    @Override
    public boolean create(Message o) {
        try {
            String req = "INSERT INTO message (objet, sujet, date, idE, idR) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getObject());
            ps.setString(2, o.getSujet());
            ps.setDate(3, new java.sql.Date(o.getDate().getTime()));
            ps.setInt(4, o.getEmpEmetteur().getId());
            ps.setInt(5, o.getEmpRecepteur().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Message o) {
        // Implémentation de la mise à jour si nécessaire
        return false;
    }

    @Override
    public boolean delete(Message o) {
        // Implémentation de la suppression si nécessaire
        return false;
    }

    @Override
    public Message findById(int id) {
        Message message = null;
        try {
            String req = "SELECT * FROM message WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                message = new Message(
                    rs.getInt("id"),
                    rs.getString("objet"),
                    rs.getString("sujet"),
                    rs.getDate("date"),
                    es.findById(rs.getInt("idE")),
                    es.findById(rs.getInt("idR"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        try {
            String req = "SELECT * FROM message";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("id"),
                    rs.getString("objet"),
                    rs.getString("sujet"),
                    rs.getDate("date"),
                    es.findById(rs.getInt("idE")),
                    es.findById(rs.getInt("idR"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
}
