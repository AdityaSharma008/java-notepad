package notepad;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class EditFunctions {
    Notepad notepad;
    EditFunctions(Notepad notepad){
        this.notepad = notepad;
    }

    void undo(){
        notepad.undoManager.unexecute();
    }

    void cutText(){
        Command cutCommand = new CutCommand(notepad, notepad.textPane.getSelectionStart(), notepad.textPane.getSelectionEnd());
        notepad.undoManager.executeCommand(cutCommand);
    }

    void copyText(){
        StringSelection stringSelection = new StringSelection(notepad.textPane.getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    void pasteText(){
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            notepad.textPane.insert(data, notepad.textPane.getCaretPosition());
        } catch (Exception e){
            System.out.println("There's nothing we can do.");
        }
    }

    void deleteText(){
        notepad.textPane.replaceSelection("");
    }

    void selectAllText(){
        notepad.textPane.selectAll();
    }
}
