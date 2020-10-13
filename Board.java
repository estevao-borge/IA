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

	public Board(String str) throws IllegalStateException
	{ 	
		if (str.length() != dim*dim) throw new IllegalStateException("Invalid arg in Board constructor"); 
		board = new int[dim][dim];
		int si = 0;
		for(int i = 0; i < dim; i++) 
			for(int j = 0; j < dim; j++)
				board[i][j] = Character.getNumericValue(str.charAt(si++));
	}

	public String toString() {
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
		System.out.println(writer.toString());
		return writer.toString();

	}




	@Override
	public List<Ilayout> children() {

		List<Ilayout> sucs = new ArrayList<>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if(board[i][j] == 0) {
					if(i > 0) {
						int[][] cpy = board.clone();
						cpy[i][j] = board[i-1][j];
						cpy[i-1][j] = 0;
						Ilayout x = new Board(cpy.toString());
						sucs.add(x);
					}

					//esquerda
					if(j > 0) {

					}
					//direita
					if(j < 2) {

					}
					//baixo
					if(i < 2) {

					}
				}
			}
		}
	}

	@Override
	public boolean isGoal(Ilayout l) {
		return board.toString().contentEquals(l.toString()); 
	}

	@Override
	public double getG() {
		// TODO Auto-generated method stub
		return 0;
	}
}