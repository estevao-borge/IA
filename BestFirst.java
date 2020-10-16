import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirst 
{
	static class State 
	{
		private Ilayout layout;
		private State father;
		private double g;
		
		public State(Ilayout l, State n) 
		{
			layout = l; 
			father = n;
			if (father != null)
				g = father.g + l.getG(); 
			else 
				g = 0.0;
		}
		
		public String toString() 
		{ 
			return layout.toString(); 
		} 
		
		public double getG() 
		{
			return g;
		}
		
	}
	
	protected Queue<State> abertos; 
	private List<State> fechados; 
	private State actual;
	private Ilayout objective;
	
	final private List<State> sucessores(State n) 
	{ 
		List<State> sucs = new ArrayList<>(); 
		List<Ilayout> children = n.layout.children(); 
		for(Ilayout e: children) 
		{
			if (n.father == null || !e.equals(n.father.layout))
			{ 
				State nn = new State(e, n);
				sucs.add(nn);
			} 
		}
		return sucs; 
	}
	
	final public Iterator<State> solve(Ilayout s, Ilayout goal) 
	{ 
		objective = goal;
		Queue<State> abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG())); 
		List<State> fechados = new ArrayList<>();		
		List<State> sucs;
		
		abertos.add(new State(s, null)); 
		
		//First Stop condition
		if(s.isGoal(objective)) {
			fechados.add(abertos.poll());
			return fechados.iterator();
		}
		
		while(!s.isGoal(objective)) {
			if(abertos.isEmpty()) {
				IllegalArgumentException err = new IllegalArgumentException("You must insert a initial configuration!");
				System.out.println(err.getMessage());
				System.exit(0);
			}
			
			actual = abertos.poll();
			if(actual.layout.isGoal(objective)) {
				break;
			}
			else {
				sucs = sucessores(actual);
				fechados.add(actual);
				for(State state : sucs) {
					if(!fechados.contains(state)) {
						abertos.add(state);
					}
				}
			}
			System.out.println("yau");
		}
		
		fechados.add(actual);
		
		return fechados.iterator();
	}
}