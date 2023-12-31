import java.util.Random;

public class TabelaHash {
    private int tamanho;
    private Registro[] tabela;
    private int numComparacoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
        this.numComparacoes = 0;
    }

    public void inserir(Registro registro) {
        int posicao = calcularPosicao(registro.getCodigo());
        if (tabela[posicao] == null) {
            tabela[posicao] = registro;
        } else {
            Registro atual = tabela[posicao];
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(registro);
        }
    }

    public Registro buscar(int codigo) {
        int posicao = calcularPosicao(codigo);
        Registro atual = tabela[posicao];
        numComparacoes = 0;
        while (atual != null) {
            numComparacoes++;
            if (atual.getCodigo() == codigo) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    private int calcularPosicao(int codigo) {
        // Função hash de resto da divisão
         return codigo % tamanho;

        // Função hash de multiplicação
        //double constante = 0.6180339887; // Constante recomendada para multiplicação
        //double valorHash = codigo * constante;
        //return (int) (tamanho * (valorHash - Math.floor(valorHash)));

        // Função hash de dobramento
        // int soma = 0;
        // while (codigo > 0) {
        //     soma += codigo % 100; // Soma os dígitos do código em grupos de 2 dígitos
        //     codigo /= 100;
        // }
        // return soma % tamanho;
    }

    public int getNumComparacoes() {
        return numComparacoes;
    }
}