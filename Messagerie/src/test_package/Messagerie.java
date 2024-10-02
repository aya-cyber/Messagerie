package test_package;

import java.util.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.Message;
import ma.projet.service.EmployeService;
import ma.projet.service.MessageService;

/**
 * Classe principale pour la gestion de la messagerie.
 */
public class Messagerie {

    public static void main(String[] args) {
        // Création d'un service employé
        EmployeService employeService = new EmployeService();
        employeService.create(new Employe("LACHGAR", "Mohamed"));
        employeService.create(new Employe("RAMI", "Ali"));
        employeService.create(new Employe("SAFI", "Fatima"));

        // Recherche d'un employé par son ID et modification s'il existe
        Employe employe = employeService.findById(3);
        if (employe != null) {
            employe.setNom("ALAOUI");
            employe.setPrenom("Manale");
            employeService.update(employe);
        }

        // Suppression d'un employé par son ID (vérification si l'employé existe)
        Employe employeToDelete = employeService.findById(7);
        if (employeToDelete != null) {
            employeService.delete(employeToDelete);
        }

        // Affichage de tous les employés
        for (Employe emp : employeService.findAll()) {
            System.out.println(emp.getNom());
        }

        // Création d'un service message
        MessageService messageService = new MessageService();
        messageService.create(new Message("Réunion", "Réunion de fin d'année", new Date(), employeService.findById(5), employeService.findById(6)));
        messageService.create(new Message("Réunion", "Réunion de fin d'année", new Date(), employeService.findById(5), employeService.findById(8)));
        messageService.create(new Message("Stage", "Stage à Marrakech", new Date(), employeService.findById(6), employeService.findById(5)));
        messageService.create(new Message("Stage", "Stage à Marrakech", new Date(), employeService.findById(6), employeService.findById(8)));

        // Affichage des sujets des messages où le récepteur a un ID de 8
        for (Message message : messageService.findAll()) {
            if (message.getEmpRecepteur() != null && message.getEmpRecepteur().getId() == 8) {
                System.out.println(message.getSujet());
            }
        }
    }
}
