
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class DesktopApp extends JFrame
{
	private Project project;

	private JButton genMetrics, saveXML;

	public static void main(String args[])
	{
		DesktopApp app = new DesktopApp();
	}

	public DesktopApp()
	{
		super("DesktopApp");

		ButtonHandler buttonHandler = new ButtonHandler();

		genMetrics = new JButton("Generate Metrics");
		genMetrics.addActionListener(buttonHandler);

		getContentPane().setLayout(new FlowLayout());

		setSize(200, 200);
		setLocation(500, 250);
		getContentPane().add(genMetrics);
		setVisible(true);
	}

	private void generateMetrics()
	{
		project = new Project();

		String dir = selectDirectory();

		if (dir == "None")
		{
			JOptionPane.showMessageDialog(null, "Invalid folder selected!");
		}
		else
		{
			project.setDirectory(dir);
			project.run();

			showResults();

			saveXML = new JButton("Save XML");
			getContentPane().add(saveXML);
			setVisible(false);
			setVisible(true);
		}
	}

	private String selectDirectory()
	{
		String directory;
		JFileChooser chooser = new JFileChooser();

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			directory = chooser.getSelectedFile() + "/";
		}
		else
		{
			directory = "None";
		}

		return directory;
	}

	private void showResults()
	{
		String resultStr = new String();

		ArrayList<Result> results = project.getResults();

		for (int i = 0; i < results.size(); i++)
		{
			String temp = results.get(i).getText() + "\n";
			resultStr += temp;
		}

		JOptionPane.showMessageDialog(null, resultStr);
	}

	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			if (ae.getSource() == genMetrics)
			{
				generateMetrics();
			}
			else if (ae.getSource() == saveXML)
			{
				//storeXML();
			}
		}
	}

}