
import java.io.*;

public class JavaFileFilter implements FileFilter
{
	public boolean accept(File file)
	{
		if (file.getName().toLowerCase().endsWith(".java"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}