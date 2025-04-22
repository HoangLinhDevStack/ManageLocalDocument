package com.manager.doc.dao.department;

import com.manager.doc.dao.interact.InteractDao;
import com.manager.doc.model.department.Department;
import com.manager.doc.model.department.DepartmentWork;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    private static void scanDepartment(Set<Department> departmentSet, ResultSet rs) throws SQLException {

        while (rs.next()) {
//                * Table department
            int idDepartment = rs.getInt("IDDepartment");
            String name = rs.getString("Name");
            String manager = rs.getString("Manager");
            int numberOfMember = rs.getInt("NumberOfMember");


            Object[] departmentFieldList = new Object[]{idDepartment, name, manager, numberOfMember};

            Department department = new Department();

            for (Object departmentWorkArray : departmentFieldList) {
                if(departmentWorkArray != null) {
                    department.setId(idDepartment);
                    department.setName(name);
                    department.setManager(manager);
                    department.setNumberOfMember(numberOfMember);

                    departmentSet.add(department);
                }
            }
        }

    }

    private static void scanDepartmentWork(Set<DepartmentWork> departmentWorkSet, ResultSet rs) throws SQLException {

        while(rs.next()) {
            //                * Table department_work
            int idDepartmentWork = rs.getInt("IDDepartmentWork");
            String position = rs.getString("position");

            Object[] departmentWorks = new Object[]{idDepartmentWork, position};

            DepartmentWork departmentWork = new DepartmentWork();

            for (Object departmentWorkArray : departmentWorks) {
                if(departmentWorkArray != null) {
                    departmentWork.setId(idDepartmentWork);
                    departmentWork.setPosition(position);

                    departmentWorkSet.add(departmentWork);
                }
            }
        }

    }

    private static void scanDepartmentWorkByIDDepartment(Department department, ResultSet rs) throws SQLException {

        while(rs.next()) {

            int idDepartmentWork = rs.getInt("IDDepartmentWork");
            int idDepartment = rs.getInt("IDDepartment");
            String position = rs.getString("Position");
            String name = rs.getString("Name");
            String manager = rs.getString("Manager");
            int numberOfMember = rs.getInt("NumberOfMember");

            DepartmentWork departmentWork = new DepartmentWork();

            Object[] departmentArray = new Object[]{idDepartmentWork, idDepartment, position, name};

            for (Object eachDepartment: departmentArray) {
                if(eachDepartment != null) {
                    departmentWork.setId(idDepartmentWork);
                    departmentWork.setPosition(position);

                    department.setId(idDepartment);
                    department.setName(name);
                    department.setManager(manager);
                    department.setNumberOfMember(numberOfMember);
                    department.getWork().add(departmentWork);
                }
            }

        }

    }

    private final ResultSetExtractor<Set<Department>> department = new ResultSetExtractor<Set<Department>>() {
        @Override
        public Set<Department> extractData(ResultSet rs) throws SQLException, DataAccessException {

            final Set<Department> departmentSet = new HashSet<>();

            scanDepartment(departmentSet, rs);

            return departmentSet;
        }
    };

    private final ResultSetExtractor<Set<DepartmentWork>> departmentWork = new ResultSetExtractor<Set<DepartmentWork>>() {
        @Override
        public Set<DepartmentWork> extractData(ResultSet rs) throws SQLException, DataAccessException {

            final Set<DepartmentWork> departmentWorkSet = new HashSet<>();

            scanDepartmentWork(departmentWorkSet, rs);

            return departmentWorkSet;
        }
    };

    private final ResultSetExtractor<Department> departmentWorkByIDDepartment = new ResultSetExtractor<Department>() {
        @Override
        public Department extractData(ResultSet rs) throws SQLException, DataAccessException {

            final Department department = new Department();

            scanDepartmentWorkByIDDepartment(department, rs);

            return department;
        }
    };

    @Override
    public Map<Integer, String> fetchDepartment() throws JSQLParserException {
        final String sql = "SELECT IDDepartment, Name FROM Department";  // * SQL query to fetch data from the 'department' table
        return interactDao.keyAndValueRowByRow(sql);  // * Return the final map containing id (int) and value (name department)
    }


    @Override
    public Set<Department> getFullDepartment() {
        final String sql = "SELECT IDDepartment, Name, Manager, NumberOfMember FROM department ";
        return jdbcTemplate.query(sql, department);
    }

    @Override
    public Set<DepartmentWork> getFullDepartmentWork() {
        final String sql = "SELECT IDDepartmentWork, Position FROM department_work";
        return jdbcTemplate.query(sql, departmentWork);
    }

    @Override
    public Department getDepartmentWorkByIDDepartment(Integer IDDepartment) {

        final String sql = "SELECT dw.IDDepartmentWork, d.IDDepartment, dw.Position, d.Name, d.Manager, d.NumberOfMember " +
                " FROM department d " +
                " LEFT OUTER JOIN department_work dw on d.IDDepartment = dw.IDDepartment WHERE d.IDDepartment = ? ";

        return jdbcTemplate.query(sql, new Object[]{IDDepartment}, departmentWorkByIDDepartment);
    }


}

//    SELECT IDDepartmentWork, IDDepartment, Position FROM department_work dw " +
//        "LEFT JOIN department d ON d.department = dw.department_work " +
//        "WHERE IDDepartment = ? and dw.IDDepartmentWork = ?

// * get department work by id
// SELECT dw.IDDepartmentWork, d.IDDepartment, dw.Position, d.name
// FROM department_work dw
// inner join department d on d.IDDepartment = dw.IDDepartment WHERE d.IDDepartment = ?


// inner join user u on u.IDDepartmentWork = dw.IDDepartmentWork
// where IDUser = 1;