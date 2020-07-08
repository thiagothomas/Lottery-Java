public class TeleSena {

    public static double valorVenda = 10;
    private int[] conjunto1;
    private int[] conjunto2;

    // recebe os rescpectivos conjuntos da Tele Sena
    public TeleSena(int[] c1, int[] c2) {
        this.conjunto1 = c1;
        this.conjunto2 = c2;
    }

    public int[] getConjunto1() {
        return this.conjunto1;
    }

    public int[] getConjunto2() {
        return this.conjunto2;
    }

}