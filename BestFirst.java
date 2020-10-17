import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
	private Hashtable<State, Double> fechados; 
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
		abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG())); 
		fechados = new Hashtable<>();		

		List<State> sucs;
		Stack<State> result = new Stack<>();

		abertos.add(new State(s, null)); 
		
		
		while(true) {
	
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
				fechados.put(actual, actual.g);
				
				for(State state : sucs) {
					if(!fechados.containsKey(state)) {

						abertos.add(state);
					}
				}
			}

		}
		
		fechados.put(actual, actual.g);
		
		while(actual.father != null) {
			State filho = actual;
			result.push(filho);
			actual = actual.father;
		}
		
		Collections.reverse(result);
		return result.iterator();

	}
}