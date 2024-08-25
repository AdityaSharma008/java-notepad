package notepad;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notepad implements ActionListener {
    static boolean saved = true;
    JFrame frame;
    JTextArea textPane;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu file, edit, view, format;
    JMenuItem fileNew, fileOpen, fileSave, fileSaveAs, fileClose, editUndo, editCut, editCopy, editPaste, editDelete, editFindReplace, editGoto, editSelectAll, formatFont, viewStatusBar;
    JCheckBoxMenuItem formatWordWrap;
    FileFunctions fileFunctions;
    EditFunctions editFunctions;
    Memento memento;
    UndoManager undoManager;
    static String notepadTitle = null;
    Notepad(){
        makeFrame();
        makeMenuBar();
        makeTextPane();
        undoManager = new UndoManager();
        notepadTitle = "Untitled";
        fileFunctions = new FileFunctions(this);
        editFunctions = new EditFunctions(this);
        frame.setVisible(true);
    }

    private void makeFrame(){
        frame = new JFrame("Untitled");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void makeTextPane(){
        textPane = new JTextArea();
        scrollPane = new JScrollPane(textPane);
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
                editFindReplace.setEnabled(textPane.getText().length() > 0);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
                editFindReplace.setEnabled(textPane.getText().length() > 0);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saved = false;
                editSelectAll.setEnabled(textPane.getText().length() > 0);
                frame.setTitle("*" + notepadTitle);
                editFindReplace.setEnabled(textPane.getText().length() > 0);
            }
        });

        textPane.addCaretListener(e -> {
            editCut.setEnabled(e.getMark() - e.getDot() != 0);
            editCopy.setEnabled(e.getMark() - e.getDot() != 0);
            editDelete.setEnabled(e.getMark() - e.getDot() != 0);
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
        editUndo = new JMenuItem("Undo");
        editUndo.addActionListener(this);
        editUndo.setActionCommand("Undo");

        editCut = new JMenuItem("Cut");
        editCut.setEnabled(false);
        editCut.addActionListener(this);
        editCut.setActionCommand("Cut");

        editCopy = new JMenuItem("Copy");
        editCopy.setEnabled(false);
        editCopy.addActionListener(this);
        editCopy.setActionCommand("Copy");

        editPaste = new JMenuItem("Paste");
        editPaste.addActionListener(this);
        editPaste.setActionCommand("Paste");

        editDelete = new JMenuItem("Delete");
        editDelete.setEnabled(false);
        editDelete.addActionListener(this);
        editDelete.setActionCommand("Delete");

        editFindReplace = new JMenuItem("Find & Replace");
        editFindReplace.setEnabled(false);
        editFindReplace.addActionListener(this);
        editFindReplace.setActionCommand("FindReplace");

        editGoto = new JMenuItem("Goto");
        editGoto.setEnabled(false);
        editGoto.addActionListener(this);
        editGoto.setActionCommand("Goto");

        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setEnabled(false);
        editSelectAll.addActionListener(this);
        editSelectAll.setActionCommand("SelectAll");

        edit.add(editUndo); edit.add(editCut); edit.add(editCopy); edit.add(editPaste); edit.add(editDelete); edit.add(editFindReplace); edit.add(editGoto); edit.add(editSelectAll);
    }

    private void makeFormatMenu(){
        formatWordWrap = new JCheckBoxMenuItem("Word Wrap");
        formatWordWrap.addChangeListener(changeListener);

        formatFont = new JMenuItem("Font");
        format.add(formatWordWrap); format.add(formatFont);
    }

    private void makeViewMenu(){
        viewStatusBar = new JMenuItem("Show Status Bar");
        view.add(viewStatusBar);
    }

    public Memento save(){
        return new Memento(textPane.getText());
    }

    public void restore(Memento memento){
        this.textPane.setText(memento.getTextContent());
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "New" -> fileFunctions.newFile();
            case "Open" -> fileFunctions.openFile();
            case "Save" -> fileFunctions.saveFile();
            case "SaveAs" -> fileFunctions.saveAsFile();
            case "Close" -> fileFunctions.closeFile();
            case "Undo" -> editFunctions.undo();
            case "Cut" -> editFunctions.cutText();
            case "Copy" -> editFunctions.copyText();
            case "Paste" -> editFunctions.pasteText();
            case "Delete" -> editFunctions.deleteText();
            case "FindReplace" -> new FindReplaceDialog(this);
            case "SelectAll" -> editFunctions.selectAllText();
        }
    }

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if(formatWordWrap.getState()){
                textPane.setLineWrap(true);
                textPane.setWrapStyleWord(true);
            }
            else{
                textPane.setLineWrap(false);
                textPane.setWrapStyleWord(false);
            }
        }
    };

    public static void main(String[] args) {
        new Notepad();
    }
}
