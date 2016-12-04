import java.io.*;

public class Main {

    static final File arquivo_final = new File("DIRETORIO/");
    static final String diretorio ="DIRETORIO/";
    static final String file_name = "arquivo-0.txt";

    public static void main (String[] args){
        //String workingDir = System.getProperty("user.id");

        escreve_arquivo();
        le_arquivo();

    }


    public static void le_arquivo(){
        int ch;
        StringBuffer strContent = new StringBuffer("");
        try {
            File f1 = new File(diretorio + "novo_arquivo.sar");
            FileInputStream f1input = new FileInputStream(f1);

            while ((ch=f1input.read()) !=-1){
                strContent.append((char)ch);
            }

            f1input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(strContent);

    }


    public static void escreve_arquivo(){
        int ch;
        String strArquivo = "Novo arquivo\n" +
                "arquivo gerado\n\n" +
                "Novo";

        FileOutputStream foutput = null;

        try {
            File f1 = new File(diretorio + "novo_arquivo.sar");

            foutput = new FileOutputStream(f1);

            if(!f1.exists()){
                f1.createNewFile();
            }

            byte[] strBytes = strArquivo.getBytes();

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

        System.out.println(strArquivo);

    }

}
