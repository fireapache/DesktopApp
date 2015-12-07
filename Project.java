
import java.io.File;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Project
{
	private String directory;
	private ArrayList<Result> results;
	private Metric metrics[];

	public List<JavaClass> classes;

	public Project()
	{
		results = new ArrayList<Result>();
		classes = new ArrayList<JavaClass>();
		metrics = new Metric[7];

		metrics[0] = new NClasses();
		metrics[1] = new NInterfaces();
		metrics[2] = new NAbstracts();
		metrics[3] = new MaxDIT();
		metrics[4] = new NMethods();
		metrics[5] = new NAtributes();
		metrics[6] = new NCodeLines();

		for (int i = 0; i < metrics.length; i++)
		{
			metrics[i].setProject(this);
		}

	}

	public void setDirectory(String path)
	{
		directory = path;
	}

	public void run()
	{
		findClasses();
		parseClasses();
		solveInheritances();
		calculateMetrics();
	}

	public ArrayList<Result> getResults()
	{
		return results;
	}

	private void findClasses()
	{
		File dir = new File(directory);

		findClasses_(dir);
	}

	private void findClasses_(File file)
	{
		if (file.isDirectory())
		{
			String fileList[] = file.list();

			for (int i = 0; i < fileList.length; i++)
			{
				findClasses_(new File(file, fileList[i]));
			}
		}
		else
		{
			if (file.getName().toLowerCase().endsWith(".java"))
			{
				classes.add(new JavaClass(file.getPath()));
				System.out.println(file.getPath());
			}
		}
	}

	private void parseClasses()
	{
		for (int i = 0; i < classes.size(); i++)
		{
			classes.get(i).parse();
		}
	}

	private void solveInheritances()
	{
		HashMap<String, JavaClass> map = new HashMap<String, JavaClass>();

		for (int i = 0; i < classes.size(); i++)
		{
			map.put(classes.get(i).getName(), classes.get(i));
		}

		JavaClass parent;

		System.out.println("===== Inheritances =====");

		for (int i = 0; i < classes.size(); i++)
		{
			parent = map.get(classes.get(i).getParentName());

			if (parent != null)
			{
				classes.get(i).setSuperClass(parent);
				System.out.println(classes.get(i).getName() + " extends " + parent.getName());
			}
		}
	}

	private void calculateMetrics()
	{
		Result localResult;

		for (int i = 0; i < metrics.length; i++)
		{
			metrics[i].calculate();
			results.add(metrics[i].result);
		}
	}

}