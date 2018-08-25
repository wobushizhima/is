package com.zhima.codegen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * Created by superz on 2018/4/17.
 */
public class MyWindow extends JFrame implements ActionListener{
	static {
	   	Font font = new Font("微软雅黑",Font.BOLD,20);
	   	UIManager.put("Button.font", font);
	   	UIManager.put("TextArea.font", font);
         UIManager.put("TextField.font", font);
         UIManager.put("Label.font", font);
         String path="C:\\Users\\Public\\Pictures\\tangshixiong";
         File file = new File(path);
         if(!file.exists()){  
        	    file.mkdirs();  
        	}  
         
	}
		
        private JPanel jp = new JPanel();
        JLabel packageName = new JLabel("包名");
        JLabel tableName = new JLabel("数据表名");
        JLabel URL = new JLabel("数据库链接");
        JLabel USER = new JLabel("用户名");
        JLabel PASSWORD = new JLabel("密码");
        JLabel DRIVER = new JLabel("驱动");
        JLabel diskPath = new JLabel("文件地址");
        private JLabel[] jl = new JLabel[]{packageName,tableName,URL,USER,PASSWORD,DRIVER,diskPath};
        JButton login = new JButton("生成");
        JButton reset = new JButton("重置");
        private JButton[] jb = new JButton[]{login,reset};
        private JTextField jpackageName= new JTextField();
        private JTextField jtableName= new JTextField();
        private JTextField jURL = new JTextField();
        private JTextField jUSER = new JTextField();
        private JTextField jPASSWORD = new JTextField();
        private JTextField jDRIVER= new JTextField();
        private JTextField jdiskPath= new JTextField();
        public MyWindow()
        {
            jp.setLayout(null);
            for(int i=0;i<7;i++)
            {
                jl[i].setBounds(60,40+80*i,360,40);
                jp.add(jl[i]);
            }
            for(int i=0;i<2;i++)
            {
                jb[i].setBounds(200+220*i,400,160,40);
                jb[i].addActionListener(this);
                jp.add(jb[i]);
            }

            jpackageName.setBounds(260,30,400,40);
            jp.add(jpackageName);
            jpackageName.addActionListener(this);

            jtableName.setBounds(260,120,400,80);
            jp.add(jtableName);
            jtableName.addActionListener(this);

            jURL.setBounds(260,210,400,40);
            jp.add(jURL);
            jURL.addActionListener(this);

            jUSER.setBounds(260,300,400,40);
            jp.add(jUSER);
            jUSER.addActionListener(this);

            jPASSWORD.setBounds(260,300,400,40);
            jp.add(jPASSWORD);
            jPASSWORD.addActionListener(this);

            jDRIVER.setBounds(260,300,400,40);
            jp.add(jDRIVER);
            jDRIVER.addActionListener(this);

            jdiskPath.setBounds(260,300,400,40);
            jp.add(jdiskPath);
            jdiskPath.addActionListener(this);
            
            
            this.add(jp);
            this.setBounds(400,400,800,600);
            this.setVisible(true);
            this.setTitle("打印窗口");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jdiskPath.requestFocus();
            
        }
        public void actionPerformed(ActionEvent a)
        {
            if(a.getSource()==jpackageName)
            {
                jtableName.requestFocus();
            }
            if(a.getSource()==jcompany)
            {
            	jcar.requestFocus();
            }
            else if(a.getSource()==jb[1])
            {
               // jl[2].setText("");
                jname.setText("");
                jcompany.setText("");
                jcar.setText("");
                jshow.setText("");
                jshow.requestFocus();
            }
            else if(a.getSource()==jb[0])
            {
               System.out.println(jname.getText());
               System.out.println(jcompany.getText());
               System.out.println(jcar.getText());
               String show=jshow.getText();
               System.out.println(show);
               if(show.isEmpty()) {
               }else {
            	   String[] array=new String[3];
            	   if(show.indexOf(",")>0) {
            		   array=show.split(",");
            	   }else {
            		   array=show.split("，"); 
            	   }
            	   String[] param=new String[3];
            	   param[2]="";
            	   for(int i=0;i<array.length;i++) {
            		   param[i]=array[i];
            	   }
            	   jname.setText(param[0]);
            	   jcompany.setText(param[1]);
            	   jcar.setText(param[2]);
            	   jshow.setText("");
               }
               jshow.requestFocus();
              
            }
        }
        public static void main(String args[])
        {
            new MyWindow();
        }
    }

