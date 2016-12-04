import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;

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

            ArrayList<String> arquivos = new ArrayList<String>();
            ArrayList<File[]> subdiretorios = new ArrayList<File[]>();

            String strNovoArquivo="";


            for(int i = 0;i<listaArquivos.length;i++){
                if(listaArquivos[i].isFile()){
                    arquivos.add(listaArquivos[i].getName());
                }else if (listaArquivos[i].isDirectory()){//caso de subdiretorios
                    File sub_diretorio = new File(listaArquivos[i].getPath()+"\\");
                    File[] listaArquivos_subdiretorio = sub_diretorio.listFiles();//Uma lista de tudo dentro do diretorio
                    subdiretorios.add(listaArquivos_subdiretorio);
                }
            }

            if(arquivos.isEmpty()){
                System.out.println("ERRO: NENHUM ARQUIVO ENCONTRADO no diretorio :" + listaArquivos[0].getParent());
            }else{
                System.out.println("Quantidade de arquivos: " + arquivos.size());
                strNovoArquivo += compacta_arquivos(listaArquivos);
            }

            if(!subdiretorios.isEmpty()){
                System.out.println("Quantidade de subdiretorios: " + subdiretorios.size());
                for(int i = 0; i<=subdiretorios.size()-1;i++){
                   strNovoArquivo += compacta_arquivos(subdiretorios.get(i));
                }

            }

            gera_novo_arquivo(strNovoArquivo);

        }
    }


    public static String compacta_arquivos(File[] listaArquivos){
        int ch;
        String strNovoArquivo = "";
        File file = null;
        FileOutputStream foutput = null;

        /*
        SALVAR NOME DO DIRETORIO PARA RECUPERAR DEPOIS
        ALGO COMO:
            &&diretorioName&&arquivo1#$#arquivo2#$#arquivo#$#&&diretorio2Name&&arquivo1#$#arquivo2#$#arquivo#$#

         */
        if(listaArquivos[0]!=null){
            strNovoArquivo+=DELIMITADOR_DIRETORIOS + listaArquivos[0].getParent()
                        + DELIMITADOR_DIRETORIOS;
        }else{
            Exception e_1 = new Exception("erro na lista de arquivos");
        }

        for(int i = 0; i<listaArquivos.length;i++){
            if(listaArquivos[i].isFile()){

                strNovoArquivo += DELIMITADOR_ARQUIVOS + le_arquivo(listaArquivos[i].getPath()+"\\"
                        ,listaArquivos[i].getName());

            }
        }

        return strNovoArquivo;

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


    public static StringBuffer le_arquivo(String diretorio, String nomeArquivo){
        int ch;
        StringBuffer strContent = new StringBuffer("");
        File file = null;
        FileInputStream fInput = null;
        System.out.println(diretorio);
        System.out.println(nomeArquivo);

        System.out.println("");

        try {
            file = new File(diretorio);
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
