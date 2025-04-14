package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();


        System.out.println("seller find by department");
        Department department = new Department(1, "teste");
        List<Seller> sellers = sellerDao.findByDepartment(department);
        for (Seller seller : sellers) {
            System.out.println(seller);
        }

        System.out.println("seller find all");
        sellers = sellerDao.findAll();
        for (Seller seller : sellers) {
            System.out.println(seller);
        }

        System.out.println("Seller insert");
        Seller newSeller = new Seller(null, "teste", "teste@teste.com", new Date(), 1000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id: " + newSeller.getId());

        System.out.println("Seller update");
        Seller seller = sellerDao.findById(1);
        seller.setName("Matheus Victorio");
        sellerDao.update(seller);
        System.out.println("Updated!");


        System.out.println("\nSeller delete");
        System.out.println("Enter id to delete");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        sellerDao.delete(id);
        System.out.println("Deleted!");
    }
}
