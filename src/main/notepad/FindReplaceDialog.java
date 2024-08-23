package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindReplaceDialog implements ActionListener {
    Notepad notepad;
    JDialog frDialog;
    JButton btnCancel, btnFindNext, btnReplace, btnReplaceAll;
    JTextField txtFind, txtReplace;

    FindReplaceDialog(Notepad notepad){
        this.notepad = notepad;
        this.frDialog = new JDialog(notepad.frame);

        makeDialogBox();
    }

    void makeDialogBox() {
        frDialog.setTitle("Find and Replace");
        frDialog.setLayout(new GridBagLayout());
        frDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblFind = new JLabel("Find: ");
        JLabel lblReplace = new JLabel("Replace with: ");
        txtFind = new JTextField(20);
        txtReplace = new JTextField(20);

        btnFindNext = new JButton("Find Next");
        btnFindNext.addActionListener(this);
        btnFindNext.setActionCommand("FindNext");

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setActionCommand("Cancel");

        btnReplace = new JButton("Replace");
        btnReplace.addActionListener(this);
        btnReplace.setActionCommand("Replace");

        btnReplaceAll = new JButton("Replace All");
        btnReplace.addActionListener(this);
        btnReplaceAll.setActionCommand("ReplaceAll");

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        frDialog.add(lblFind, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frDialog.add(txtFind, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frDialog.add(lblReplace, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frDialog.add(txtReplace, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frDialog.add(btnFindNext, gbc);

        gbc.gridx = 2;
        frDialog.add(btnCancel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        frDialog.add(btnReplace, gbc);

        gbc.gridx = 2;
        frDialog.add(btnReplaceAll, gbc);

        frDialog.pack();
        frDialog.setLocationRelativeTo(notepad.frame);
        frDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "Cancel" -> {
                frDialog.dispose();
            }
            case "FindNext" -> {
                System.out.println("I am find next");
            }
            case "Replace" -> {
                System.out.println("I am replace");
            }
            case "ReplaceAll" -> {
                System.out.println("I am replace all");
            }
        }
    }
}
