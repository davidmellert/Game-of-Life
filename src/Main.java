import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	private int[][] board;
	private ArrayList<Cord> startingLifes;
	
	public Main() {
		board = new int[20][20];
		startingLifes = new ArrayList<Cord>();
		
		fillBoard();
		
		/*startingLifes.add(new Cord(5,4));			
		startingLifes.add(new Cord(5,3));
		startingLifes.add(new Cord(5,5));
		startingLifes.add(new Cord(0,0));
		startingLifes.add(new Cord(0,1));
		startingLifes.add(new Cord(1,0));
		startingLifes.add(new Cord(1,1));
		
		*/
		//Um neue lebende Zellen zu Beginn hinzuzufügen, einfach hier eine Zeile ergänzen mit den entsprechenden Koordinaten
		startingLifes.add(new Cord(1,1));
		startingLifes.add(new Cord(1,3));
		startingLifes.add(new Cord(0,3));
		startingLifes.add(new Cord(2,3));
		startingLifes.add(new Cord(2,2));
		
		
		addLifes(startingLifes);
		
		System.out.println("Generation "+ 0 + ": \n" +drawBoard(board));
		
		//Der erste Parameter bestimmt, wie viele Generationen berechnet werden sollen
		generate(40, 1, board);
	}
	
	private void fillBoard() {
		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	private void addLifes(ArrayList<Cord> lifes) {
		
		startingLifes.forEach(c -> board[c.x][c.y] = 1);
				
	}
	private String drawBoard(int[][] pBoard) {
		String result = "";
		for(int j=0; j<20; j++) {
			for(int i=0; i<20; i++) {
				if(pBoard[i][j] == 1)					
					result += "\u2B1B";
				else
					result += "\u2B1C";
			}
			result += "\n";
		}
		
		return result;
	}
	
	private void generate(int generations, int aktGeneration, int[][] pBoard) {			
		if(generations > 0) {
			int[][] tempBoard = new int[20][20];
				
			for(int y=0; y<20; y++) {
				for(int x=0; x<20; x++) {
					int aliveNeighbours = 0;
					for(int c=-1; c<=1; c++) {
						for(int d=-1; d<=1; d++) {
							if(!(c==0 && d==0) && (x+c)>=0 && (x+c)<=19 && (y+d)>=0 && (y+d)<=19) {
								aliveNeighbours += pBoard[x+c][y+d];
							}
						}
					}
					if(pBoard[x][y] == 0) {
						if(aliveNeighbours == 3) {
							tempBoard[x][y] = 1;
						}else {
							tempBoard[x][y] = 0;
						}
					}else {
						if(aliveNeighbours < 2 || aliveNeighbours>3) {
							tempBoard[x][y] = 0;
						}
						else {
							tempBoard[x][y] = 1;
						}
					}
				}
			}		
			System.out.println("Generation "+ aktGeneration+ ": \n" +drawBoard(tempBoard));
			generations--;
			aktGeneration++;
			generate(generations, aktGeneration, tempBoard);
		}	
	}
}
