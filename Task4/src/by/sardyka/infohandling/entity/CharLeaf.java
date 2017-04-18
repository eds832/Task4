package by.sardyka.infohandling.entity;

public class CharLeaf implements IComponent {
    private Character ch;

    public CharLeaf(char ch) {
        this.ch = ch;
    }
    
    @Override
    public String toString() {
        return ch.toString();
    }
}
