package br.com.joaovjsilva.model;

import java.util.Random;

public class Meteoro extends Asset {

    private static final Random RANDOM = new Random();

    private  final int tamanho;

    public Meteoro(double larguraTela){
        this(
                RANDOM.nextInt(8) + 1,
                RANDOM.nextDouble() * Math.max(1, larguraTela - 120)
        );
    }

    public Meteoro(int tamanho, double x){
        super(
                x,
                -100,
                1 + RANDOM.nextDouble() * 2,
                Direcao.BAIXO,
                tamanho,
                imagemDoMeteoro(tamanho),
                larguraDoMeteoro(tamanho),
                alturaDoMeteoro(tamanho)
        );

        this.tamanho = tamanho;
    }

    private static String imagemDoMeteoro(int tamanho){
        return "/br/com/joaovjsilva/images/meteoro/meteoro" + tamanho + ".png";
    }

    private static double larguraDoMeteoro(int tamanho){
        return 15 + tamanho * 12;
    }

    private static double alturaDoMeteoro(int tamanho){
        return 15 + tamanho * 10;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void tomarTiro(Tiro tiro){
        setPoder(getPoder() - tiro.getPoder());
    }

    public boolean foiDestruido(){
        return getPoder() <= 0;
    }

}
