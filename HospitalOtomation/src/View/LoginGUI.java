package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Helper.DBConnection;
import Helper.*;
import Model.*;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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

	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setForeground(new Color(0, 0, 0));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medicine.png")));
		lbl_logo.setBounds(217, 11, 50, 50);
		w_pane.add(lbl_logo);

		JLabel lbl_welcome = new JLabel("Hastane Yönetim Sistemine Hoş Geldiniz !");
		lbl_welcome.setBounds(65, 58, 353, 50);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		w_pane.add(lbl_welcome);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 170, 464, 180);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_hastaLogin.setForeground(new Color(0, 0, 0));
		w_tabpane.addTab("Hasta girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lbl_TcNumara = new JLabel("T.C Numaranız: ");
		lbl_TcNumara.setBounds(10, 11, 129, 25);
		lbl_TcNumara.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		w_hastaLogin.add(lbl_TcNumara);

		JLabel lbl_sifre = new JLabel("Şifre:");
		lbl_sifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lbl_sifre.setBounds(10, 45, 49, 25);
		w_hastaLogin.add(lbl_sifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaTc.setBounds(149, 11, 300, 25);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_hastaRegister = new JButton("Kayıt Ol");
		btn_hastaRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.setVisible(true);
				dispose();
			}
		});
		btn_hastaRegister.setBounds(10, 94, 175, 47);
		w_hastaLogin.add(btn_hastaRegister);

		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.ShowMsg("fill");
				} else {
					Connection con = conn.connDb();

					try {
						boolean count = true;
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * from user");
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Hasta")) {
									count = false;
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									System.out.println(hasta.getName());
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
								}
							}
						}
						if(count) {
							Helper.ShowMsg("wrong");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btn_hastaLogin.setBounds(274, 94, 175, 47);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(149, 51, 300, 25);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctor_login = new JPanel();
		w_doctor_login.setLayout(null);
		w_doctor_login.setForeground(Color.BLACK);
		w_doctor_login.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriş", null, w_doctor_login, null);

		JLabel lbl_TcNumara_1 = new JLabel("T.C Numaranız: ");
		lbl_TcNumara_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lbl_TcNumara_1.setBounds(10, 11, 129, 25);
		w_doctor_login.add(lbl_TcNumara_1);

		JLabel lbl_doctorPass = new JLabel("Şifre:");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lbl_doctorPass.setBounds(10, 45, 49, 25);
		w_doctor_login.add(lbl_doctorPass);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(149, 11, 300, 25);
		w_doctor_login.add(fld_doctorTc);

		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.ShowMsg("fill");
				} else {
					Connection con = conn.connDb();
					try {
						boolean control = true;
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * from user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("BasHekim")) {
									control = false;
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									System.out.println(bhekim.getName());
									BasHekimGUI bGUI = new BasHekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("Doktor")) {
									control = false;
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword(rs.getString("password"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									System.out.println(doctor.getName());
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							} 
						}
						if(control) {
							Helper.ShowMsg("wrong");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_doctorLogin.setBounds(10, 94, 439, 47);
		w_doctor_login.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(149, 51, 300, 25);
		w_doctor_login.add(fld_doctorPass);
	}
}
