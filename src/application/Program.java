package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department obj = new Department(1, "Department 1");
        System.out.println(obj);

        Seller obj2 =new Seller(1, "Seller 1", "seller1@gmail.com", new Date(), 1000.0, obj);
        System.out.println(obj2);
    }
}
