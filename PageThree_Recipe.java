import java.awt.*;
import javax.swing.*;


import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;   
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PageThree_Recipe extends JFrame implements ActionListener{
	
	public static final PageOne_Login PageOne = null;
	public static final PageTwo_FoodList PageTwo = null;
	public static ArrayList<String> selected = new ArrayList<String>(); 
	
	JLabel lblrcp = new JLabel("레시피");
	JLabel lblrm = new JLabel("추천메뉴");

	String[] rcpname1 = new String[] {"김치찌개","감자볶음","계란말이","스팸계란볶음밥", "떡볶이", "두부조림", "된장찌개" , "호박전",
			"어묵볶음", "호박나물", "버섯볶음", "김치볶음밥", "김치전", "감자전", "어묵조림", "잡채", "두부김치", "떡만두국", "감자샐러드", "오므라이스"};
	public int[] rcp_index = new int[20]; // 추천 메뉴 정렬을 위한 숫자 배열 1

	JLabel []rcpname = {
			new JLabel("김치찌개"), new JLabel("감자볶음"), new JLabel("계란말이")
			, new JLabel("스팸계란볶음밥"), new JLabel("떡볶이"), new JLabel("두부조림")
			, new JLabel("된장찌개"), new JLabel("호박전"), new JLabel("어묵볶음")
			, new JLabel("호박나물"), new JLabel("버섯볶음"), new JLabel("김치볶음밥")
			, new JLabel("김치전"), new JLabel("감자전"), new JLabel("어묵조림")
			, new JLabel("잡채"), new JLabel("두부김치"), new JLabel("떡만두국")
			, new JLabel("감자샐러드"), new JLabel("오므라이스")
	};
	public static JTextArea txtrcp = new JTextArea(); // 레시피 출력 공간
	JScrollPane sprcp = new JScrollPane(txtrcp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JButton [] rcp = new JButton[20];
	
	// 레시피 txt파일을 읽어오기 위한 변수
	public static String filePath;
	boolean [] rcpCheck = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
		
	// 스탑워치 관련 변수
	JButton start, reset, pause ,timerImg, prevPage;
	Thread p_display, t_display;
	JLabel w1, w2, w3;
	int mm, ss, ms, t=0;
	JPanel p, bp, wp;
	JLabel c1, c2;
	ImageIcon icon, icon2;
	JPanel menuBtn;
	private JScrollPane sp;
	
	public static int count = 0;

    public PageThree_Recipe (){
    	for (int i = 0; i < PageTwo.menus.length; i++) {
    		if (PageTwo.menus[i].order >= 1) {
    			count++;
    		}
    	}
    	
    	sp = new JScrollPane(menuBtn, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	// PageTwo에서 선택한 메뉴와 관련된 메뉴 출력
    	txtrcp.append("*선택한 재료 "+selected.toString()+"\n\n");
    	for (int i=0; i<PageTwo.menus.length; i++) {
        	if (PageTwo.menus[i].order >= 1) {
        		txtrcp.append("레시피: "+ PageTwo.menus[i].recipeName+"\n" + "있는 재료 / 필요한 재료 : "+ PageTwo.menus[i].order +"/" +PageTwo.menus[i].ingredient.length + "\n\n");}
        	for (int j = 0; j<rcpname1.length;j++) {
    			if (PageTwo.menus[i].recipeName == rcpname1[j]) {
    				rcpname1[j] = PageTwo.menus[i].recipeName;
    				rcp_index[i]= j;
    			}
        	}
    	}
    	
        buildGUI();
    	icon = new ImageIcon("timer.png");
		icon = imageSetSize(icon, 25,25);
		timerImg = new JButton(icon);
		timerImg.setBorderPainted(false);
		timerImg.setContentAreaFilled(false);
		timerImg.setFocusPainted(false);
		
		icon2 = new ImageIcon("return.png"); // 뒤로 가기
		icon2 = imageSetSize(icon2, 35,35);
		prevPage = new JButton(icon2);
		prevPage.setBorderPainted(false);
		prevPage.setContentAreaFilled(false);
    	
		ImageIcon backgroundIcon = new ImageIcon("pagethree_background.png");
		JPanel back = new JPanel() {
    	public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(backgroundIcon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false); 
                super.paintComponent(g);
            }
    	};

    	String userName = PageOne.userText.getText();
		setTitle("오늘뭐먹지? : "+userName+"의 냉장고");
        PageThree_Panel(back);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false); //구현 확인 위해 풀어둠
        setLocation(600, 300);
		setSize(900,500);
    }
    public void PageThree_menuPanel() {
    	menuBtn = new JPanel();
    	menuBtn.setLayout(null);
    	
    	// PageTwo에서 선택한 재료와 연관이 큰 순서대로 추천 메뉴 저장
    	for (int i=0; i<rcp_index.length; i++)
		{	
			int k= rcp_index[i]; 
			rcp[k] = new JButton(new ImageIcon("rcp" + (k+1) +"_o.jpg"));
			rcp[k].setIcon(new ImageIcon("rcp" + (k+1) +"_o.jpg"));
		}
    	RecipeBtn();
    	
    	// 추천 메뉴 출력
    	for(int i=0; i<rcpname.length; i++) {
    		menuBtn.add(rcp[i]);
    		rcpname[i].setFont(new Font("맑은 고딕",Font.BOLD, 12));
    		menuBtn.add(rcpname[i]);
    		rcp[i].addActionListener(this);
    	}
    }

	public void PageThree_Panel(JPanel panel3){
		// 좌표설정 초기화
		setContentPane(panel3);
        panel3.setLayout(null);
        Dimension size = new Dimension();
        size.setSize(350,150*count + 30);
        PageThree_menuPanel();
        menuBtn.setBackground(Color.white);
		menuBtn.setLocation(55, 100);
		menuBtn.setPreferredSize(size);
		
		sp.setBounds(60, 57, 300, 345);
		sp.setBackground(Color.white);
		sp.setViewportView(menuBtn);
        panel3.add(sp);
        
		// 레시피 라벨 좌표설정
		lblrcp.setSize(350, 100);
		lblrcp.setHorizontalAlignment(JLabel.CENTER);
		lblrcp.setFont(new Font("맑은 고딕",Font.BOLD, 15));
		lblrcp.setBounds(580, 30, 100, 100);
		panel3.add(lblrcp);
		// 추천메뉴 라벨 좌표설정
        lblrm.setBounds(175, 20, 100, 30);
		lblrm.setFont(new Font("맑은 고딕",Font.BOLD, 15));
		String selectedfood = "*선택한 재료 "+selected.toString();
		panel3.add(lblrm);
		// 스크롤 좌표설정
		sprcp.setPreferredSize(new Dimension(350, 350));
		sprcp.setBounds(430, 100, 400, 300);
		panel3.add(sprcp);
		// 스크롤 위치 조정
		txtrcp.setCaretPosition(0);
		
		bp.setBackground(Color.white);
		bp.setSize(225, 35);
		bp.setLocation(630, 20);
		panel3.add(bp);
				
		wp.setBackground(Color.white);
		wp.setSize(120,35);
		wp.setLocation(500, 22);
		panel3.add(wp);
				
		prevPage.setSize(50, 50);
		prevPage.setLocation(20, 400);
		panel3.add(prevPage);
		
		timerImg.setSize(35,35);
		timerImg.setLocation(460,22);
		panel3.add(timerImg);

		prevPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == prevPage) {
					setVisible(false);
					if (e.getSource() == prevPage) {
						PageTwo_FoodList.selected.clear();
						PageTwo_FoodList.selectedFood.clear();
						PageTwo_FoodList.selectedKcal.clear();
						selected.clear();
						count = 0;
						txtrcp.setText("");
						setVisible(false);
						new PageTwo_FoodList();
						}
				}
		}});
	}
	private Dimension getPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	private ImageIcon imageSetSize(ImageIcon icon, int i, int j) { // 이미지 아이콘 크기 조정
		// TODO Auto-generated method stub
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	// 추천 메뉴 출력 간격 조정
	public void RecipeBtn(){
		int width1 = 30;
		int width2 = 100;
		for(int i=0; i<rcp.length; i++) {
			int k= rcp_index[i];
			rcp[k].setBounds(50, width1, 180, 120);
			width1 += 150;
			rcpname[k].setBounds(115, width2, 180, 120);
			width2 += 150;
		}
	}
	// 추천 메뉴에 해당하는 레시피 txt 파일 불러오기 + 메뉴 선택 시 이미지 아이콘 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i=0;i<rcp.length;i++) {
			if(e.getSource()==rcp[i] & (rcpCheck[i] == false)) {
				rcpCheck[i] = true;
				filePath = "recipe"+(i+1) +".txt";
				getOneFileRead();
				txtrcp.setCaretPosition(0);
				for (int j=0;j<rcp.length;j++) {
					if (rcpCheck[j] == false) rcp[j].setEnabled(false);
				}
			}
			else if(e.getSource()==rcp[i] & (rcpCheck[i] == true)) {
				rcpCheck[i] = false;
				txtrcp.setText("*선택한 재료 "+selected.toString()+"\n\n");
				for (int j=0; j<PageTwo.menus.length; j++) {
					if (PageTwo.menus[j].order >= 1) {
		        		txtrcp.append("레시피: "+ PageTwo.menus[j].recipeName+"\n" + "있는 재료 / 필요한 재료 : "+ PageTwo.menus[j].order +"/" +PageTwo.menus[j].ingredient.length + "\n\n");}
					if (rcpCheck[j] == false) rcp[j].setEnabled(true);
				}
				txtrcp.setCaretPosition(0);
			}
		}
	}
	private void buildGUI() {
		// TODO Auto-generated method stub
		bp = new JPanel();
		wp = new JPanel();
		bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		wp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		c1 = new JLabel(" : ");
		c2 = new JLabel(" : ");
		
		w1 = new JLabel("00");
		w2 = new JLabel("00");
		w3 = new JLabel("00");
		
		start = new JButton("START");
		pause = new JButton("PAUSE");
		reset = new JButton("RESET");
		
		bp.add(start);
		bp.add(pause);
		bp.add(reset);
		
		wp.add(w1);
		wp.add(c1);
		wp.add(w2);
		wp.add(c2);
		wp.add(w3);
		
		start.setFont(new Font("serif", Font.BOLD, 11));
		pause.setFont(new Font("serif", Font.BOLD, 11));
		reset.setFont(new Font("serif", Font.BOLD, 11));
		
		w1.setFont(new Font("courier", Font.BOLD, 17));
		w2.setFont(new Font("courier", Font.BOLD, 17));
		w3.setFont(new Font("courier", Font.BOLD, 17));
		
		c1.setFont(new Font("courier", Font.BOLD, 17));
		c2.setFont(new Font("courier", Font.BOLD, 17));
		
		pause.setEnabled(false);
		reset.setEnabled(false);

		start.addActionListener(new ButtonListener());
		pause.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			
			if(s.equals("START")) {
				start.setEnabled(false);
				pause.setEnabled(true);
				reset.setEnabled(false);
				
				p_display = new Thread(new Runnable() {
					@Override
					public void run() {
						mm = Integer.parseInt(w1.getText());
						ss = Integer.parseInt(w1.getText());
						ms = Integer.parseInt(w1.getText());
						
						while (p_display == Thread.currentThread()) {
							mm = t % (1000*60) / 100/ 60;
							ss = t % (1000*60) / 100 %60;
							ms = t %100;
							
							try {
								Thread.sleep(9);
								
								w1.setText(String.format("%02d", mm));
								w2.setText(String.format("%02d", ss));
								w3.setText(String.format("%02d", ms));
								t++;
								
								if (ss >= 5 & (ss % 5 ==0) && ms == 0) {
									JOptionPane.showMessageDialog(null, "냉장고를 너무 오래 열었습니다.\n");  // 일정 시간마다 메세지 출력
								}
							} catch (InterruptedException e1) {}
						}
					}
				});
				p_display.start();
			}
			else if (s.equals("PAUSE")) {
				start.setEnabled(true);
				pause.setEnabled(false);
				reset.setEnabled(true);
				p_display = null;
			} else if (s.equals("RESET")) {
				start.setEnabled(true);
				pause.setEnabled(false);
				reset.setEnabled(false);
				w3.setText("00");
				w2.setText("00");
				w1.setText("00");
				t=0;
			}
		}
	}
	// 레시피 txt파일을 읽어오기
    public static void getOneFileRead() {
		try {
			FileInputStream fileStream = null;  //파일을 읽어오기 위한 FileInputStream 객체 선언
			
			fileStream = new FileInputStream(filePath); //FileInputStream에 읽어올 파일 경로 지정 실시
			
			byte readBuffer[] = new byte[fileStream.available()];
			
			while (fileStream.read(readBuffer) != -1) {
				txtrcp.setText(new String(readBuffer));

			}
			fileStream.close(); //스트림 닫기
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}