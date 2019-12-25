package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import model.Actor;
import model.Director;
import model.Genre;
import model.Movie;

public class MovieDAO {

	//@SuppressWarnings("null")
	public static List<Movie> getAll() throws Exception {
		
		HashMap<Integer, Movie> movies = new  HashMap<Integer, Movie>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT Movie.id, Movie.active, Movie.name, Movie.duration, Movie.productionYear, Movie.description, Movie.distributor, Movie.countryOfOrigin, Acting.idMovie, Actor.id, Actor.active, Actor.name,  MovieGenre.idMovie, Genre.id, Genre.active, Genre.name, Directing.idMovie, Director.id, Director.active, Director.name" 
							+ " FROM Movie" 
							+ " LEFT JOIN Acting ON Movie.id = Acting.idMovie" 
							+ " LEFT JOIN Actor ON Acting.idActor = Actor.id"
							+ " LEFT JOIN MovieGenre ON Movie.id = MovieGenre.idMovie"
							+ " LEFT JOIN Genre ON MovieGenre.idGenre = Genre.id"
							+ " LEFT JOIN Directing ON Movie.id = Directing.idMovie"
							+ " LEFT JOIN Director ON Directing.idDirector = Director.id"
							+ " WHERE Movie.active = 1"
							+ " ORDER BY Movie.id";
			
			pstmt = conn.prepareStatement(query);			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				if (!movies.containsKey(rset.getInt(1))) {
					Movie movie = createMovie(rset);
					movies.put(movie.getId(), movie);
				}
				if(rset.getInt(10) != 0) {
					Actor actor = createActor(rset); 
					movies.get(rset.getInt(1)).getActors().add(actor);
				}
				if (rset.getInt(14) != 0) {
					Genre genre = createGenre(rset);
					movies.get(rset.getInt(1)).getGenres().add(genre);
				}
				
				if (rset.getInt(18) != 0) {
					Director director = createDirectors(rset);
					movies.get(rset.getInt(1)).getDirectors().add(director);
				}

				
			}//od while
			

		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}

		return movies.values().stream().collect(Collectors.toList());
		
		
	} 
	
	
	public static boolean add(Movie newMovie) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		
		try {
			conn.setAutoCommit(false); 
			conn.commit();
			String query = "INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			
			pstmt.setInt(index++, newMovie.isActive() ? 1 : 0);
			pstmt.setString(index++, newMovie.getName());
			pstmt.setInt(index++, newMovie.getDuration());
			pstmt.setInt(index++, newMovie.getProductionYear());
			pstmt.setString(index++, newMovie.getDescription());
			pstmt.setString(index++, newMovie.getDistributor());
			pstmt.setString(index++, newMovie.getCountryOfOrigin());
			

			
			int affectedRows = pstmt.executeUpdate();

	        if (affectedRows != 0) {
	            //
	        	 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	 	            if (generatedKeys.next()) {
	 	            	newMovie.setId(generatedKeys.getInt(1));
	 	            }
	 	            else {
	 	                throw new SQLException("Creating movie failed, no ID obtained.");
	 	            }
	 	        }
	        	 
	        	if (!newMovie.getDirectors().isEmpty()) {
	        		
	        		for (Director d : newMovie.getDirectors()) {
	        			query = "INSERT INTO Directing (idMovie, idDirector) VALUES (?, ?)";
	        			pstmt = conn.prepareStatement(query);
	        			pstmt.setInt(1, newMovie.getId());
	    				pstmt.setInt(2, d.getId());
	    				pstmt.executeUpdate();
	        		}
	        	}
	        	
	        	if (!newMovie.getActors().isEmpty()) {
	        		
	        		for (Actor a : newMovie.getActors()) {
	        			query = "INSERT INTO Acting (idMovie, idActor) VALUES (?, ?)";
	        			pstmt = conn.prepareStatement(query);
	        			pstmt.setInt(1, newMovie.getId());
	    				pstmt.setInt(2, a.getId());
	    				pstmt.executeUpdate();
	        		}

	        	}
	        	
	        	if (!newMovie.getGenres().isEmpty()) {
	        		
	        		for (Genre g : newMovie.getGenres()) {
	        			query = "INSERT INTO MovieGenre (idMovie, idGenre) VALUES (?, ?)";
	        			pstmt = conn.prepareStatement(query);
	        			pstmt.setInt(1, newMovie.getId());
	    				pstmt.setInt(2, g.getId());
	    				pstmt.executeUpdate();
	        		}

	        	}
	        	 
	        	
	        	 
	        	 
	        	 
	        } else {
	        	throw new SQLException("Creating movie failed, no rows affected.");
	        }
	        	
	       conn.commit(); // sada se izvršavaju sve naredbe između 2 commit-a

	       return affectedRows != 0 && newMovie.getId() != 0;

		} catch (Exception ex) {
			try {conn.rollback();} catch (Exception ex1) {ex1.printStackTrace();} // ako je 2. commit neuspešan, vratiti bazu u stanje koje je zapamćeno 1. commit-om
			throw ex;		
		} finally {
			try {conn.setAutoCommit(true);} catch (Exception ex1) {ex1.printStackTrace();} // svaku sledeću naredbu izvršavati odmah
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

	
	}
	
	public static boolean delete(int idMovie) throws SQLException {
		
		Movie m = getById(idMovie);
		m.setActive(false);
		if (update(m)) {
			return true;
		}
		return false;
	}
	
	public static Movie getById(int movieId) throws SQLException {
		HashMap<Integer, Movie> movies = new  HashMap<Integer, Movie>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT Movie.id, Movie.active, Movie.name, Movie.duration, Movie.productionYear, Movie.description, Movie.distributor, Movie.countryOfOrigin, Acting.idMovie, Actor.id, Actor.active, Actor.name,  MovieGenre.idMovie, Genre.id, Genre.active, Genre.name, Directing.idMovie, Director.id, Director.active, Director.name" 
					+ " FROM Movie" 
					+ " LEFT JOIN Acting ON Movie.id = Acting.idMovie" 
					+ " LEFT JOIN Actor ON Acting.idActor = Actor.id"
					+ " LEFT JOIN MovieGenre ON Movie.id = MovieGenre.idMovie"
					+ " LEFT JOIN Genre ON MovieGenre.idGenre = Genre.id"
					+ " LEFT JOIN Directing ON Movie.id = Directing.idMovie"
					+ " LEFT JOIN Director ON Directing.idDirector = Director.id"
					+ " WHERE Movie.active = 1 AND Movie.id = ?";
					

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, movieId);
			
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				if (!movies.containsKey(rset.getInt(1))) {
					Movie movie = createMovie(rset);
					movies.put(movie.getId(), movie);
				}
		         
				if (rset.getInt(10) != 0) {
					Actor actor = createActor(rset); 
					movies.get(rset.getInt(1)).getActors().add(actor);
				}
				
				if (rset.getInt(14) != 0) {
					Genre genre = createGenre(rset);
					movies.get(rset.getInt(1)).getGenres().add(genre);
				}
				
				if (rset.getInt(18) != 0) {
					Director director = createDirectors(rset);
					movies.get(rset.getInt(1)).getDirectors().add(director);
					
				}
					
				

				
				
			}
			
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
	
		
		
		return movies.get(movieId);
	}
	
	
	public static boolean update(Movie movie) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			//active, name, duration, productionYear, description
			String query = "UPDATE Movie SET active = ?, name = ?, duration = ?,  productionYear = ?, description = ? "
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			
			pstmt.setInt(index++, movie.isActive() ? 1 : 0);
			pstmt.setString(index++, movie.getName());
			pstmt.setInt(index++, movie.getDuration());
			pstmt.setInt(index++, movie.getProductionYear());
			pstmt.setString(index++, movie.getDescription());
			pstmt.setInt(index++, movie.getId());

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	
	}
	
	
