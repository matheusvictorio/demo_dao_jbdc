package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(model.entities.Seller seller);
    void update(model.entities.Seller seller);
    void delete(Integer id);
    model.entities.Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);

}
