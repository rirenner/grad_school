import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;

class postfix_stack {
		private int maxs;
		private char[] stackArray;
		private int head;

		public postfix_stack(int s) {
			maxs = s;
			stackArray = new char[maxs];
			head = -1;
		}

		public void push(char j) {
  			stackArray[++head] = j;
		}
		public long pop() {
  			return stackArray[head--];
		}
		public long peek() {
  			return stackArray[head];
		}
		public boolean isEmpty() {
  			return (head == -1);
		}
		public boolean isFull() {
  			return (head == maxs - 1);
		}

}
public class new_hotness {


	public static void main(String [] args)
	{
		// String inputfile = args[0];
		// commenting out for testing b/c its dumb to type that much
		String inputfile = "test_in.txt";

		// String outputfile = args[1];
		// commenting out for testing b/c its dumb to type that much
		String outputfile = "test_out.txt";

		try {
		BufferedReader br = new BufferedReader(new FileReader(inputfile));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();


		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
			int slen = line.toString().length();
			postfix_stack mystack = new postfix_stack(50);
			for (int i = 0; i< slen; i++)
			{
				char c = line.charAt(i);
				mystack.push(c);
				System.out.println("pushing char "+c );

			}
			System.out.println("mystack "+mystack);
			System.out.println(line);

		}
		String everything = sb.toString();
		br.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("error");
		}
		catch (IOException e)
		{
			System.out.println("error");
		}
		//System.out.println(everything);
	}

}

