
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class DesktopApp extends JFrame
{
	private Project project;

	private JButton genMetrics, saveXML;

	private ButtonHandler buttonHandler;

	public static void main(String args[])
	{
		DesktopApp app = new DesktopApp();
	}

	public DesktopApp()
	{
		super("DesktopApp");

		buttonHandler = new ButtonHandler();

		genMetrics = new JButton("Generate Metrics");
		genMetrics.addActionListener(buttonHandler);

		getContentPane().setLayout(new FlowLayout());

		setSize(200, 200);
		setLocation(500, 250);
		getContentPane().add(genMetrics);
		setVisible(true);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

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
			showSaveXMLButton();
		}
	}

	private void showSaveXMLButton()
	{
		if (saveXML == null)
		{
			saveXML = new JButton("Save XML");
			getContentPane().add(saveXML);
			setVisible(false);
			setVisible(true);
			saveXML.addActionListener(buttonHandler);
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

	private String selectFile()
	{
		String filePath;
		JFileChooser chooser = new JFileChooser();

		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			filePath = chooser.getSelectedFile() + "";
		}
		else
		{
			filePath = "None";
		}

		return filePath;
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

	private String generateXML()
	{
		String xmlFile = "";
		MeanResult meanResult;

		xmlFile += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + '\n';
		xmlFile += "<metrics>" + '\n';

		ArrayList<Result> results = project.getResults();

		for (int i = 0; i < results.size(); i++)
		{
			xmlFile += "\t" + "<" + results.get(i).metric + '>' + '\n';
			xmlFile += "\t\t" + "<value>" + results.get(i).value + "</value>" + '\n';

			try
			{
				meanResult = (MeanResult)results.get(i);
				xmlFile += "\t\t" + "<deviation>" + meanResult.stdDeviation + "</deviation>" + '\n';
			}
			catch (Exception e)
			{

			}

			xmlFile += "\t" + "<" + '/' + results.get(i).metric + '>' + '\n';
		}

		xmlFile += "</metrics>";

		return xmlFile;
	}

	private void storeXML()
	{
		String xmlContent;
		String filePath;

		xmlContent = generateXML();
		filePath = selectFile();

		try
		{
			FileWriter writer = new FileWriter(filePath, false);
			PrintWriter printer = new PrintWriter(writer);

			printer.print(xmlContent);
			printer.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("====================");
		System.out.println("XML file saved!");
		System.out.println(filePath);
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
				storeXML();
			}
		}
	}

}