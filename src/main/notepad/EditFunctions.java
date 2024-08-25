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
        Command cutCommand = new CutCommand(notepad);
        notepad.undoManager.executeCommand(cutCommand);
    }

    void copyText(){
        StringSelection stringSelection = new StringSelection(notepad.textPane.getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    void pasteText(){
        Command pasteCommand = new PasteCommand(notepad);
        notepad.undoManager.executeCommand(pasteCommand);
    }

    void deleteText(){
        Command deleteCommand = new DeleteCommand(notepad);
        notepad.undoManager.executeCommand(deleteCommand);
    }

    void selectAllText(){
        notepad.textPane.selectAll();
    }
}
