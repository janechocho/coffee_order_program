package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;

public class signView extends JFrame implements ActionListener {
	private JButton btn1,btn2;
	private JTextField textField[];
	
	public signView() {
		super("회원가입");
		setLayout(null);
		
		String labText[] = {"ID","PW","NAME","EMAIL"};
		JLabel label[] = new JLabel[labText.length];
		
		for (int i = 0; i < labText.length; i++) {
			label[i] = new JLabel();
			label[i].setText(labText[i]);
			label[i].setFont(new Font("Serif", Font.BOLD, 20));
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setBounds(50, 60 + 60 * i, 80, 50);
			add(label[i]);
		}
		
		textField = new JTextField[label.length];
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField();
			textField[i].setBounds(140, 70 + 60 * i, 150,30);
			add(textField[i]);
		}
		/*
		   id = textField[0].getText();
		  pwd = textField[1].getText();
		 name = textField[2].getText();
		email = textField[3].getText();
		 */
		
		btn1 = new JButton("CHECK");
		btn1.setBounds(300, 70, 80, 30);
		add(btn1);
		
		btn2 = new JButton("SIGN");
		btn2.setBounds(50, 300, 350, 30);
		btn2.setVisible(false);
		add(btn2);
		
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		setBounds(0,0,600,480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	/*
		JButton btn = (JButton)e.getSource();
		String btnTitle = btn.getLabel();
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = null;
		
		if(btnTitle.equalsIgnoreCase("CHECK")) {
			//빈칸 체크 
			if(textField[0].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주세요.");
				return;
			}
			String id = textField[0].getText().trim();
			boolean b = dao.getId(id);
			
			if(b) {
				JOptionPane.showMessageDialog(null, "사용할 수 없는 ID입니다.");
				textField[0].setText("");
				return;
			} else {
				JOptionPane.showMessageDialog(null, textField[0].getText()+"는 사용하실 수 있습니다.");
				//회원가입버튼 활성화
				btn2.setVisible(true);
			}
			
		} else if (btnTitle.equalsIgnoreCase("SIGN")) {
			
			
			if(textField[0].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주세요.");
				return;
			} else if (textField[1].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "pw를  입력해주세요.");
				return;
			} else if (textField[2].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을  입력해주세요.");
				return;
			}
			
			String id = textField[0].getText();
			String pwd = textField[1].getText();
			String name = textField[2].getText();
			String email = textField[3].getText();
			
			dto = new MemberDto(id, pwd, name, email, 1);
			
			boolean b = dao.addMember(dto);
			if(b) {
			JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
			}
			new loginView();
			this.dispose();
		}
		/**/
		JButton btn = (JButton)e.getSource();
		String btnTitle = btn.getLabel();
		
		Singleton s = Singleton.getInstance();
		
		if ( btnTitle.equalsIgnoreCase("CHECK")) {
			//id중복체크
			boolean b = s.memCtrl.idCheck(textField[0].getText());
			if(b) {
				JOptionPane.showMessageDialog(null, "사용할 수 없는 ID입니다.");
				textField[0].setText("");
			} else {
				JOptionPane.showMessageDialog(null, textField[0].getText()+"는 사용할 수 있습니다.");
				btn2.setVisible(true);
			}
			
		} else if (btnTitle.equalsIgnoreCase("SIGN")) {
			//회원가입버튼
			
			//빈칸체크
			if(textField[0].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주세요.");
				return;
			} else if (textField[1].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "pw를  입력해주세요.");
				return;
			} else if (textField[2].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을  입력해주세요.");
				return;
			}
			
			s.memCtrl.regiAf(textField[0].getText(), 
							textField[1].getText(), 
							textField[2].getText(), 
							textField[3].getText());
			
			this.dispose();
			
		}
	}

}
