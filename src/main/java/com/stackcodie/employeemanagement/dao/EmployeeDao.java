package com.stackcodie.employeemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.stackcodie.employeemanagement.beans.Employee;

public class EmployeeDao {

	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(Employee emp) {
		String sql = "insert into employee(name,salary,designation) values('"+emp.getName()+"',"+emp.getSalary()+",'"+emp.getDesignation()+"')";
		return template.update(sql);
	}
	
	@SuppressWarnings("deprecation")
	public Employee getEmployeeById(int id) {
		String sql = "Select * from employee where id = ?";
		return template.queryForObject(sql, new Object[] {id},new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
	public int update(Employee emp) {
        String sql = "UPDATE employee SET name = ?, salary = ?, designation = ? WHERE id = ?";
        return template.update(sql, emp.getName(), emp.getSalary(), emp.getDesignation(), emp.getId());
    }
	
	public int delete(int id) {
		String sql = "delete from employee where id = "+id+"";
		return template.update(sql);
	}
	public List<Employee> getEmployees(){
		return template.query("select * from employee",new RowMapper<Employee>(){
			public Employee mapRow(ResultSet rs, int row)throws SQLException{
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				
				return e;
			}
		});
	}
}
