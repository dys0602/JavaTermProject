import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 
public class PageOne_Login extends JFrame{
	
	// 외부 클래스 연결을 위해
	public PageTwo_FoodList PageTwo;
    public static JTextField userText;
    
    JScrollPane scrollPane;
    ImageIcon backgroundIcon;
    
    JButton btnLogin;
    JPasswordField passText;
    String nickname;
    boolean LoginCheck;
    
    Color col_green = new Color(0x70AD47);
   
    public PageOne_Login() {

    	// background image 삽입
    	backgroundIcon = new ImageIcon("login_background.png");
    	
    	JPanel back = new JPanel() {
    		public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(backgroundIcon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false); 
                super.paintComponent(g);
            }
    	};
    	
        // setting
        setTitle("오늘 뭐 먹지?");
        setSize(400, 500);
        setResizable(false);
        setLocation(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LoginPanel(back);
        add(back);
        setVisible(true);
    }
   
    public void LoginPanel(JPanel panel1){
    	
    	panel1.setLayout(null);   
        
    	// nickname, password panel 이미지 디자인
    	JLabel userImage = new JLabel(new ImageIcon("username.png"));
    	userImage.setBounds(20, 110, 50, 70);
        panel1.add(userImage);
        
    	JLabel passwordImage = new JLabel(new ImageIcon("password.png"));
    	passwordImage.setBounds(20, 190, 50, 70);
        panel1.add(passwordImage);
        
        // nickname, password panel label 디자인
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("맑은 고딕",Font.BOLD, 15));
        userLabel.setBounds(70, 140, 100, 25);
        panel1.add(userLabel);
       
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("맑은 고딕",Font.BOLD, 15));
        passLabel.setBounds(70, 220, 100, 25);
        panel1.add(passLabel);
        
        // nickname, password 입력 받기
        userText = new JTextField(30);
        userText.setFont(new Font("맑은 고딕",Font.BOLD, 18));
        userText.setBounds(170, 140, 160, 25);
        panel1.add(userText);
        
        passText = new JPasswordField(30);
        passText.setFont(new Font("맑은 고딕",Font.BOLD, 20));
        passText.setBounds(170, 220, 160, 25);
        panel1.add(passText);
        
        // passText에서 enter를 입력해도 로그인 확인으로 넘어가기
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
        
        // 로그인 버튼 디자인
        btnLogin = new JButton(new ImageIcon("login_button.png"));
        btnLogin.setBounds(130, 380, 130, 50);
        btnLogin.setBackground(col_green);
        panel1.add(btnLogin);
        
        // 로그인 버튼 클릭 시, 로그인 확인으로 넘어가기
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
   
    // 냉장고 연결(로그인 조건) 함수
    public void isLoginCheck(){
    	if(new String(passText.getPassword()).equals("1234")){ // 비밀번호 == 1234일 때, 로그인 성공
            JOptionPane.showMessageDialog(null, "냉장고를 열었습니다.\n"+userText.getText()+" 님의 냉장고 보러가기!");
            LoginCheck = true;
            
            // 냉장고 연결됐으면(LoginCheck = true라면) 다음 창 뛰우기
            if(isLogin()){
            	this.dispose();
            	PageTwo.userName = userText.getText();
            	final PageTwo_FoodList PageTwo = new PageTwo_FoodList();
            }  
        } else { // 비밀번호 != 1234일 때, 냉장고 연결 X
            JOptionPane.showMessageDialog(null, "냉장고를 열지 못했습니다.");
        }
    }
    
    public boolean isLogin() {     
        return LoginCheck;
    }
}