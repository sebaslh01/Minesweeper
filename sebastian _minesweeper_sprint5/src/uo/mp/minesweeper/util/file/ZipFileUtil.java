package uo.mp.minesweeper.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;



public class ZipFileUtil extends FileUtil {


	@Override
	public BufferedReader getReader(String filename) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(filename))));
			return br;
		}
		catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public BufferedWriter getWriter(String outFileName) {
		try{
			
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(outFileName))));
			return out;
		}
		catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


}
