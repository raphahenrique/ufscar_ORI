#include <stdio.h>
#include <stdlib.h>
#define MAX 1000

typedef struct No No;
int grau_arvore, count = 0;
char caminho[100];

struct No
{
    int chave[MAX-1];
    int id;
    int num_chaves, folha, raiz;
    No *ponteiro[MAX], *pai;
};

int mediana (No *atual){
    int aux = atual->num_chaves / 2 + 1;
    return aux;

}

int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}



int Verifica_num_chaves(No *atual){
    if((atual->raiz == 1 && atual->num_chaves < (grau_arvore - 1)) || (atual->num_chaves >= (grau_arvore - 1) && atual->num_chaves < (2 * grau_arvore - 1))  )
        return 1;
    else if(atual->num_chaves == (2* grau_arvore - 1))
        return 0;
}

void Divide_No(No *atual){
    int aux = mediana(atual) - 1;
    No filhodir, filhoesq;
    int i;
    int j = 0;
    filhoesq.num_chaves = 0;
    filhodir.num_chaves = 0;
    for(i=0; i< aux; i++){
        filhoesq.chave[i] = atual->chave[i];
        filhoesq.num_chaves++;
    }
    for(i=aux+1; i< atual->num_chaves; i++){
        filhodir.chave[j] = atual->chave[i];
        filhodir.num_chaves++;
        j++;
    }
    filhodir.folha = 1;
    filhoesq.folha = 1;
    atual->ponteiro[0] = &filhoesq;
    atual->ponteiro[1] = &filhodir;
    filhoesq.pai = &atual;
    filhodir.pai = &atual;
    atual->chave[0] = atual->chave[aux];
    atual->num_chaves = 1;
}

void Divide_no_filho(No *atual){
    int aux = mediana(atual) - 1;
    int i;
    int j;
    int k = 0;
    atual->pai->chave[atual->pai->num_chaves] = atual->chave[aux];
    atual->pai->num_chaves++;
    qsort(atual->pai->chave, atual->pai->num_chaves, sizeof(int), cmpfunc);
    for(i=0;i< atual->pai->num_chaves ;i++){
        if(atual->pai->chave[i] == atual->chave[aux])
            j = i; // j é a posicao que a chave que foi para o pai se encontra.
    }
    No novo;
    novo.num_chaves = 0;
    int cont = atual->num_chaves;
    for(i=aux+1;i< cont;i++){
        novo.chave[k] = atual->chave[i];
        novo.num_chaves++;
        atual->num_chaves--;
        k++;
    }
    atual->num_chaves--;
    novo.pai = atual->pai;
    novo.folha = 1;
    printf("%d ", atual->pai->num_chaves);
    for(i=atual->pai->num_chaves; i>j+1; i-- ){
        atual->pai->ponteiro[i] = atual->pai->ponteiro[i-1];// Ponteiro
    }
    atual->pai->ponteiro[i] = &novo;


}

void Inserindo_chave(No *atual, int key){
    int aux;
    aux = Verifica_num_chaves(atual);
    int i = 0;

    if(aux == 1 && atual->folha == 1){
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        qsort(atual->chave, atual->num_chaves, sizeof(int), cmpfunc);
    }
    else if(aux == 1 && atual->folha == 0){
            for(i=0; i<atual->num_chaves ;i++){
                if(key < atual->chave[i]){
                    Inserindo_chave(atual->ponteiro[i], key);
                    break;}
                else if(i == atual->num_chaves - 1 && key > atual->chave[i])
                    Inserindo_chave(atual->ponteiro[i+1], key);
            }
    }
    else if(aux == 0 && atual->raiz == 1){
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        qsort(atual->chave, atual->num_chaves, sizeof(int), cmpfunc);
        Divide_No(atual);
        atual->folha = 0;
    }
    else if(aux == 0 && atual->folha == 1){
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        qsort(atual->chave, atual->num_chaves, sizeof(int), cmpfunc);
        Divide_no_filho(atual);

    }
}

