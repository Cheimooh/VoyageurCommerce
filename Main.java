public class Main {
    public static void main(String[] args) {
        Graph gConnexe = new Graph();

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");

        gConnexe.addNoeud(A);
        gConnexe.addNoeud(B);
        gConnexe.addNoeud(C);
        gConnexe.addNoeud(D);
        gConnexe.addNoeud(E);
        gConnexe.addNoeud(F);
        gConnexe.addNoeud(G);

        gConnexe.addArrete(A, B, 2);
        gConnexe.addArrete(A, E, 3);
        gConnexe.addArrete(B, D, 1);
        gConnexe.addArrete(B, F, 4);
        gConnexe.addArrete(C, D, 5);
        gConnexe.addArrete(C, E, 6);
        gConnexe.addArrete(E, F, 40);
        gConnexe.addArrete(F, G, 8);

        System.out.println(gConnexe.isConnexe());

        gConnexe.initMatriceDistance();
    }
}
