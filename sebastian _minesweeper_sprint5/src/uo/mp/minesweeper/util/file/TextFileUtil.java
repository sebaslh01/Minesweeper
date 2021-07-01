package uo.mp.minesweeper.util.file;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.OutputStreamWriter;

public class TextFileUtil extends FileUtil {

	@Override
	public BufferedReader getReader(String inFileName) throws FileNotFoundException  {
		BufferedReader br = new BufferedReader(new FileReader(inFileName));
		return br;
	}

	@Override
	
	public BufferedWriter getWriter(String outFileName) {
		try{
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFileName)));
			return out;
		}
		catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}




}


