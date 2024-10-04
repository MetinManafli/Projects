package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Hasta;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JLabel lbl_password;
	private JPasswordField fld_password;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sİstemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 34, 264, 30);
		w_pane.add(fld_name);

		JLabel lbl_name = new JLabel("Ad Soyad");
		lbl_name.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_name.setBounds(10, 11, 89, 23);
		w_pane.add(lbl_name);

		JLabel lbl_tcno = new JLabel("T.C Numarası");
		lbl_tcno.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_tcno.setBounds(10, 75, 156, 23);
		w_pane.add(lbl_tcno);

		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 98, 264, 30);
		w_pane.add(fld_tcno);

		lbl_password = new JLabel("Şifre");
		lbl_password.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_password.setBounds(10, 139, 156, 23);
		w_pane.add(lbl_password);

		fld_password = new JPasswordField();
		fld_password.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		fld_password.setBounds(10, 162, 264, 30);
		w_pane.add(fld_password);

		JButton btn_register = new JButton("Kayıt ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_password.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Helper.ShowMsg("fill");

				} else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_password.getText(),
								fld_name.getText());
						if (control) {
							Helper.ShowMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							Helper.ShowMsg("error");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_register.setBounds(10, 203, 264, 30);
		w_pane.add(btn_register);

		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_backto.setBounds(10, 250, 264, 30);
		w_pane.add(btn_backto);
	}
}
