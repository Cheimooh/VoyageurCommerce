import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    String nom;
    private List<Node> brother;

    public Node(String nom){
        this.nom = nom;
        this.brother = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Node> getBrother() {
        return brother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nom, node.nom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nom);
    }
}
