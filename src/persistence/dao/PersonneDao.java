package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import business.entite.Personne;
import persistence.exception.DaoException;
import persistence.manager.JDBCManagerSolo;

public class PersonneDao implements IDAO<Personne>{

	Connection cnx = null;

	private final static String sql1 = "select * from clients";
	private final static String sql1Id = "select * from clients where id = ?";
	private final static String sql2 = "insert into clients (nom, prenom, age, sexe) values (?,?,?,?)";
	private final static String sql3 = "update clients set nom = ?, prenom = ?, age = ?, sexe =  where id = ?";
	private final static String sql4 = "delete clients from clients where id = ?";
	

	@Override
	public Personne create(Personne pT) throws DaoException {
		// TODO Auto-generated method stub

	
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();

			if(!pT.equals(null) && !pT.getNom().equals(null) && !pT.getPrenom().equals(null) && pT.getNom().length() != 0 && pT.getPrenom().length() != 0) {
				PreparedStatement pst = cnx.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setString(1, pT.getNom());
				pst.setString(2, pT.getPrenom());
				pst.setInt(3,pT.getAge());
				pst.setString(4, pT.getSexe());
				pst.execute();

				ResultSet rs = pst.getGeneratedKeys();
				while(rs.next()) {
					long id = rs.getLong("GENERATED_KEY");	
					pT.setId(id);
				}
			}
			
			else {
				pT = null;
			}

		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			try {
				JDBCManagerSolo.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
		return pT;
	}

	@Override
	public Personne findById(long pId) throws DaoException {
		Personne person = null;

		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst1 = cnx.prepareStatement(sql1Id);
			pst1.setLong(1,pId);
			ResultSet rs1 = pst1.executeQuery();
			long id = 0L;
			String nom;
			String prenom;			
			String sexe;
			int age;

			while (rs1.next()) {
				id = rs1.getLong("id");
				nom = rs1.getString("nom");			
				prenom = rs1.getString("prenom");
				sexe = rs1.getString("sexe");
				age = rs1.getInt("age");
				person = new Personne(id, nom,prenom,age,sexe);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Personne> findList() throws DaoException {
		Personne person = null;
		List<Personne> listePersonne = new ArrayList<>();
		
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst1 = cnx.prepareStatement(sql1);			
			ResultSet rs1 = pst1.executeQuery();
			long id = 0L;
			String nom;
			String prenom;			
			String sexe;
			int age;

			while (rs1.next()) {
				id = rs1.getLong("id");
				nom = rs1.getString("nom");			
				prenom = rs1.getString("prenom");
				sexe = rs1.getString("sexe");
				age = rs1.getInt("age");
				person = new Personne(id, nom,prenom,age,sexe);
				listePersonne.add(person);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listePersonne;
	}

	@Override
	public Personne updateById(Personne pT) throws DaoException {
		if (pT == null ) {
			return null;
		}

		try {		

			Connection cnx1 = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps1 = cnx1.prepareStatement(sql3);
			ps1.setString(1,pT.getNom());
			ps1.setString(2, pT.getPrenom());
			ps1.setInt(3, pT.getAge());
			ps1.setString(4, pT.getSexe());
			ps1.setLong(5, pT.getId());
			ps1.execute();


		} catch (Exception e) {
			try {
				JDBCManagerSolo.getInstance().closeConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DaoException(e);
		}


		return pT;
	}

	@Override
	public void deleteById(long pId) throws DaoException {
		// TODO Auto-generated method stub
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst = cnx.prepareStatement(sql4);
			pst.setLong(1, pId);
			pst.execute();
			
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			try {
				JDBCManagerSolo.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}		

	}

}
