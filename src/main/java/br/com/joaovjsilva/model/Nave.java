package br.com.joaovjsilva.model;

public class Nave extends Asset {

    private static final double LIMITE_ESQUERDO = 0;
    private static final double LIMITE_DIREITO = 547;

    private int movimentoHorizontal;

    public Nave() {
        super(
                280,
                400,
                5,
                Direcao.CIMA,
                1,
                "/br/com/joaovjsilva/images/ships/playerShip1_blue.png",
                53,
                40
        );

        movimentoHorizontal = 0;
    }

    public void setMovimentoHorizontal(int movimento) {
        movimentoHorizontal = Integer.compare(movimento, 0);
    }

    @Override
    public void mover() {
        double novaPosicao = getX()
                + movimentoHorizontal * getVelocidade();

        novaPosicao = Math.max(
                LIMITE_ESQUERDO,
                Math.min(LIMITE_DIREITO, novaPosicao)
        );

        setX(novaPosicao);
    }

    public Tiro atirar(int poder) {
        double xTiro = getX()
                + getImagem().getFitWidth() / 2 - 6;

        double yTiro = getY() - 30;

        return new Tiro(
                xTiro,
                yTiro,
                10,
                Direcao.CIMA,
                poder
        );
    }
}