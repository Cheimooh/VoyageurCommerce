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

        System.out.println("graphe connexe :" + gConnexe.isConnexe());

        System.out.println("matrice distance :");
        gConnexe.initMatriceDistance();
        System.out.println();

        Graph gComplet = new Graph();
        gComplet.addNoeud(A);
        gComplet.addNoeud(B);
        gComplet.addNoeud(C);
        gComplet.addNoeud(D);
        gComplet.addNoeud(E);

        gComplet.addArrete(A, B, 1);
        gComplet.addArrete(A, C, 2);
        gComplet.addArrete(A, D, 3);
        gComplet.addArrete(A, E, 4);
        gComplet.addArrete(B, C, 5);
        gComplet.addArrete(B, D, 6);
        gComplet.addArrete(B, E, 7);
        gComplet.addArrete(C, D, 8);
        gComplet.addArrete(C, E, 9);
        gComplet.addArrete(D, E, 10);

        System.out.println();
        System.out.println("graphe complet :" + gComplet.isComplet());

        System.out.println("chemin le plus court :");

        gComplet.voyageurDeCommerce();
    }
}
