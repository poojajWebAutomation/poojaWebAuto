package com.bungii.common.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUtility {
	public static String autoHome ;
	/**
	 * Checks file exist or not at specified path
	 *
	 * @param sPath
	 *            path of file
	 * @return boolean result
	 * @throws Exception
	 */
	public static boolean makePath(String sPath) throws Exception {
		File file = new File(sPath);
		if (file.exists()) {
			return true;
		}
		if (!makePath(file.getParent())) {
			return false;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			logger.handleError("Exception caught in creating file :"
					+ e.getMessage());
		}
		return file.exists();
	}

	/**
	 * Copy file from source to destination
	 * @param sPath  Source path
	 * @param dPath  Destination path
	 * @return
	 */
	public static boolean copyFile(String sPath, String dPath){

		Path FROM = Paths.get(sPath);
		Path TO = Paths.get(dPath);
		CopyOption[] options = new CopyOption[]{
		  StandardCopyOption.REPLACE_EXISTING,
		  StandardCopyOption.COPY_ATTRIBUTES
		};
		try {
			Files.copy(FROM, TO, options);
			return true;
		} catch (IOException e) {
			logger.error("Exception caught in coping file :"+e.getMessage());
		//	e.printStackTrace();
			return false;
		}

	}
	/**
	 * Returns boolean value depending on file exists or not
	 *
	 * @param sFile
	 *            name of file
	 * @return boolean result
	 */
	public static boolean fileExists(String sFile) {
		return isExist(sFile);
	}

	// STEP #2 - File Doesn't Exists - This Method Will Throw The 'NoSuchFileException'.
	public static void deletFile(String sFile) {
		if(isExist(sFile)){
		Path filePath = Paths.get(sFile);
		try {
			Files.delete(filePath);
		} catch (IOException ioException) {
			System.out.println("! Error Caught In Deleting File As The File Is Not Present !");
			ioException.printStackTrace();
		}
		}else{
			logger.detail("File "+sFile+" doesnot exists");
		}
	}

	/**
	 * Returns boolean value depending on folder exists or not
	 *
	 * @param sFolder
	 *            name of folder
	 * @return boolean result
	 */
	public static boolean folderExists(String sFolder) {
		return isExist(sFolder);
	}

	/**
	 * Returns boolean value depending on existence
	 *
	 * @param sName
	 *            name of object needed to check
	 * @return boolean result
	 */
	private static boolean isExist(String sName) {
		return new File(sName).exists();
	}

	/**
	 * Removes folder specified
	 *
	 * @param sFolderPath
	 *            path of folder to be removed
	 */
	public static void removeFolder(String sFolderPath) {
		File file = new File(sFolderPath);
		file.delete();
	}

	/**
	 * Makes folder with specified path and returns boolean value
	 *
	 * @param sFolderPath
	 *            path of folder
	 * @return boolean result
	 */
	public static boolean makeFolder(String sFolderPath) {
		if (!new File(sFolderPath).isDirectory())
			return new File(sFolderPath).mkdirs();
		else
			return true;// as directory already exists
	}


	/**
	 * Creates text file with specified name
	 *
	 * @param sFile
	 *            name for file
	 * @throws Exception
	 */
	public static void createTextFile(String sFile) throws Exception {
		File file = new File(sFile);
		try {
			file.createNewFile();
		} catch (IOException e) {
			logger.handleError("Error while creating new file ",sFile, e);
		}
	}
//MOVED from RESOURCE PATH
	private static String createPath(String home, String relPath, String relName) {
		if (!relPath.equals(""))
			home = home + "/" + relPath;
		if (!relName.equals(""))
			home = home + "/" + relName;
		//System.out.println("Path Formed : "+ home);
		return home;
	}
//MOVED from RESOURCE PATH
	public static String getSuiteResource(String relPath, String relName) {

		return createPath(autoHome, relPath, relName);
	}

	private FileUtility() {
	}

	private static LogUtility logger = new LogUtility();
}
