package com.zhima.codegen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * Created by superz on 2018/4/17.
 */
public class MyWindow extends JFrame implements ActionListener {
    static {
        Font font = new Font("微软雅黑", Font.BOLD, 20);
        UIManager.put("Button.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Label.font", font);
    }

    private JPanel jp = new JPanel();
    JLabel packageName = new JLabel("包名:");
    JLabel tableAnnotation = new JLabel("注释:");
    JLabel tableName = new JLabel("数据表名:");
    JLabel url = new JLabel("数据库链接:");
    JLabel user = new JLabel("用户名:");
    JLabel password = new JLabel("密码:");
    JLabel driver = new JLabel("驱动:");
    JLabel diskPath = new JLabel("文件地址:");
    private JLabel[] jl = new JLabel[]{packageName,tableAnnotation, tableName, url, user, password, driver, diskPath};
    JButton login = new JButton("生成");
    JButton reset = new JButton("重置");
    private JButton[] jb = new JButton[]{login, reset};
    private JTextField jpackageName = new JTextField();
    private JTextField jtableAnnotation = new JTextField();
    private JTextField jtableName = new JTextField();
    private JTextField jurl = new JTextField();
    private JTextField juser = new JTextField();
    private JTextField jpassword = new JTextField();
    private JTextField jdriver = new JTextField();
    private JTextField jdiskPath = new JTextField();
    private JTextField[] jTextFields=new JTextField[]{jpackageName,jtableAnnotation,jtableName,jurl,juser,jpassword,jdriver,jdiskPath};

    public MyWindow() {
        CodeGenerateUtils codeGenerateUtils=new CodeGenerateUtils();
        jp.setLayout(null);
        for (int i = 0; i < 8; i++) {
            jl[i].setBounds(60, 40 + 40 * i, 360, 40);
            jp.add(jl[i]);
        }
        for (int i = 0; i < 2; i++) {
            jb[i].setBounds(200 + 220 * i, 400, 160, 40);
            jb[i].addActionListener(this);
            jp.add(jb[i]);
        }

        for(int i=0;i<8;i++){
            jTextFields[i].setBounds(260,40+40*i,400, 40);
            jTextFields[i].addActionListener(this);
            jp.add(jTextFields[i]);
        }

        jurl.setText(codeGenerateUtils.getUrl());
        juser.setText(codeGenerateUtils.getUser());
        jpassword.setText(codeGenerateUtils.getPassword());
        jdriver.setText(codeGenerateUtils.getDriver());
        jdiskPath.setText(codeGenerateUtils.getDiskPath());
        jpackageName.setText(codeGenerateUtils.getPackageName());
        jpackageName.requestFocus();

        this.add(jp);
        this.setBounds(400, 400, 800, 600);
        this.setVisible(true);
        this.setTitle("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpackageName.requestFocus();

    }

    public void actionPerformed(ActionEvent a) {
        System.out.println("new");
        CodeGenerateUtils codeGenerateUtils=new CodeGenerateUtils();
        if (a.getSource() == jpackageName) {
            jtableAnnotation.requestFocus();
        }
        if (a.getSource() == jtableAnnotation) {
            jtableName.requestFocus();
        }
        if (a.getSource() == jtableName) {
            jurl.requestFocus();
        }
        if (a.getSource() == jurl) {
            juser.requestFocus();
        }
        if (a.getSource() == juser) {
            jpassword.requestFocus();
        }
        if (a.getSource() == jpassword) {
            jdriver.requestFocus();
        }
        if (a.getSource() == jdriver) {
            jdiskPath.requestFocus();
        } else if (a.getSource() == jb[1]) {
            // jl[2].setText("");
            jpackageName.setText(codeGenerateUtils.getPackageName());
            jtableAnnotation.setText("");
            jtableName.setText("");
            jurl.setText(codeGenerateUtils.getUrl());
            juser.setText(codeGenerateUtils.getUser());
            jpassword.setText(codeGenerateUtils.getPassword());
            jdriver.setText(codeGenerateUtils.getDriver());
            jpackageName.requestFocus();
        } else if (a.getSource() == jb[0]) {
            codeGenerateUtils.setPackageName(jpackageName.getText());
            codeGenerateUtils.setTableName(jtableName.getText());
            codeGenerateUtils.setUrl(jurl.getText());
            codeGenerateUtils.setUser(juser.getText());
            codeGenerateUtils.setPassword(jpassword.getText());
            codeGenerateUtils.setDriver(jdriver.getText());
            codeGenerateUtils.setDiskPath(jdiskPath.getText());
            codeGenerateUtils.setTableAnnotation(jtableAnnotation.getText());
            try {
                codeGenerateUtils.generate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new MyWindow();
    }
}

