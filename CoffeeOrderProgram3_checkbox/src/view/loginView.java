package view;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;


public class loginView extends JFrame implements ActionListener {
	private JTextField id_field;
	private JPasswordField pw_field;
	private JButton btn1,btn2;

	public loginView() {
		super("로그인");
	

		setLayout(null);
		
		JLabel label1, label2;
		label1 = new JLabel("ID");      
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setBounds(100,100,40,40);
		add(label1);
		
		id_field = new JTextField();
		id_field.setBounds(140, 110, 100, 20);
		add(id_field);
		
		label2 = new JLabel("PW");      
		label2.setFont(new Font("Serif", Font.BOLD, 20));
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(100,140,40,40);
		add(label2);
		
		pw_field = new JPasswordField();
		pw_field.setBounds(140, 150, 100, 20);
		pw_field.setEchoChar('*');
		add(pw_field);

		
		btn1 = new JButton("LOGIN");
		btn1.setBounds(100, 200, 70,50);
		add(btn1);
		
		btn2 = new JButton("SIGN");
		btn2.setBounds(180, 200, 70,50);
		add(btn2);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		setBounds(0,0,400,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		/*
		JButton btn = (JButton)e.getSource();
		String btnTitle = btn.getLabel();
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = null;
		
		String id = id_field.getText().trim();
		String pw = pw_field.getText().trim();
		dto = dao.logId(id, pw);
	
		if(btnTitle.equalsIgnoreCase("LOGIN")) {
			if ( dto == null){
				JOptionPane.showMessageDialog(null, "ID 혹은 PW가 틀립니다. 다시 입력해주세요");
				id_field.setText("");
				pw_field.setText("");
				return;
			} else {
				JOptionPane.showMessageDialog(null, dto.getId()+"님 환영합니다.");
				// login한 ID를 저장 -> (Web) Session에 저장
				dao.setLoginId(dto.getId());
				new bbsListView();
			}
		} else if(btnTitle.equalsIgnoreCase("SIGN")) {
			new signView();
		}
		this.dispose();
		/**/
		
		Singleton s = Singleton.getInstance();
		
		if( obj == btn1) {
			//로그인
			s.memCtrl.loginAf(id_field.getText(), pw_field.getText());
		} else if ( obj == btn2 ) {
			//회원가입
			s.memCtrl.regi();
		}
		this.dispose();
	}
	
  
}
