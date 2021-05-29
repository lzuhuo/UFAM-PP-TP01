package model;

public class Message {
    public boolean status;
    public String message;
    public int codigo;

    public Message(boolean status, String message, int codigo){
        this.status = status;
        this.message = message;
        this.codigo = codigo;
    }
}
