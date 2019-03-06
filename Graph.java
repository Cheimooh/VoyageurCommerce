import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> noeuds;
    private List<Edges> arretes;
    private int[][] matriceDistance;

    public Graph() {
        noeuds = new ArrayList<>();
        arretes = new ArrayList<>();
    }

    public int addNoeud(Node noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            return 0;
        }
        return -1;
    }

    public void initMatriceDistance(){
        matriceDistance = new int[noeuds.size()][noeuds.size()];
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = 0; j < noeuds.size(); j++) {
                matriceDistance[i][j] = -1;
                if(i == j) matriceDistance[i][j] = 0;
            }
        }
        for (int i = 0; i < noeuds.size(); i++) {
            Node n1 = noeuds.get(i);
            for (int j = 0; j < noeuds.size(); j++) {
                Node n2 = noeuds.get(j);
                if (n1.getBrother().contains(n2)){
                    for (int k = 0; k < arretes.size(); k++) {
                        if (arretes.get(k).getNoeudDepart() == n1 && arretes.get(k).getNoeudArrivee() == n2
                                || arretes.get(k).getNoeudDepart() == n2 && arretes.get(k).getNoeudArrivee() == n1 ){
                            matriceDistance[i][j] = arretes.get(k).getDistance();
                        }
                    }
                }
            }
        }
        for (int i = 0; i < noeuds.size(); i++) {
            System.out.println();
            for (int j = 0; j < noeuds.size(); j++) {
                System.out.print(matriceDistance[i][j] + " ");
            }
        }
    }

    public int addArrete(Node noeud1, Node noeud2, int distance) {
        if (noeuds.contains(noeud1) && noeuds.contains(noeud2)) {
            if (!arretes.contains(new Edges(noeud1, noeud2, distance))) {
                arretes.add(new Edges(noeud1, noeud2, distance));
                noeud1.getBrother().add(noeud2);
                noeud2.getBrother().add(noeud1);
                return 0;
            }
        }
        return -1;
    }


    public boolean isConnexe() {
        List<Node> noeudsVisites = new ArrayList<>();
        noeudsVisites = testConnexe(noeuds.get(0), noeudsVisites);
        return noeudsVisites.size() == noeuds.size();
    }

    private List<Node> testConnexe(Node node, List<Node> noeudsVisites) {
        for (Node noeudFrere : node.getBrother()) {
            if (!noeudsVisites.contains(noeudFrere)) {
                noeudsVisites.add(noeudFrere);
                testConnexe(noeudFrere, noeudsVisites);
            }
        }
        return noeudsVisites;
    }




    public List<Node> getNoeuds() {
        return noeuds;
    }

    public List<Edges> getArretes() {
        return arretes;
    }
}
