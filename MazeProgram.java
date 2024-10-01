package Misc.MazeProgram;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MazeProgram extends JPanel implements KeyListener
{

	JFrame frame;
	String [][] maze;
	String [][] minimap;
	Hero hero;
	int endR, endC;
	int numMoves;
	boolean in2d = true;
	boolean keys = false;
	ArrayList<Wall> walls;
	int s = 50;
	public void keyTyped(KeyEvent e)
	{

	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 32)
		{
			in2d = !in2d;
		}


		if(!in2d)
			set3d();

		hero.move(e.getKeyCode());
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void set3d()
	{
		walls = new ArrayList<Wall>();

		for(int a = 0; a<5; a++)
		{
			if((hero.getC() - a >=0 && hero.getD() == 'W')|| (hero.getR() - a >=0 && hero.getD() == 'N') || (hero.getC() + a < maze[hero.getR()].length && hero.getD() == 'E') || (hero.getR() + a < maze.length && hero.getD() == 'S'))
			{
			leftWall(a); //layered triangles

			int [] x = {100 + 50*a, 150 + 50*a, 150 + 50*a, 100 + 50*a};
			int [] y = {100 + 50*a, 100 + 50*a, 500 - 50*a, 500 - 50*a};
			walls.add(new Wall(x,y, 200 - 50*a, 200 - 50*a, 200 - 50*a, "Left", null)); //darker for perspective. rectangles
			}
		}

		for(int a = 0; a<5; a++)
		{
			if((hero.getC() - a >=0 && hero.getD() == 'W')|| (hero.getR() - a >=0 && hero.getD() == 'N') || (hero.getC() + a < maze[hero.getR()].length && hero.getD() == 'E') || (hero.getR() + a < maze.length && hero.getD() == 'S'))
			{
			rightWall(a);

			int [] x = {600 - 50*a, 550 - 50*a, 550 - 50*a, 600 - 50*a};
			int [] y = {100 + 50*a, 100 + 50*a, 500 - 50*a, 500 - 50*a};
			walls.add(new Wall(x,y, 200 - 50*a, 200 - 50*a, 200 - 50*a, "Right", null));
			}
	}
		int w = 0;
		for(int a = 4; a>=0; a--)
		{

			int row = hero.getR();
			int col = hero.getC();
			char dir = hero.getD();
			if((col - a < 0 && dir == 'W') || (row - a < 0 && dir == 'N') || (row + a >= maze.length && dir == 'S') || (col + a >= maze[row].length && dir == 'E'))
			{

			}

			else
			{
			bottomWall(a);
			topWall(a);
			}

			switch(dir)
			{

				case 'E':
				try
				{
					if(col + a < maze[row].length)
					{
				if(maze[row-1][col+a].equals("*"))
				{
					leftWall(a);
				}

				if(maze[row+1][col+a].equals("*"))
				{
					rightWall(a);
				}

				if(maze[row][col+a].equals("*"))
				{
					frontWall(w);
				}

				if(maze[row][col+a].equals("l"))
				{
					lockWall(w);
				}

				if(maze[row][col+a].equals("k") && !keys)
				{
					keyWall(a);
				}
			}


				}
				catch(ArrayIndexOutOfBoundsException e){}
				break;

				case 'S':
				try
				{
					if(row + a < maze.length)
					{
				if(maze[row+a][col+1].equals("*"))
				{
					leftWall(a);
				}

				if(maze[row+a][col-1].equals("*"))
				{
					rightWall(a);
				}
				if(maze[row + a][col].equals("*"))
				{
					frontWall(w);
				}
				if(maze[row + a][col].equals("l"))
				{
					lockWall(w);
				}
				if(maze[row + a][col].equals("k") && !keys)
				{
					keyWall(a);
				}
			}

				}
				catch(ArrayIndexOutOfBoundsException e){}
				break;

				case 'W':
				try
				{
				if(col - a >= 0)
				{
				if(maze[row+1][col-a].equals("*"))
				{
					leftWall(a);
				}

				if(maze[row-1][col-a].equals("*"))
				{
					rightWall(a);
				}

				if(maze[row][col-a].equals("*"))
				{
					frontWall(w);
				}
				if(maze[row][col-a].equals("l"))
				{
					lockWall(w);
				}
				if(maze[row][col-a].equals("k") && !keys)
				{
					keyWall(a);
				}
				}


				}
				catch(ArrayIndexOutOfBoundsException e){}
				break;

				case 'N':
				try
				{
				if(row - a >= 0)
				{
				if(maze[row-a][col-1].equals("*"))
				{
					leftWall(a);
				}

				if(maze[row-a][col+1].equals("*"))
				{
					rightWall(a);
				}

				if(maze[row-a][col].equals("*"))
				{
					frontWall(w);
				}
				if(maze[row-a][col].equals("l"))
				{
					lockWall(w);
				}

				if(maze[row-a][col].equals("k") && !keys)
				{
					keyWall(a);
				}
				}
				}
				catch(ArrayIndexOutOfBoundsException e){}
				break;
			}
			w++;
}
	}

	public void frontWall(int a)
	{
			int [] r = {300 - 50*a, 300 - 50*a, 400 + 50*a, 400 + 50*a}; //rightleft
			int [] c = {250 - 50*a, 350 + 50*a, 350 + 50*a, 250 - 50*a}; //updown
			//walls.add(new Wall(r,c, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Front", null));
			walls.add(new Wall(r,c, 55 + 50*a, 55 + 50*a, 55 + 50*a, "Front", null));
	}

	public void lockWall(int a)
	{
			int [] r = {300 - 50*a, 300 - 50*a, 400 + 50*a, 400 + 50*a}; //rightleft
			int [] c = {250 - 50*a, 350 + 50*a, 350 + 50*a, 250 - 50*a}; //updown
			//walls.add(new Wall(r,c, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Front", null));
			walls.add(new Wall(r,c, 119, 46, 0, "Front", null));
	}

	public void leftWall(int a)
	{
			int [] x = {100 + 50*a, 150 + 50*a, 150 + 50*a, 100 + 50*a};
			int [] y = {50 + 50*a, 100 + 50*a, 500 - 50*a, 550 - 50*a};
			walls.add(new Wall(x,y, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Left", null));
	}


	public void rightWall(int a)
	{
			int [] x = {600 - 50*a, 550 - 50*a, 550 - 50*a, 600 - 50*a};
			int [] y = {50 + 50*a, 100 + 50*a, 500 - 50*a, 550 - 50*a};
			walls.add(new Wall(x,y, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Right", null));

	}

	public void bottomWall(int a)
	{
			int [] x = {50 + 50*a, 100 + 50*a, 100 + 50*a, 50 + 50*a};
			int [] y = {100 + 50*a, 150 + 50*a, 550 - 50*a, 600 - 50*a};
			walls.add(new Wall(y,x, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Bottom", null));
	}

	public void keyWall(int a)
	{
			int [] x = {530 - 50*a, 525 - 50*a, 525 - 50*a, 530 - 50*a};//updown
			int [] y = {150 + 50*a, 150 + 50*a, 400 - 50*a, 400 - 50*a};//leftright
			walls.add(new Wall(y,x, 255, 255, 0, "Top", null));
	}

	public void topWall(int a)
	{
			int [] x = {550 - 50*a, 500 - 50*a, 500 - 50*a, 550 - 50*a};
			int [] y = {100 + 50*a, 150 + 50*a, 550 - 50*a, 600 - 50*a};
			walls.add(new Wall(y,x, 255 - 50*a, 255 - 50*a, 255 - 50*a, "Top", null));
	}



	public class Wall
	{
		int [] x,y;
		private int r,g,b;
		private String type;
		private Color color;

		public Wall(int [] x, int [] y, int r, int g, int b, String type, Color color)
		{
			this.x = x;
			this.y = y;
			this.r = r;
			this.b = b;
			this.g = g;
			this.type = type;
			this.color = color;
		}

		public void setColor(Color color)
		{
			this.color = color;
		}

		public Color getBreadCrumb()
		{
			return color;
		}

		public String getType()
		{
			return type;
		}

		public Polygon getPoly()
		{
			return new Polygon(x,y,4);
		}

		public GradientPaint getPaint()
		{
		int endr = r - 50;
		int endg = g - 50;
		int endb = b - 50;

		if(r<0){r = 0;}
		if(g<0){g = 0;}
		if(b<0){g = 0;}
		if(endr<0){endr = 0;}
		if(endg<0){endg = 0;}
		if(endb<0){endb = 0;}

		Color startColor = new Color(r,g,b);
		Color endColor = new Color(endr,endg,endb);

		switch(type)
		{
			case "Right":
			case "Left":
			return new GradientPaint(x[0],y[0],startColor,x[1],y[0],endColor);
		}
		return new GradientPaint(x[0],y[0],startColor,x[0],y[1],endColor);

		}

		public Color getColor()
		{
			return new Color(r,g,b);
		}
	}

	public MazeProgram()
	{
		frame = new JFrame();
		frame.add(this);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaze();
		frame.addKeyListener(this);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); //giant eraser
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK); //new Color(120, 140, 0)
		g2.fillRect(0, 0, 1000, 600);

		if(hero.getR() == endR && hero.getC() == endC)
		{
			g2.setColor(Color.RED);
			g2.drawString("You found your way out! Great job!", 670, 400);
			g2.drawString("Total Number of Moves: " + numMoves, 670, 430);

			g2.setColor(Color.BLACK);
		}
	//	System.out.println(endR + " "+ endC);


		if(in2d)
		{
			g2.setColor(Color.GRAY);

			for(int x = 0; x<maze.length; x++)
			{
				for(int y = 0; y<maze[x].length; y++)
				{
					if(maze[x][y].equals("*"))
					{
						g2.fillRect(y*10 + 20,x*10+20,10,10);
					}

					if(maze[x][y].equals("l"))
					{
						g2.setColor(Color.BLUE);
						g2.fillRect(y*10 + 20,x*10+20,10,10);
						g2.setColor(Color.GRAY);
					}

					if(maze[x][y].equals("k") && !keys)
					{
						g2.setColor(Color.YELLOW);
						g2.fillRect(y*10 + 22,x*10+23,5,3);
						g2.setColor(Color.GRAY);
					}

				}
			}

		g2.setColor(Color.WHITE);
		g2.fillOval(hero.getC()*10 + 20, hero.getR()*10 + 20, 10, 10);
		}

		else
		{
			int r = hero.getR();
			int c = hero.getC();
			minimap = new String[9][9];

			g2.setColor(Color.GRAY);
			try
			{
			int w = 0;
			for(int x = r - 4; x<= r + 4; x++)
			{
				int z = 0;
				for(int y = c - 4; y<= c + 4; y++)
				{
					if(x < 0 || x >= maze.length)
					{
						minimap[w][z] = "*";
					}

					else if(y < 0 || y >= maze[x].length)
					{
						minimap[w][z] = "*";
					}
					else
					{
					minimap[w][z] = maze[x][y];
					}
					z++;
				}
				w++;
			}

			for(int x = 0; x<minimap.length; x++)
			{
				for(int y = 0; y<minimap[x].length; y++)
				{
					if(minimap[x][y].equals("*"))
					{
						g2.setColor(Color.GRAY);
						g2.fillRect(y*10 + 700,x*10+200,10,10);
					}

					if(minimap[x][y].equals("l"))
					{
						g2.setColor(Color.BLUE);
						g2.fillRect(y*10 + 700,x*10+200,10,10);
						g2.setColor(Color.GRAY);
					}

					if(minimap[x][y].equals("k") && !keys)
					{
						g2.setColor(Color.YELLOW);
						g2.fillRect(y*10 + 702,x*10+203,5,3);
						g2.setColor(Color.GRAY);
					}
				}
			}
						}
						catch(ArrayIndexOutOfBoundsException e){}


			g2.setColor(Color.WHITE);
			g2.fillOval(740,240, 10, 10);
			g2.setColor(Color.RED);
			g2.fillRect(700,200, 90, 5);
			g2.fillRect(700,290, 90, 5);
			g2.fillRect(700,200, 5, 90);
			g2.fillRect(790,200, 5, 95);

			for(Wall wall: walls)
			{

				//g2.setColor(Color.GRAY);
				if(wall.getBreadCrumb()!=null)
				{
					g2.setColor(wall.getBreadCrumb());
				}
				else
				{
					g2.setPaint(wall.getPaint());
				}
				g2.fillPolygon(wall.getPoly());
				g2.setColor(Color.BLACK);
				g2.drawPolygon(wall.getPoly());
			}
		}
	}

	public void setMaze()
	{
		File file = new File("Maze1.txt");
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String text;
				maze = new String[25][60];
				int r = 0;
				boolean first = true;

				while((text = reader.readLine()) != null)
				{
					if(first)
					{
						String [] pieces = text.split(" ");
						int row = Integer.parseInt(pieces[0]);
						int col = Integer.parseInt(pieces[1]);
						hero = new Hero(row, col, pieces[2].charAt(0));
						endC = Integer.parseInt(pieces[3]);
						endR = Integer.parseInt(pieces[4]);
						first = false;
					}

					else
					{
						String [] pieces = text.split("");
						maze[r] = pieces;
						r++;
					}

				}
			}
			catch(IOException e){};
			//hero = new Hero();


			for(int x = 0; x<maze.length; x++)
			{
				for(int y = 0; y<maze[x].length; y++)
				{
					System.out.print(maze[x][y]);
				}
				System.out.println();
			}
	}

	public static void main(String[] args)
	{
		MazeProgram app = new MazeProgram();

	}

	public class Hero
	{
		 int r;
		 int c;
		 char d;

		public Hero(int r, int c, char d)
		{
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public int getR()
		{
			return r;
		}

		public void setR(int rr)
		{
			r = rr;
		}

		public int getC()
		{
			return c;
		}

		public void setC(int cc)
		{
			c = cc;
		}

		public char getD()
		{
			return d;
		}

		public void move(int key)
		{
			switch(key)
			{
				//left
				case 37:
					switch(d)
					{
						case 'E':
						d = 'N';
						break;

						case 'N':
						d = 'W';
						break;

						case 'W':
						d = 'S';
						break;

						case 'S':
						d = 'E';
						break;
					}
				break;

				//forward
				case 38:
				switch(d)
				{
					case 'E':
					try
					{
						if(maze[r][c+1].equals(" "))
						{
							numMoves++;
							c++;
						}

						else if(maze[r][c+1].equals("k"))
						{
							numMoves++;
							c++;
							keys = true;
						}

						if(maze[r][c+1].equals("l") && keys)
						{
							c++;
							numMoves++;
						}
					}
					catch(ArrayIndexOutOfBoundsException e){}
					break;

					case 'N':
					try
					{
						if(maze[r-1][c].equals(" "))
						{
							r--;
							numMoves++;
						}

						else if(maze[r-1][c].equals("k"))
						{
							r--;
							keys = true;
							numMoves++;
						}

						if(maze[r-1][c].equals("l") && keys)
						{
							r--;
							numMoves++;
						}
					}
					catch(ArrayIndexOutOfBoundsException e){}
					break;

					case 'S':
					try
					{
						if(maze[r+1][c].equals(" "))
						{
							r++;
							numMoves++;
						}

						else if(maze[r+1][c].equals("k"))
						{
							r++;
							keys = true;
							numMoves++;
						}

						else if(maze[r+1][c].equals("F"))
						{
							r++;
							numMoves++;
						}

						if(maze[r+1][c].equals("l") && keys)
						{
							r++;
							numMoves++;
						}
					}
					catch(ArrayIndexOutOfBoundsException e){}
					break;

					case 'W':
					try
					{
						if(maze[r][c-1].equals(" "))
						{
							c--;
							numMoves++;
						}

						else if(maze[r][c-1].equals("k"))
						{
							numMoves++;
							c--;
							keys = true;
						}

						if(maze[r][c-1].equals("l") && keys)
						{
							c--;
							numMoves++;
						}
					}
					catch(ArrayIndexOutOfBoundsException e){}
					break;
				}
				break;

				//right
				case 39:
				switch(d)
				{
					case 'N':
					d = 'E';
					break;

					case 'E':
					d = 'S';
					break;

					case 'S':
					d = 'W';
					break;

					case 'W':
					d = 'N';
					break;
				}
				break;
			}
		}

	}
}
