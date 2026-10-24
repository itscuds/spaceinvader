package br.com.joaovjsilva.model;

public class Tiro extends Asset {


    public Tiro(double x,
                double y,
                double velocidade,
                Direcao direcao,
                int poder) {
        super(x,
                y,
                velocidade,
                direcao,
                poder,
                escolherImagem(poder),
                13,
                37);
    }

    private static String escolherImagem(int poder){
        int tipo = Math.max(1, Math.min(4, poder));

        return "/br/com/joaovjsilva/images/laser/laser" + tipo + ".png";
    }

}
