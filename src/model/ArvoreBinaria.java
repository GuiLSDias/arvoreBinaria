package model;

public class ArvoreBinaria<T extends Comparable<T>> {

    private BinNo<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(T valor) {
        BinNo<T> novo = new BinNo<>(valor);
        raiz = inserir(raiz, novo);
    }

    private BinNo<T> inserir(BinNo<T> atual, BinNo<T> novo) {
        if (atual == null){
            return novo;
        }else if (novo.getConteudo().compareTo(atual.getConteudo()) < 0){
            atual.setNoEsq(inserir(atual.getNoEsq(), novo));
        }else{
            atual.setNoDir(inserir(atual.getNoDir(), novo));
        }
        return atual;
    }

    public void exibirInOrdem(){
        System.out.println("\nExibindo em ordem");
        exibirInOrdem(this.raiz);
    }

    private void exibirInOrdem(BinNo<T> atual) {
        if (atual != null){
            exibirInOrdem(atual.getNoEsq());
            System.out.println(atual.getConteudo() + ", ");
            exibirInOrdem(atual.getNoDir());
        }
    }

    public void exibirPosOrdem(){
        System.out.println("\nExibindo em pos-ordem");
        exibirPosOrdem(this.raiz);
    }

    private void exibirPosOrdem(BinNo<T> atual) {
        if (atual != null){
            exibirPosOrdem(atual.getNoEsq());
            exibirPosOrdem(atual.getNoDir());
            System.out.println(atual.getConteudo() + ", ");

        }
    }

    public void exibirPreOrdem(){
        System.out.println("\nExibindo em pre-ordem");
        exibirPreOrdem(this.raiz);
    }

    private void exibirPreOrdem(BinNo<T> atual) {
        if (atual != null){
            System.out.println(atual.getConteudo() + ", ");
            exibirPreOrdem(atual.getNoEsq());
            exibirPreOrdem(atual.getNoDir());
        }
    }

    public void remover(T valor){
        try{
            BinNo<T> atual = this.raiz;
            BinNo<T> pai = null;
            BinNo<T> filho = null;
            BinNo<T> temp = null;

            while(atual != null && !atual.getConteudo().equals(valor)){
                pai = atual;
                if(valor.compareTo(atual.getConteudo()) < 0){
                    atual = atual.getNoEsq();
                }else{
                    atual = atual.getNoDir();
                }
            }

            if(atual == null){
                System.out.println("Conteudo nao encontrado");
            }

            if(pai == null){
                if(atual.getNoDir() == null){
                    this.raiz = atual.getNoEsq();
                }else if(atual.getNoEsq() == null){
                    this.raiz = atual.getNoDir();
                }else{
                    for(temp = atual, filho = atual.getNoEsq();
                    filho.getNoDir() != null;
                    temp = filho, filho = filho.getNoDir()){
                        if(filho != atual.getNoEsq()){
                            temp.setNoEsq(filho.getNoEsq());
                            filho.setNoEsq(raiz.getNoEsq());
                        }
                    }
                    filho.setNoDir(raiz.getNoDir());
                    raiz = filho;
                }
            }else if(atual.getNoDir() == null){
                if(pai.getNoEsq() == atual){
                    pai.setNoEsq(atual.getNoEsq());
                }else{
                    pai.setNoDir(atual.getNoEsq());
                }
            }else if(atual.getNoEsq() == null){
                if(pai.getNoEsq() == atual){
                    pai.setNoEsq(atual.getNoDir());
                }else{
                    pai.setNoDir(atual.getNoDir());
                }
            }else{
                for(temp = atual, filho = atual.getNoEsq();
                filho.getNoDir() != null;
                temp = filho, filho = filho.getNoDir()){
                    if(filho != atual.getNoEsq()){
                        temp.setNoEsq(filho.getNoEsq());
                        filho.setNoEsq(atual.getNoEsq());
                    }
                    filho.setNoDir(atual.getNoDir());
                    if(pai.getNoEsq() == atual){
                        pai.setNoEsq(filho);
                    }else{
                        pai.setNoDir(filho);
                    }
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Conteudo nao encontrado");
        }
    }

}
