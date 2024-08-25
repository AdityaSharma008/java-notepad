package notepad;

import java.util.Stack;

public class UndoManager {
    private Stack<Command> commandStack = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        commandStack.push(command);
    }

    public void unexecute(){
        if(!commandStack.isEmpty()){
            Command command = commandStack.pop();
            command.unexecute();
        }
    }
}
