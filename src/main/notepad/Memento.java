package notepad;

public class Memento {
    private final String textContent;

    Memento(String textContent){
        this.textContent = textContent;
    }

    public String getTextContent(){
        return textContent;
    }
}
