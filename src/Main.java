import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 469079866188870003L;
	public Main(){
		super();
	}
	Random rand = new Random();
	String file = null;
	public static void main(String[] args){
        JFrame frame = new JFrame("Random Game Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        Main panel = new Main();
        frame.setContentPane(panel);
        frame.setSize((int)(dim.width * 0.8), (int)(dim.height * 0.8));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel.file = panel.getFile();
	}
	public void paintComponent(Graphics g){
		Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		g.setColor(color);
		Font font = new Font("Arial", Font.BOLD, 12);
		g.drawString("RandomGameChooser V1.12 by SamGames", 0, 12);
		font = new Font("Arial", Font.BOLD, 60);
		g.setFont(font);
		g.drawString(getGame(), 0, (getMiddleHeight() - 40));
	}
	private String getFile(){
		URL url = getClass().getResource("Config.txt");
		File file = new File(url.getPath());
		String fileName = null;
		try {
			Scanner config = new Scanner(file);
			config.nextLine();
			fileName = config.nextLine();
			config.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	public String getGame(){
		int lines = 0;
		int rgame = 0;
		String game = null;
		URL url = getClass().getResource(file);
		File file = new File(url.getPath());
		try {
			Scanner lineNumber = new Scanner(file);
			while(lineNumber.hasNext()){
				lines++;
				lineNumber.nextLine();
			}
			lineNumber.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println(lines);
		try {
			Scanner input = new Scanner(file);
			rgame = rand.nextInt(lines) + 1;
			for (int i = 0; i < rgame; i++){
				game = input.nextLine();
			}
			System.out.println(game);
			input.close();
		}
		catch(FileNotFoundException e){
			System.err.format("no file found");
		}
		catch(NoSuchElementException e){
			System.err.format("line not found");
		}
		return game;
	}
	public int getMiddleHeight(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int middleHeight = (int) (dim.height * 0.8 / 2);
        return middleHeight;
	}
	public int getMiddleWidth(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int middleWidth = (int) (dim.width * 0.8 / 2);
        return middleWidth;
	}
}
