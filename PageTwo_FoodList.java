import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PageTwo_FoodList extends JFrame {
	public PageThree_Recipe PageThree;
	public static final PageOne_Login PageOne = null;
	
	public static final Object[] ColName = null;
	// 재료리스트 
	String[] food = {"김치", "감자", "양파", "두부", "돼지고기", "어묵", "계란", "햄", "만두", "참치", "떡", "버섯", "애호박", "당근", "파", "고추", "마늘"};
	
	// 칼로리리스트 (재료 리스트와 동일한 인덱스인 경우 해당 재료의 칼로리를 나타냄) 
	int[] kcal = {
			46, 66, 39, 76,
			242, 130, 74, 305,
			22, 131, 224, 22,
			26, 41, 34, 40, 126};
	
	ArrayList<Integer> foodList = new ArrayList<Integer>();
	String[] newFood = new String[12];
	Integer[] newKcal = new Integer[12];
	Random rand = new Random();
	
	// 재료 버튼
	JButton foodBtn[] = new JButton[newFood.length];
	ImageIcon icon[] = new ImageIcon[newKcal.length];
	
	// 좌측 하단 버튼
	String[] Str = {"확인","선택 취소", "전체 취소"};
	JButton[] strBtn = new JButton[Str.length];
 	
 	//좌측 상단 안내 텍스트
 	public static String userName = PageOne.userText.getText();
 	JLabel jl1 = new JLabel(userName + " 님 안녕하세요!");
 	JLabel jl2 = new JLabel("재료 선택 후 확인 버튼을 눌러주세요.");
 	
 	// 우측 하단에 총 칼로리와 재료 개수 텍스트
 	JLabel num = new JLabel("총 재료 개수");
 	JLabel kc = new JLabel("총 칼로리");
 	
 	// 우측 하단 총 칼로리와 재료 개수 출력되는 텍스트필드
 	JTextField numTf = new JTextField(20);
 	JTextField kcalTf = new JTextField(20);
 	
 	// 로그인 화면으로 돌아가는 버튼, 레시피 확인 버튼
	String[] page = {"이전", "레시피출력"}; 
	String [][] Data;
	JButton showRecipe = new JButton();
	JButton Prevpage = new JButton();
	
	ImageIcon icon1 = new ImageIcon("home_button.png"); 	// icon1 - 로그인 화면 이미지버튼
	ImageIcon icon2 = new ImageIcon("recipe_check.png"); 	// icon2 - 레시피 확인 화면 이미지 버튼
	String [] ColName1 = {"재료","칼로리", "수량"}; 			// 선택된 재료 출력되는 테이블의 첫번째 행 
	
	DefaultTableModel model = new DefaultTableModel(Data, ColName1);
	JTable table = new JTable(model);
	// 선택된 재료 담아두는 selectedFood 리스트와 그 재료의 칼로리 담아두는 selectedKcal 리스트
	public static ArrayList<Integer> selectedKcal = new ArrayList<Integer>();
	public static ArrayList<String> selectedFood = new ArrayList<String>();
	public static ArrayList<String> selected = new ArrayList<String>();
	
	// 총 칼로리와 총 재료개수 나타내는 int형 변수
	int totalKcal;
	int howMany;
	
	int row; // 삭제할 행의 인덱스 담아두는 변수
	boolean flag = false; // 확인버튼 누르면 값 true로 변경
	
	public PageTwo_FoodList() {
		// background image
		ImageIcon backgroundIcon = new ImageIcon("pagetwo_background.png");
    	
    	JPanel back = new JPanel() {
    		public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(backgroundIcon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false); 
                super.paintComponent(g); 
            }
    	};
    	
		setTitle("오늘뭐먹지? : "+userName+"의 냉장고");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,500);
		setVisible(true);
		setResizable(false);
		setLocation(600, 300);
		
        PageTwo_Panel(back);
        add(back);
        for (int i = 0; i< selectedFood.size(); i++) {
			selected.add(selectedFood.get(i));
		}
	}
	
	public void PageTwo_Panel(JPanel panel2){
		panel2.setLayout(null);
		
		for (int i = 0; i < 12; i++) {
			int listNum = rand.nextInt(food.length);
			foodList.add(listNum);
			for (int j = 0; j < i; j++) {
				if (foodList.get(i) == foodList.get(j)) {
					foodList.remove(i);
					i--;
					break;
				}
			}
		}
		
		for (int i = 0; i < foodList.size(); i++) {
			int indexNumber = foodList.get(i);
			newFood[i] = food[indexNumber];
			newKcal[i] = kcal[indexNumber];
		}
		
		Screen sc = new Screen();
		StrBtn sbtn = new StrBtn();
		PrevpageBtn ppb = new PrevpageBtn();
		RecipeBtn rcb = new RecipeBtn();
		MenuBtn mbtn = new MenuBtn();
		
		// 선택된 재료 출력되는 테이블 위치설정 및 사이즈조절
		sc.setSize(500, 330);
		sc.setLocation(350, 20);
		panel2.add(sc);
		
		// 뒤로가기 버튼 위치설정 및 사이즈조절
		ppb.setSize(35, 35);
		ppb.setLocation(25, 410);
		panel2.add(ppb);
		
		// 레시피확인 버튼 위치설정 및 사이즈조절
		rcb.setSize(100, 30);
		rcb.setLocation(710, 375);
		panel2.add(rcb);
		
		// 확인, 선택취소, 전체취소 버튼 위치설정 및 사이즈조절
		sbtn.setSize(300, 20);
		sbtn.setLocation(27, 375);
		panel2.add(sbtn);
		
		// 재료버튼 위치설정 및 사이즈조절
		mbtn.setSize(300, 300);
		mbtn.setLocation(25, 60);
		panel2.add(mbtn);
		
		// 좌측상단 안내 텍스트 위치설정 및 사이즈조절
		jl1.setSize(300, 20);
		jl1.setLocation(115,15);
		jl1.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(jl1);
				
		jl2.setSize(300, 20);
		jl2.setLocation(75,35);
		jl2.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(jl2);
				
		// 총 칼로리 출력 위치설정 및 사이즈조절
		kc.setSize(70, 50);
		kc.setLocation(400,350);
		kc.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(kc);
		
		// 총 칼로리 값 출력되는 텍스트필드
		kcalTf.setSize(200, 27);
		kcalTf.setLocation(490, 360);
		kcalTf.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(kcalTf);
				
		// 총 재료개수 출력 위치설정 및 사이즈조절
		num.setSize(70, 50);
		num.setLocation(400, 380);
		num.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(num);
				
		// 총 재료개수 출력되는 텍스트필드
		numTf.setSize(200, 27);
		numTf.setLocation(490, 393);
		numTf.setFont(new Font("맑은 고딕",Font.BOLD, 12));
		panel2.add(numTf);
		
		// 재료 이미지 버튼에 이벤트 추가	
		for(int i=0;i<newFood.length;i++) {
			final int index = i;
			foodBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton foodBtn = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{newFood[index],newKcal[index],1}); // 클릭시 해당 데이터의 재료, 칼로리, 수량 나타나는 행 출력
					selectedFood.add(newFood[index]); // selectedFood, selectedKcal 리스트에서 해당 데이터 추가
					selectedKcal.add(newKcal[index]);
					totalKcal += newKcal[index]; // totalKcal에 selectedKcal 리스트의 원소값을 합산
					howMany = selectedFood.size(); // howMany에 selectedFood 리스트의 원소개수 넣는다
				}
			});
		}
		
		// 확인 버튼에 이벤트 추가 - 클릭된 재료들의 총 개수, 총 칼로리 합산해서 우측 하단의 텍스트필드에 출력
		strBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == strBtn[0]) {
					kcalTf.setText(""+totalKcal+" Kcal");
					numTf.setText(""+ howMany+" 개");
					flag = true;
				}
			}
		});
		
		// 선택 취소 버튼에 이벤트 추가 - 선택된 테이블 행 삭제 & 수정된 총 재료개수, 총 칼로리 출력
		strBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent k) {
				// TODO Auto-generated method stub
				if (k.getSource() == strBtn[1]) {
					JButton foodBtn = (JButton)k.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					
					//선택 취소 에러 관련 try-catch문 수행
					try {
						m.removeRow(table.getSelectedRow()); // 선택된 테이블 행 삭제
						
						// 선택된 재료, 칼로리 담아두는 리스트인 selectedFood, selectedKcal 리스트에서 해당 데이터 삭제
						selectedFood.remove(row);
						selectedKcal.remove(row);
						
						howMany = selectedFood.size(); // 총 재료개수 담아두는 변수인 howMany는 selectedFood 리스트의 원소 개수를 값으로 갖는다
						
						// 총 칼로리 변수인 totalKcal 0으로 초기화 시켜주고 selectedKcal 리스트의 원소값을 더해서 새로운 값을 담아준다
						totalKcal = 0;
						for (int i = 0; i < selectedKcal.size(); i++) {
							totalKcal += selectedKcal.get(i);
						}
						
						// 총 재료개수, 총 칼로리 값 출력
						kcalTf.setText(""+totalKcal+ " Kcal");
						numTf.setText(""+ howMany+" 개");
					} catch(ArrayIndexOutOfBoundsException e) {
						JOptionPane.showMessageDialog(null, "삭제할 재료 행을 선택 후, 버튼을 눌러주세요.");
					}
				}
			}
		});
		
		// 전체취소 버튼에 이벤트 추가 - 모든 테이블 행 삭제 & 총 재료개수, 총 칼로리 0으로 초기화
		strBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == strBtn[2]) {
					JButton foodBtn = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.setRowCount(0);
					
					// 선택된 재료, 칼로리 담아두는 리스트인 selectedFood, selectedKcal 리스트에서 모든 데이터 삭제
					selectedFood.clear();
					selectedKcal.clear();
					totalKcal = 0;
					kcalTf.setText("");
					numTf.setText("");
					}
				}
		});
		
		// 뒤로 넘어가기 버튼에 이벤트 추가 - 로그인 화면 넘어가기
		Prevpage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == Prevpage) {
					selected.clear();
					selectedFood.clear();
					selectedKcal.clear();
					PageThree_Recipe.selected.clear();
					PageThree_Recipe.count = 0;
					PageThree_Recipe.txtrcp.setText("");
					setVisible(false);
					new PageOne_Login();
				}
			}
		});
		
		// 레시피 확인 버튼에 이벤트 추가 - 다음 화면 넘어가기
		showRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == showRecipe) {
					if (selectedFood.size() == 0) {
						JOptionPane.showMessageDialog(null, "아직 재료를 선택하지 않았습니다.");  // 재료 미선택시 알림메세지
					} else if (!flag) { 
						JOptionPane.showMessageDialog(null, "확인 버튼을 눌러주세요");  		  // 확인 버튼을 누르지 않았을 시 알림메세지
					} else {
						foundRecipe(); // 레시피 검색 이벤트
						setVisible(false);
						dedupe();
						PageThree.selected.clear();
						for (int i = 0; i < selected.size(); i++) {
							PageThree.selected.add(selected.get(i));
						}
						new PageThree_Recipe(); 
					}
				}
			}
		});
	}
	public void dedupe() {
		// TODO Auto-generated method stub
		for (int i = selectedFood.size()-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (selectedFood.get(i) == selectedFood.get(j)) {
					selectedFood.remove(i);
					selectedKcal.remove(i);
					break;
				}
			}	
		}
	}
	
	// 우측 상단에 위치한 선택한 재료 출력되는 테이블  
	class Screen extends JPanel implements MouseListener{
		Screen(){
			setBackground(Color.WHITE);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(40); // 행 높이 설정
			table.setFont(new Font("맑은 고딕",Font.PLAIN, 14)); // 테이블 폰트 설정
			// 행 길이가 지정된 화면 사이즈를 넘어갈경우 자동으로 scrollpane 생성
			add(new JScrollPane(table));
			table.addMouseListener(this);
			table.setPreferredScrollableViewportSize(new Dimension(480, 300));
            table.setFillsViewportHeight(true);
            table.setRowSelectionAllowed(true);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 row = table.getSelectedRow();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	// 재료이미지 버튼 생성 클래스
	 	class MenuBtn extends JPanel{
			MenuBtn(){
				setLayout(new GridLayout(4,3,3,3));
				setBackground(Color.WHITE);
				for(int i=0; i<newFood.length; i++) {
					int indexNumber = foodList.get(i);
					foodBtn[i]= new JButton(food[indexNumber]);
					int n = indexNumber + 1;
					// 이미지 로딩 및 사이즈 조정
					icon[i] = new ImageIcon("food"+ n +".jpg");
					icon[i] = imageSetSize(icon[i], 110,110);
					// 버튼에 이미지 등록
					foodBtn[i].setIcon(icon[i]);
					add(foodBtn[i]);
				}
			}
			// 이미지 아이콘 사이즈 조정 위한 클래스 생성
			private ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
				// TODO Auto-generated method stub
				Image ximg = icon.getImage();
				Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
				ImageIcon xyimg = new ImageIcon(yimg);
				return xyimg;
			}
		}
 	// 레시피확인 버튼 생성 클래스
 	class RecipeBtn extends JPanel {
 		RecipeBtn() {
 			setLayout(new GridLayout(1,1,3,3));
 			setBackground(Color.WHITE);
 			showRecipe = new JButton();
 			icon2 = imageSetSize(icon2, 110, 40);
 			showRecipe.setIcon(icon2);
 			add(showRecipe);
 		}
 		public ImageIcon imageSetSize(ImageIcon icon2, int i, int j) {
			// TODO Auto-generated method stub
			Image ximg = icon2.getImage();
			Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
			ImageIcon xyimg = new ImageIcon(yimg);
			return xyimg;
		}
 	}
 	// 이전페이지로 돌아가는 버튼 생성 클래스 
 	class PrevpageBtn extends JPanel {
 		PrevpageBtn() {
 			setLayout(new GridLayout(1,1,3,3));
 			setBackground(Color.WHITE);
 			Prevpage = new JButton();
 			icon1 = imageSetSize(icon1, 35, 35);
 			Prevpage.setIcon(icon1);
 			add(Prevpage);
 		}
		private ImageIcon imageSetSize(ImageIcon icon1, int i, int j) {
			// TODO Auto-generated method stub
			Image ximg = icon1.getImage();
			Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
			ImageIcon xyimg = new ImageIcon(yimg);
			return xyimg;
		}
 	}
	// 확인, 선택취소, 전체취소 버튼 생성 클래스
 	class StrBtn extends JPanel{
		StrBtn(){
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1,2,3,3));
			
			for(int i=0;i<Str.length;i++) {
				strBtn[i]= new JButton(new ImageIcon("pagetwo_button"+i+".png"));
				add(strBtn[i]);
			}
		}	
	}
	//Menu 클래스
	public class Menu implements Comparable<Menu>{
		    String recipeName;
			String[] ingredient;
			int order = 0;
			
			public void putIngre(String recipeName,String[] st) {
				
				this.recipeName = recipeName;
				this.ingredient = new String[st.length];
				
				for (int i=0; i<st.length;i++) {
					ingredient[i]=st[i];
				}
			}
			@Override
			public int compareTo(Menu m) {
				if(this.order<m.order) return 1;
				else if(this.order>m.order) return -1;
				return 0;
			}
	}
	public static Menu[] menus = new Menu[20];
		
	public void foundRecipe() {
			
		for (int i = 0; i< selectedFood.size(); i++) {
			dedupe();
			selected.add(selectedFood.get(i));
		}
			
		Menu menu0= new Menu();
		Menu menu1= new Menu();
		Menu menu2 = new Menu();
		Menu menu3 = new Menu();
		Menu menu4= new Menu();
		Menu menu5= new Menu();
		Menu menu6 = new Menu();
		Menu menu7 = new Menu();
		Menu menu8 = new Menu();
		Menu menu9 = new Menu();
		Menu menu10 = new Menu();
		Menu menu11 = new Menu();
		Menu menu12 = new Menu();
		Menu menu13 = new Menu();
		Menu menu14 = new Menu();
		Menu menu15 = new Menu();
		Menu menu16 = new Menu();
		Menu menu17 = new Menu();
		Menu menu18 = new Menu();
		Menu menu19 = new Menu();
			
		menus[0] = menu0;
		menus[1] = menu1;
		menus[2] = menu2;
		menus[3] = menu3;
		menus[4] = menu4;
		menus[5] = menu5;
		menus[6] = menu6;
		menus[7] = menu7;
		menus[8] = menu8;
		menus[9] = menu9;
		menus[10] = menu10;
		menus[11] = menu11;
		menus[12] = menu12;
		menus[13] = menu13;
		menus[14] = menu14;
		menus[15] = menu15;
		menus[16] = menu16;
		menus[17] = menu17;
		menus[18] = menu18;
		menus[19] = menu19;
			
		String[] menu0Ingre = new String[] {"감자","양파","파","마늘"};
		menu0.putIngre("감자볶음", menu0Ingre);
			
		String[] menu1Ingre = new String[] {"김치","돼지고기","두부","고추","양파","파","마늘","고춧가루"};
		menu1.putIngre("김치찌개",menu1Ingre);
			
		String[] menu2Ingre = new String[] {"계란","파","마늘", "식용유", "소금", "후춧가루"};
		menu2.putIngre("계란말이",menu2Ingre);

		String[] menu3Ingre = new String[] {"햄","계란","굴소스","식용유"};
		menu3.putIngre("스팸계란볶음밥",menu3Ingre);
			
		String[] menu4Ingre = new String[] {"떡","양파","파","피망","햄","고추장","간장","설탕","물엿"};
		menu4.putIngre("떡볶이",menu4Ingre);
			
		String[] menu5Ingre = new String[] {"두부","식용유","간장","물엿","고춧가루","마늘","파","참기름","깨소금"};
		menu5.putIngre("두부조림",menu5Ingre);
			
		String[] menu6Ingre = new String[] {"감자","두부","쇠고기","애호박","양파","버섯","고추","파","육수","된장","고춧가루","마늘"};
		menu6.putIngre("된장찌개",menu6Ingre);
			
		String[] menu7Ingre = new String[] {"애호박","계란","밀가루","콩기름","소금"};
		menu7.putIngre("호박전",menu7Ingre);
			
		String[] menu8Ingre = new String[] {"어묵","양파","피망","식용유","깨","간장", "굴소스","물엿","설탕","마늘","후춧가루"};
		menu8.putIngre("어묵볶음",menu8Ingre);
			
		String[] menu9Ingre = new String[] {"애호박","양파","고추","새우젓","파","마늘","들기름","소금","깨"};
		menu9.putIngre("호박나물",menu9Ingre);
			
		String[] menu10Ingre = new String[] {"버섯","양파","굴소스","당근","참기름","파","식용유","깨"};
		menu10.putIngre("버섯볶음",menu10Ingre);
			
		String[] menu11Ingre = new String[] {"밥","김치","파","계란","식용유","고추가루","간장","참기름"};
		menu11.putIngre("김치볶음밥",menu11Ingre);
			
		String[] menu12Ingre = new String[] {"김치","양파","참치","부침가루","감자전분"};
		menu12.putIngre("김치전",menu12Ingre);
			
		String[] menu13Ingre = new String[] {"감자","계란","베이컨","소금","후추가루","체다치즈"};
		menu13.putIngre("감자전",menu13Ingre);
			
		String[] menu14Ingre = new String[] {"당근","고추","식용유","쯔유","간장","올리고당","맛술","깨", "곤약"};
		menu14.putIngre("어묵조림",menu14Ingre);
			
		String[] menu15Ingre = new String[] {"돼지고기","양파","어묵","버섯","당근","간장","설탕","파", "마늘", "깨소금", "후춧가루","참기름"};
		menu15.putIngre("잡채",menu15Ingre);
			
		String[] menu16Ingre = new String[] {"김치","돼지고기","양파","두부","소금","식용유","참기름","고추가루", "고추장", "간장", "설탕", "파", "마늘","깨소금"};
		menu16.putIngre("두부김치",menu16Ingre);
			
		String[] menu17Ingre = new String[] {"떡","만두","소고기","계란","소금","파","마늘","국간장", "소금"};
		menu17.putIngre("떡만두국",menu17Ingre);
			
		String[] menu18Ingre = new String[] {"감자","오이","당근","계란","버터","소금","후춧가루","마요네즈", "허니머스터드","올리고당","설탕"};
		menu18.putIngre("감자샐러드",menu18Ingre);
			
		String[] menu19Ingre = new String[] {"계란","양파","당근","옥수수","완두콩","밥","우유","올리브유", "마늘","피망","케첩","스리라차칠리소스","소금","후춧가루"};
		menu19.putIngre("오므라이스",menu19Ingre);

		//있는 재료와 레시피 재료 겹치는 개수 구하기
		for(int j=0; j<menus.length; j++) {
			for(int i=0; i<selected.size(); i++) {
				if(Arrays.asList(menus[j].ingredient).contains(selected.get(i))) {
					menus[j].order++;
				}
			}	
		}
		//정렬, order 크기 대로 내림차순 정렬
		Arrays.sort(menus);
	}
}