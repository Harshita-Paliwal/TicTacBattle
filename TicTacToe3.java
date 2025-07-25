import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe3 
{
	private JFrame frame;
	private JPanel[] panels;
	private JLabel label;
	private JButton[] button;
	private JButton reset,exit;
	private ImageIcon icon1,icon2;
	private int player=1;
	private int count=0;
	private boolean winnerFound=false;
	public TicTacToe3()
	{
		frame=new JFrame("TIC TAC TOE");
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);//taki setBounds se set kre
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		addPanels();
		addLabel();
		addButtons();
		loadImages();
		addExitResetButton();
		frame.setVisible(true);
	}
	private void loadImages()
	{
		icon1=new ImageIcon(getClass().getResource("images/user1.png"));
		icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	}
	private void addPanels() 
	{
		panels=new JPanel[3];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel();
			frame.add(panels[i]);
		}
		panels[0].setBounds(50, 30, 400, 40);
		panels[1].setBounds(50, 100, 400, 350);
		panels[2].setBounds(50, 480, 400, 40);
		panels[2].setOpaque(false);
	}
	public void addLabel()
	{
		label=new JLabel("First Player Turn....");
		label.setFont(new Font("elephant",0,25));
		panels[0].add(label);
		label.setForeground(Color.blue);
		panels[0].setBackground(Color.cyan);
	}
	private void addButtons() 
	{
		button=new JButton[9];
		panels[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0;i<button.length;i++)
		{
			button[i]=new JButton();
			button[i].addActionListener(listener);
			button[i].setBackground(Color.yellow);
			panels[1].add(button[i]);
		}
	}
	private void addExitResetButton() 
	{
		reset=new JButton("RESET");
		panels[2].add(reset);
		
		exit=new JButton("EXIT");
		panels[2].add(exit);
		Font font=new Font("arial",0,20);
		reset.setFont(font);
		exit.setFont(font);
		reset.setEnabled(false);
		exit.setForeground(Color.red);
		reset.addActionListener(new ResetListener());
		exit.addActionListener(new ExitListener());
	}
	//Creating Listener for tic buttons
	//Setting images on button on click
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton bb=(JButton)e.getSource();
			if(player==1) 
			{
			bb.setIcon(icon1);//cross
			label.setText("Second Player Turn....");
			label.setForeground(Color.black);
			panels[0].setBackground(Color.white);
			player=2;
			}
			else if(player==2)
			{
			bb.setIcon(icon2);//circle
			label.setText("First Player Turn....");
			label.setForeground(Color.blue);
			panels[0].setBackground(Color.cyan);
			player=1;
			}
			bb.setEnabled(false);//ek hi button ko baar cross ya circle na kre
			findWinner();
			count++;
			//When game ended in a tie
			if(count==9 && winnerFound==false)
			{
				label.setText("GAME IS OVER");
				panels[0].setBackground(Color.red);
				label.setForeground(Color.white);
				reset.setEnabled(true);
				JOptionPane.showMessageDialog(frame, "Game ended in a tie");
			}
		}//end of actionPerformed() method
		//Finding winner by checking each rows , each columns and each diagonal

		private void findWinner() 
		{
			//Checking first row for first player 
			if (button[0].getIcon()==icon1&&button[1].getIcon()==icon1&&button[2].getIcon()==icon1)
				announceWinner(0,1,2);
			//Checking first row for second player 
			else if (button[0].getIcon()==icon2&&button[1].getIcon()==icon2&&button[2].getIcon()==icon2)
				announceWinner(0,1,2);
			//Checking second row for first player 
			else if (button[3].getIcon()==icon1&&button[4].getIcon()==icon1&&button[5].getIcon()==icon1)
				announceWinner(3,4,5);
			//Checking second row for second player 
			else if (button[3].getIcon()==icon2&&button[4].getIcon()==icon2&&button[5].getIcon()==icon2)
				announceWinner(3,4,5);
			//Checking Third row for first player 
			else if (button[6].getIcon()==icon1&&button[7].getIcon()==icon1&&button[8].getIcon()==icon1)
				announceWinner(6,7,8);
			//Checking Third row for second player 
			else if (button[6].getIcon()==icon2&&button[7].getIcon()==icon2&&button[8].getIcon()==icon2)
				announceWinner(6,7,8);
			//Checking First column for first player 
			else if (button[0].getIcon()==icon1&&button[3].getIcon()==icon1&&button[6].getIcon()==icon1)
				announceWinner(0,3,6);
			//Checking First column for second player 
			else if (button[0].getIcon()==icon2&&button[3].getIcon()==icon2&&button[6].getIcon()==icon2)
				announceWinner(0,3,6);
			//Checking Second column for first player 
			else if (button[1].getIcon()==icon1&&button[4].getIcon()==icon1&&button[7].getIcon()==icon1)
				announceWinner(1,4,7);
			//Checking Second column for second player 
			else if (button[1].getIcon()==icon2&&button[4].getIcon()==icon2&&button[7].getIcon()==icon2)
				announceWinner(1,4,7);
			//Checking Third column for first player
			else if (button[2].getIcon()==icon1&&button[5].getIcon()==icon1&&button[8].getIcon()==icon1)
				announceWinner(2,5,8);
			//Checking Third column for second player 
			else if (button[2].getIcon()==icon2&&button[5].getIcon()==icon2&&button[8].getIcon()==icon2)
				announceWinner(2,5,8);
			//Checking diagonals for first player 
			else if (button[0].getIcon()==icon1&&button[4].getIcon()==icon1&&button[8].getIcon()==icon1)
				announceWinner(0,4,8);
			else if (button[2].getIcon()==icon1&&button[4].getIcon()==icon1&&button[6].getIcon()==icon1)
				announceWinner(2,4,6);
			//Checking diagonals for second player
			else if (button[0].getIcon()==icon2&&button[4].getIcon()==icon2&&button[8].getIcon()==icon2)
				announceWinner(0,4,8);
			else if (button[2].getIcon()==icon2&&button[4].getIcon()==icon2&&button[6].getIcon()==icon2)
				announceWinner(2,4,6);
		}//end of findWinner method
		//Method to disable all buttons
		private void disableButton() 
		{
			for (JButton b:button)
				b.setEnabled(false);
		}
		private void announceWinner(int i,int j , int k)
		{
			winnerFound=true;
			button[i].setBackground(Color.green);
			button[j].setBackground(Color.green);
			button[k].setBackground(Color.green);
			label.setText("GAME IS OVER");
			panels[0].setBackground(Color.red);
			label.setForeground(Color.white);
			disableButton();
			reset.setEnabled(true);
			if(player==2)
				JOptionPane.showMessageDialog(frame, "First player has won");
			else
				JOptionPane.showMessageDialog(frame, "Second player has won");
		}
	}//end of TicListener
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			player=1;
			count=0;
			winnerFound=false;
			label.setText("First player turn....");
			panels[0].setBackground(Color.cyan);
			label.setForeground(Color.blue);
			for(JButton b:button)
			{
				b.setEnabled(true);
				b.setIcon(null);
				b.setBackground(Color.yellow);
			}
			reset.setEnabled(false);
		}		
	}//end of ResetListener
	class ExitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			int ch=JOptionPane.showConfirmDialog(frame, "Are you sure to exit?");
			if(ch==JOptionPane.YES_OPTION)
				System.exit(0);//Terminates JVM
		}	
	}
	public static void main(String[] args) 
	{
		new TicTacToe3();
	}
}
