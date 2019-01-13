import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// @author Anne Hu

public class ReadFile {
	static File users = new File("unoDataBase.txt");
	static String[] indiData;

	/*
	 * This method is given the string to search and the type of string to search
	 * given an index number to check for (to differentiate the different types,
	 * username, password)
	 */
	public static String[] getFileInfo(String infoToSearch, int indexNum) {

		try {
			BufferedReader getInfo = new BufferedReader(new FileReader(users));
			String info = getInfo.readLine();

			// splits data into an array separated by commas, and if it found its
			// information, it will return the string array
			while (info != null) {
				indiData = info.split(",");
				if (indiData[indexNum].equals(infoToSearch)) {
					return indiData;
				}
				info = getInfo.readLine();
			}
			getInfo.close();
		}

		catch (IOException e) {
			System.out.println("An I/O Error Occurred");
			System.exit(0);

		}
		return indiData;
	}
}
