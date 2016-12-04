import javax.print.DocFlavor;
import java.io.*;

public class Main {

    static final String DELIMITADOR_ARQUIVOS = "#$#";
    static final String DELIMITADOR_DIRETORIOS = "&&";

    static final File arquivo_final = new File("DIRETORIO/");
    static final String diretorio ="DIRETORIO/";
    static final String file_name = "arquivo-0.txt";



    public static void main (String[] args){
        //String workingDir = System.getProperty("user.id");

        compacta_diretorio(diretorio);


    }


    public static void compacta_diretorio(String diretorioPath){

        if(!isDirectory(diretorioPath)){
            System.out.println("ERRO: CAMINHO INFORMADO NÃO É UM DIRETORIO VÁLIDO!!");
        }else{
            File diretorio = new File(diretorioPath);
            File[] listaArquivos = diretorio.listFiles();//Uma lista de tudo dentro do diretorio

            for(int i = 0;i<listaArquivos.length;i++){
                if(listaArquivos[i].isFile()){//COMPACTAR ARQUIVOS EM UM
                    compacta_arquivos(listaArquivos);


                }else if (listaArquivos[i].isDirectory()){//caso de subdiretorios


                }
            }
        }

    }

    public static void compacta_arquivos(File[] listaArquivos){
        int ch;
        String strNovoArquivo = "";
        File file = null;
        FileOutputStream foutput = null;

        /*
        SALVAR NOME DO DIRETORIO PARA RECUPERAR DEPOIS
        ALGO COMO ?? :
                    &&diretorioName&&arquivo1#$#arquivo2#$#arquivo#$#

         */
        if(listaArquivos[0]!=null){
            strNovoArquivo+=DELIMITADOR_DIRETORIOS + listaArquivos[0].getParent()
                        + DELIMITADOR_DIRETORIOS;
        }else{
            Exception e_1 = new Exception("erro na lista de arquivos");
        }

        for(int i = 0; i<listaArquivos.length;i++){

            strNovoArquivo += DELIMITADOR_ARQUIVOS + le_arquivo(listaArquivos[i].getName());

        }

        gera_novo_arquivo(strNovoArquivo);

    }

    //FUNCAO QUE CRIA UM NOVO ARQUIVO COM OS OUTROS COMPACTADOS
    public static void gera_novo_arquivo(String strNovoArquivo) {
        int ch;

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


    public static boolean isDirectory(String diretorio){
        File file=null;
        try{
            file = new File(diretorio);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (file.isDirectory()){
            return true;
        }else
            return false;
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


    public static void descompactar(String arquivoSAR){

    }


}
