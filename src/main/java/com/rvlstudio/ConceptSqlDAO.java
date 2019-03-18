package com.rvlstudio;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ConceptSqlDAO implements ConceptDAO {
	private String url, username, password;
	private static ConceptSqlDAO dao = null;
	
	private Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			System.out.println(e);
		}
		return con;
	}
		
	public static ConceptSqlDAO getDAO(String url, String username, String password) {
		if(dao == null) dao = new ConceptSqlDAO(url, username, password);
		return dao;
	}
	private ConceptSqlDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public void addConcept(Concept concept) {
		String sql = "INSERT INTO Concepts (uuid, description, examples) VALUES(?, ?, ?)";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setObject(1, concept.getUuid().toString(), Types.OTHER);
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
			stmt.setObject(1, uuid.toString(), Types.OTHER);
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
		String sql = "SELECT Concepts.uuid, Concepts.description, Concepts.examples FROM Concepts, Tags, ConceptTags WHERE Concepts.uuid=ConceptTags.Conceptsuuid AND Tags.uuid=ConceptTags.Tagsuuid AND Tags.name=?";
		ArrayList<Concept> cl = new ArrayList<>();
		for(Tag t : tags) {
			try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, t.getName());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					UUID u = UUID.fromString(rs.getString(1));
					String d = rs.getString(2);
					String e = rs.getString(3);
					cl.add(new Concept(u, d, e));
				}
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return cl;
	}

	public List<Concept> getByTags(String... tags) {
		String sql = "SELECT Concepts.uuid, Concepts.description, Concepts.examples FROM Concepts, Tags, ConceptTags WHERE Concepts.uuid=ConceptTags.Conceptsuuid AND Tags.uuid=ConceptTags.Tagsuuid AND Tags.name=?";
		ArrayList<Concept> cl = new ArrayList<>();
		for(String t : tags) {
			try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, t);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					UUID u = UUID.fromString(rs.getString(1));
					String d = rs.getString(2);
					String e = rs.getString(3);
					cl.add(new Concept(u, d, e));
				}
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return cl;
	}

	public void addTag(Tag tag) {
		String sql = "INSERT INTO Tags (uuid, name) VALUES(?, ?)";
		try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setObject(1, tag.getUUID().toString(), Types.OTHER);
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
