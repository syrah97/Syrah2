package View;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import Controller.FrontController;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.TextArea;
import java.awt.Panel;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;

public class GUI extends JFrame implements ActionListener {

//	private FrontController controller = new FrontController();
	private JPanel contentPane;

	private String mId = null;
	private String mPw = null;

	JButton bt1, bt2, bt3, bt4, bt5, b6;
	JLabel lLogin, lMovie, lCinema, lTheater, lMember;
	JTextField tLogin, tMovie, tCinema, tTheater, tMember;
	JTextArea area;
	JScrollPane scroll;

	JFrame loginView = new JFrame("login View");
	JButton loginBtn, exitBtn;
	JTextField txMid, txMpw;

	// Panel Image Set
//	public void paint(Graphics g) {
//		g.drawImage(background, 0, 0, null);
//	}
//
//	private Image background = new ImageIcon(GUI.class.getResource("../View/cinema.jpg")).getImage();

	public GUI() {

		// Fream Set
//		JFrame fream = new JFrame();
		JPanel contentPane = new JPanel();
		setSize(500, 406);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setResizable(false); // 창 크기 조절 불가(false)
		setLocationRelativeTo(null); // 창 중간 정렬
		getContentPane().setLayout(null); // 레이아웃 설정

		setContentPane(contentPane);
		setTitle("Movie Reserve Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Label Set
		JLabel title = new JLabel("Move Reserve Program");
		title.setBounds(0, 10, 484, 40);
		title.setFont(new Font("Verdana", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.TOP);

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 56, 464, 210);

		JPanel panel = new JPanel();
		panel.setBounds(0, 272, 484, 179);

		JLabel label_4 = new JLabel("");
		label_4.setBounds(204, 17, 0, 0);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(209, 17, 0, 0);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(219, 17, 0, 0);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(214, 17, 0, 0);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(38, 27, 114, 55);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_1 = new JButton("예매하기");
		btnNewButton_1.setBounds(186, 27, 114, 55);
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 11));

		JLabel label = new JLabel("");
		label.setBounds(199, 17, 0, 0);

		JButton btnNewButton_2 = new JButton("상영 영화 조회");
		btnNewButton_2.setBounds(38, 92, 113, 55);
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 11));

		JButton btnNewButton_3 = new JButton("영화관 조회");
		btnNewButton_3.setBounds(330, 92, 114, 55);
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 11));

		JLabel label_5 = new JLabel("");
		label_5.setBounds(444, 17, 0, 0);

		JButton btnNewButton_4 = new JButton("상영관 조회");
		btnNewButton_4.setBounds(186, 92, 114, 55);
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 11));

		JLabel label_6 = new JLabel("");
		label_6.setBounds(240, 47, 0, 0);

		JLabel label_7 = new JLabel("");
		label_7.setBounds(245, 47, 0, 0);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(250, 47, 0, 0);

		JLabel label_9 = new JLabel("");
		label_9.setBounds(255, 47, 0, 0);
		panel.setLayout(null);
		panel.add(btnNewButton);
		panel.add(btnNewButton_1);
		panel.add(label);
		panel.add(label_4);
		panel.add(label_3);
		panel.add(label_1);
		panel.add(label_2);
		panel.add(btnNewButton_2);
		panel.add(btnNewButton_3);
		panel.add(label_5);
		panel.add(btnNewButton_4);
		panel.add(label_6);
		panel.add(label_7);
		panel.add(label_8);
		panel.add(label_9);

		JButton btnNewButton_5 = new JButton("회원 정보");
		btnNewButton_5.setBounds(330, 27, 114, 55);
		btnNewButton_5.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		panel.add(btnNewButton_5);
		contentPane.setLayout(null);
		contentPane.add(title);
		contentPane.add(textArea);
		contentPane.add(panel);

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) {

		}
	}
}
