package com.rvlstudio;

import java.util.List;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConceptSqlDAO implements ConceptDAO {
	private String url, username, password;
	
	private Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			System.out.println(e);
		}
		return con;
	}
	
	public ConceptSqlDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public void addConcept(Concept concept) {
		String sql = "INSERT INTO Concept (uuid, description, examples, tags) VALUES(?, ?, ?)";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, concept.getUuid().toString());
			stmt.setString(2, concept.getDescription());
			stmt.setString(3, concept.getExamples());
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public void deleteConcept(Concept concept) {
		String sql = "DELETE FROM Concept WHERE uuid = ?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, concept.getUuid().toString());
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public void updateConcept(Concept concept) {
		// TODO Auto-generated method stub

	}

	public Concept getConcept(UUID uuid) {
		Concept c = null;
		String sql = "SELECT description, examples FROM Concept WHERE uuid = ?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, uuid.toString());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String d = rs.getString(1);
				String e = rs.getString(2);
				c = new Concept(uuid, d, e);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return c;
	}

	public List<Concept> getByTags(Tag... tags) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTag(Tag tag) {
		// TODO Auto-generated method stub

	}

	public void addTags(Tag... tag) {
		// TODO Auto-generated method stub

	}

	public void deleteTag(Tag tag) {
		// TODO Auto-generated method stub

	}

	public Tag getTag(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tag getTag(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
