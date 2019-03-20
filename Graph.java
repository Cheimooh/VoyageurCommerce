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

        List<Node> nonMarques = new ArrayList<>();
        List<Integer> poidsNonMarque = new ArrayList<>();

        for (int i = 0; i < matriceDistance.length ; i++) {
            int[] lDistance = new int[noeuds.size()];
            for (int j = 0; j < noeuds.size(); j++) {
                lDistance[j] = -1;
            }
            for (int j = 0; j < noeuds.size(); j++) {
                nonMarques.add(noeuds.get(j));
                poidsNonMarque.add(-1);
            }
            matriceDistance[i] = parcoursDesVoisins(noeuds.get(i), nonMarques, lDistance, i);
        }
        for (int i = 0; i < noeuds.size(); i++) {
            System.out.println();
            for (int j = 0; j < noeuds.size(); j++) {
                System.out.print(matriceDistance[i][j] + " ");
                if (matriceDistance[i][j] < 10){
                    System.out.print(" ");
                }
            }
        }
    }

    public int[] parcoursDesVoisins(Node noeudDepart, List<Node> nonMarques, int[] lDistance, int indiceOriginel) {
        nonMarques.remove(noeudDepart);

        //ajoute les distance avec des voisins
        for (int i = 0; i < noeudDepart.getBrother().size(); i++) {
            lDistance[indiceOriginel] = 0;
            Node n = noeudDepart.getBrother().get(i);
            int distance = -1;
            for (int j = 0; j < arretes.size(); j++) {
                if (arretes.get(j).getNoeudDepart() == noeudDepart && arretes.get(j).getNoeudArrivee() == n ||
                        arretes.get(j).getNoeudDepart() == n && arretes.get(j).getNoeudArrivee() == noeudDepart){
                    distance = arretes.get(j).getDistance();

                    if (lDistance[noeuds.indexOf(n)] == -1 && nonMarques.contains(n)) {
                        lDistance[noeuds.indexOf(n)] = lDistance[noeuds.indexOf(noeudDepart)] + distance;
                    }
                    else if (lDistance[noeuds.indexOf(n)] > 0 && nonMarques.contains(n)){
                        if (lDistance[noeuds.indexOf(noeudDepart)] + distance < lDistance[noeuds.indexOf(n)]) {
                            lDistance[noeuds.indexOf(n)] = lDistance[noeuds.indexOf(noeudDepart)] + distance;
                        }
                    }
                }
            }
        }
         //si tout les noeuds sont marqués
        if (nonMarques.isEmpty()){
            return lDistance;
        }

        //si il reste des noeuds non marqués
        noeudDepart = nonMarques.get(0);
        int poids = lDistance[noeuds.indexOf(nonMarques.get(0))];
        for (int i = 0; i < nonMarques.size(); i++) {
            int tempPoids = lDistance[noeuds.indexOf(nonMarques.get(i))];
            if (poids == -1){
                poids = tempPoids;
                noeudDepart = nonMarques.get(i);
            }
            if (tempPoids<poids && tempPoids > 0) {
                poids = tempPoids;
                noeudDepart = nonMarques.get(i);
            }
        }
        return parcoursDesVoisins(noeudDepart, nonMarques, lDistance, indiceOriginel);
    }

    public void voyageurDeCommerce(){
        ArrayList<ArrayList<Node>> toutLesVoyageur = new ArrayList<>();

        for (int i = 0; i < noeuds.size(); i++) {
            toutLesVoyageur.add(parcoursNoeuds(noeuds.get(i), new ArrayList<Node>()));
        }

        for (int i = 0; i < noeuds.size(); i++) {
            System.out.println();
            for (int j = 0; j < noeuds.size(); j++) {
                System.out.print(toutLesVoyageur.get(i).get(j).getNom() + "  ");
            }
        }
    }

    public ArrayList<Node> parcoursNoeuds(Node noeudDepart, ArrayList<Node> noeudsParcourus){
        noeudsParcourus.add(noeudDepart);
        int distance = -1;
        Node noeudLePlusProche =new Node("temp");
        for (int i = 0; i < noeudDepart.getBrother().size(); i++) {
            Node n = noeudDepart.getBrother().get(i);
            for (int j = 0; j < arretes.size(); j++) {
                if (arretes.get(j).getNoeudDepart() == noeudDepart && arretes.get(j).getNoeudArrivee() == n ||
                        arretes.get(j).getNoeudDepart() == n && arretes.get(j).getNoeudArrivee() == noeudDepart) {
                    int tempdistance = arretes.get(j).getDistance();
                    if ((distance == -1 || tempdistance < distance) && !noeudsParcourus.contains(n)){
                        distance = tempdistance;
                        noeudLePlusProche = n;
                    }
                }
            }
        }
        noeudDepart = noeudLePlusProche;
        if (noeudsParcourus.size()==noeuds.size()){
            return noeudsParcourus;
        }
        return parcoursNoeuds(noeudDepart, noeudsParcourus);
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

    public boolean isComplet() {
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = 0; j < noeuds.size(); j++) {
                if (!noeuds.get(i).getBrother().contains(noeuds.get(j))){
                    if (i != j){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Node> getNoeuds() {
        return noeuds;
    }

    public List<Edges> getArretes() {
        return arretes;
    }
}
