package notepad;

import javax.swing.*;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import static notepad.Notepad.saved;
import static notepad.Notepad.notepadTitle;

public class FileFunctions {
    Notepad notepad;
    String fileName, fileAddress;
    FileFunctions(Notepad notepad){
        this.notepad = notepad;
        this.fileName = null;
        this.fileAddress = null;
    }

    void newFile(){
        if(!saved){
            int choice = JOptionPane.showConfirmDialog(notepad.frame, "Do you want to save changes?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                saveFile();
            }
            else if(choice == JOptionPane.CANCEL_OPTION){
                return;
            }
        }
        notepad.textPane.setText("");
        notepad.frame.setTitle("Untitled");
        notepadTitle = "Untitled";
        fileName = null;
        fileAddress = null;
    }

    void openFile(){
        FileDialog fileDialog = new FileDialog(notepad.frame, "Open", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if(fileDialog.getFile() != null){
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            notepad.frame.setTitle(fileName);
            notepadTitle = fileName;
        }

        try {
            notepad.textPane.setText("");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress + fileName));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                StyledDocument styledDocument = (StyledDocument) notepad.textPane.getDocument();
                styledDocument.insertString(styledDocument.getLength(), line, null);
            }
            bufferedReader.close();
        } catch (Exception e){
            System.out.println();
        }
    }

    void saveFile(){
        if(fileName == null){
            saveAsFile();
        }
        else{
            try {
                FileWriter fileWriter = new FileWriter(fileAddress + fileName);
                fileWriter.write(notepad.textPane.getText());
                fileWriter.write(notepad.textPane.getText());
                fileWriter.close();
            } catch(Exception e){
                System.out.println();
            }
        }

        notepadTitle = fileName;
        notepad.frame.setTitle(fileName);
    }

    void saveAsFile(){
        FileDialog fileDialog = new FileDialog(notepad.frame, "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);

        if(fileDialog.getFile() != null){
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            notepad.frame.setTitle(fileName);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileAddress + fileName);
            fileWriter.write(notepad.textPane.getText());
            fileWriter.close();
        } catch (Exception e){
            System.out.println();
        }

        notepadTitle = fileName;
        notepad.frame.setTitle(fileName);
    }

    void closeFile(){
        System.exit(0);
    }
}
