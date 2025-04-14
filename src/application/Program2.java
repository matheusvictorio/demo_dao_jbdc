package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department insert =====");
        model.entities.Department department = new model.entities.Department(null, "test");
        departmentDao.insert(department);
        System.out.println("Inserted! New id = " + department.getId());

        System.out.println("\n=== TEST 2: department update =====");
        System.out.println("Enter id for update test: ");
        int id = sc.nextInt();
        department = departmentDao.findById(id);
        department.setName("Human Resources");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println("\n=== TEST 3: department delete =====");
        System.out.println("Enter id for delete test: ");
        id = sc.nextInt();
        departmentDao.delete(id);
        System.out.println("Delete completed");


        System.out.println("\n=== TEST 4: department findbyId =====");
        department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println("\n=== TEST 5: department findAll =====");
        java.util.List<model.entities.Department> list = departmentDao.findAll();
        for (model.entities.Department obj : list) {
            System.out.println(obj);
        }


    }
}
