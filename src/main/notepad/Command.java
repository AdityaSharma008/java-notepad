package notepad;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public interface Command {
    void execute();
    void unexecute();
}

class CutCommand implements Command{
    private Notepad notepad;
    private int start;
    private int end;
    private String cutText;
    private Memento memento;

    public CutCommand(Notepad notepad, int start, int end){
        this.notepad = notepad;
        this.start = start;
        this.end = end;
    }

    public void execute(){
        memento = notepad.save();
        String currentText = notepad.textPane.getText();
        cutText = currentText.substring(start, end);
        notepad.textPane.setText(currentText.substring(0, start) + currentText.substring(end));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(cutText), null);
    }

    public void unexecute(){
        notepad.restore(memento);
    }

    public String getCutText(){
        return cutText;
    }
}
