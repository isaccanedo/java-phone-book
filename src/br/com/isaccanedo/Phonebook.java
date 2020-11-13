package br.com.isaccanedo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Isac Canedo
 */

public class Phonebook {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Properties props = new Properties();

        String name, number;

        File file = new File("src/isac.dat");

        FileInputStream fin = null;
        FileOutputStream fout;
        boolean changed = false;

        try {
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existe. Vamos criar um!");
        }

        if (fin != null) {
            try {
                props.load(fin);
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        do {
            System.out.println("Digite o novo nome ('quit' para parar):");
            name = br.readLine();
            if (name.equals("quit"))
                break;
            System.out.println("Digite o número:");
            number = br.readLine();
            props.setProperty(name, number);
            changed = true;
        } while (!"quit".equals(name));

        if (changed) {
            fout = new FileOutputStream(file);
            props.store(fout, "lista telefônica");
            fout.close();
        }
        System.out.println("******* Para pesquisar *********");
        System.out.println("Insira o nome para encontrar o número:");
        name = br.readLine();

        System.out.println("O número é: " + props.getProperty(name, "Não encontrado"));

    }

}
