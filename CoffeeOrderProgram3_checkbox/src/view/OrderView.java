package view;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.bind.ParseConversionEvent;

import dto.MenuDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderView extends JFrame implements ActionListener{
	JComboBox<String> choiceMenu;
	JButton menuBtn, orderBtn;	//가격표 , 주문
	JTextField cup_F;	//몇잔
	JRadioButton size_short,size_tall,size_grande;	//크기선택
	JRadioButton syr_vanila,syr_caramel, syr_hazelnut; //시럽선택
	ButtonGroup size_group, syrup_group;

	JCheckBox chShot,chWhip;	//샷추가,휘핑크림 추가선택
	
	boolean open = false;
	public OrderView() {
		super("주문페이지");
		setLayout(null);
		
		//가격표버튼, choice
		menuBtn = new JButton("메뉴판");
		menuBtn.setBounds(530,20,120,30);
		menuBtn.addActionListener(this);
		add(menuBtn);
		
		Singleton s = Singleton.getInstance();
		List<MenuDto> list=s.orderCtrl.getMenuList();

		choiceMenu = new JComboBox<String>();
		choiceMenu.addItem("---------------------음료선택---------------------");
		for (int i = 0; i < list.size(); i++) {
			choiceMenu.addItem(list.get(i).getCoffee_name());
		}
		choiceMenu.setBounds(100,70,350,30);
		add(choiceMenu);
		
		
		//크기선택
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4,1));
		JLabel label1 = new JLabel("크기");
		panel1.add(label1);
		size_short = new JRadioButton("Short",false);
		size_tall = new JRadioButton("Tall", false);
		size_grande = new JRadioButton("Grande", false);
		
		size_group = new ButtonGroup();
		size_group.add(size_short);
		size_group.add(size_tall);
		size_group.add(size_grande);
		
		
		size_short.addActionListener(this);
		size_tall.addActionListener(this);
		size_grande.addActionListener(this);
		panel1.add(size_short);
		panel1.add(size_tall);
		panel1.add(size_grande);
		panel1.setBounds(100,120,100,150);
		add(panel1);
	
		//시럽선택
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4,1));
		JLabel label2 = new JLabel("시럽");
		panel2.add(label2);
		syr_vanila = new JRadioButton("바닐라",false);
		syr_caramel = new JRadioButton("카라멜",false);
		syr_hazelnut = new JRadioButton("헤이즐넛",false);
		
		syrup_group = new ButtonGroup();
		syrup_group.add(syr_vanila);
		syrup_group.add(syr_caramel);
		syrup_group.add(syr_hazelnut);

		
		syr_vanila.addActionListener(this);
		syr_caramel.addActionListener(this);
		syr_hazelnut.addActionListener(this);
		
		panel2.add(syr_vanila);
		panel2.add(syr_caramel);
		panel2.add(syr_hazelnut);
		panel2.setBounds(240,120,100,150);
		add(panel2);
		
		//기타
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4,1));
		JLabel label3 = new JLabel("기타");
		panel3.add(label3);
		chShot = new JCheckBox("샷 추가");
		chShot.addActionListener(this);
		panel3.add(chShot);
		chWhip = new JCheckBox("휘핑 추가");
		chWhip.addActionListener(this);
		panel3.add(chWhip);
		panel3.setBounds(380,120,100,150);
		add(panel3);
		
		// 몇 잔, 주문하기 Btn
		cup_F = new JTextField();
		cup_F.setText("0");
		cup_F.setBounds(200,300,50,50);
		cup_F.setHorizontalAlignment(JTextField.CENTER);
		add(cup_F);
		
		JLabel label4 = new JLabel("잔");
		label4.setBounds(270,300,30,50);
		add(label4);
		
		
		orderBtn = new JButton("담기");
		orderBtn.setBounds(310,300,100,50);
		orderBtn.addActionListener(this);
		add(orderBtn);

				
		setBounds(0,0,680,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int totalPrice() {
		//총금액 
		int price=0;
		
		Singleton s = Singleton.getInstance();
		String menuName = choiceMenu.getSelectedItem()+"";
	
		if(choiceMenu.getSelectedItem().equals(s.orderCtrl.getMenuPrice(menuName).getCoffee_name())) {
			if(size_short.isSelected()) {
				price = s.orderCtrl.getMenuPrice(menuName).getCoffee_short();
			} else if (size_tall.isSelected()) {
				price = s.orderCtrl.getMenuPrice(menuName).getCoffee_tall();
			} else if (size_grande.isSelected()) {
				price = s.orderCtrl.getMenuPrice(menuName).getCoffee_grande();
			}
		}
		System.out.println("short:"+s.orderCtrl.getMenuPrice(menuName).getCoffee_short());
		System.out.println("메뉴: "+menuName+", 가격: "+price);
		return price* Integer.parseInt(cup_F.getText());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String size=null;
		String syrup=null;
		int shot=0;	
		int whip=0;
		//0 : 추가안함
		
		
		if(obj.equals(menuBtn)) {
			//가격표보기
				
			Singleton s = Singleton.getInstance();
			if(!open) {
				s.visible(s.orderCtrl.getMenuList());
				s.mnView.setVisible(true);
				open = true;
			}
			else {
				s.mnView.setVisible(false);
				open = false;
			}
		} else if (obj.equals(orderBtn)) {
			String cup_str = cup_F.getText();
			for (int i = 0; i < cup_str.length(); i++) {
				char c = cup_str.charAt(i);
				if(c< 48 || c> 57) {
					//숫자가아닌 값이 입력됐을때
					JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.");
					return;
				}
			}
			
			if(choiceMenu.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "음료를 선택해주세요");
				return;
			} else if (!size_short.isSelected()&&!size_tall.isSelected()&&!size_grande.isSelected()) {
				JOptionPane.showMessageDialog(null, "크기를 선택해주세요");
				return;
			} else if(cup_F.getText().trim().equals("")||cup_F.getText().trim().equals("0")) {
				JOptionPane.showMessageDialog(null, "잔수를 입력해주세요");
				return;
			}
			
			
			//크기선택
			if(size_short.isSelected()) {
				size = "short";
			} else if(size_tall.isSelected()) {
				size = "tall";
			} else if(size_grande.isSelected()) {
				size = "grande";
			} else if (size_group.isSelected(null)) {
				size ="선택해주세요";
				return;
			}
			//시럽선택
			if(syr_vanila.isSelected()) {
				syrup = "바닐라";
			} else if (syr_caramel.isSelected()) {
				syrup = "카라멜";
			} else if (syr_hazelnut.isSelected()) {
				syrup = "헤이즐넛";

			} else if (syrup_group.isSelected(null)) {
				syrup = "시럽선택안함";
			}
			
			//기타
			if(chWhip.isSelected()) {
				whip = 1;
			}
			
			if(chShot.isSelected()) {
				shot = 1;
			}
						
			//JOptionPane.showMessageDialog(null, "음료:"+choiceMenu.getSelectedItem());
			System.out.println("음료:"+choiceMenu.getSelectedItem());
			System.out.println("사이즈:"+size);
			System.out.println("시럽:"+syrup);
			System.out.println("샷추가:"+shot);
			System.out.println("휩추가:"+whip);
			System.out.println("총 잔수: "+cup_F.getText());
			
			int price = totalPrice();
			System.out.println("총 금액: "+ price);
			
			Singleton s = Singleton.getInstance();
			String id = s.getLoginId();
			String c_name = choiceMenu.getSelectedItem()+"";
			int c_cup = Integer.parseInt(cup_F.getText());
			
			OrderDto orderDto = new OrderDto(0, id, c_name, 
					size,c_cup,syrup, whip, 
					shot, price, null);
			s.list.add(orderDto);
		
			s.orderCtrl.cartView();
			System.out.println(orderDto.toString());
			this.dispose();
			
		}  
		
	}
	
}
