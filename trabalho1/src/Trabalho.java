import java.io.*;
import java.util.ArrayList;

public class Trabalho {

    static final String DELIMITADOR_ARQUIVOS = "#"; // Delimitador entre arquivos
    static final String DELIMITADOR_NOME_ARQUIVO = "|"; // Delimitador do conteudo do arquivo com o nome dele.
    static final String DELIMITADOR_DIRETORIOS = "&"; // Delimitador para saber quando comeca outro diretorio.

//    static final File arquivo_final = new File("DIRETORIO/");
//    static final String diretorio ="DIRETORIO/";
//    static final String file_name = "arquivo-0.txt";



    public static void main (String[] args){
        //String workingDir = System.getProperty("user.id");
        if (args[0].equals("-c")){
            compacta_diretorio(args[1]);
            System.out.println("0: Execucao bem sucedida");
        }
        else if(args[0].equals("-e")){
            descompactar(args[1]);
            System.out.println("0: Execucao bem sucedida");
        }
        else if(args[0].equals("-l"))
        {
            Listagem(args[1]);
            System.out.println("0: Execucao bem sucedida");
        }

//        compacta_diretorio(diretorio);
//        descompactar("C:\\Users\\User\\Documents\\GitHub\\ufscar_ORI\\novo_arquivo.sar");

    }

    public static void Listagem(String pasta) {
        if (!isDirectory(pasta)) {
            throw new IllegalArgumentException("pasta não é um Diretorio");
        }
        int count= 0;
        StringBuilder pt= new StringBuilder();
        listagem(pasta, count, pt);

        System.out.println(pt.toString());
    }

    private static void listagem(String pasta, int count, StringBuilder pt) {
        if (!isDirectory(pasta)) {
            throw new IllegalArgumentException("Pasta nao é um diretorio");
        }
        File folder = new File(pasta);
        pt.append(getCountString(count));
        pt.append("+--");
        pt.append(folder.getName());
        pt.append("/");
        pt.append("\n");
        File[] arquivo = folder.listFiles();
        for (int i = 0; i<arquivo.length;i++) {
            if (arquivo[i].isDirectory()) {
                listagem(arquivo[i].getPath(), count + 1, pt);
            } else {
                printArquivo(arquivo[i], count + 1, pt);
            }
        }

    }

    private static void printArquivo(File arquivo, int count, StringBuilder pt) {
        pt.append(getCountString(count));
        pt.append("+--");
        pt.append(arquivo.getName());
        pt.append("\n");
    }

    private static String getCountString(int count) {
        StringBuilder pt = new StringBuilder();
        for (int i = 0; i < count; i++) {
            pt.append("|  ");
        }
        return pt.toString();
    }

    public static void compacta_diretorio(String diretorioPath){

        if(!isDirectory(diretorioPath)){
            System.out.println("ERRO: CAMINHO INFORMADO NAO E UM DIRETORIO VALIDO!!");
        }else{
            File diretorio = new File(diretorioPath);
            File[] listaArquivos = diretorio.listFiles();//Uma lista de tudo dentro do diretorio
            File[] listaArquivos_subdiretorio = new File[0];
            File sub_diretorio;

            ArrayList<String> arquivos = new ArrayList<String>();
            ArrayList<File[]> subdiretorios = new ArrayList<File[]>();

            String strNovoArquivo="";

            // Verifica e retorna os arquivos e diretorios do arquivo principal
            for(int i = 0;i<listaArquivos.length;i++){
                if(listaArquivos[i].isFile()){
                    arquivos.add(listaArquivos[i].getName());
                }else if (listaArquivos[i].isDirectory()){//caso de subdiretorios
                    sub_diretorio = new File(listaArquivos[i].getPath()+"\\");
                    listaArquivos_subdiretorio = sub_diretorio.listFiles();//Uma lista de tudo dentro do sub_diretorio
                    subdiretorios.add(listaArquivos_subdiretorio);
                }
            }
//            int j = 0;
//            listaArquivos = null;
//            while(j == 0){
//                int aux = listaArquivos_subdiretorio.length;
//                for (int i = 0; i < aux; i++) {
//                    if(listaArquivos_subdiretorio[i].isFile()){
//                        arquivos.add(listaArquivos[i].getName());
//                    }
//                    else if(listaArquivos_subdiretorio[i].isDirectory()){
//                        sub_diretorio = new File(listaArquivos[i].getPath() + "\\");
//                        listaArquivos_subdiretorio = sub_diretorio.listFiles();
//                        subdiretorios.add(listaArquivos_subdiretorio);
//                    }
//                }
//
//            }

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
            strNovoArquivo+=DELIMITADOR_DIRETORIOS + listaArquivos[0].getParent();
        }else{
            Exception e_1 = new Exception("erro na lista de arquivos");
        }

        for(int i = 0; i<listaArquivos.length ;i++){
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
            file = new File("COMPACTADO.sar");

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

        //System.out.println(strNovoArquivo);

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
            strContent.append(DELIMITADOR_NOME_ARQUIVO + nomeArquivo);

            fInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(strContent);

        return strContent;
    }


    public static void descompactar(String arquivoSAR){
        String arquivo = "";
        String path_arquivo = "";
        String nome_diretorio = "";
        InputStream input;
        try{
            input = new FileInputStream(arquivoSAR);
            int data = input.read();

            while (data != -1) {

                if((char) data != '|' && (char)data != '&'){ // Leio o arquivo inteiro ate chegar no delimitador |
                    //significa que os proximos caracteres serão o nome do arquivo
                    arquivo += (char) data;
                }
                else if((char)data == '&'){
                    nome_diretorio = "";
                    data = input.read();
                    while((char) data != '#' && data != -1){
                        nome_diretorio += (char) data; // Aqui o nome do diretorio é separado e colocado na variavel nome_diretorio
                        data = input.read();// O delimitador final do nome do diretorio é #, que é o inicio de um arquivo
                        // ou EOF que é o final do arquivo.
                    }
                    Cria_Diretorio(nome_diretorio);
                }
                else{ // Significa que chegou na parte final do arquivo, onde se encontra o nome dele.
                    data = input.read();
                    while((char) data != '#' && data != -1 && (char)data != '&' ){
                        path_arquivo += (char) data; // Aqui o nome do arquivo é separado e colocado na variavel path_arquivo
                        data = input.read();// O delimitador final do arquivo pode ser os caracteres #, & ou o final do arquivo EOF.
                    }
                    Cria_arquivo(arquivo, nome_diretorio + "\\" + path_arquivo);
                    path_arquivo = ""; // é necessario apagar o conteudo de path_arquivo, pois esta variavel
                    // ira armazenar o nome do proximo arquivo que vai ser criado.

                    arquivo = ""; // é necessario apagar o conteudo de arquivo, pois esta variavel ira armazenar o conteudo
                    //do proximo arquivo que vai ser criado
                }
                if((char)data != '&')
                    data = input.read();

            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void Cria_arquivo(String text, String nomeArquivo){

        try {
            FileWriter arquivo;
            arquivo = new FileWriter(new File(nomeArquivo)); // Crio o arquivo com o nome de nomeArquivo.
            arquivo.write(text); // Aqui é o conteudo do arquivo. O nome e o conteudo do arquivo foram passador por parametros.
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Cria_Diretorio(String nomeDiretorio){
        try {
            File diretorio = new File(nomeDiretorio);
            diretorio.mkdir();
        }catch (Exception ex){
            System.out.println("Erro ao criar o diretorio");
        }
    }


}
