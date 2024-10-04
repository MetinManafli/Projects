package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String SelectDoctorName = null;
	private JTable table_myapps;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {

		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		// Whour Model
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		// Appoint Model
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i=0;i<appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);

		}
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_welcome = new JLabel("HoşGeldiniz, Sayın " + hasta.getName());
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_welcome.setBounds(10, 11, 296, 22);
		w_pane.add(lbl_welcome);

		JButton btn_exit = new JButton("Çıkış Yap");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_exit.setBounds(635, 11, 89, 22);
		w_pane.add(btn_exit);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 44, 714, 406);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 53, 280, 314);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lbl_list = new JLabel("Doktor Listesi");
		lbl_list.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_list.setBounds(10, 20, 175, 22);
		w_appointment.add(lbl_list);

		JLabel lbl_clinicName = new JLabel("Poliklinik Adı");
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_clinicName.setBounds(300, 20, 95, 23);
		w_appointment.add(lbl_clinicName);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(300, 53, 150, 30);
		select_clinic.addItem("--Poliklinik seç");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_clinic);

		JLabel lbl_selectDoctor = new JLabel("Doktor Seç");
		lbl_selectDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_selectDoctor.setBounds(300, 125, 150, 23);
		w_appointment.add(lbl_selectDoctor);

		JButton btn_selectDoctor = new JButton("Seç");
		btn_selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					SelectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
				} else {
					Helper.ShowMsg("Lütfen Bir Doktor Seçiniz !");
				}
			}
		});
		btn_selectDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_selectDoctor.setBounds(300, 150, 150, 35);
		w_appointment.add(btn_selectDoctor);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(460, 53, 239, 314);
		w_appointment.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);

		JLabel lbl_whour = new JLabel("Çalışma Saatleri");
		lbl_whour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_whour.setBounds(460, 20, 239, 22);
		w_appointment.add(lbl_whour);

		JLabel lbl_appointment = new JLabel("Randevu Ayarla");
		lbl_appointment.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_appointment.setBounds(300, 262, 150, 23);
		w_appointment.add(lbl_appointment);

		JButton btn_selectApp = new JButton("Randevu Al");
		btn_selectApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				try {
					if (selRow >= 0) {
						String date = table_whour.getModel().getValueAt(selRow, 1).toString();
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), SelectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.ShowMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} else {
							Helper.ShowMsg("error");
						}

					} else {
						Helper.ShowMsg("Lütfen geçerli bir tarih seçiniz !");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btn_selectApp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_selectApp.setBounds(300, 287, 150, 35);
		w_appointment.add(btn_selectApp);

		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(Color.WHITE);
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane scroll_appoint = new JScrollPane();
		scroll_appoint.setBounds(10, 11, 689, 356);
		w_appoint.add(scroll_appoint);

		table_myapps = new JTable(appointModel);
		table_myapps.setBackground(new Color(255, 255, 255));
		scroll_appoint.setViewportView(table_myapps);
	}

	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
