package br.com.joaovjsilva.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Asset {
    private double x;
    private double y;
    private double velocidade;

    private Direcao direcao;
    private int poder;
    private final ImageView imagem;

    public Asset(
            double x,
            double y,
            double velocidade,
            Direcao direcao,
            int poder,
            String caminhoImagem,
            double largura,
            double altura
    ){
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.direcao = direcao;
        this.poder = poder;

        Image imagemCarregada = new Image(Asset.class.getResourceAsStream(caminhoImagem));
        this.imagem = new ImageView(imagemCarregada);
        this.imagem.setFitWidth(largura);
        this.imagem.setFitHeight(altura);
        this.imagem.setPreserveRatio(true);

        atualizarPosicao();
    }

    public void mover(){
        if (direcao == null){
            return;
        }

        x += direcao.getX() * velocidade;
        y += direcao.getY() * velocidade;

        atualizarPosicao();
    }

    public boolean colidiuCom(Asset outro){
        Bounds limitesAtuais = imagem.localToScene(imagem.getBoundsInLocal());

        Bounds limitesOutros = outro.imagem.localToScene(outro.imagem.getBoundsInLocal());

        return limitesAtuais.intersects(limitesOutros);
    }

    protected void atualizarPosicao(){
        imagem.setLayoutX(x);
        imagem.setLayoutY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        atualizarPosicao();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        atualizarPosicao();
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public ImageView getImagem() {
        return imagem;
    }
}
