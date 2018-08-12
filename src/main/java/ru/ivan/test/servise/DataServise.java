package ru.ivan.test.servise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import ru.ivan.test.Model.TableEntity;
import ru.ivan.test.Model.UralModel;

@Component
public class DataServise {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<TableEntity> getAll () {
		return jdbcTemplate.query("select * from testh2", new RowMapper<TableEntity> () {
			@Override
			public TableEntity mapRow (ResultSet rs, int rownumber) throws SQLException {
				TableEntity entity = new TableEntity();
				entity.setId(rs.getLong("id"));
				entity.setFirstName(rs.getString("firstName"));
				entity.setLastName(rs.getString("lastName"));
				entity.setAccount1(rs.getLong("account1"));
				entity.setAccount2(rs.getLong("account2"));
				return entity;
			}
		});
	}
		
	
	
	public boolean setAll (List<TableEntity> in) {
		for (TableEntity entity : in) {
			jdbcTemplate.update("insert INTO testh2 (id, firstName, lastName, account1, account2) values (?,?,?,?,?)", 
					entity.getId(), entity.getLastName(), entity.getFirstName(), entity.getAccount1(), entity.getAccount2());
		}
		return true;
	}
	
	public List<List<String>> testSelect () {
		return jdbcTemplate.query("SELECT DATE, Correspondent_Name, MAX(OUTGOING_BALANCE) AS \"MAX BALANS\" FROM URAL GROUP BY DATE, Correspondent_Name", new RowMapper<List<String>>() {
			public List<String> mapRow (ResultSet rs, int rownumber) throws SQLException {
				List<String> list = new ArrayList<>(); 
				list.add(rs.getTimestamp("DATE").toString());
				list.add(rs.getString("Correspondent_Name"));
				list.add(rs.getString("MAX BALANS"));
				//System.out.println(rs.getTimestamp("DATE").toString());
				//System.out.println(rs.getString("Correspondent_Name").toString());
				//System.out.println(rs.getString("MAX BALANS").toString());
				return list;
			}
		});
		
	}
}
