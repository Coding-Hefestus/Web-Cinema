package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import DAO.MovieDAO;
import model.Dimension;
import model.Genre;
import model.Movie;

public class TestMain {

	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//ArrayList<Movie> movies = (ArrayList<Movie>) MovieDAO.getAll();
		//System.out.println(movies.size());
		//Movie m = movies.get(0);
		//System.out.println(m.getDuration() + " " + m.getId() + " " + m.isActive());
		if (Dimension.IID.equals(Dimension.valueOf("IID"))) {
			System.out.println("AF");
		}
		
		HashSet<Genre> set = new HashSet<Genre>();
		
		Genre g1 = new Genre(1, true, "Comedy");
		Genre g2 = new Genre(1, true, "Comedy");
		
		set.add(g1);
		set.add(g2);
		
		System.out.println(set.size());
		
		
		
	}

}
