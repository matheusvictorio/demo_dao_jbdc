package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDaoJDBC implements DepartmentDao {
    private java.sql.Connection con;

    public DepartmentDaoJDBC(java.sql.Connection con) {
        this.con = con;
    }

    @Override
    public void insert(model.entities.Department department) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(
                    "INSERT INTO department " +
                            "(Name) " +
                            "VALUES " +
                            "(?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, department.getName());
            int rows = st.executeUpdate();
            if (rows > 0) {
                java.sql.ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(model.entities.Department department) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?"
            );
            st.setString(1, department.getName());
            st.setInt(2, department.getId());

            st.executeUpdate();
    }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(
                    "DELETE FROM department " +
                            "WHERE Id = ?"
            );
            st.setInt(1,id);
           int rows = st.executeUpdate();
           if (rows == 0){
               throw new DbException("Erro ao deletar o registro, Insira um ID válido");
           }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public model.entities.Department findById(Integer id) {
        PreparedStatement st = null;
        java.sql.ResultSet rs = null;
        try {
            st = con.prepareStatement(
                    "SELECT * FROM department " +
                            "WHERE Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                model.entities.Department department = new model.entities.Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                return department;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public java.util.List<model.entities.Department> findAll() {
        PreparedStatement st = null;
        java.sql.ResultSet rs = null;
        try {
            st = con.prepareStatement(
                    "SELECT * FROM department"
            );

            rs = st.executeQuery();

            java.util.List<model.entities.Department> departments = new java.util.ArrayList<>();
            while (rs.next()) {
                model.entities.Department department = new model.entities.Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                departments.add(department);
            }
            return departments;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
