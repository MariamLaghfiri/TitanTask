package titantask.model;

import titantask.services.ConnectionBaseDonne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Categorie {
	private int id;
	private String name;
	Scanner scanner = new Scanner(System.in);
	ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();
	Connection connection = connectionBaseDonne.connectionBD();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", name=" + name + "]";
	};
	
	
	
	public int ajouterCategorie(){
    
		try {

	        // Create a statement
	        Statement statementCat = connection.createStatement();
	    	
     		System.out.print("new categorie : ");
     		String newcat = scanner.next();
             // Execute a query
             int resultSetCat = statementCat.executeUpdate("INSERT INTO `categorie`( `categorie_nom`) VALUES ('"+newcat+"')");
     		System.out.println("add categorie : "+resultSetCat);
             
             // Close resources
     		statementCat.close();
     		return 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	       
	    }
    };
    
	public int supprimerCategorie() {
        
		System.out.print(" entrez le nom de la Categorie a supprimer : ");
		String categirieSup = scanner.next();
		try {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `categorie` WHERE categorie_nom = '"+categirieSup+"'");
	
			if(verifTache.next()) {
        
	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet = statement.executeUpdate("DELETE FROM `categorie` WHERE categorie_nom = '"+categirieSup+"'");
	    		System.out.println("delete tache : "+resultSet);
	            
	            // Close resources
	            statement.close();

			}else {
				System.out.print("cette categorie n'existe pas");
			}	
			return 1;
		}catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		};
    
    public static void main(String[] args) {
		
		
		Categorie test = new Categorie();
		test.supprimerCategorie();
		
	}
	
	
}
