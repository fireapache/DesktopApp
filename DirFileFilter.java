
import java.io.*;

public class DirFileFilter implements FileFilter
{
	public boolean accept(File file)
	{
		return file.isDirectory();
	}
}