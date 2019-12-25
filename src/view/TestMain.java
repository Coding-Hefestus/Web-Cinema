package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import DAO.MovieDAO;
import model.Actor;
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
//		if (Dimension.IID.equals(Dimension.valueOf("IID"))) {
//			System.out.println("AF");
//		}
		
		HashSet<Actor> set = new HashSet<Actor>();
		
		Actor g1 = new Actor(1, true, "Dwayne Johnson - Rock");
		Actor g2 = new Actor(2, true, "Dwayne Johnson - Rock");
		
		set.add(g1);
		set.add(g2);
		System.out.println(set.size());
		set.remove(new Actor(1, false, "Dwayne Johnson - Rock"));
		System.out.println(set.size());
		
		
		
	}

}
