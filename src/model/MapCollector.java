package model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

public class MapCollector implements Collector<Report, Map<String, Double>, Map<String, Double>>{

	@SuppressWarnings("serial")
	@Override
	public Supplier<Map<String, Double>> supplier() {
		
		return () ->  new HashMap<String, Double>() {{
		    put("projections", 0.0);
		    put("tickets", 0.0);
		    put("income", 0.0);
		}};
	}

	@Override
	public BiConsumer<Map<String, Double>, Report> accumulator() {
		
		return (map, report) -> 
		{
			map.put("projections", map.get("projections") + report.getProjections());
			map.put("tickets", map.get("tickets") + report.getTickets());
			map.put("income", map.get("income") + report.getIncome());			
		};
	}

	@Override
	public BinaryOperator<Map<String, Double>> combiner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function<Map<String, Double>, Map<String, Double>> finisher() {
		// TODO Auto-generated method stub
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(EnumSet.of(
				IDENTITY_FINISH, CONCURRENT));
	}

}
