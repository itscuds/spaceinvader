package br.com.joaovjsilva;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import br.com.joaovjsilva.model.Asset;
import br.com.joaovjsilva.model.Jogo;
import br.com.joaovjsilva.model.Meteoro;
import br.com.joaovjsilva.model.Nave;
import br.com.joaovjsilva.model.Tiro;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

    @FXML
    private ImageView imgNave;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label level;

    @FXML
    private Label pontos;

    private final Jogo jogo = new Jogo();

    private int frame = 0;

    private AnimationTimer timer;

    public void keyHandler(KeyEvent event) {
        Nave nave = jogo.getNave();

        if (event.getCode() == KeyCode.LEFT) {
            nave.setMovimentoHorizontal(-1);
            event.consume();

        } else if (event.getCode() == KeyCode.RIGHT) {
            nave.setMovimentoHorizontal(1);
            event.consume();

        } else if (event.getCode() == KeyCode.SPACE) {
            atirar();
            event.consume();
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT
                || event.getCode() == KeyCode.RIGHT) {

            jogo.getNave().setMovimentoHorizontal(0);
            event.consume();
        }
    }

    private void atualizar() {
        frame++;

        // Cria um meteoro aproximadamente a cada 100 quadros.
        if (frame % 100 == 0) {
            jogo.criarMeteoro(pane.getWidth());
        }

        // Atualiza a movimentação da nave.
        jogo.getNave().mover();

        imgNave.setLayoutX(jogo.getNave().getX());
        imgNave.setLayoutY(jogo.getNave().getY());

        List<Asset> ativos =
                new ArrayList<>(jogo.getAssets());

        Set<Asset> remover = new HashSet<>();

        // Move os tiros e os meteoros.
        for (Asset asset : ativos) {
            asset.mover();

            // Remove tiros que saíram pelo topo.
            if (asset instanceof Tiro
                    && asset.getY() < -60) {

                remover.add(asset);

                // Remove meteoros que passaram pelo fundo.
            } else if (asset instanceof Meteoro
                    && asset.getY() > pane.getHeight()) {

                remover.add(asset);

                // Remove objetos que saíram pelas laterais.
            } else if (asset.getX() > pane.getWidth()
                    || asset.getX() < -60) {

                remover.add(asset);
            }
        }

        // Verifica colisões entre tiros e meteoros.
        for (Asset asset : ativos) {
            if (!(asset instanceof Tiro)
                    || remover.contains(asset)) {
                continue;
            }

            Tiro tiro = (Tiro) asset;

            for (Asset outro : ativos) {
                if (!(outro instanceof Meteoro)
                        || remover.contains(outro)) {
                    continue;
                }

                Meteoro meteoro = (Meteoro) outro;

                if (tiro.colidiuCom(meteoro)) {
                    meteoro.tomarTiro(tiro);
                    remover.add(tiro);

                    if (meteoro.foiDestruido()) {
                        remover.add(meteoro);
                        jogo.pontuar();
                    }

                    break;
                }
            }
        }

        // Remove os objetos sem modificar a lista durante a iteração.
        for (Asset asset : remover) {
            jogo.removerAsset(asset);
            pane.getChildren().remove(asset.getImagem());
        }

        // Atualiza as imagens dos objetos restantes.
        for (Asset asset : jogo.getAssets()) {
            ImageView imagem = asset.getImagem();

            imagem.setLayoutX(asset.getX());
            imagem.setLayoutY(asset.getY());

            if (!pane.getChildren().contains(imagem)) {
                pane.getChildren().add(imagem);
            }
        }

        // Atualiza os textos da interface.
        pontos.setText(String.valueOf(jogo.getPontos()));
        level.setText(String.valueOf(jogo.getNivel()));
    }

    private void atirar() {
        jogo.atirar(1);
    }

    @Override
    public void initialize(
            URL url,
            ResourceBundle resourceBundle
    ) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                atualizar();
            }
        };

        timer.start();
    }
}