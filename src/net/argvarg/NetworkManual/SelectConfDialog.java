package net.argvarg.NetworkManual;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class SelectConfDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private DefaultListModel<String> confList = new DefaultListModel<String>();
    private JList listConfListContainer = new JList(confList);
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JScrollPane scrollpaneMain;
    private File[] confs = Main.confsDirContents;
    private String selectedConf = null;

    public SelectConfDialog() {
        loadConfList();
        scrollpaneMain.setViewportView(listConfListContainer);
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

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRefresh();
            }
        });

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNew();
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
    private void loadConfList() {
        if (confList != null) {
            System.out.println("confsList.size = " + confList.size());
            for (int i = 0; i < confs.length; i++) {
                System.out.println(i);
                confList.addElement(confs[i].getName());
                System.out.println("Conf: " + confs[i].getName());
            }
            if (confs.length == 0) {
                confList.addElement("No Configurations Found!");
            }
        } else  { System.out.println("FETAL: confList is null!");}
    }

    private void onRefresh() {

        for (int i = 0; i < confList.size(); i++) {
            confList.remove(i);
        }
        confs = new File(Main.absolutePathToConfsDir).listFiles();
        loadConfList();
        scrollpaneMain.setViewportView(listConfListContainer);
    }

    private void onOK() {
        if (selectedConf != null) {
            Main.setSelectedConf(selectedConf);
            dispose();
        }
    }

    private void onNew() {
        NewConfDialog.run();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void run() {
        SelectConfDialog dialog = new SelectConfDialog();
        dialog.pack();
        dialog.setVisible(true);
    }
}