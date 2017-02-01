#include <stdio.h>
#include <stdlib.h>
#define MAX 1000

typedef struct No No;
int grau_arvore;

struct No
{
    int chave[MAX-1];
    int id;
    int num_chaves, folha, raiz;
    No *ponteiro[MAX];
};

int mediana (No atual){
    int aux = atual.num_chaves / 2 + 1;
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
    int aux = mediana(*atual) - 1;
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
    atual->chave[0] = atual->chave[aux];
    atual->num_chaves = 1;
}

void Inserindo_chave(No *atual, int key){
    int aux;
    aux = Verifica_num_chaves(atual);
    int i = 0;

    if(aux == 1 && atual->folha == 1){
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        printf("Num chaves: %d \n", atual->num_chaves);
    }
    else if(aux == 1 && atual->folha == 0){
            for(i=0; i<atual->num_chaves ;i++){
                if(key < atual->chave[i])
                    Inserindo_chave(atual->ponteiro[i], key);
                else if(i == atual->num_chaves - 1 && key > atual->chave[i])
                    Inserindo_chave(atual->ponteiro[i+1], key);
            }
    }
    else{
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        qsort(atual->chave, atual->num_chaves, sizeof(int), cmpfunc);
        Divide_No(atual);
        atual->folha = 0;
    }
    qsort(atual->chave, atual->num_chaves, sizeof(int), cmpfunc);
}

int Busca_chave(No atual, int key){
    int i = 0;

    do{
            if(key == atual.chave[i]){
                printf("Foi encontrada no indice: %d", i);
                return key;
            }
            else if(key < atual.chave[i] && atual.folha == 0)
                return Busca_chave(*atual.ponteiro[i], key);

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

}

int main()
{
    No T;
    //No filho1, filho2, filho3, filho4, filho5;
    Criar_arvore(&T);
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
    filho2.chave[0] = 11;
    filho2.chave[1] = 12;
    filho2.chave[2] = 13;
    filho2.chave[3] = 14;
    filho3.chave[0] = 16;
    filho3.chave[1] = 17;
    filho3.chave[2] = 18;
    filho3.chave[3] = 19;
    filho4.chave[0] = 21;
    filho4.chave[1] = 22;
    filho4.chave[2] = 23;
    filho4.chave[3] = 24;
    filho5.chave[0] = 31;
    filho5.chave[1] = 32;
    filho5.chave[2] = 33;
    filho5.chave[3] = 40;

    filho1.folha = 1;
    filho2.folha = 1;
    filho3.folha = 1;
    filho4.folha = 1;
    filho5.folha = 1;

    filho1.num_chaves = 4;
    filho2.num_chaves = 4;
    filho3.num_chaves = 4;
    filho4.num_chaves = 4;
    filho5.num_chaves = 4;

    T.num_chaves = 4;

    printf("Informe a chave: ");
    scanf("%d", &key);

    busca = Busca_chave(T, key);*/

    printf("Digite o grau da Arvore B: ");
    scanf("%d", &grau_arvore);
    int busca = 0;
    int aux;
    int i, j;

    do{

        printf("Informe a chave: ");
        scanf("%d", &key);

        Inserindo_chave(&T, key);

        printf("Deseja insirir outra chave:");
        scanf("%d", &aux);

    }while(aux == 0);

    for(i = 0; i<T.num_chaves; i++){
            printf("%10d ", T.chave[i]);
        }
        printf("\n");
        for(i = 0; i<T.num_chaves+1; i++){
            for(j = 0; j< T.ponteiro[i]->num_chaves; j++){
                printf("%d ", T.ponteiro[i]->chave[j]);
            }
            printf("\n");
        }

    return 0;
}
