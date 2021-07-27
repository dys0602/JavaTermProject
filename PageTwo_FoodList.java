import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PageTwo_FoodList extends JFrame {
	public PageThree_Recipe PageThree;
	public static final PageOne_Login PageOne = null;
	
	public static final Object[] ColName = null;
	// ��Ḯ��Ʈ 
	String[] food = {"��ġ", "����", "����", "�κ�", "�������", "�", "���", "��", "����", "��ġ", "��", "����", "��ȣ��", "���", "��", "����", "����"};
	
	// Į�θ�����Ʈ (��� ����Ʈ�� ������ �ε����� ��� �ش� ����� Į�θ��� ��Ÿ��) 
	int[] kcal = {
			46, 66, 39, 76,
			242, 130, 74, 305,
			22, 131, 224, 22,
			26, 41, 34, 40, 126};
	
	ArrayList<Integer> foodList = new ArrayList<Integer>();
	String[] newFood = new String[12];
	Integer[] newKcal = new Integer[12];
	Random rand = new Random();
	
	// ��� ��ư
	JButton foodBtn[] = new JButton[newFood.length];
	ImageIcon icon[] = new ImageIcon[newKcal.length];
	
	// ���� �ϴ� ��ư
	String[] Str = {"Ȯ��","���� ���", "��ü ���"};
	JButton[] strBtn = new JButton[Str.length];
 	
 	//���� ��� �ȳ� �ؽ�Ʈ
 	public static String userName = PageOne.userText.getText();
 	JLabel jl1 = new JLabel(userName + " �� �ȳ��ϼ���!");
 	JLabel jl2 = new JLabel("��� ���� �� Ȯ�� ��ư�� �����ּ���.");
 	
 	// ���� �ϴܿ� �� Į�θ��� ��� ���� �ؽ�Ʈ
 	JLabel num = new JLabel("�� ��� ����");
 	JLabel kc = new JLabel("�� Į�θ�");
 	
 	// ���� �ϴ� �� Į�θ��� ��� ���� ��µǴ� �ؽ�Ʈ�ʵ�
 	JTextField numTf = new JTextField(20);
 	JTextField kcalTf = new JTextField(20);
 	
 	// �α��� ȭ������ ���ư��� ��ư, ������ Ȯ�� ��ư
	String[] page = {"����", "���������"}; 
	String [][] Data;
	JButton showRecipe = new JButton();
	JButton Prevpage = new JButton();
	
	ImageIcon icon1 = new ImageIcon("home_button.png"); 	// icon1 - �α��� ȭ�� �̹�����ư
	ImageIcon icon2 = new ImageIcon("recipe_check.png"); 	// icon2 - ������ Ȯ�� ȭ�� �̹��� ��ư
	String [] ColName1 = {"���","Į�θ�", "����"}; 			// ���õ� ��� ��µǴ� ���̺��� ù��° �� 
	
	DefaultTableModel model = new DefaultTableModel(Data, ColName1);
	JTable table = new JTable(model);
	// ���õ� ��� ��Ƶδ� selectedFood ����Ʈ�� �� ����� Į�θ� ��Ƶδ� selectedKcal ����Ʈ
	public static ArrayList<Integer> selectedKcal = new ArrayList<Integer>();
	public static ArrayList<String> selectedFood = new ArrayList<String>();
	public static ArrayList<String> selected = new ArrayList<String>();
	
	// �� Į�θ��� �� ��ᰳ�� ��Ÿ���� int�� ����
	int totalKcal;
	int howMany;
	
	int row; // ������ ���� �ε��� ��Ƶδ� ����
	boolean flag = false; // Ȯ�ι�ư ������ �� true�� ����
	
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
    	
		setTitle("���ù�����? : "+userName+"�� �����");
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
		
		// ���õ� ��� ��µǴ� ���̺� ��ġ���� �� ����������
		sc.setSize(500, 330);
		sc.setLocation(350, 20);
		panel2.add(sc);
		
		// �ڷΰ��� ��ư ��ġ���� �� ����������
		ppb.setSize(35, 35);
		ppb.setLocation(25, 410);
		panel2.add(ppb);
		
		// ������Ȯ�� ��ư ��ġ���� �� ����������
		rcb.setSize(100, 30);
		rcb.setLocation(710, 375);
		panel2.add(rcb);
		
		// Ȯ��, �������, ��ü��� ��ư ��ġ���� �� ����������
		sbtn.setSize(300, 20);
		sbtn.setLocation(27, 375);
		panel2.add(sbtn);
		
		// ����ư ��ġ���� �� ����������
		mbtn.setSize(300, 300);
		mbtn.setLocation(25, 60);
		panel2.add(mbtn);
		
		// ������� �ȳ� �ؽ�Ʈ ��ġ���� �� ����������
		jl1.setSize(300, 20);
		jl1.setLocation(115,15);
		jl1.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(jl1);
				
		jl2.setSize(300, 20);
		jl2.setLocation(75,35);
		jl2.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(jl2);
				
		// �� Į�θ� ��� ��ġ���� �� ����������
		kc.setSize(70, 50);
		kc.setLocation(400,350);
		kc.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(kc);
		
		// �� Į�θ� �� ��µǴ� �ؽ�Ʈ�ʵ�
		kcalTf.setSize(200, 27);
		kcalTf.setLocation(490, 360);
		kcalTf.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(kcalTf);
				
		// �� ��ᰳ�� ��� ��ġ���� �� ����������
		num.setSize(70, 50);
		num.setLocation(400, 380);
		num.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(num);
				
		// �� ��ᰳ�� ��µǴ� �ؽ�Ʈ�ʵ�
		numTf.setSize(200, 27);
		numTf.setLocation(490, 393);
		numTf.setFont(new Font("���� ���",Font.BOLD, 12));
		panel2.add(numTf);
		
		// ��� �̹��� ��ư�� �̺�Ʈ �߰�	
		for(int i=0;i<newFood.length;i++) {
			final int index = i;
			foodBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton foodBtn = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{newFood[index],newKcal[index],1}); // Ŭ���� �ش� �������� ���, Į�θ�, ���� ��Ÿ���� �� ���
					selectedFood.add(newFood[index]); // selectedFood, selectedKcal ����Ʈ���� �ش� ������ �߰�
					selectedKcal.add(newKcal[index]);
					totalKcal += newKcal[index]; // totalKcal�� selectedKcal ����Ʈ�� ���Ұ��� �ջ�
					howMany = selectedFood.size(); // howMany�� selectedFood ����Ʈ�� ���Ұ��� �ִ´�
				}
			});
		}
		
		// Ȯ�� ��ư�� �̺�Ʈ �߰� - Ŭ���� ������ �� ����, �� Į�θ� �ջ��ؼ� ���� �ϴ��� �ؽ�Ʈ�ʵ忡 ���
		strBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == strBtn[0]) {
					kcalTf.setText(""+totalKcal+" Kcal");
					numTf.setText(""+ howMany+" ��");
					flag = true;
				}
			}
		});
		
		// ���� ��� ��ư�� �̺�Ʈ �߰� - ���õ� ���̺� �� ���� & ������ �� ��ᰳ��, �� Į�θ� ���
		strBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent k) {
				// TODO Auto-generated method stub
				if (k.getSource() == strBtn[1]) {
					JButton foodBtn = (JButton)k.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					
					//���� ��� ���� ���� try-catch�� ����
					try {
						m.removeRow(table.getSelectedRow()); // ���õ� ���̺� �� ����
						
						// ���õ� ���, Į�θ� ��Ƶδ� ����Ʈ�� selectedFood, selectedKcal ����Ʈ���� �ش� ������ ����
						selectedFood.remove(row);
						selectedKcal.remove(row);
						
						howMany = selectedFood.size(); // �� ��ᰳ�� ��Ƶδ� ������ howMany�� selectedFood ����Ʈ�� ���� ������ ������ ���´�
						
						// �� Į�θ� ������ totalKcal 0���� �ʱ�ȭ �����ְ� selectedKcal ����Ʈ�� ���Ұ��� ���ؼ� ���ο� ���� ����ش�
						totalKcal = 0;
						for (int i = 0; i < selectedKcal.size(); i++) {
							totalKcal += selectedKcal.get(i);
						}
						
						// �� ��ᰳ��, �� Į�θ� �� ���
						kcalTf.setText(""+totalKcal+ " Kcal");
						numTf.setText(""+ howMany+" ��");
					} catch(ArrayIndexOutOfBoundsException e) {
						JOptionPane.showMessageDialog(null, "������ ��� ���� ���� ��, ��ư�� �����ּ���.");
					}
				}
			}
		});
		
		// ��ü��� ��ư�� �̺�Ʈ �߰� - ��� ���̺� �� ���� & �� ��ᰳ��, �� Į�θ� 0���� �ʱ�ȭ
		strBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == strBtn[2]) {
					JButton foodBtn = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.setRowCount(0);
					
					// ���õ� ���, Į�θ� ��Ƶδ� ����Ʈ�� selectedFood, selectedKcal ����Ʈ���� ��� ������ ����
					selectedFood.clear();
					selectedKcal.clear();
					totalKcal = 0;
					kcalTf.setText("");
					numTf.setText("");
					}
				}
		});
		
		// �ڷ� �Ѿ�� ��ư�� �̺�Ʈ �߰� - �α��� ȭ�� �Ѿ��
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
		
		// ������ Ȯ�� ��ư�� �̺�Ʈ �߰� - ���� ȭ�� �Ѿ��
		showRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == showRecipe) {
					if (selectedFood.size() == 0) {
						JOptionPane.showMessageDialog(null, "���� ��Ḧ �������� �ʾҽ��ϴ�.");  // ��� �̼��ý� �˸��޼���
					} else if (!flag) { 
						JOptionPane.showMessageDialog(null, "Ȯ�� ��ư�� �����ּ���");  		  // Ȯ�� ��ư�� ������ �ʾ��� �� �˸��޼���
					} else {
						foundRecipe(); // ������ �˻� �̺�Ʈ
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
	
	// ���� ��ܿ� ��ġ�� ������ ��� ��µǴ� ���̺�  
	class Screen extends JPanel implements MouseListener{
		Screen(){
			setBackground(Color.WHITE);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(40); // �� ���� ����
			table.setFont(new Font("���� ���",Font.PLAIN, 14)); // ���̺� ��Ʈ ����
			// �� ���̰� ������ ȭ�� ����� �Ѿ��� �ڵ����� scrollpane ����
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
	
	// ����̹��� ��ư ���� Ŭ����
	 	class MenuBtn extends JPanel{
			MenuBtn(){
				setLayout(new GridLayout(4,3,3,3));
				setBackground(Color.WHITE);
				for(int i=0; i<newFood.length; i++) {
					int indexNumber = foodList.get(i);
					foodBtn[i]= new JButton(food[indexNumber]);
					int n = indexNumber + 1;
					// �̹��� �ε� �� ������ ����
					icon[i] = new ImageIcon("food"+ n +".jpg");
					icon[i] = imageSetSize(icon[i], 110,110);
					// ��ư�� �̹��� ���
					foodBtn[i].setIcon(icon[i]);
					add(foodBtn[i]);
				}
			}
			// �̹��� ������ ������ ���� ���� Ŭ���� ����
			private ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
				// TODO Auto-generated method stub
				Image ximg = icon.getImage();
				Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
				ImageIcon xyimg = new ImageIcon(yimg);
				return xyimg;
			}
		}
 	// ������Ȯ�� ��ư ���� Ŭ����
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
 	// ������������ ���ư��� ��ư ���� Ŭ���� 
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
	// Ȯ��, �������, ��ü��� ��ư ���� Ŭ����
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
	//Menu Ŭ����
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
			
		String[] menu0Ingre = new String[] {"����","����","��","����"};
		menu0.putIngre("���ں���", menu0Ingre);
			
		String[] menu1Ingre = new String[] {"��ġ","�������","�κ�","����","����","��","����","���尡��"};
		menu1.putIngre("��ġ�",menu1Ingre);
			
		String[] menu2Ingre = new String[] {"���","��","����", "�Ŀ���", "�ұ�", "���尡��"};
		menu2.putIngre("�������",menu2Ingre);

		String[] menu3Ingre = new String[] {"��","���","���ҽ�","�Ŀ���"};
		menu3.putIngre("���԰��������",menu3Ingre);
			
		String[] menu4Ingre = new String[] {"��","����","��","�Ǹ�","��","������","����","����","����"};
		menu4.putIngre("������",menu4Ingre);
			
		String[] menu5Ingre = new String[] {"�κ�","�Ŀ���","����","����","���尡��","����","��","���⸧","���ұ�"};
		menu5.putIngre("�κ�����",menu5Ingre);
			
		String[] menu6Ingre = new String[] {"����","�κ�","����","��ȣ��","����","����","����","��","����","����","���尡��","����"};
		menu6.putIngre("�����",menu6Ingre);
			
		String[] menu7Ingre = new String[] {"��ȣ��","���","�а���","��⸧","�ұ�"};
		menu7.putIngre("ȣ����",menu7Ingre);
			
		String[] menu8Ingre = new String[] {"�","����","�Ǹ�","�Ŀ���","��","����", "���ҽ�","����","����","����","���尡��"};
		menu8.putIngre("�����",menu8Ingre);
			
		String[] menu9Ingre = new String[] {"��ȣ��","����","����","������","��","����","��⸧","�ұ�","��"};
		menu9.putIngre("ȣ�ڳ���",menu9Ingre);
			
		String[] menu10Ingre = new String[] {"����","����","���ҽ�","���","���⸧","��","�Ŀ���","��"};
		menu10.putIngre("��������",menu10Ingre);
			
		String[] menu11Ingre = new String[] {"��","��ġ","��","���","�Ŀ���","���߰���","����","���⸧"};
		menu11.putIngre("��ġ������",menu11Ingre);
			
		String[] menu12Ingre = new String[] {"��ġ","����","��ġ","��ħ����","��������"};
		menu12.putIngre("��ġ��",menu12Ingre);
			
		String[] menu13Ingre = new String[] {"����","���","������","�ұ�","���߰���","ü��ġ��"};
		menu13.putIngre("������",menu13Ingre);
			
		String[] menu14Ingre = new String[] {"���","����","�Ŀ���","����","����","�ø����","����","��", "���"};
		menu14.putIngre("�����",menu14Ingre);
			
		String[] menu15Ingre = new String[] {"�������","����","�","����","���","����","����","��", "����", "���ұ�", "���尡��","���⸧"};
		menu15.putIngre("��ä",menu15Ingre);
			
		String[] menu16Ingre = new String[] {"��ġ","�������","����","�κ�","�ұ�","�Ŀ���","���⸧","���߰���", "������", "����", "����", "��", "����","���ұ�"};
		menu16.putIngre("�κα�ġ",menu16Ingre);
			
		String[] menu17Ingre = new String[] {"��","����","�Ұ��","���","�ұ�","��","����","������", "�ұ�"};
		menu17.putIngre("�����α�",menu17Ingre);
			
		String[] menu18Ingre = new String[] {"����","����","���","���","����","�ұ�","���尡��","�������", "��ϸӽ��͵�","�ø����","����"};
		menu18.putIngre("���ڻ�����",menu18Ingre);
			
		String[] menu19Ingre = new String[] {"���","����","���","������","�ϵ���","��","����","�ø�����", "����","�Ǹ�","��ø","��������ĥ���ҽ�","�ұ�","���尡��"};
		menu19.putIngre("���Ƕ��̽�",menu19Ingre);

		//�ִ� ���� ������ ��� ��ġ�� ���� ���ϱ�
		for(int j=0; j<menus.length; j++) {
			for(int i=0; i<selected.size(); i++) {
				if(Arrays.asList(menus[j].ingredient).contains(selected.get(i))) {
					menus[j].order++;
				}
			}	
		}
		//����, order ũ�� ��� �������� ����
		Arrays.sort(menus);
	}
}