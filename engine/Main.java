package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;

public class Main {
    public static void main(String[] args) {
        // 1. Création du contrôleur pour gérer le jeu d'échecs
        ChessController controller = new Game();

        // 2. Création de la vue désirée
        ChessView view = new GUIView(controller);
        //ChessView view = new ConsoleView(controller); MODE CONSOLE

        // 3. Lancement du programme
        controller.start(view);
    }
}