package view;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.MenuDto;
import singleton.Singleton;

public class MenuView extends JFrame implements WindowListener{
	private JTable jtable;
	private JScrollPane jscrPane;
	String columnNames[] = {
			"Espresso Beverages", "Short", "Tall", "Grande"	
		};
	
	Object rowData[][];	
	DefaultTableModel model;	// table의 넓이를 설정
	
	List<MenuDto> list = null;
	
	public MenuView(List<MenuDto> list) {

		super("메뉴판");
		setLayout(null);
		
		JLabel label = new JLabel("MENU");
		label.setBounds(300, 20, 120, 15);
		add(label);
		
		this.list = list;
		rowData = new Object[list.size()][4];
		
		for (int i = 0; i < list.size(); i++) {
			MenuDto dto = list.get(i);
			
			rowData[i][0] = dto.getCoffee_name();
			rowData[i][1] = dto.getCoffee_short();
			rowData[i][2] = dto.getCoffee_tall();
			rowData[i][3] = dto.getCoffee_grande();
		}
		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
		// 테이블 생성
		jtable = new JTable(model);
					
		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(300);	// 커피이름
		jtable.getColumnModel().getColumn(1).setMaxWidth(100);	// short
		jtable.getColumnModel().getColumn(2).setMaxWidth(100);	// tall
		jtable.getColumnModel().getColumn(3).setMaxWidth(100);	// grande
		
		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
		
		jtable.getColumn("Espresso Beverages").setCellRenderer(celAlignCenter);
		jtable.getColumn("Short").setCellRenderer(celAlignCenter);
		jtable.getColumn("Tall").setCellRenderer(celAlignCenter);
		jtable.getColumn("Grande").setCellRenderer(celAlignCenter);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 185);
		add(jscrPane);
	
		setBounds(0, 0, 640, 300);
		setVisible(false);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Singleton s = Singleton.getInstance();
		s.visible(s.orderCtrl.getMenuList());
		s.mnView.setVisible(false);

		//this.setVisible(false);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
