/**
 * 
 */
package snippet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Warnotte Renaud
 *
 */
public class WordSolver
{
	
	char [][] grid = null;
	List<String> words = null;
	int w = 0;
	int h = 0;
	
	public enum sens {LEFT_TO_RIGHT, RIGHT_TO_LEFT, UP_TO_DOWN, DOWN_TO_UP, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT}; 
	Map<sens, Integer []>  map = new HashMap<>();
	
	Map<String, Integer []> sol_pos = null;
	Map<String, sens> sol_sens = null;
	
	public static void main(String args[]) throws Exception{
		WordSolver snip = new WordSolver();
		
	}
	
	public WordSolver() throws Exception {
		readWords("words1.txt");
		readGrid("grid1.txt");
		
		map.put(sens.LEFT_TO_RIGHT, new Integer[]{+1,+0});
		map.put(sens.RIGHT_TO_LEFT, new Integer[]{-1,+0});
		map.put(sens.UP_TO_DOWN, new Integer[]{+0,+1});
		map.put(sens.DOWN_TO_UP, new Integer[]{+0,-1});
		map.put(sens.UP_LEFT, new Integer[]{-1,-1});
		map.put(sens.UP_RIGHT, new Integer[]{+1,-1});
		map.put(sens.DOWN_LEFT, new Integer[]{-1,+1});
		map.put(sens.DOWN_RIGHT, new Integer[]{+1,+1});
		
		
		solve();
		
	}
	
	public void readWords(String filename) {		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

			//br returns as stream and convert it into a List
			words = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readGrid(String filename) {
		
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

			//br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		grid = new char [list.size()][];
		for (int y = 0; y < list.size(); y++)
		{
			String line = list.get(y);
			grid[y]= new char [line.length()];
			for (int x = 0; x < line.length(); x++)
				grid[y][x]=line.charAt(x);
		}
		
		w = grid[0].length;
		h = grid.length;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	private void solve() throws Exception
	{
		sol_pos = new HashMap<>();
		sol_sens = new HashMap<>();
		int find = 0;
		// Boucle sur toutes les cases;
		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				char curChar = grid[y][x];
				
				List<String> list_mots_commenceant_par = words.stream().filter(e -> e.startsWith(""+curChar)).collect(Collectors.toList());
				//System.err.println(x+","+y);
				for (Iterator<String> iterator = list_mots_commenceant_par.iterator(); iterator.hasNext();)
				{
					String string = iterator.next();
					// System.err.println(string);
					int len = string.length();
					
					for (int i = 0; i < snippet.WordSolver.sens.values().length; i++)
					{
						sens sens1 = snippet.WordSolver.sens.values()[i];
						String word = getWord(x, y, len, sens1);
						if (word==null)
							continue;
						if (word.equals(string))
						{
							sol_pos.put(word, new Integer[]{x,y});
							sol_sens.put(word, sens1);
							System.err.println("Find : "+word+ " - "+sens1+" pos = "+x+","+y);
							find++;
							break;
						}
					}
				}
				
			}
		}
		
		if (find != words.size())
			throw new Exception("Error somewhere");
	}

	/**
	 * @param x
	 * @param y
	 * @param len
	 * @param sens1
	 */
	private String getWord(int x, int y, int len, sens sens1)
	{
		Integer [] offs = map.get(sens1);
		int curx = x;
		int cury = y;
		String res = ""+grid[cury][curx];
		
		int remainLen = len-1;
		while (true)
		{
			
			curx += offs[0];
			cury += offs[1];
			
			if (curx<0) return null;
			if (curx>=w) return null;
			if (cury<0) return null;
			if (cury>=h) return null;
			res+=""+grid[cury][curx];
			
			remainLen--;
			if (remainLen<=0) break;
			
		}
		return res;
		
	}

}

