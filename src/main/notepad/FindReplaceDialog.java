package notepad;

import javax.swing.*;
import java.awt.*;

public class FindReplaceDialog {
    Notepad notepad;
    JDialog frDialog;
    JButton btnFind, btnFindNext, btnReplace, btnReplaceAll;
    JTextField txtFind, txtReplace;

    FindReplaceDialog(Notepad notepad) {
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
        btnFind = new JButton("Find");
        btnFindNext = new JButton("Find Next");
        btnReplace = new JButton("Replace");
        btnReplaceAll = new JButton("Replace All");

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
        frDialog.add(btnFind, gbc);

        gbc.gridx = 2;
        frDialog.add(btnFindNext, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        frDialog.add(btnReplace, gbc);

        gbc.gridx = 2;
        frDialog.add(btnReplaceAll, gbc);

        frDialog.pack();
        frDialog.setLocationRelativeTo(notepad.frame);
        frDialog.setVisible(true);
    }
}
