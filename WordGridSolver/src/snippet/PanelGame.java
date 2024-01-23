/**
 * 
 */
package snippet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import snippet.WordSolver.sens;

/**
 * @author Warnotte Renaud
 *
 */
public class PanelGame extends JPanel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 949208671856153457L;
	
	WordSolver solver = null;
	
	/**
	 * Create the panel.
	 */
	public PanelGame(WordSolver solver)
	{
		this.solver = solver;
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(new Font("Verdana", Font.BOLD, 16));
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight()); 
		
		
		for (int i = 0; i < solver.words.size(); i++)
		{
			
			String word = solver.words.get(i);
			
			Color col = getColor(word);
			System.err.println("word = "+word);
			Integer [] pos = solver.sol_pos.get(word);
			sens sens1 = solver.sol_sens.get(word);
			
		//	float posX = (0+pos[0])*16+50;
		//	float posY = (0+pos[1]-1)*16+50;
			
		//	g2d.fillRect((int)posX, (int)posY, 16, 16);
			
			Integer [] offs = solver.map.get(sens1);
			
			int curx = pos[0];
			int cury = pos[1]-1;
			for (int j = 0; j < word.length(); j++)
			{
				
				float posX = (curx)*16+50-2;
				float posY = (cury)*16+50+1;
				
				g2d.setColor(col);
				g2d.fillRect((int)posX, (int)posY, 16, 16);
				g2d.setColor(Color.black);
				g2d.drawRect((int)posX, (int)posY, 16, 16);
				
				curx += offs[0];
				cury += offs[1];
				
			}
			
		}
		
		g2d.setColor(Color.BLACK);
		
		for (int y = 0; y < solver.h; y++)
		{
			for (int x = 0; x < solver.w; x++)
			{
				float posX = (x)*16+50;
				float posY = (y)*16+50;
				g2d.drawString(""+solver.grid[y][x], posX, posY);
			}
		}
		
	}

	/**
	 * @param word
	 * @return
	 */
	private Color getColor(String word)
	{
		int hashCode = word.hashCode();
		Color col = new Color(hashCode);
		col = new Color(col.getRed(), col.getGreen(), col.getBlue(), 127);
		return col;
		
	}
	
	

	
}
