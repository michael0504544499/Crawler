
package DP_project;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JProgressBar;

import Enums.UserAgent;

/**
 *
 * @author Michael
 */
public class Download {



	public static void download(String URL, String folderName,JProgressBar fileProcess) {

		// Make sure that this directory exists
		File files = new File(System.getProperty("user.dir") + "/" + folderName);
		if (!files.exists()) {
			files.mkdirs();
		}
		String dirName = System.getProperty("user.dir") + "/" + folderName+"/";
		System.out.println(dirName);
		String url = URL;
		String name = url.substring(url.lastIndexOf("/") + 1, url.length());
		String path=dirName + name;
		try {
			saveFileFromUrlWithJavaIO(path, url,fileProcess);
		} catch (Exception e) {

		}

	}

	// Using Java IO
	//the method get URL and open http connection, buffer for stream of file 
	//create the file 
	public static void saveFileFromUrlWithJavaIO(String fileName, String Url,JProgressBar fileProcess)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			URL url =new URL(Url);
			URLConnection uc;
			uc=url.openConnection();
			uc.setReadTimeout(3000);
			uc.addRequestProperty("User-Agent",UserAgent.MOZILLA_USER_AGENT_4.getAgent());
			in = new BufferedInputStream(uc.getInputStream());
			fout = new FileOutputStream(fileName);

			byte data[] = new byte[4096];
			int count;
			while ((count = in.read(data, 0, 4096)) != -1) {
				fout.write(data, 0, count);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}

	// Using Commons IO library
	// Available at http://commons.apache.org/io/download_io.cgi
	// public static void saveFileFromUrlWithCommonsIO(String fileName,
	// String fileUrl) throws MalformedURLException, IOException {
	// FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
	// }
}
