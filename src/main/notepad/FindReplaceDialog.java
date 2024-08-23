package notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindReplaceDialog implements ActionListener {
    Notepad notepad;
    JDialog frDialog;
    JButton btnCancel, btnFindNext, btnReplace, btnReplaceAll;
    JTextField txtFind, txtReplace;
    int startIndex, oldIndex;
    String text;

    FindReplaceDialog(Notepad notepad){
        this.notepad = notepad;
        this.frDialog = new JDialog(notepad.frame);
        text = notepad.textPane.getText();
        oldIndex = 0;
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

        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonStates();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonStates();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonStates();
            }
        };

        txtFind.getDocument().addDocumentListener(docListener);
        txtReplace.getDocument().addDocumentListener(docListener);

        btnFindNext = new JButton("Find Next");
        btnFindNext.setEnabled(false);
        btnFindNext.addActionListener(this);
        btnFindNext.setActionCommand("FindNext");

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setActionCommand("Cancel");

        btnReplace = new JButton("Replace");
        btnReplace.setEnabled(false);
        btnReplace.addActionListener(this);
        btnReplace.setActionCommand("Replace");

        btnReplaceAll = new JButton("Replace All");
        btnReplaceAll.setEnabled(false);
        btnReplaceAll.addActionListener(this);
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

    private void updateButtonStates() {
        String replaceText = txtReplace.getText();
        String findText = txtFind.getText();
        boolean isFindTextNotEmpty = findText.length() > 0;
        boolean isReplaceTextNotEmpty = replaceText.length() > 0;

        btnFindNext.setEnabled(isFindTextNotEmpty);
        btnReplace.setEnabled(isFindTextNotEmpty && isReplaceTextNotEmpty);
        btnReplaceAll.setEnabled(isFindTextNotEmpty && isReplaceTextNotEmpty);
    }

    private void find(String toFind){
        startIndex = text.indexOf(toFind, oldIndex);
        oldIndex = startIndex + 1;
        notepad.textPane.select(startIndex, startIndex + toFind.length());
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        String toFind = txtFind.getText();
        String newString = txtReplace.getText();

        switch (cmd) {
            case "FindNext" -> find(toFind);
            case "Cancel" -> frDialog.dispose();
            case "Replace" -> {
                if(notepad.textPane.getSelectedText() == null){
                    find(toFind);
                    return;
                }
                else {
                    notepad.textPane.replaceSelection(newString);
                }
                text = notepad.textPane.getText();
            }
            case "ReplaceAll" -> {
                notepad.textPane.setText(text.replaceAll(toFind, newString));
                text = notepad.textPane.getText();
            }
        }
    }
}
