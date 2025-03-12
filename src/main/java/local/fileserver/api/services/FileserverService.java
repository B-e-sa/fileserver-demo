package local.fileserver.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.fileserver.api.models.File;
import local.fileserver.api.models.File.Extension;
import local.fileserver.api.repositories.FileRepository;

@Service
public class FileserverService {
	@Autowired
	FileRepository fileRepository;
	
	/**
	 * Searches for the file by name
	 * 
	 * @param name the file name
	 * @return the file if it exists, or null if not
	 */
	public File findByName(String name)
	{
		return fileRepository.findByName(name);
	}
	
	/**
	 * Pushes an new file to the file array, if it's name does not exists yet
	 * 
	 * @param name the file name to add 
	 * @return true if the file doesn't exist already, or false if it does.
	 */
	public boolean push(File file)
	{	
		if (this.findByName(file.getName()) != null)
			return false;
		fileRepository.save(file);
		return true;
	}
	
	/**
	 * Delete the given file by name if it exists
	 * 
	 * @param name the file name to delete 
	 * @return true if the file exists, or false if it doesn't.
	 */
	public boolean delete(String name)
	{
        File fileToDelete = this.findByName(name);
        if (fileToDelete == null)
            return false;
        fileRepository.delete(fileToDelete);
        return true;
	}
	
	/**
	 * List all the current files in the array
	 * 
	 * @return the files array, it can be empty
	 */
	public List<File> list()
	{
		return fileRepository.findAll();
	}
}
