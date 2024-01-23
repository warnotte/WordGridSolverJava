package test;
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
 
 
public class Test {
 
    static JPanel southPanel;
    static JButton closeButton;
    static JFrame frame;
    static JTextArea textArea;
    private static final Dimension REASON_AREA_SIZE = new Dimension(250, 50);
 
    public static void main(String args[]) {
        String title = "";       
        var toto = new JPanel();
        
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 800);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        JScrollPane lScrollPane = new JScrollPane(textArea);
        lScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        lScrollPane.setPreferredSize(REASON_AREA_SIZE);
        frame.add(lScrollPane, BorderLayout.CENTER);
        frame.add(createSouthPanel(), BorderLayout.SOUTH);
 
        //Here the close button is defined by default, the focus should be on it
        setDefaultButton();
        
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
			public void windowGainedFocus(WindowEvent e) {
            	closeButton.requestFocusInWindow();
            }
        });
 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);                                                                                                                                                                          
        frame.setVisible(true);
        
    }
 
    private static JPanel createSouthPanel() {
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        southPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        addCloseButton(southPanel);
        return southPanel;
    }
 
    /**
     * Set the close button as default
     */
    protected static void setDefaultButton() {
    	frame.getRootPane().setDefaultButton(closeButton);
    	
    }
 
    private static void addCloseButton(JPanel pButtonsPanel) {
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent pEvent) {
                frame.dispose();
            }
        });
        pButtonsPanel.add(closeButton);
    }
}