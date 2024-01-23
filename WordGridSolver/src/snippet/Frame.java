/**
 * 
 */
package snippet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Warnotte Renaud
 *
 */
public class Frame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781992682413659664L;
	private JPanel	contentPane;
	private JPanel panel;

	WordSolver solver = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Frame() throws Exception
	{
		solver = new WordSolver();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new PanelGame(solver);
		}
		return panel;
	}
}
