package br.com.joaovjsilva.model;

public enum Direcao {
    CIMA(0, -1),
    BAIXO(0, 1),
    ESQUERDA(-1, 0),
    DIREITA(1, 0);

    private final int x;
    private final int y;

    Direcao(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
