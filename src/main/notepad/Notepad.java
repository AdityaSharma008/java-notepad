package notepad;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notepad implements ActionListener {
    static boolean saved = true;
    JFrame frame;
    JTextPane textPane;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu file, edit, view, format;
    JMenuItem fileNew, fileOpen, fileSave, fileSaveAs, fileClose, editCut, editCopy, editPaste, editDelete, editFind, editReplace, editGoto, editSelectAll, formatWordWrap, formatFont, viewStatusBar;
    FileFunctions fileFunctions;
    static String notepadTitle = null;
    Notepad(){
        makeFrame();
        makeMenuBar();
        makeTextPane();
        notepadTitle = "Untitled";
        fileFunctions = new FileFunctions(this);
        frame.setVisible(true);
    }

    private void makeFrame(){
        frame = new JFrame("Untitled");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void makeTextPane(){
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
            }
        });

        textPane.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                editCut.setEnabled(e.getMark() - e.getDot() != 0);
                editCopy.setEnabled(e.getMark() - e.getDot() != 0);
                editDelete.setEnabled(e.getMark() - e.getDot() != 0);
            }
        });

        frame.add(scrollPane);
    }

    private void makeMenuBar(){
        menuBar = new JMenuBar();

        file = new JMenu("File");
        makeFileMenu();

        edit = new JMenu("Edit");
        makeEditMenu();

        format = new JMenu("Format");
        makeFormatMenu();

        view = new JMenu("View");
        makeViewMenu();

        menuBar.add(file); menuBar.add(edit); menuBar.add(format); menuBar.add(view);

        frame.setJMenuBar(menuBar);
    }

    private void makeFileMenu(){
        fileNew = new JMenuItem("New");
        fileNew.addActionListener(this);
        fileNew.setActionCommand("New");

        fileOpen = new JMenuItem("Open...");
        fileOpen.addActionListener(this);
        fileOpen.setActionCommand("Open");

        fileSave = new JMenuItem("Save");
        fileSave.addActionListener(this);
        fileSave.setActionCommand("Save");

        fileSaveAs = new JMenuItem("Save As...");
        fileSaveAs.addActionListener(this);
        fileSaveAs.setActionCommand("SaveAs");

        fileClose = new JMenuItem("Close");
        fileClose.addActionListener(this);
        fileClose.setActionCommand("Close");

        file.add(fileNew); file.add(fileOpen); file.add(fileSave); file.add(fileSaveAs); file.add(fileClose);
    }

    private void makeEditMenu(){
        editCut = new JMenuItem("Cut");
        editCut.setEnabled(false);
        editCut.addActionListener(this);
        editCut.setActionCommand("Cut");

        editCopy = new JMenuItem("Copy");
        editCopy.setEnabled(false);
        editCopy.addActionListener(this);
        editCopy.setActionCommand("Copy");

        editPaste = new JMenuItem("Paste");
        editPaste.setEnabled(false);
        editPaste.addActionListener(this);
        editPaste.setActionCommand("Paste");

        editDelete = new JMenuItem("Delete");
        editDelete.setEnabled(false);
        editDelete.addActionListener(this);
        editDelete.setActionCommand("Delete");

        editFind = new JMenuItem("Find");
        editFind.setEnabled(false);
        editFind.addActionListener(this);
        editFind.setActionCommand("Find");

        editReplace = new JMenuItem("Replace");
        editReplace.setEnabled(false);
        editReplace.addActionListener(this);
        editReplace.setActionCommand("Replace");

        editGoto = new JMenuItem("Goto");
        editGoto.setEnabled(false);
        editGoto.addActionListener(this);
        editGoto.setActionCommand("Goto");

        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setEnabled(false);
        editSelectAll.addActionListener(this);
        editSelectAll.setActionCommand("SelectAll");

        edit.add(editCut); edit.add(editCopy); edit.add(editPaste); edit.add(editDelete); edit.add(editFind); edit.add(editReplace); edit.add(editGoto); edit.add(editSelectAll);
    }

    private void makeFormatMenu(){
        formatWordWrap = new JMenuItem("Word Wrap");
        formatFont = new JMenuItem("Font");
        format.add(formatWordWrap); format.add(formatFont);
    }

    private void makeViewMenu(){
        viewStatusBar = new JMenuItem("Show Status Bar");
        view.add(viewStatusBar);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "New" -> fileFunctions.newFile();
            case "Open" -> fileFunctions.openFile();
            case "Save" -> fileFunctions.saveFile();
            case "SaveAs" -> fileFunctions.saveAsFile();
            case "Close" -> fileFunctions.closeFile();
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
