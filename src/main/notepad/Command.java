package notepad;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public interface Command {
    void execute();
    void unexecute();
}

class CutCommand implements Command{
    private final Notepad notepad;
    private Memento memento;

    public CutCommand(Notepad notepad){
        this.notepad = notepad;
    }

    @Override
    public void execute(){
        memento = notepad.save();
        String cutText = notepad.textPane.getSelectedText();
        notepad.textPane.replaceSelection("");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(cutText), null);
    }

    @Override
    public void unexecute(){
        notepad.restore(memento);
    }
}

class PasteCommand implements Command{
    private final Notepad notepad;
    private Memento memento;

    public PasteCommand(Notepad notepad){
        this.notepad = notepad;
    }

    @Override
    public void execute() {
        memento = notepad.save();
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            notepad.textPane.insert(data, notepad.textPane.getCaretPosition());
        } catch (Exception e){
            System.out.println("There's nothing we can do.");
        }
    }

    @Override
    public void unexecute() {
        notepad.restore(memento);
    }
}

class DeleteCommand implements Command{
    private Notepad notepad;
    private Memento memento;
    private String toDelete;

    DeleteCommand(Notepad notepad){
        this.notepad = notepad;
    }

    @Override
    public void execute() {
        memento = notepad.save();
        notepad.textPane.replaceSelection("");
    }

    @Override
    public void unexecute() {
        notepad.restore(memento);
    }
}