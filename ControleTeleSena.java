import java.lang.Math;

public class ControleTeleSena {

	private Pessoa[] participantes;
	private Pessoa[] vencedores;
	private int[] teleSenaSorteada;
	private int quantTeleSenasRestantes;
	private int numVencedores;

	public ControleTeleSena() {
		String[] nomes = {
			"Thiago", "Frederico", "Ayanna", "Anderson", "Planadiscleuson", "Claudio", "Eliverton", "Asdrugbal",
			"Michael", "Johnson", "Ismael", "Gabriel", "Terezinha", "Juscelina", "Grizelda", "Josuela",
			"Leonardo", "Helena", "Fernandes", "Magda"
		};

		this.quantTeleSenasRestantes = 300;
		this.numVencedores = 0;
		this.participantes = new Pessoa[20];
		this.vencedores = new Pessoa[20];
		this.teleSenaSorteada = new int[61];

		for(int i = 0; i < this.participantes.length; i++) {
			// gerando numero de jogos aleatorios para cada pessoa com os nomes da array acima
			this.participantes[i] = new Pessoa(nomes[i], ((int) (Math.random() * 15 + 1)));
		}
	}

	// mesmo raciocinio de Pessoa.jogosTeleSena()
	public void sorteioTeleSena() {
		int[] tele = new int[61];

		for (int j = 0; j < 25; j++) {
			int aux;

			do {
				aux = (int) (Math.random() * 60 + 1);
			} while (tele[aux] == 1);

			tele[aux] = 1;
		}
		this.teleSenaSorteada = tele;
	}

	public boolean verificaVencedor() {

		// percorrendo os participantes
		for (Pessoa participante : this.participantes) {

			// percorrerendo os jogos dos participantes
			for (int j = 0; j < participante.getQuantJogos(); j++) {
				int cont1 = 0;
				int cont2 = 0;

				// percorrendo os numeros de cada conjunto
				for (int k = 0; k < 61; k++) {
					// se a posicao do conjunto e da tele sena sorteada forem 1, soma +1 na quantidade de numeros iguais
					if (participante.getTeleSenas()[j].getConjunto1()[k] == 1 && this.teleSenaSorteada[k] == 1) {
						cont1++;
					}
					if (participante.getTeleSenas()[j].getConjunto2()[k] == 1 && this.teleSenaSorteada[k] == 1) {
						cont2++;
					}
				}
				/* Se um dos conjuntos tiver 25 posicoes iguais ao da tele sena, a pessoa é adicionada ao array
				 de vencedores. Logo, parte para o proximo participante do array. */
				if (cont1 == 25 || cont2 == 25) {
					this.vencedores[this.numVencedores++] = participante;
					break;
				}
			}
		}
		return this.numVencedores > 0;
	}

	public void sorteioNovoNumero() {
		int aux;

		do {
			aux = (int) (Math.random() * 60 + 1);
		} while (this.teleSenaSorteada[aux] == 1);

		this.teleSenaSorteada[aux] = 1;
	}

	public void atualizaQuantRestante() {
		for (int i = 0; i < 20; i++) {
			this.quantTeleSenasRestantes -= this.participantes[i].getQuantJogos();
		}
	}

	public double calculaValorTotal() {
		double valorTotal = 0;

		for (Pessoa participante : this.participantes) {
			valorTotal += participante.getValorGasto();
		}
		return valorTotal;
	}

	public double valorPremiacao() {
		// premio e 80% do valor total dividido igualmente entre os vencedores
		double premio = (calculaValorTotal() * 4/5) / this.numVencedores;

		for (Pessoa vencedor : this.vencedores) {
			if (vencedor != null) {
				vencedor.setPremiacao(premio);
			}
		}
		return premio;
	}

	public void pausa(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (Exception ignored) {}
	}

	public void imprimeInformacoesTeleSena() {

		pausa(1500);
		System.out.print("Números sorteados: ");
		for (int i = 0; i < 61; i++) {
			if (this.teleSenaSorteada[i] == 1) {
				System.out.print(i + " ");
				pausa(200);
			}
		}

		System.out.println("\n\n+------------------- " +
				"Relatório Tele Sena "
				+ "-------------------+");

		pausa(1000);
		System.out.printf("| %-38s\t%d\t\t\t%5s", ("Total de Tele Senas vendidas: "), (300 - this.quantTeleSenasRestantes), "|");
		pausa(1000);
		System.out.printf("\n| %-38s\t%d\t\t\t%5s", "Quantidade de ganhadores: ", this.numVencedores, "|");
		pausa(1000);
		System.out.printf("\n| %-38s\t", "Nome(s) do(s) Ganhador(es): ");
		pausa(500);

		int virg = 0;
		for (Pessoa vencedor : this.vencedores) {
			if (vencedor != null) {
				if (virg > 0) {
					System.out.printf("\n|%43s%-16s%1s", " ",(vencedor.getNome()), "|");
				}
				else {
					System.out.printf("%-16s%1s", (vencedor.getNome()), "|");
				}
				pausa(250);
				virg++;
			}
		}
		pausa(1000);
		System.out.printf("\n| %-38s\t%s%.2f\t%5s", "Prêmio do(s) sortudo(s):", "R$ ", this.valorPremiacao(), "|");
		pausa(1000);
		System.out.printf("\n| %-38s\t%s%.2f\t%5s", "Valor total das Tele Senas vendidas:", "R$ ",this.calculaValorTotal(), "|");
		pausa(1000);
		System.out.printf("\n| %-38s\t%s%.2f\t%5s", "Lucro obtido por Silvio Santos:","R$ ", ((this.calculaValorTotal()) * 1/5), "|");
		System.out.println("\n+-----------------------------------------------------------+");
	}

}