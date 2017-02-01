#include <stdio.h>
#include <stdlib.h>
#define MAX 1000

typedef struct No No;
int grau_arvore;
int mediana;

struct No
{
    int chave[MAX-1];
    int id;
    int num_chaves, folha, raiz;
    No *ponteiro[MAX];
};

int Verifica_num_chaves(No atual){
    if(atual.raiz == 1 || atual.num_chaves >= (grau_arvore - 1) && atual.num_chaves <= (2 * grau_arvore - 1)  )
        return 1;
    else if(atual.num_chaves >= grau_arvore)
        Divide_No(&atual);
        return 0;
}

void Divide_No(No *atual){



}

void Inserindo_chave(No *atual){
    int aux = Verifica_num_chaves(*atual);
    int key;
    printf("Insira a chave: ");
    scanf("%d", &key);
    if(aux == 1){
        atual->chave[atual->num_chaves] = key;
        atual->num_chaves++;
        printf("Num chaves: %d", atual->num_chaves);
    }
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

            else if(i == atual.num_chaves && key > atual.chave[i] && atual.folha == 0)
                return Busca_chave(*atual.ponteiro[i+1], key);
            i++;

    } while (i < atual.num_chaves + 1);
    printf("Busca sem sucesso");
    return 0;
}

void Criar_arvore(No *atual){

    atual->num_chaves = 0;
    atual->raiz = 1;
    atual->folha = 0;

}


int main()
{
    No T;
    No filho1, filho2, filho3, filho4, filho5;

    Criar_arvore(&T);
    int key;
    T.chave[0] = 10;
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

    printf("Digite o grau da Arvore B: ");
    scanf("%d", &grau_arvore);

    printf("Informe a chave: ");
    scanf("%d", &key);
    T.num_chaves = 3;
    int aux = Busca_chave(T, key);

    return 0;
}
