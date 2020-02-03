package view;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.MenuDto;
import dto.OrderDto;
import singleton.Singleton;

public class CartView extends JFrame implements ActionListener, MouseListener {
	Singleton s = Singleton.getInstance();
	private JTable jtable;
	private JScrollPane jscrPane;
	private JTextField total_F;
	private JButton backBtn, orderBtn, deleteBtn;
	String columnNames[] = { "Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액", "선택" };

	Object rowData[][];
	Checkbox deleteCh;
	DefaultTableModel model; // table의 넓이를 설정
	List<OrderDto> list = null;

	public CartView() {
		super("장바구니");
		setLayout(null);

		JLabel label = new JLabel("장바구니");
		label.setBounds(270, 20, 120, 15);
		add(label);

		this.list = s.list;

		int total = 0;
		
		rowData = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			OrderDto dto = list.get(i);
			// "Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액", "선택"
			rowData[i][0] = dto.getCoffee_name();
			rowData[i][1] = dto.getCoffee_syrup();
			rowData[i][2] = dto.getCoffee_size();
			String shotStr = "추가안함";
			if (dto.getShot() == 1) {
				shotStr = "추가";
			}
			rowData[i][3] = shotStr;
			String whipStr = "추가안함";
			if (dto.getWhipping() == 1) {
				whipStr = "추가";
			}
			rowData[i][4] = whipStr;
			rowData[i][5] = dto.getCup();
			rowData[i][6] = dto.getTotal();
			rowData[i][7] = false;
			total = total +dto.getTotal();
		}
		JLabel label2 = new JLabel("총 금액 ");
		//label2.setBounds(390, 250, 50, 30);
		label2.setBounds(300, 250, 70, 30);
		add(label2);
		
		total_F = new JTextField();
		total_F.setText(total+"");
		total_F.setHorizontalAlignment(JTextField.CENTER);
		total_F.setBounds(390,250,80,30);
		total_F.setEditable(false);
		add(total_F);

		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
//		// 테이블 생성
		jtable = new JTable(model);

		// column의 폭을 설정
		// "Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액"
		jtable.getColumnModel().getColumn(0).setMaxWidth(150); // 커피이름
		jtable.getColumnModel().getColumn(1).setMaxWidth(80); // 시럽
		jtable.getColumnModel().getColumn(2).setMaxWidth(50); // 크기
		jtable.getColumnModel().getColumn(3).setMaxWidth(80); // 샷추가
		jtable.getColumnModel().getColumn(4).setMaxWidth(80); // 휘핑크림
		jtable.getColumnModel().getColumn(5).setMaxWidth(80); // 잔
		jtable.getColumnModel().getColumn(6).setMaxWidth(50); // 총액
		jtable.getColumnModel().getColumn(7).setMaxWidth(30); // 체크박스

		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER); // 중간

		jtable.getColumn("Espresso Beverages").setCellRenderer(celAlignCenter);
		jtable.getColumn("시럽").setCellRenderer(celAlignCenter);
		jtable.getColumn("크기").setCellRenderer(celAlignCenter);
		jtable.getColumn("샷추가").setCellRenderer(celAlignCenter);
		jtable.getColumn("휘핑크림").setCellRenderer(celAlignCenter);
		jtable.getColumn("잔").setCellRenderer(celAlignCenter);
		jtable.getColumn("총액").setCellRenderer(celAlignCenter);
		jtable.getColumn("선택").setCellRenderer(celAlignCenter);

		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JCheckBox box = new JCheckBox();
				//box.setVisible(false);
				box.setSelected(((Boolean) value).booleanValue());
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
			}

		};

		jtable.getColumn("선택").setCellRenderer(dcr);
		JCheckBox box = new JCheckBox();
		jtable.getColumn("선택").setCellEditor(new DefaultCellEditor(box));

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public Class<?> getColumnClass(int col) {
				return col == 7 ? Boolean.class : String.class;
			}
		};
		jtable = new JTable(model);

		jtable.addMouseListener(this);
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 185);
		add(jscrPane);

		deleteBtn = new JButton("삭제하기");
		deleteBtn.setBounds(500, 10, 100, 30);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				int w = 0;
				//w : list의 전체갯수
				
				if (result == JOptionPane.YES_OPTION) {
					for (int i = 0; i < list.size(); i++) {
						// i : 원본갯수
						Boolean checked = (Boolean) model.getValueAt(i, 7);
						if (checked) {
							s.list.remove(w);
							w--;	
						}
						w++;
					}

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							new CartView();
						}
					});
				}
				
				dispose();
			}
		});
		add(deleteBtn);

		backBtn = new JButton("추가주문");
		backBtn.setBounds(10, 250, 120, 30);
		backBtn.addActionListener(this);
		add(backBtn);

		orderBtn = new JButton("결제하기");
		orderBtn.setBounds(480, 250, 120, 30);
		//orderBtn.setBounds(250, 250, 120, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);

		setBounds(0, 0, 640, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(backBtn)) {
			Singleton s = Singleton.getInstance();
			s.orderCtrl.orderMenu();
			this.dispose();
		} else if (obj.equals(orderBtn)) {
			// DB에넣기
			Singleton s = Singleton.getInstance();
			for (int i = 0; i < list.size(); i++) {
				rowData[i][7] = false;
			}
			s.orderCtrl.orderAf();
			this.dispose();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
	

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
