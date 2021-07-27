import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 
public class PageOne_Login extends JFrame{
	
	// �ܺ� Ŭ���� ������ ����
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

    	// background image ����
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
        setTitle("���� �� ����?");
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
        
    	// nickname, password panel �̹��� ������
    	JLabel userImage = new JLabel(new ImageIcon("username.png"));
    	userImage.setBounds(20, 110, 50, 70);
        panel1.add(userImage);
        
    	JLabel passwordImage = new JLabel(new ImageIcon("password.png"));
    	passwordImage.setBounds(20, 190, 50, 70);
        panel1.add(passwordImage);
        
        // nickname, password panel label ������
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("���� ���",Font.BOLD, 15));
        userLabel.setBounds(70, 140, 100, 25);
        panel1.add(userLabel);
       
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("���� ���",Font.BOLD, 15));
        passLabel.setBounds(70, 220, 100, 25);
        panel1.add(passLabel);
        
        // nickname, password �Է� �ޱ�
        userText = new JTextField(30);
        userText.setFont(new Font("���� ���",Font.BOLD, 18));
        userText.setBounds(170, 140, 160, 25);
        panel1.add(userText);
        
        passText = new JPasswordField(30);
        passText.setFont(new Font("���� ���",Font.BOLD, 20));
        passText.setBounds(170, 220, 160, 25);
        panel1.add(passText);
        
        // passText���� enter�� �Է��ص� �α��� Ȯ������ �Ѿ��
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
        
        // �α��� ��ư ������
        btnLogin = new JButton(new ImageIcon("login_button.png"));
        btnLogin.setBounds(130, 380, 130, 50);
        btnLogin.setBackground(col_green);
        panel1.add(btnLogin);
        
        // �α��� ��ư Ŭ�� ��, �α��� Ȯ������ �Ѿ��
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
   
    // ����� ����(�α��� ����) �Լ�
    public void isLoginCheck(){
    	if(new String(passText.getPassword()).equals("1234")){ // ��й�ȣ == 1234�� ��, �α��� ����
            JOptionPane.showMessageDialog(null, "����� �������ϴ�.\n"+userText.getText()+" ���� ����� ��������!");
            LoginCheck = true;
            
            // ����� ���������(LoginCheck = true���) ���� â �ٿ��
            if(isLogin()){
            	this.dispose();
            	PageTwo.userName = userText.getText();
            	final PageTwo_FoodList PageTwo = new PageTwo_FoodList();
            }  
        } else { // ��й�ȣ != 1234�� ��, ����� ���� X
            JOptionPane.showMessageDialog(null, "����� ���� ���߽��ϴ�.");
        }
    }
    
    public boolean isLogin() {     
        return LoginCheck;
    }
}