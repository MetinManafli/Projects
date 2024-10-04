package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Bashekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {

	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private static final long serialVersionUID = 1L;
	private JPanel w_doctorName;
	private JTextField fld_doctorName;
	private JTextField fld_doctorTcNo;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField fld_doctorPassword;
	private JTextField fld_doctorID;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTable table_clinic;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(bashekim);
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
	public BasHekimGUI(Bashekim bashekim) throws SQLException {

		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);

		}

		// Clinic Model

		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}

		// Worker Model

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_doctorName = new JPanel();
		w_doctorName.setBackground(Color.WHITE);
		w_doctorName.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_doctorName);
		w_doctorName.setLayout(null);

		JLabel lblNewLabel = new JLabel("HoşGeldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 296, 22);
		w_doctorName.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(635, 11, 89, 22);
		w_doctorName.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 44, 714, 406);
		w_doctorName.add(w_tab);

		JPanel w_doctorYonetim = new JPanel();
		w_doctorYonetim.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctorYonetim, null);
		w_doctorYonetim.setLayout(null);

		fld_doctorName = new JTextField();
		fld_doctorName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorName.setBounds(534, 34, 165, 30);
		w_doctorYonetim.add(fld_doctorName);
		fld_doctorName.setColumns(10);

		fld_doctorTcNo = new JTextField();
		fld_doctorTcNo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorTcNo.setBounds(534, 91, 165, 30);
		w_doctorYonetim.add(fld_doctorTcNo);
		fld_doctorTcNo.setColumns(10);

		JButton btn_doctorAdd = new JButton("Ekle");
		btn_doctorAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorName.getText().length() == 0 || fld_doctorTcNo.getText().length() == 0
						|| fld_doctorPassword.getText().length() == 0) {
					Helper.ShowMsg("fill");
				} else {
					try {
						boolean kontrol = bashekim.addDoctor(fld_doctorTcNo.getText(), fld_doctorPassword.getText(),
								fld_doctorName.getText());
						if (kontrol) {
							Helper.ShowMsg("success");
							fld_doctorName.setText(null);
							fld_doctorPassword.setText(null);
							fld_doctorTcNo.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorAdd.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_doctorAdd.setBounds(534, 186, 165, 35);
		w_doctorYonetim.add(btn_doctorAdd);

		JLabel lbl_doctorName = new JLabel("Ad Soyad");
		lbl_doctorName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_doctorName.setBounds(534, 11, 89, 23);
		w_doctorYonetim.add(lbl_doctorName);

		JLabel lbl_doctorTc = new JLabel("T.C No");
		lbl_doctorTc.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_doctorTc.setBounds(534, 68, 89, 23);
		w_doctorYonetim.add(lbl_doctorTc);

		JLabel lbl_doctorPassword = new JLabel("Şifre");
		lbl_doctorPassword.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_doctorPassword.setBounds(534, 126, 89, 23);
		w_doctorYonetim.add(lbl_doctorPassword);

		JLabel lblNewLabel_4 = new JLabel("Kullanıcı ID");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_4.setBounds(534, 232, 165, 23);
		w_doctorYonetim.add(lblNewLabel_4);

		JButton btn_doctorDel = new JButton("Sil");
		btn_doctorDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.ShowMsg("Lütfen geçerli bir doktor seçiniz");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deletDoctor(selectID);
							if (control) {
								Helper.ShowMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_doctorDel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_doctorDel.setBounds(534, 300, 165, 35);
		w_doctorYonetim.add(btn_doctorDel);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 514, 334);
		w_doctorYonetim.add(w_scrollDoctor);

		fld_doctorPassword = new JTextField();
		fld_doctorPassword.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorPassword.setColumns(10);
		fld_doctorPassword.setBounds(534, 148, 165, 30);
		w_doctorYonetim.add(fld_doctorPassword);

		fld_doctorID = new JTextField();
		fld_doctorID.setEditable(false);
		fld_doctorID.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(534, 266, 165, 30);
		w_doctorYonetim.add(fld_doctorID);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception e2) {

				}
			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcNo = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPassword = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcNo, selectName, selectPassword);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("PoliKlinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane scroll_clinic = new JScrollPane();
		scroll_clinic.setBounds(10, 11, 260, 356);
		w_clinic.add(scroll_clinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deletMenu = new JMenuItem("sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deletMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deletMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deletClinic(selID)) {
							Helper.ShowMsg("success");
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.ShowMsg("error");
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		scroll_clinic.setViewportView(table_clinic);

		JLabel lbl_clinicName = new JLabel("Poliklinik Adı");
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_clinicName.setBounds(280, 11, 95, 23);
		w_clinic.add(lbl_clinicName);

		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(280, 34, 150, 30);
		w_clinic.add(fld_clinicName);

		JButton btn_clinicAdd = new JButton("Ekle");
		btn_clinicAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.ShowMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.ShowMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_clinicAdd.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_clinicAdd.setBounds(280, 72, 150, 35);
		w_clinic.add(btn_clinicAdd);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(455, 11, 244, 356);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(280, 286, 150, 35);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();

		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.ShowMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
								table_worker.setModel(workerModel);
							}
						} else {
							Helper.ShowMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.ShowMsg("Lütfen bir poliklinik seçiniz");
				}
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_addWorker.setBounds(280, 332, 150, 35);
		w_clinic.add(btn_addWorker);

		JLabel lbl_clinicName_1 = new JLabel("Poliklinik Adı");
		lbl_clinicName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_clinicName_1.setBounds(280, 150, 95, 23);
		w_clinic.add(lbl_clinicName_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);

				} else {
					Helper.ShowMsg("Lütfen bir Poliklinik seçiniz !");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_workerSelect.setBounds(280, 184, 150, 35);
		w_clinic.add(btn_workerSelect);

	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);

		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}
	}
}
