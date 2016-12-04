package labyrinthe;
import java.util.*; //

public class Labyrinthe {

	public static class Point { // la classe point
		int col;
		int row;
		Point origine;
		
		public Point(int col, int row, Point origine) { // constructeur 
			this.col = col;
			this.row = row; 
			this.origine = origine;
		}
		
		public Point getOrigine() { // récupérer le point d'origine
			return this.origine;
		}
		public String toString() { //  afficher l'instance de la classe
			return "Col Row = " + col + " " + row;
		}
	}
	
	public static Queue<Point> queue = new LinkedList<Point>(); // création de la file
	
	static List<Point> echappeToi(boolean[][] labyrinthe) { 
		List<Point> solucePoints = new ArrayList<Point>(); // création de la liste solution
		int N = labyrinthe.length;
		int[][] maze = new int[N][N];
		int i = 0;
		int j = 0;
		//recuperation du tableau booleen en int
		while (j < N) 
		{
			i = 0;
			while (i < N)
			{
				if (labyrinthe[j][i])
					maze[j][i] = 1;
				else
					maze[j][i] = 0;
				i++;
			}
			j++;
		}
		//création de la premiere requete 
		queue.add(new Point(0, N - 1, null));
		//tant qu'on a des requetes
		while (!queue.isEmpty())
		{
			Point points = queue.remove(); // on va vérifier l'element suivant de la queue
			
			if (points.col == N - 1 && points.row == 0) { // Solution trouvé
				System.out.println("Le chemin a été trouvé.");
				while (points.getOrigine() != null) // on construit a partir de la dest
				{
					solucePoints.add(points); // on cree la liste de points solution
					points = points.getOrigine(); // points précédent
				}
				return solucePoints;
			}
			if (chemin(points.col, points.row - 1, maze, N)) { // On vérifie en haut
				maze[points.row][points.col] = -1; // Visité 
				Point nextP = new Point(points.col, points.row - 1, points); // création du point en haut
				queue.add(nextP); // ajout du point dans la file
			}
			
			if (chemin (points.col + 1, points.row, maze, N)) { // On vérifie a droite
				maze[points.row][points.col] = -1; // Visité 
				Point nextP = new Point(points.col + 1, points.row, points); // création du point a droite
				queue.add(nextP); // ajout du point dans la file
			}
			
			if (chemin (points.col, points.row + 1, maze, N)) { // On vérifie en bas
				maze[points.row][points.col] = -1; // Visité 
				Point nextP = new Point(points.col, points.row + 1, points); // création du point en bas
				queue.add(nextP); // ajout du point dans la file
			}
			
			if (chemin (points.col - 1, points.row, maze, N)) { // On vérifie a gauche
				maze[points.row][points.col] = -1;// Visité
				Point nextP = new Point(points.col - 1, points.row, points); // création du points a gauche
				queue.add(nextP); // ajout du point dans la file
			}
		}
		return null; // aucun chemin disponible
	}
	public static boolean chemin(int col, int row, int maze[][], int N) {
		if ((col >= 0 && col < N) && (row >= 0 && row < N) && (maze[row][col] == 0)) { // on vérifie qu'on est toujours dans le tableau
			return true;											// et la disponibilité de chemin
		}
		return false;
	}
	public static void main(String[] args) {
		boolean[][] labyrinthe = new boolean[][] {
			{false,true,false,false,false},
			{false,true,false,true,true},
			{false,true,false,false,true},
			{false,false,true,false,true},
			{false,false,false,false,true},
		};
		
		List<Point> points = echappeToi(labyrinthe);
		if (points != null)
		{
			for (int i = 0; i < points.size(); i++)
			{
				System.out.println(points.get(i)); // utilise la méthode toString pour afficher 
			}
			System.out.println("Ceci est le chemin le plus court disponible");
		}
		else
			System.out.println("Il n'y a pas de solution");// pas de solution
	}
}