package main;

import java.io.File;
import java.util.Scanner;

import model.FileStorage;


public class Main {
public static void main(String[] args) {
	
	
	String path = System.getProperty("java.io.tmpdir");
	File file = new File(path);
	Scanner sc = new Scanner(System.in);
	FileStorage b = new FileStorage();
	
	File[] files = file.listFiles(File::isFile);
	File[] archives = file.listFiles(File::isDirectory);
	
	
	System.out.println("-------------------------------------------------");
    System.out.println("\r\n"
    		+ "██╗░░██╗███╗░░██╗██████╗░\r\n"
    		+ "██║░██╔╝████╗░██║██╔══██╗\r\n"
    		+ "█████═╝░██╔██╗██║██║░░██║\r\n"
    		+ "██╔═██╗░██║╚████║██║░░██║\r\n"
    		+ "██║░╚██╗██║░╚███║██████╔╝\r\n"
    		+ "╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░");
	System.out.println("-------------------------------------------------");
	System.out.println("Por favor feche todos os programas em aberto.");
	System.out.print("Deseja deletar todos os arquivos e pastas temporarias? (y/n): ");
	char result = sc.nextLine().charAt(0);
	switch(result){
	case 'y':
		for(File p : archives) {
			deleteFiles(p,b);
		}
		
		for(File f : files) {

			if(f.delete()) {
				b.addBytes(f.length());
				System.out.println("Apagado: " + f.getName());
			}else {
				System.out.println("Não foi possivel apagar: " + f.getName());
			}
		}
		
		System.out.println("Finalizado.");
		
		break;
	case 'n':
		System.out.println("Encerrando...");
		System.exit(0);
		break;
	default:
		System.out.println("Operação errada, encerrando...");
		System.exit(-1);
		break;
	}
	System.out.println("-------------------------------------------------");
	freeSpace(b);
	System.out.println("Digite qualquer tecla para encerrar...");
	String parar = sc.nextLine();
	System.out.println("-------------------------------------------------");
}

	public static void deleteFiles(File fl,FileStorage b) {
		File file = new File(fl.getPath());
		File[] files = file.listFiles(File::isFile);
		File[] arqs = file.listFiles(File::isDirectory);
		
		for(File f : files) {
			if(f.delete()) {
				b.addBytes(f.length());
				System.out.println("Apagado: " + f.getName());
			}else {
				System.out.println("Não foi possivel apagar: " + f.getName());
			}
		}
		
		for(File a : arqs) {
			System.out.println(a.getName());
			deleteFiles(a,b);
		}
	}
	
	public static void freeSpace(FileStorage b) {
		
		double bytes = b.getBytes();
		double result = 0;
		String unidade = "";
		
		if (bytes < 1024) {
			result = bytes;
	        unidade = "B";
	    } else if (bytes < Math.pow(1024, 2)) {
			result = bytes / 1024;
	        unidade = "KB"; 
	    } else if (bytes < Math.pow(1024, 3)) {
			result = bytes / Math.pow(1024, 2);
	        unidade = "MB";
	    } else {
	    	result = bytes / Math.pow(1024, 3);
	        unidade = "GB";
	    }
		
		System.out.println("Espaço liberado: " + String.format("%.2f", result) + " " + unidade);

		
		
	}
}
