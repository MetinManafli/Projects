import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input;
	private double answer,number;
	int operation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addInput(String X) {
		input.setText(input.getText() + X);
	}
	
	public void calc() {
		switch(operation) {
		case 1:
			answer = number + Double.parseDouble(input.getText());
			input.setText(Double.toString(answer));
			break;
		case 2:
			answer = number - Double.parseDouble(input.getText());
			input.setText(Double.toString(answer));
			break;
		case 3:
			answer = number * Double.parseDouble(input.getText());
			input.setText(Double.toString(answer));
			break;
		case 4:
			answer = number / Double.parseDouble(input.getText());
			input.setText(Double.toString(answer));
			break;
		
			
			
		}
	}
	
	/**
	 * Create The Frame.
	 */
	public Calculator() {
		setForeground(new Color(0, 0, 0));
		setType(Type.UTILITY);
		setTitle("Hesap Makinesi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Screen = new JPanel();
		Screen.setBounds(10, 11, 364, 70);
		contentPane.add(Screen);
		Screen.setLayout(null);
		
		input = new JTextField();
		input.setEditable(false);
		input.setBackground(new Color(255, 255, 255));
		input.setHorizontalAlignment(SwingConstants.RIGHT);
		input.setFont(new Font("Times New Roman", Font.BOLD, 20));
		input.setBounds(0, 25, 364, 45);
		Screen.add(input);
		input.setColumns(10);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1.setBounds(0, 0, 364, 24);
		Screen.add(lbl1);
		
		JPanel ButtonLocalization = new JPanel();
		ButtonLocalization.setBounds(10, 92, 364, 318);
		contentPane.add(ButtonLocalization);
		ButtonLocalization.setLayout(new GridLayout(4, 4, 20, 20));
		
		JButton carp = new JButton("*");
		carp.setForeground(new Color(0, 0, 0));
		carp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number = Double.parseDouble(input.getText());
				operation = 3;
				input.setText("");
				lbl1.setText(number + e.getActionCommand());
			}
		});
		carp.setFont(new Font("Tahoma", Font.BOLD, 20));
		ButtonLocalization.add(carp);
		
		JButton vur = new JButton("/");
		vur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number = Double.parseDouble(input.getText());
				operation = 4;
				input.setText("");
				lbl1.setText(number + e.getActionCommand());
			}
		});
		vur.setFont(new Font("Tahoma", Font.BOLD, 20));
		ButtonLocalization.add(vur);
		
		JButton C = new JButton("C");
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(input.getText().toString().equals(""))) {
					input.setText(input.getText().substring(0,input.getText().length() - 1));
				}
			}
		});
		C.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(C);
		
		JButton esit = new JButton("=");
		esit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calc();
				lbl1.setText("");
			}
		});
		esit.setFont(new Font("Tahoma", Font.BOLD, 20));
		ButtonLocalization.add(esit);
		
		JButton dokuz = new JButton("9");
		dokuz.setFont(new Font("Tahoma", Font.BOLD, 25));
		dokuz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		ButtonLocalization.add(dokuz);
		
		JButton sekiz = new JButton("8");
		sekiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		sekiz.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(sekiz);
		
		JButton yedi = new JButton("7");
		yedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		yedi.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(yedi);
		
		JButton cik = new JButton("-");
		cik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number = Double.parseDouble(input.getText());
				operation = 2;
				input.setText("");
				lbl1.setText(number + e.getActionCommand());
			}
		});
		cik.setFont(new Font("Tahoma", Font.BOLD, 20));
		ButtonLocalization.add(cik);
		
		JButton alti = new JButton("6");
		alti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		alti.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(alti);
		
		JButton bes = new JButton("5");
		bes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		bes.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(bes);
		
		JButton dort = new JButton("4");
		dort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		dort.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(dort);
		
		JButton topla = new JButton("+");
		topla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number = Double.parseDouble(input.getText());
				operation = 1;
				input.setText("");
				lbl1.setText(number + e.getActionCommand());
			}
		});
		topla.setFont(new Font("Tahoma", Font.BOLD, 20));
		ButtonLocalization.add(topla);
		
		JButton uc = new JButton("3");
		uc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		uc.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(uc);
		
		JButton iki = new JButton("2");
		iki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		iki.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(iki);
		
		JButton bir = new JButton("1");
		bir.setFont(new Font("Tahoma", Font.BOLD, 25));
		bir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		ButtonLocalization.add(bir);
		
		JButton sifir = new JButton("0");
		sifir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput(e.getActionCommand());
			}
		});
		sifir.setFont(new Font("Tahoma", Font.BOLD, 25));
		ButtonLocalization.add(sifir);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, Screen, ButtonLocalization, input, dokuz, carp, vur, esit, C, uc, topla, yedi, sekiz, dort, bes, alti, cik, iki, bir, sifir, lbl1}));
	}
}
