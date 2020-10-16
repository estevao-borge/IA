import org.junit.Test;
import static org.junit.Assert.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PuzzleUnitTests 
{
	@Test
	public void testConstructor1() 
	{
		Board b = new Board("023145678");
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		pw.println(" 23");
		pw.println("145");
		pw.println("678");
		assertEquals(b.toString(), writer.toString());
		pw.close();
	}
	
	@Test
	public void testConstructor2() 
	{
		Board b = new Board("123485670");
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		pw.println("123");
		pw.println("485");
		pw.println("67 ");
		assertEquals(b.toString(), writer.toString());
		pw.close();
	}
	
	@Test
	public void testToString() 
	{
		
	}
	
	@Test
	public void testIsGoal() 
	{
		Ilayout a = new Board("123456780");
		Ilayout a1 = new Board("123456780");
		Ilayout a2 = new Board("103123789");
		
		assertEquals(a.isGoal(a1), true);
		assertEquals(a.isGoal(a2), false);
		assertEquals(a1.isGoal(a2), false);	
	}
}