//	za odredjeno filtriranje...
//	public static List<Movie> getAll(String name, double lowPrice, double highPrice) throws Exception {
//		return new ArrayList<>();
//	}
	private static Movie createMovie(ResultSet rset) throws SQLException {
		Movie movie = new Movie();
		
		int index, id, duration, productionYear;
		String name, description, distributor, countryOfOrigin;
		boolean active;
		index = 1;
		id = rset.getInt(index++);
		movie.setId(id);
		active = false;
		active = (rset.getInt(index++) == 0 ? active : true);
		movie.setActive(active);

		name = rset.getString(index++);
		movie.setName(name);
					
		duration = rset.getInt(index++);
		movie.setDuration(duration);
	
		productionYear = rset.getInt(index++);
		movie.setProductionYear(productionYear);

		description = rset.getString(index++);
		movie.setDescription(description);
		
		distributor = rset.getString(index++);
		movie.setDistributor(distributor);
		
		countryOfOrigin = rset.getString(index++);
		movie.setCountryOfOrigin(countryOfOrigin);
		
		return movie;
	}
	
	
	private static Actor createActor(ResultSet rset) throws SQLException {
		Actor actor = new Actor();
		//int index = 10;
		//int idActor = rset.getInt(10);
		//if (rset.getInt(10).equals("null"))
	
		actor.setId(rset.getInt(10));
	
		actor.setActive(rset.getInt(11) == 0 ? false : true);

		actor.setName(rset.getString(12));
		return actor;
		
	}
	
	private static Genre createGenre(ResultSet rset) throws SQLException{
		Genre genre = new Genre();
		//int index = 14;
		
		genre.setId(rset.getInt(14));
		genre.setActive(rset.getInt(15) == 0 ? false : true);
		genre.setName(rset.getString(16));
		
		return genre;
		
	}
	
	private static Director createDirectors(ResultSet rset) throws SQLException{
		Director director = new Director();
		//int index = 18;
		
		director.setId(rset.getInt(18));
		director.setActive(rset.getInt(19) == 0 ? false : true);
		director.setName(rset.getString(20));
		
		return director;
	}
	
} //od klase
