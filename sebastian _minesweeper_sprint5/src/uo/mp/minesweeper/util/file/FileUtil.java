package uo.mp.minesweeper.util.file;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.LinkedList;


public abstract class FileUtil {
	public abstract BufferedReader getReader(String filename) throws FileNotFoundException;
	public abstract BufferedWriter getWriter(String filename);

	public List<String> readLines(String inFileName) throws FileNotFoundException{
		List<String> lines = new LinkedList<>();	
		String line  = null;
		BufferedReader br = getReader(inFileName);
		try {
			try {
			line = br.readLine();
			while(line!=null) {
				lines.add(line);
				line = br.readLine();
			}
			
		} finally {
			br.close();
		}}
		catch ( IOException ioe ) {
			throw new RuntimeException(ioe.getMessage());
		}
		return lines;	
	}
	

	public void writeLines(String outFileName, List<String> lines) {
		try {
			BufferedWriter out = getWriter(outFileName);
			try {
				for (String line : lines) {
					out.write(line + "\n");
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	
}
