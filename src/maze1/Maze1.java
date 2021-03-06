package maze1;
import java.util.*; 

class Maze 
{ 
static int ROW ; 
static int COL ; 

// To store matrix cell cordinates 
static class Point 
{ 
	int x; 
	int y; 

	public Point(int x, int y) 
	{ 
		this.x = x; 
		this.y = y; 
	} 
}; 

// A Data Structure for queue used in BFS 
static class queueNode 
{ 
	Point pt; // The cordinates of a cell 
	int dist; // cell's distance of from the source 

	public queueNode(Point pt, int dist) 
	{ 
		this.pt = pt; 
		this.dist = dist; 
	} 
}; 

// check whether given cell (row, col) 
// is a valid cell or not. 
static boolean isValid(int row, int col) 
{ 
	// return true if row number and 
	// column number is in range 
	return (row >= 0) && (row < ROW) && 
		(col >= 0) && (col < COL); 
} 

// These arrays are used to get row and column 
// numbers of 4 neighbours of a given cell 
static int rowNum[] = {-1, 0, 0, 1}; 
static int colNum[] = {0, -1, 1, 0}; 

// function to find the shortest path between 
// a given source cell to a destination cell. 
static int BFS(char mat[][], Point src,Point dest) 
{ 
	
	boolean [][]visited = new boolean[ROW][COL]; 
	
	// Mark the source cell as visited 
	visited[src.x][src.y] = true; 

	// Create a queue for BFS 
	Queue<queueNode> q = new LinkedList<>(); 
	
	// Distance of source cell is 0 
	queueNode s = new queueNode(src, 0); 
	q.add(s); // Enqueue source cell 

	// Do a BFS starting from source cell 
	while (!q.isEmpty()) 
	{ 
		queueNode curr = q.peek(); 
		Point pt = curr.pt; 

		// If we have reached the destination cell, 
		// we are done 
		if (pt.x  == dest.x && pt.y == dest.y) 
			return curr.dist; 

		// Otherwise dequeue the front cell 
		// in the queue and enqueue 
		// its adjacent cells 
		q.remove(); 

		for (int i = 0; i < 4; i++) 
		{ 
			int row = pt.x + rowNum[i]; 
			int col = pt.y + colNum[i]; 
			
			// if adjacent cell is valid, has path 
			// and not visited yet, enqueue it. 
			if (isValid(row, col) && 
					(mat[row][col] == '.' || mat[row][col]== 'e') && 
					!visited[row][col]) 
			{ 
				// mark cell as visited and enqueue it 
				visited[row][col] = true; 
                                mat[row][col]='C';
                                src.x=row;
                                src.y = col;
//                                printSolution(mat);
    				queueNode Adjcell = new queueNode(new Point(row, col),curr.dist+1 ); 
				q.add(Adjcell); 
			} 
                       
		} 
                
	} 
        

	// Return -1 if destination cannot be reached 
	return -1; 
} 
 static void print(char sol[][]) 
    { 
        for (int i = 0; i < ROW; i++) { 
            for (int j = 0; j < COL; j++) 
                System.out.print( 
                    " " + sol[i][j] + " "); 
            System.out.println(); 
        } 
    } 
// Driver Code 
 
 
 static int a ,b,x,y ;
 
 
public static void main(String[] args) 
{ 
//	char mat[][] =   {      { 'C', '*', '*', '*', '.', '.', '*', '.', '.', '.' }, 
//				{ '.', '*', '*', '*', '.', '.', '.', '*', '.', '.' }, 
//				{ '.', '*', '*', '*', '.', '.', '*', '.', '*', '.' }, 
//				{ '.', '.', '*', '*', '.', '*', '*', '*', '*', '.' }, 
//				{ '*', '.', '*', '*', 'e', '.', '.', '*', '.', '*' }, 
//				{ '*', '.', '.', '.', '.', '*', '*', '.', '*', '*' }, 
//				{ '.', '*', '*', '*', '*', '*', '*', '.', '*', '.' }, 
//				{ '.', '*', '.', '.', '.', '.', '*', '.', '.', '.' }, 
//				{ '.', '.', '.', '*', '*', '*', '.', '*', '*', '.' }
//                        }; 
        
//        char mat[][] =   {    { '.', '*', '*', '*', '.', '.', '*', '.', '.', '.' }, 
//				{ '.', '*', '*', '*', '.', '.', '.', '*', '.', '.' }, 
//				{ '.', '*', '*', '*', '.', '.', '*', '.', '*', '.' }, 
//				{ '.', '.', '*', '*', '.', '*', '*', '*', '*', '.' }, 
//				{ '*', '.', '*', '*', '.', '.', '.', '*', '.', '*' }, 
//				{ '*', 'e', '*', '.', '.', '.', '*', '.', '*', '*' }, 
//				{ '.', '.', '*', '*', '.', '*', '*', '.', '*', '.' }, 
//				{ '.', '*', '.', '.', '.', '.', '*', '.', '.', '.' }, 
//				{ 'c', '.', '*', '*', '*', '*', '.', '*', '*', '.' }
//                        }; 
        
        
        
       char mat[][] = {         { '*', '*', '*', '*', '.', '.', '*', '.', '.', '.' }, 
				{ '*', '*', '*', '.', '.', '.', '.', '*', '.', '.' }, 
				{ '.', '*', '.', '*', '.', '.', '*', '.', '*', '.' }, 
				{ '*', '*', '*', '*', '.', '*', '*', '.', '.', '.' }, 
				{ 'c', '.', '.', '*', 'e', '.', '*', '*', '.', '*' }, 
				{ '*', '*', '.', '*', '.', '*', '*', '.', '*', '*' }, 
				{ '.', '*', '.', '.', '.', '*', '.', '.', '*', '.' }, 
				{ '.', '.', '*', '*', '*', '.', '*', '.', '.', '.' }, 
				{ '.', '.', '.', '*', '*', '.', '.', '*', '*', '.' }
                        }; 
ROW = mat.length;
COL = mat[0].length;
        
        
        for (int i = 0; i < ROW; i++) { 
            for (int j = 0; j < COL; j++){
                if(mat[i][j]=='c'){
                    a = i;
                    b = j;
                }
                 if(mat[i][j]=='e'){
                    x = i;
                    y = j;
                }

            }
        } 

        
        
        

	Point source = new Point(a, b); 
	Point dest = new Point(x, y); 

	int dist = BFS(mat, source, dest); 

	if (dist != Integer.MAX_VALUE) 
		System.out.println("Shortest Path is " + dist); 
	else
		System.out.println("Shortest Path doesn't exist"); 
        
        mat[x][y]='e';
        print(mat);

	} 
} 


