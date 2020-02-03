package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.OrderDto;
import singleton.Singleton;

public class PaymentView extends JFrame implements ActionListener {
	private JTable jtable;
	private JScrollPane jscrPane;
	private JButton backBtn, logoutBtn;
	private JTextField total_F;
	String columnNames[] = { "Espresso Beverages", "주문날짜", "크기", "잔", "총액" };

	Object rowData[][];
	DefaultTableModel model; // table의 넓이를 설정
	List<OrderDto> list = null;

	public PaymentView(List<OrderDto> list) {
		super("주문내역");
		setLayout(null);

		JLabel label = new JLabel("주문내역");
		label.setBounds(270, 20, 120, 15);
		add(label);

		//Singleton s = Singleton.getInstance();
		//List<OrderDto> list= s.list;
		this.list = list;
		rowData = new Object[list.size()][5];
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			OrderDto dto = list.get(i);
			// "Espresso Beverages", "주문날짜", "크기", "잔","총액"
			rowData[i][0] = dto.getCoffee_name();
			rowData[i][1] = dto.getOrder_date();
			rowData[i][2] = dto.getCoffee_size();
			rowData[i][3] = dto.getCup();
			rowData[i][4] = dto.getTotal();
			total = total +dto.getTotal();
			
		}

		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
		// 테이블 생성
		jtable = new JTable(model);
		
		// column의 폭을 설정
		// "Espresso Beverages", "주문날짜", "크기", "잔","총액"
		jtable.getColumnModel().getColumn(0).setMaxWidth(180); // 커피이름
		jtable.getColumnModel().getColumn(1).setMaxWidth(150); // 주문날짜
		jtable.getColumnModel().getColumn(2).setMaxWidth(90); // 크기
		jtable.getColumnModel().getColumn(3).setMaxWidth(80); // 잔
		jtable.getColumnModel().getColumn(4).setMaxWidth(100); // 총액
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
		
		jtable.getColumn("Espresso Beverages").setCellRenderer(celAlignCenter);
		jtable.getColumn("주문날짜").setCellRenderer(celAlignCenter);
		jtable.getColumn("크기").setCellRenderer(celAlignCenter);
		jtable.getColumn("잔").setCellRenderer(celAlignCenter);
		jtable.getColumn("총액").setCellRenderer(celAlignCenter);
		
		JLabel label2 = new JLabel("총 금액 ");
		label2.setBounds(390, 250, 50, 30);
		add(label2);
		
		total_F = new JTextField();
		total_F.setText(total+"");
		total_F.setHorizontalAlignment(JTextField.CENTER);
		total_F.setBounds(480,250,120,30);
		total_F.setEditable(false);
		add(total_F);
		
		backBtn = new JButton("주문하기");
		backBtn.setBounds(10,250,120,30);
		backBtn.addActionListener(this);
		add(backBtn);
		
		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(480,10,120,30);
		logoutBtn.addActionListener(this);
		add(logoutBtn);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 185);
		add(jscrPane);
		
		

		setBounds(0, 0, 640, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();

		if(obj.equals(backBtn)) {
			s.orderCtrl.orderMenu();
			s.list.clear();
			this.dispose();
		} else if(obj.equals(logoutBtn)) {
			//로그아웃
			s.setLoginId("");
			s.memCtrl.login();
			this.dispose();
		}

	}

}
