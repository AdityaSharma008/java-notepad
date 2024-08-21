package notepad;

import javax.swing.*;

public class Notepad {
    JFrame frame;
    JTextPane textPane;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu file, edit, view, format;
    JMenuItem fileNew, fileOpen, fileSave, fileSaveAs, fileClose, editCut, editCopy, editPaste, editDelete, editFind, editReplace, editGoto, editSelectAll, formatWordWrap, formatFont, viewStatusBar;
    Notepad(){
        makeFrame();
        makeMenuBar();
        makeTextPane();

        frame.setVisible(true);
    }

    private void makeFrame(){
        frame = new JFrame("Notepad");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void makeTextPane(){
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
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
        fileOpen = new JMenuItem("Open...");
        fileSave = new JMenuItem("Save");
        fileSaveAs = new JMenuItem("Save As...");
        fileClose = new JMenuItem("Close");
        file.add(fileNew); file.add(fileOpen); file.add(fileSave); file.add(fileSaveAs); file.add(fileClose);
    }

    private void makeEditMenu(){
        editCut = new JMenuItem("Cut");
        editCopy = new JMenuItem("Copy");
        editPaste = new JMenuItem("Paste");
        editDelete = new JMenuItem("Delete");
        editFind = new JMenuItem("Find");
        editReplace = new JMenuItem("Replace");
        editGoto = new JMenuItem("Goto");
        editSelectAll = new JMenuItem("Select All");
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

    public static void main(String[] args) {
        new Notepad();
    }
}
