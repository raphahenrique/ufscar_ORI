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

int Busca_chave(No *atual, int key){
    int i = -1;

    do{
            if(key == atual->chave[i]){
                printf("Foi encontrada no indice: %d", i);
                return key;
            }
            if(atual->folha == 0){
                if(key < atual->chave[i] && atual->folha == 0)
                        return Busca_chave(&atual->ponteiro[i], key);
                else if(i == atual->num_chaves && key > atual->chave[i])
                        return Busca_chave(&atual->ponteiro[i+1], key);
                    i++;
            }
    } while (i == atual->num_chaves + 1);
    printf("Busca sem sucesso");
    return 0;
}

void Criar_arvore(No *atual){
    atual->ponteiro[0] = NULL;
    atual->num_chaves = 0;
    atual->raiz = 1;
    atual->folha = 1;
}


int main()
{
    No *T;
    T = malloc(MAX * sizeof(No));
    Criar_arvore(&T);
    int key;
    printf("Informe a chave: ");
    scanf("%d", &key);
    Busca_chave(&T, key);

    printf("Digite o grau da Arvore B: ");
    scanf("%d", &grau_arvore);

    mediana = grau_arvore/2;
    printf("%d", mediana);
    /*do{
        switch(opcao){

        case 1:
            Inserindo_chave(&raiz);
            break;
        case 2:
            Buscando_chave(&raiz);
            break;
        case 3:
            printf("Saindo...");
            break;
        }
    }while opcao != 3;*/

    return 0;
}
