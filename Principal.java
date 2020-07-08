public class Principal {

    public static void main(String[] args) {
        ControleTeleSena c = new ControleTeleSena();

        System.out.println("Sorteando...");
        c.sorteioTeleSena();

        //para sortear um novo numero enquanto nao encontrar vencedor
        while (!c.verificaVencedor()) {
            c.sorteioNovoNumero();
        }

        c.atualizaQuantRestante();
        c.imprimeInformacoesTeleSena();
    }

}
