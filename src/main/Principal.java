package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Principal extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			/* Gérer les éventuelles erreurs ici. */
		}
		String url = "jdbc:mysql://192.168.0.227:8457/Munchkin";
		String utilisateur = "user";
		String motDePasse = "password";
		Connection connexion = null ;
		try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery( "SELECT nom FROM Dungeon;");
		      while ( resultat.next() ) {
		      System.out.println(resultat.getString("nom"));
		      }
			

		} catch ( SQLException e ) {
			/* Gérer les éventuelles erreurs ici */
		} finally {
			if ( connexion != null )
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch ( SQLException ignore ) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				}
		}
		System.out.println("OK");
	}
	
}
