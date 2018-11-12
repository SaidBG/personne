package business.entite;

public class Personne {
	
	private long id;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Personne() {
	}
	
	public Personne(long id, String nom, String prenom, int age, String sexe) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
