import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

interface Ilayout 
{ 
	/**
	@return the children of the receiver. 
	*/
	List<Ilayout> children();
		
	/**
	@return true if the receiver equals the argument l;
	@return false otherwise.
	*/
	boolean isGoal(Ilayout l);
		
	/**
	@return the cost for moving from the input config to the receiver.
	*/
	double getG();
}
	
class Board implements Ilayout, Cloneable 
{ 		
	private static final int dim = 3;
	private int board[][];
		
	public Board() 
	{ 
		board = new int[dim][dim]; 
	}
	public Board(int b[][]) {
		board = b;
	}
		
	public Board(String str) throws IllegalStateException
	{ 	
		if (str.length() != dim*dim) throw new IllegalStateException("Invalid arg in Board constructor"); 
		board = new int[dim][dim];
		int si = 0;
		for(int i = 0; i < dim; i++) 
			for(int j = 0; j < dim; j++)
				board[i][j] = Character.getNumericValue(str.charAt(si++));
	}
	
	public String toString() 
	{
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				if(board[i][j] == 0)
					pw.print(" ");
				else
					pw.print(board[i][j]);
			}
			pw.println();
		}
		return writer.toString();
	}

	private int[][] contentTransfer(){
		int[][] clone = new int[dim][dim]; 
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				clone[i][j] = board[i][j];
			}
		}
		
		return clone;
	}
	
	
	@Override
	public List<Ilayout> children() 
	{
	
		List<Ilayout> sucs = new ArrayList<>();
		for (int i = 0; i < dim; i++) 
		{
			for (int j = 0; j < dim; j++) 
			{
				if(board[i][j] == 0) 
				{
					//Mover 0 para cima
					if(i > 0) 
					{
						int[][] cpy = contentTransfer(); 
						cpy[i][j] = board[i-1][j];
						cpy[i-1][j] = 0;
						Ilayout x = new Board(cpy);
						sucs.add(x);
					}

					//Mover 0 para esquerda
					if(j > 0) 
					{
						int[][] cpy = contentTransfer(); 
						cpy[i][j] = board[i][j-1];
						cpy[i][j-1] = 0;
						Ilayout x = new Board(cpy);
						sucs.add(x);
					}
					
					//Mover 0 para a direita
					if(j < 2) 
					{
						int[][] cpy = contentTransfer(); 
						cpy[i][j] = board[i][j+1];
						cpy[i][j+1] = 0;
						Ilayout x = new Board(cpy);
						sucs.add(x);
					}
					
					//Mover Peça para baixo
					if(i < 2) 
					{
						int[][] cpy = contentTransfer(); 
						cpy[i][j] = board[i+1][j];
						cpy[i+1][j] = 0;
						Ilayout x = new Board(cpy);
						sucs.add(x);
					}
				}
			}
		}
		System.out.println("filhos");
		for(Ilayout e : sucs) {
			System.out.println(e.toString());
		}
		return sucs;
	}

	@Override
	public boolean isGoal(Ilayout l) {
		return toString().contentEquals(l.toString());
	}

	@Override
	public double getG() 
	{
		return 1;
	}
}