int Busca_chave(No atual, int key){
    int i = 0;
    No *pont;
    do{
            if(key == atual.chave[i]){
                printf("Foi encontrada no indice: %d", i);
                while(pont !=NULL){
                    pont = atual.pai;
                    count++;
                }
                return key;
            }
            else if(key < atual.chave[i] && atual.folha == 0){
                    sprintf(caminho, "%d", i);
                    count++;
                    return Busca_chave(*atual.ponteiro[i], key);
            }

            else if(i == atual.num_chaves-1 && key > atual.chave[i] && atual.folha == 0)
                return Busca_chave(*atual.ponteiro[i+1], key);
            i++;

    } while (i < atual.num_chaves );
    printf("Busca sem sucesso");
    return 0;
}

void Criar_arvore(No *atual){

    atual->num_chaves = 0;
    atual->raiz = 1;
    atual->folha = 1;
    atual->pai = NULL;

}

int main(int argc, char *argv[])
{
    No T;
    if (argc==3)
    {
        Criar_arvore(&T);
        printf("Arvore criada com sucesso.");
    }

    grau_arvore = atoi(argv[2]);

    No filho1, filho2, filho3, filho4, filho5;

    int key;

    /*T.chave[0] = 10;
    T.chave[1] = 15;
    T.chave[2] = 20;
    T.chave[3] = 30;
    T.ponteiro[0] = &filho1;
    T.ponteiro[1] = &filho2;
    T.ponteiro[2] = &filho3;
    T.ponteiro[3] = &filho4;
    T.ponteiro[4] = &filho5;

    filho1.chave[0] = 5;
    filho1.chave[1] = 6;
    filho1.chave[2] = 7;
    filho1.chave[3] = 8;
    filho1.chave[4] = 9;

    filho2.chave[0] = 11;
    filho2.chave[1] = 12;

    filho3.chave[0] = 16;
    filho3.chave[1] = 17;

    filho4.chave[0] = 21;
    filho4.chave[1] = 22;
    filho4.chave[2] = 23;
    filho4.chave[3] = 24;
    filho4.chave[4] = 28;

    filho5.chave[0] = 31;
    filho5.chave[1] = 32;

    filho1.folha = 1;
    filho2.folha = 1;
    filho3.folha = 1;
    filho4.folha = 1;
    filho5.folha = 1;

    filho1.num_chaves = 5;
    filho2.num_chaves = 2;
    filho3.num_chaves = 2;
    filho4.num_chaves = 5;
    filho5.num_chaves = 2;

    filho1.pai = &T;
    filho2.pai = &T;
    filho3.pai = &T;
    filho4.pai = &T;
    filho5.pai = &T;

    T.num_chaves = 4;
    T.folha = 0;*/

     int control= 0;
        while(control!=3)
        {
          printf("Escolha uma operacao abaixo:\n");
          printf("1 - Insercao\n 2 - Busca\n 3 - Sair\n");
          scanf("%d", &control);
          switch (control)
          {
              case 1:
              printf("entrando na funcao de insercao\n");
              system("cls");
              printf("Informe a chave: ");
              scanf("%d", &key);
              Inserindo_chave(&T, key);
              break;
              case 2:
              printf("entrando na funcao de busca\n");
              system("cls");
              printf("Informe a chave: ");
              scanf("%d", &key);
              int aux = Busca_chave(T, key);
              break;
              case 3:
              break;
          }
        }

 //   printf("Informe a chave: ");
 //   scanf("%d", &key);

 //   busca = Busca_chave(T, key);*/

    int aux;
    int i, j;

    /*do{

        printf("Informe a chave: ");
        scanf("%d", &key);

        Inserindo_chave(&T, key);

        printf("Deseja insirir outra chave:");
        scanf("%d", &aux);

    }while(aux == 0);*/

    /*for(i = 0; i<T.num_chaves; i++){
            printf("%10d ", T.chave[i]);
        }
        printf("\n");
        for(i = 0; i<T.num_chaves+1; i++){
            for(j = 0; j< T.ponteiro[i]->num_chaves; j++){
                printf("%d ", T.ponteiro[i]->chave[j]);
            }
            printf("\n");
        }*/

    return 0;
}

