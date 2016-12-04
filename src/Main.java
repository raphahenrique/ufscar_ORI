import java.io.*;

public class Main {

    static final String DELIMITADOR = "#$#";

    static final File arquivo_final = new File("DIRETORIO/");
    static final String diretorio ="DIRETORIO/";
    static final String file_name = "arquivo-0.txt";



    public static void main (String[] args){
        //String workingDir = System.getProperty("user.id");

        escreve_arquivo();


    }


    public static StringBuffer le_arquivo(String nomeArquivo){
        int ch;
        StringBuffer strContent = new StringBuffer("");
        File file = null;
        FileInputStream fInput = null;
        try {
            file = new File(diretorio + nomeArquivo);
            fInput = new FileInputStream(file);

            while ((ch=fInput.read()) !=-1){
                strContent.append((char)ch);
            }

            fInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(strContent);

        return strContent;
    }


    public static void escreve_arquivo(){

        int ch;

        String strNovoArquivo = (   le_arquivo("arquivo-0.txt").toString() +
                DELIMITADOR +       le_arquivo("ARQUIVO-4.txt").toString());

        File file = null;
        FileOutputStream foutput = null;

        try {
            file = new File(diretorio + "novo_arquivo.sar");

            foutput = new FileOutputStream(file);

            if(!file.exists()){
                file.createNewFile();
            }

            byte[] strBytes = strNovoArquivo.getBytes();

            foutput.write(strBytes);
            foutput.flush();
            foutput.close();

            System.out.println("dpne");

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                if (foutput != null) {
                    foutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(strNovoArquivo);

    }


    public static void descompactar(String arquivoSAR){
        
    }


}
