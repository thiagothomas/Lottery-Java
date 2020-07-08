import java.lang.Math;

public class Pessoa {

    private String nome;
    private TeleSena[] teleSenas;
    private int quantJogos;
    private double valorGasto;
    private double premiacao;

    public Pessoa(String nome, int quantJogos) {
        this.nome = nome;
        this.quantJogos = quantJogos;
        this.teleSenas = new TeleSena[quantJogos];
        this.valorGasto = 0;

        // cria os jogos na construcao da pessoa e atualiza o valor gasto por ela
        jogosTeleSena();
        atualizaValorGasto();
    }

    public String getNome() {
        return this.nome;
    }

    public TeleSena[] getTeleSenas() {
        return this.teleSenas;
    }

    public double getValorGasto() {
        return this.valorGasto;
    }

    public int getQuantJogos() {
        return this.quantJogos;
    }

    public void setPremiacao(double premio) {
        this.premiacao = premio;
    }


    /**
     * sortear os 2 conjuntos de cada jogo que uma pessoa comprar
     */
    public void jogosTeleSena() {
        for (int i = 0; i < this.quantJogos; i++) {
            int[] c1 = new int[61];
            int[] c2 = new int[61];

            // laco para sortear apenas 25 numeros de cada conjunto
            for (int j = 0; j < 25; j++) {
                int aux;

                // vai sortear uma posicao para colocar o valor de 1. Enquanto posicao sorteada ja estiver ocupada com 1,
                // sorteia novamente
                do {
                    aux = (int) (Math.random() * 60 + 1);
                } while (c1[aux] == 1);

                c1[aux] = 1;

                do {
                    aux = (int) (Math.random() * 60 + 1);
                } while(c2[aux] == 1);

                c2[aux] = 1;
            }
            this.teleSenas[i] = new TeleSena(c1, c2);
        }
    }

    public void atualizaValorGasto() {
        this.valorGasto = this.quantJogos * (TeleSena.valorVenda);
    }

}