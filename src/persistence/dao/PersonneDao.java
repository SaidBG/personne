package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import business.entite.Personne;
import persistence.exception.DaoException;
import persistence.manager.JDBCManagerSolo;

public class PersonneDao implements IDAO<Personne>{
	
	Connection cnx = null;
	
	private final String sql1 = "select * from personne";
	private final static String sql2 = "insert into personne (nom, prenom, sexe, age) values (?,?,?,?)";
	private final static String sql3 = "update equipe set name = ?, budget = ? where id = ?";
	private final static String sql4 = "delete equipe from equipe where id = ?";
	private final static String sql5 = "select * from equipe where id = ?";

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne updateById(Personne pT) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long pId) throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
