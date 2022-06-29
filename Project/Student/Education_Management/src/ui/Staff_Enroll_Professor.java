package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Staff_Enroll_Professor extends JFrame {

	private JPanel contentPane;
	private JTextField pro_id;
	private JTextField pro_name;
	private JTextField college;
	private JTextField major;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_Enroll_Professor frame = new Staff_Enroll_Professor();
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
	public Staff_Enroll_Professor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fixed_1 = new JLabel("\uAD50\uC218 \uB4F1\uB85D");
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setFont(new Font("���� ����", Font.BOLD, 36));
		fixed_1.setBounds(386, 66, 167, 49);
		contentPane.add(fixed_1);
		
		JComboBox enroll_year = new JComboBox();
		enroll_year.setBackground(Color.WHITE);
		enroll_year.setModel(new DefaultComboBoxModel(new String[] {"\uC784\uC6A9\uB144\uB3C4", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"}));
		enroll_year.setBounds(514, 175, 75, 23);
		contentPane.add(enroll_year);
		
		JLabel fixed_2 = new JLabel("\uAD50\uC218 \uBC88\uD638");
		fixed_2.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_2.setFont(new Font("���� ����", Font.BOLD, 18));
		fixed_2.setBounds(300, 208, 98, 23);
		contentPane.add(fixed_2);
		
		pro_id = new JTextField();
		pro_id.setToolTipText("");
		pro_id.setBounds(410, 208, 179, 27);
		contentPane.add(pro_id);
		pro_id.setColumns(10);
		
		pro_name = new JTextField();
		pro_name.setColumns(10);
		pro_name.setBounds(410, 257, 179, 27);
		contentPane.add(pro_name);
		
		JLabel fixed_3 = new JLabel("\uC774\uB984");
		fixed_3.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_3.setFont(new Font("���� ����", Font.BOLD, 18));
		fixed_3.setBounds(341, 257, 57, 23);
		contentPane.add(fixed_3);
		
		college = new JTextField();
		college.setColumns(10);
		college.setBounds(410, 308, 179, 27);
		contentPane.add(college);
		
		JLabel fixed_4 = new JLabel("\uB2E8\uACFC");
		fixed_4.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_4.setFont(new Font("���� ����", Font.BOLD, 18));
		fixed_4.setBounds(341, 308, 57, 23);
		contentPane.add(fixed_4);
		
		major = new JTextField();
		major.setColumns(10);
		major.setBounds(410, 358, 179, 27);
		contentPane.add(major);
		
		JLabel fixed_5 = new JLabel("\uC804\uACF5");
		fixed_5.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_5.setFont(new Font("���� ����", Font.BOLD, 18));
		fixed_5.setBounds(341, 358, 57, 23);
		contentPane.add(fixed_5);
		
		JButton enter_btn = new JButton("\uB4F1\uB85D");
		enter_btn.setBounds(386, 451, 65, 23);
		contentPane.add(enter_btn);
		
		JButton back_btn = new JButton("\uCDE8\uC18C");
		back_btn.setBounds(495, 451, 65, 23);
		contentPane.add(back_btn);
	}
}