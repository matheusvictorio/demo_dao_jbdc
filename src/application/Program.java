package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("seller find by department");
        Department department = new Department(1, "teste");
        List<Seller> sellers = sellerDao.findByDepartment(department);
        for (Seller seller : sellers) {
            System.out.println(seller);
        }
    }
}
