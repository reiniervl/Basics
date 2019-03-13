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
		String sql = "INSERT INTO Concepts (uuid, description, examples, tags) VALUES(?, ?, ?)";
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
		String sql = "DELETE FROM Concepts WHERE uuid = ?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, concept.getUuid().toString());
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public void updateConcept(Concept concept) {
		String sql = "UPDATE Concepts SET description=?, examples=? WHERE uuid = ?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, concept.getDescription());
			stmt.setString(2, concept.getExamples());
			stmt.setString(3, concept.getUuid().toString());
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public Concept getConcept(UUID uuid) {
		Concept c = null;
		String sql = "SELECT description, examples FROM Concepts WHERE uuid = ?";
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
		String sql = "INSERT INTO Tags (uuid, name) VALUES(?, ?)";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, tag.getUUID().toString());
			stmt.setString(2, tag.getName());
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public void addTags(Tag... tag) {
		for(Tag t : tag) addTag(t);
	}

	public void deleteTag(Tag tag) {
		String sql = "DELETE FROM Tags WHERE uuid=?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, tag.getUUID().toString());
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	public Tag getTag(UUID uuid) {
		Tag t = null;
		String sql = "SELECT name FROM Tags WHERE uuid=?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, uuid.toString());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString(1);
				t = new Tag(uuid, name);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return t;
	}

	public Tag getTag(String name) {
		Tag t = null;
		String sql = "SELECT uuid FROM Tags WHERE name=?";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				UUID u = UUID.fromString(rs.getString(1));
				t = new Tag(u, name);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return t;
	}

}
