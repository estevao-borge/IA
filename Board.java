import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

	public Board(int b[][]) 
	{
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

	@Override
	public String toString() 
	{
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		for(int i = 0; i < dim; i++) 
		{
			for(int j = 0; j < dim; j++) 
			{
				if(board[i][j] == 0)
					pw.print(" ");
				else
					pw.print(board[i][j]);
			}
			pw.println();
		}
		return writer.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

        Board Copy = (Board) super.clone();
        Copy.board = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Copy.board[i][j] = this.board[i][j];
            }
        }
        return Copy;
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
					//MOVER 0 PARA CIMA
					if(i > 0) 
					{
						try {
							
							Board cpy = (Board) clone();
							cpy.board[i][j] = board[i-1][j];
							cpy.board[i-1][j] = 0;
							sucs.add(cpy);
							
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						} 
					}

					//MOVER 0 PARA ESQUERDA
					if(j > 0) 
					{
						try {	
							
							Board cpy = (Board) clone();
							cpy.board[i][j] = board[i][j-1];
							cpy.board[i][j-1] = 0;
							sucs.add(cpy);
							
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						} 
				
					}

					//MOVER 0 PARA DIREITA
					if(j < 2) 
					{
						try {
						
							Board cpy = (Board) clone();
							cpy.board[i][j] = board[i][j+1];
							cpy.board[i][j+1] = 0;
							sucs.add(cpy);
							
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						} 
					}

					//MOVER 0 PARA BAIXO
					if(i < 2) 
					{
						try {
							
							Board cpy = (Board) clone();
							cpy.board[i][j] = board[i+1][j];
							cpy.board[i+1][j] = 0;
							sucs.add(cpy);
							
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}
		return sucs;
	}

//	@Override
//	public boolean isGoal(Ilayout l) {
//		return toString().contentEquals(l.toString());
//	}

	@Override 	
	public boolean isGoal(Ilayout l) {
		return this.toString().equals(l.toString());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public double getG() 
	{
		return 1;
	}
}