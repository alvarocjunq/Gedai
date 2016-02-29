package br.com.gedai.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

public class FileUtils {

	private static final String DIR_ARCHIVE = "/br/com/gedai/archives/";
	
	public static File getFile(HttpSession session, String arquivo){
		return new File(StringUtils.concat(session.getServletContext().getRealPath("WEB-INF/classes/"+DIR_ARCHIVE), "/", arquivo));
	}
	
	public static InputStream getInputStream(String arquivo){
		return FileUtils.class.getResourceAsStream(StringUtils.concat(DIR_ARCHIVE, arquivo));
	}
	
	public static File getFile(String arquivo, String diretorio){
		return new File(StringUtils.concat(diretorio, arquivo));
		
	}
	
	public static String getPastaUltimaVersao(File diretorio, String identificadorPasta){
		SortedSet<String> pastas = new TreeSet<String>();
		for(String pasta: diretorio.list()){
			if(pasta.contains(identificadorPasta))
				pastas.add(pasta);
		}
		return pastas.last();
	}
	
	public static File copyFile(String arquivo, String arquivoDestino, HttpSession session) {
		
		try {
		URL url = FileUtils.class.getResource(DIR_ARCHIVE);
		String decoded = URLDecoder.decode(url.getFile(), "UTF-8");

		if (decoded.startsWith("/")) 
		    decoded = decoded.replaceFirst("/", "");
		
		File file = new File(decoded, arquivoDestino);
		Path destino = Paths.get(file.toString());
		
		InputStream is = FileUtils.class.getResourceAsStream(StringUtils.concat(DIR_ARCHIVE, arquivo));
		Files.copy(is, destino);
		
		return file;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
