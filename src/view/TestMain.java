package view;

//import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
import java.util.stream.Stream;

//import DAO.MovieDAO;
//import model.Actor;
//import model.Dimension;
//import model.Genre;
//import model.Movie;

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
		//for (Integer  i : IntStream.rangeClosed(1, 3).g) {
			
		//}
		//HashSet<Actor> set = new HashSet<Actor>();
		
//		Actor g1 = new Actor(1, true, "Dwayne Johnson - Rock");
//		Actor g2 = new Actor(2, true, "Dwayne Johnson - Rock");
//		
//		set.add(g1);
//		set.add(g2);
//		System.out.println(set.size());
//		set.remove(new Actor(1, false, "Dwayne Johnson - Rock"));
//		System.out.println(set.size());
//		
//		LocalDateTime l = LocalDateTime.MIN;

		String uri = "seats=2|seats=3";
		

		Stream.of(uri.split("\\|"))
		.map(s -> s.split("seats="))
		.flatMap(Arrays::stream)
		.filter(x -> !x.contentEquals(""))
		.forEach(System.out::println);

		
	}

}
