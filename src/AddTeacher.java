import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddTeacher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public AddTeacher(MyFrame frame) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
                frame.setContentPane(new TeacherSubject(frame).getPanel());
                frame.revalidate();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        // add your code here
        String ss = textField1.getText();
        String ss2 = textField2.getText();
        String ss3 = textField3.getText();
        try {
            String serverName = "jdbc:mysql://localhost/";
            String baseName = "schoolschedule?serverTimezone=UTC";
            String userName = "root";
            String password = "";
            String url = serverName + baseName;
            Connection connection = DriverManager.getConnection(url, userName, password);
            String qInsert = "insert into teachers (Name, Surname, Midname) values ('"+ ss + "', '"+ ss2+"', '" + ss3 + "')" ;
            Statement statement = connection.createStatement();
            if(ss.isEmpty() || ss2.isEmpty()) {
                System.out.println(1);
            }else{
                statement.executeUpdate(qInsert);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
