package br.com.joaovjsilva.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogo {

    private final Nave nave;
    private final List<Asset> assets;

    private int pontos;
    private int nivel;

    public Jogo() {
        nave = new Nave();
        assets = new ArrayList<>();

        pontos = 0;
        nivel = 1;
    }

    public Tiro atirar(int poder) {
        Tiro tiro = nave.atirar(poder);

        assets.add(tiro);

        return tiro;
    }

    public Meteoro criarMeteoro(double larguraTela) {
        Meteoro meteoro = new Meteoro(larguraTela);

        assets.add(meteoro);

        return meteoro;
    }

    public void pontuar() {
        pontos++;

        if (pontos % 10 == 0 && nivel < 4) {
            nivel++;
        }
    }

    public Nave getNave() {
        return nave;
    }

    public List<Asset> getAssets() {
        return Collections.unmodifiableList(assets);
    }

    public void removerAsset(Asset asset) {
        assets.remove(asset);
    }

    public int getPontos() {
        return pontos;
    }

    public int getNivel() {
        return nivel;
    }
}