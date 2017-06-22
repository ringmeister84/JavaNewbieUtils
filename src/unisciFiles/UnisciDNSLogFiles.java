package unisciFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UnisciDNSLogFiles{

	public static void main(String[] args) throws IOException{
		String[] serverNames = new String[]{"Web103", "Web124"};
		for (String server : serverNames){
			//i files originali vanno prima convertiti in UTF8 (Notepad++)
			//nel file head.log verificare che non ci siano start/stop non correttamente alternati
			File head = new File("C:\\MariaElena\\Work\\" + server + "\\head.log");
			//nel file content.log (quello con l'elenco risposte) vanno sostituite le righe vuote con Z per avere un carattere su cui fare confronti per il termine risposta
			File content = new File("C:\\MariaElena\\Work\\" + server + "\\content.log");
		    PrintWriter finalePW = new PrintWriter("C:\\MariaElena\\Work\\" + server + "\\Finale" + server +".log", "UTF-8");
			BufferedReader headBR = new BufferedReader(new FileReader(head));
			BufferedReader contentBR = new BufferedReader(new FileReader(content));
			String headLine = null;
			String contentLine = null;
			while ((headLine = headBR.readLine()) != null) {
				finalePW.write(headLine + "\r\n");
				int blocco = 0;
				while ((contentLine = contentBR.readLine()) != null && blocco<6){
					if(contentLine.startsWith("Z"))
						blocco++;
					else
						finalePW.write(contentLine + "\r\n");
				}	
				if((headLine = headBR.readLine()) != null)
					finalePW.write(headLine + "\r\n");
			}
			contentBR.close();
			headBR.close();
			finalePW.close();
		}
	}

}
