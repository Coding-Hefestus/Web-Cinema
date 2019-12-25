package model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Movie extends Moviefiable{
	
	private String name;
	private int duration;
	private int productionYear;
	private String description;
	private String distributor;
	private String countryOfOrigin;
	private HashSet<Actor> actors;
	private HashSet<Genre> genres;
	private HashSet<Director> directors;
	

	public Movie(int id, boolean active, String name, int duration, int productionYear, 
			String description, String distributor, String countryOfOrigin, 
			HashSet<Actor> actors, HashSet<Genre> genres, HashSet<Director> directors ) {
		
		super(id, active);
		this.name = name;
		this.duration = duration;
		this.productionYear = productionYear;
		this.description = description;
		this.distributor = distributor;
		this.countryOfOrigin = countryOfOrigin;
		this.actors = actors;
		this.genres = genres;
		this.directors = directors;
	}
	
	//constructor when adding new Movie
	public Movie(boolean active, String name, int duration, int productionYear, 
			String description,  String distributor, String countryOfOrigin, 
			HashSet<Actor> actors, HashSet<Genre> genres, HashSet<Director> directors) {
		
		this.active = active;
		this.name = name;
		this.duration = duration;
		this.productionYear = productionYear;
		this.description = description;
		this.distributor = distributor;
		this.countryOfOrigin = countryOfOrigin;
		this.actors = actors;
		this.genres = genres;
		this.directors = directors;
	}
	
	
	public Movie() {this(-1, true, "", -1, -1, "", "", "", new HashSet<Actor>(), new HashSet<Genre>(),new HashSet<Director>());}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getProductionYear() {
		return productionYear;
	}


	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}	
	
	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public HashSet<Actor> getActors() {
		return actors;
	}

	public void setActors(HashSet<Actor> actors) {
		this.actors = actors;
	}
	

	public HashSet<Genre> getGenres() {
		return genres;
	}

	public void setGenres(HashSet<Genre> genres) {
		this.genres = genres;
	}

	public HashSet<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(HashSet<Director> directors) {
		this.directors = directors;
	}

	public static Predicate<Movie> nameFilter(String filter){
		return m -> m.getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	
	public static Predicate<Movie> durationFilter(int fromFilter, int toFilter){
		return  m -> fromFilter <= m.getDuration() && m.getDuration() <= toFilter;
	}
	

	public static Predicate<Movie> productionFilter(int fromFilter, int toFilter){			
		return m -> fromFilter <=  m.getProductionYear() && m.getProductionYear() <= toFilter;		
	}
	
	public static Predicate<Movie> descriptionFilter(String filter){
		return m -> m.getDescription().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Movie> distributorFilter(String filter){
		return m -> m.getDistributor().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Movie> countryOfOriginFilter(String filter){
		return m -> m.getCountryOfOrigin().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Movie> actorsFilter(String filter){
		return m -> m.getActorsString().contains(filter.toLowerCase());
	}
	
	public static Predicate<Movie> genresFilter(String filter){
		return m -> m.getGenresString().contains(filter.toLowerCase());
	}
	
	public static Predicate<Movie> directorsFilter(String filter){
		return m -> m.getDirectorsString().contains(filter.toLowerCase());
	}
	

	public static Comparator<Movie> comparatorByName(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getName);
		case "dsc":		
			return Comparator.comparing(Movie::getName).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> comparatorByDuration(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getDuration);
		case "dsc":		
			return Comparator.comparing(Movie::getDuration).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> comparatorByProductionYear(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getProductionYear);
		case "dsc":		
			return Comparator.comparing(Movie::getProductionYear).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> comparatorByDescription(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getDescription);
		case "dsc":		
			return Comparator.comparing(Movie::getDescription).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> comparatorByDistributor(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getDistributor);
		case "dsc":		
			return Comparator.comparing(Movie::getDistributor).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> comparatorByCountryOfOrigin(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getCountryOfOrigin);
		case "dsc":		
			return Comparator.comparing(Movie::getCountryOfOrigin).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<Movie> compartorByActors(String direction){
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getActorsString);
		case "dsc":		
			return Comparator.comparing(Movie::getActorsString).reversed();
		default: return null;
		}	
	}
	
	public static Comparator<Movie> compartorByGenres(String direction){
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getGenresString);
		case "dsc":		
			return Comparator.comparing(Movie::getGenresString).reversed();
		default: return null;
		}	
	}
	
	public static Comparator<Movie> compartorByDirectors(String direction){
		switch (direction) {
		case "asc":
			return Comparator.comparing(Movie::getDirectorsString);
		case "dsc":		
			return Comparator.comparing(Movie::getDirectorsString).reversed();
		default: return null;
		}	
	}
	
	public String getActorsString() {
		if (this.getActors().size() == 0) return "";
		return this.getActors().stream().map(Actor::getName).collect(Collectors.joining("")).toLowerCase();
	}
	
	
	public String getActorsDisplay() {
		if (this.getActors().size() == 0) return "";
		return this.getActors().stream().map(Actor::getName).collect(Collectors.joining("," + "\n"));

	}
	
	public String getGenresString() {
		if (this.getGenres().size() == 0) return "";
		return this.getGenres().stream().map(Genre::getName).collect(Collectors.joining("")).toLowerCase();
	}
	
	public String getGenresDisplay() {
		if (this.getGenres().size() == 0) return "";
		return this.getGenres().stream().map(Genre::getName).collect(Collectors.joining("," + "\n"));
	}
	
	public String getDirectorsString() {
		if (this.getDirectors().size() == 0) return "";
		return this.getDirectors().stream().map(Director::getName).collect(Collectors.joining("")).toLowerCase();
	}
	
	public String getDirectorsDisplay() {
		if (this.getDirectors().size() == 0) return "";
		return this.getDirectors().stream().map(Director::getName).collect(Collectors.joining("," + "\n"));
	}
	
	
	
}
