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
    JLabel tableName = new JLabel("数据表名:");
    JLabel URL = new JLabel("数据库链接:");
    JLabel USER = new JLabel("用户名:");
    JLabel PASSWORD = new JLabel("密码:");
    JLabel DRIVER = new JLabel("驱动:");
    JLabel diskPath = new JLabel("文件地址:");
    private JLabel[] jl = new JLabel[]{packageName, tableName, URL, USER, PASSWORD, DRIVER, diskPath};
    JButton login = new JButton("生成");
    JButton reset = new JButton("重置");
    private JButton[] jb = new JButton[]{login, reset};
    private JTextField jpackageName = new JTextField();
    private JTextField jtableName = new JTextField();
    private JTextField jURL = new JTextField();
    private JTextField jUSER = new JTextField();
    private JTextField jPASSWORD = new JTextField();
    private JTextField jDRIVER = new JTextField();
    private JTextField jdiskPath = new JTextField();
    private JTextField[] jTextFields=new JTextField[]{jpackageName,jtableName,jURL,jUSER,jPASSWORD,jDRIVER,jdiskPath};

    public MyWindow() {
        CodeGenerateUtils codeGenerateUtils=new CodeGenerateUtils();
        jp.setLayout(null);
        for (int i = 0; i < 7; i++) {
            jl[i].setBounds(60, 40 + 40 * i, 360, 40);
            jp.add(jl[i]);
        }
        for (int i = 0; i < 2; i++) {
            jb[i].setBounds(200 + 220 * i, 400, 160, 40);
            jb[i].addActionListener(this);
            jp.add(jb[i]);
        }

        for(int i=0;i<7;i++){
            jTextFields[i].setBounds(260,40+40*i,400, 40);
            jTextFields[i].addActionListener(this);
            jp.add(jTextFields[i]);
        }

        jURL.setText(codeGenerateUtils.getURL());
        jUSER.setText(codeGenerateUtils.getUSER());
        jPASSWORD.setText(codeGenerateUtils.getPASSWORD());
        jDRIVER.setText(codeGenerateUtils.getDRIVER());
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
            jtableName.requestFocus();
        }
        if (a.getSource() == jtableName) {
            jURL.requestFocus();
        }
        if (a.getSource() == jURL) {
            jUSER.requestFocus();
        }
        if (a.getSource() == jUSER) {
            jPASSWORD.requestFocus();
        }
        if (a.getSource() == jPASSWORD) {
            jDRIVER.requestFocus();
        }
        if (a.getSource() == jDRIVER) {
            jdiskPath.requestFocus();
        } else if (a.getSource() == jb[1]) {
            // jl[2].setText("");
            jpackageName.setText(codeGenerateUtils.getPackageName());
            jtableName.setText("");
            jURL.setText(codeGenerateUtils.getURL());
            jUSER.setText(codeGenerateUtils.getUSER());
            jPASSWORD.setText(codeGenerateUtils.getPASSWORD());
            jDRIVER.setText(codeGenerateUtils.getDRIVER());
            jdiskPath.setText(codeGenerateUtils.getDiskPath());
            jpackageName.requestFocus();
        } else if (a.getSource() == jb[0]) {
            codeGenerateUtils.setPackageName(jpackageName.getText());
            codeGenerateUtils.setTableName(jtableName.getText());
            codeGenerateUtils.setURL(jURL.getText());
            codeGenerateUtils.setUSER(jUSER.getText());
            codeGenerateUtils.setPASSWORD(jPASSWORD.getText());
            codeGenerateUtils.setDRIVER(jDRIVER.getText());
            codeGenerateUtils.setDiskPath(jdiskPath.getText());
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

