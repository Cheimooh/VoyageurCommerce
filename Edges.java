import java.util.Objects;

public class Edges {
    private Node noeudDepart;
    private Node noeudArrivee;
    private int distance;

    public Edges(Node noeud1, Node noeud2, int poids){
        this.noeudDepart = noeud1;
        this.noeudArrivee = noeud2;
        this.distance = poids;
    }

    public Node getNoeudDepart() {
        return noeudDepart;
    }

    public Node getNoeudArrivee() {
        return noeudArrivee;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edges edges = (Edges) o;
        return (Objects.equals(noeudDepart, edges.noeudDepart) &&
                Objects.equals(noeudArrivee, edges.noeudArrivee)) ||
                (Objects.equals(noeudDepart, edges.noeudArrivee) &&
                Objects.equals(noeudArrivee, edges.noeudDepart));
    }

    @Override
    public int hashCode() {

        return Objects.hash(noeudDepart, noeudArrivee);
    }
}
