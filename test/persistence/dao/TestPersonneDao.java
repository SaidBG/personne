package persistence.dao;

import business.entite.Personne;
import persistence.exception.DaoException;
import persistence.pere.TU_Pere;

public class TestPersonneDao extends TU_Pere{
	
	Personne pers;
	PersonneDao persDao;
	int nbLigne;
	
	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		pers = new Personne();
		persDao = new PersonneDao();
		nbLigne = 4;
	}
	
	
	public void testCreate() throws DaoException {
		pers = new Personne(50, "Tailor", "Jimmy", 25, "M");
		persDao.create(pers);
		assertEquals(persDao.findList().get((int)pers.getId()-1).getPrenom(), pers.getPrenom());
	}
	
	public void testDelete() throws DaoException {
		pers = new Personne(50, "Tailor", "Jimmy", 25, "M");
		persDao.create(pers);
		persDao.deleteById((int)pers.getId()-1);
		assertEquals(persDao.findList().size(), nbLigne);
	}

}
