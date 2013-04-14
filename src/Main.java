import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 469079866188870003L;
	public Main(){
		super();
	}
	private Random rand = new Random();
	private String file;
	private int width;
	private int height;
	public static void main(String[] args){
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
        JFrame frame = new JFrame("Random Game Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        Main panel = new Main();
        frame.setContentPane(panel);
        panel.width = (int) (dim.width * 0.8);
        panel.height = (int) (dim.height * 0.8);
        frame.setSize(panel.width, panel.height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel.file = panel.getFile();
        panel.addComponents();
        frame.setVisible(true);
	}
	private void addComponents(){
		Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		JTextField version = new JTextField();
		version.setText("RandomGameChooser V1.2 by Sammidysam");
		version.setFont(new Font("Arial", Font.BOLD, 12));
		version.setForeground(color);
		version.setEditable(false);
		version.setHighlighter(null);
		version.setHorizontalAlignment(JTextField.LEFT);
		version.setBorder(BorderFactory.createEmptyBorder());
		this.add(version);
		JTextField result = new JTextField();
		result.setText(getGame());
		result.setFont(new Font("Arial", Font.BOLD, 60));
		result.setForeground(color);
		result.setEditable(false);
		result.setHighlighter(null);
		result.setHorizontalAlignment(JTextField.CENTER);
		result.setBorder(BorderFactory.createEmptyBorder());
		result.setPreferredSize(new Dimension(width, height));
		this.add(result);
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
}
