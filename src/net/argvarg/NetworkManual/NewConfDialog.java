package net.argvarg.NetworkManual;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NewConfDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboboxSecType;
    private JLabel labelSecType;
    private JLabel labelSSID;
    private JTextField textfieldSSID;
    private JLabel labelPassword;
    private JPasswordField passwordfieldNetworkPassword;
    private JLabel labelFileName;
    private JTextField textfieldFileName;
    public File configFile;
    private String[] keyMgmt = {"", "WEP", "WPA-PSK"};

    public NewConfDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        configFile = new File(Main.absolutePathToConfsDir + (Main.confsDirContents.length) + textfieldFileName.getText() + ".conf");
        String password = String.valueOf(passwordfieldNetworkPassword.getPassword());
        String[] configContents = ConfigFile.createConfig(textfieldSSID.getText(), keyMgmt[comboboxSecType.getSelectedIndex()], password);
        try {
            PrintWriter configWriter = new PrintWriter(configFile);
            for (int i = 0; i < configContents.length; i++) {
                configWriter.println(configContents[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void run() {
        NewConfDialog dialog = new NewConfDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
