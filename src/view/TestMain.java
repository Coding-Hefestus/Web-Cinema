package view;

import java.util.ArrayList;

import DAO.MovieDAO;
import model.Dimension;
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
		
	}

}
