package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
import project.five.pos.sale.action.*;
import project.five.pos.sale.render.*;

public class OrderDisplay extends JFrame {

	JPanel southPanel;
	JPanel centerPanel;

	// 결제, 취소 버튼	
	JButton payBtn;
	JButton cancleBtn;
	
	
	// 주문내역 정보 라벨
	JLabel info_label;
	
	// 주문내역 테이블
	JTable table;
	JScrollPane scroll;
	String[] header= {"메뉴", "옵션", "수량", "▲", "▼", "가격", "취소"};
	Object[][] selectlist;
	int cellBtnSize;
	ArrayList<SaleDTO> cartlist;
	int orderCount;

	Font font;
						// 패널로 보낸다 생각하면 
						// 메인프레임에 전달 받은 값으로 값을 받을 수있음
	public OrderDisplay(String device_id) {	
		// 더미 데이터
		// 	 버튼으로 상품의 정보 전달 받음 (한번의 주문량)
		// 새로운 주문번호 들어올때 1증가
		SaleDAO dao = new SaleDAO();
		cartlist = new ArrayList<>();
		int orderNumber = dao.MaxOrderNumber();
		orderNumber++;
//		cartlist.add(dao.testOrder("아메리카노", "HOT", 2));
		cartlist.add(dao.testOrder("아메리카노", "ICE", 1));
		cartlist.add(dao.testOrder("홍차", "HOT", 1));
		cartlist.add(dao.testOrder("케이크", null, 1));
//		cartlist.add(dao.testOrder("빙수", null, 1));
		orderCount = cartlist.size();	
	
		setLayout(new BorderLayout());
		southPanel = new JPanel();
		centerPanel = new JPanel();
		
		// 주문번호, 주문내역 라벨 표시
		info_label = new JLabel("주문 내역");
		add(info_label, BorderLayout.NORTH);
		
		// 주문 내역 화면
		//	- 상품객체 정보 배열에 받아서 출력
		selectlist = new Object[orderCount][6];
		for (int i = 0; i < orderCount; i++) {
			selectlist[i][0] = cartlist.get(i).getProduct_name();
			selectlist[i][1] = cartlist.get(i).getTermsofcondition();
			selectlist[i][2] = cartlist.get(i).getOrder_count();
			selectlist[i][5] = cartlist.get(i).getProduct_price() * cartlist.get(i).getOrder_count();
		};
		DefaultTableModel dtm = new DefaultTableModel(selectlist, header);
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		
		cellBtnSize = 40;
		table.getColumn("취소").setCellRenderer(new DeleteBtnRender());
		table.getColumn("취소").setCellEditor(new DeleteAction(new JCheckBox(), table, dtm));
		table.getColumn("취소").setPreferredWidth(cellBtnSize);
		
		table.getColumn("▲").setCellRenderer(new UpDonwBtnRender("▲"));
		table.getColumn("▲").setCellEditor(new UpDownAction(new JCheckBox(), table, "▲"));
		table.getColumn("▲").setPreferredWidth(cellBtnSize);
		
		table.getColumn("▼").setCellRenderer(new UpDonwBtnRender("▼"));
		table.getColumn("▼").setCellEditor(new UpDownAction(new JCheckBox(), table, "▼"));
		table.getColumn("▼").setPreferredWidth(cellBtnSize);
		
		// 패널로 바꿀거면 참고하기
		scroll.setPreferredSize(new Dimension(480, 100));
		centerPanel.add(scroll);

		payBtn = new SaleBtn("결제", 50);
		cancleBtn = new SaleBtn("취소", 50);

		// 결제 버튼 -> cartTable에 데이터 저장(commit X) 및 현재Frame false , 다음프레임 true
		//					패널용 생성자도 있음
		payBtn.addActionListener(new PaymentPageAction(this, dtm, orderNumber, device_id));
		
		// 취소 버튼 -> (예전화면으로 돌아가고) 장바구니 초기화
		cancleBtn.addActionListener(new CancleAction(dtm));
		
		southPanel.add(payBtn);
		southPanel.add(cancleBtn);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "장바구니 화면", true);
	}

	public static void main(String[] args) {
		new OrderDisplay("1234");
	}
}

