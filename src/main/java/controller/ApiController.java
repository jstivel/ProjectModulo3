package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Traveler;
import model.Game;

import java.io.IOException;

@WebServlet(name = "ApiController", urlPatterns = {"/start", "/continue"})
public class ApiController extends HttpServlet {
    Traveler traveler;
    Game game;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Traveler traveler = (Traveler) session.getAttribute("traveler");

        if (traveler == null) {
            traveler = new Traveler("Unknown");  // Si no hay jugador, se crea uno temporal
            session.setAttribute("traveler", traveler);
        }

        Game game = traveler.getGame(); // Obtener el juego asociado al viajero

        request.setAttribute("username", traveler.getName());
        request.setAttribute("accept", "Accept the challenge");
        request.setAttribute("reject", "Reject the challenge");
        request.setAttribute("head", "You lost your memory.");
        request.setAttribute("question", "Accept the UFO challenge?");
        request.setAttribute("win", game.getCountVictory());
        request.setAttribute("defeat", game.getCountDefeat());
        request.setAttribute("full", game.getTotalGames());

        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        HttpSession session = request.getSession();

        if (action.equals("/start")) {
            start(request, response, session);
        } else if (action.equals("/continue")) {
            next(request, response, traveler, game);
        }
    }

    private void start(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String name = request.getParameter("traveler_name");

        if (name != null && !name.trim().isEmpty()) {
            traveler = new Traveler(name.trim());
            game = traveler.getGame();
            session.setAttribute("traveler", traveler);
            request.setAttribute("username", traveler.getName());
        }

        doGet(request, response);
    }

    private void next(HttpServletRequest request, HttpServletResponse response, Traveler traveler, Game game) throws ServletException, IOException {
        String actionForm = request.getParameter("btnradio");

        switch (actionForm) {
            case "Accept the challenge":
                request.setAttribute("accept", "Go up to the bridge");
                request.setAttribute("reject", "Refuse to go up to the bridge");
                request.setAttribute("head", "You accepted the challenge.");
                request.setAttribute("question", "Are you going up to the captain's bridge?");
                break;
            case "Reject the challenge":
                game.addDefeat();
                request.setAttribute("defeatText", "You rejected the challenge.");
                request.getRequestDispatcher("/defeat.jsp").forward(request, response);
                return;
            case "Go up to the bridge":
                request.setAttribute("accept", "Tell the truth about yourself");
                request.setAttribute("reject", "Lie about yourself");
                request.setAttribute("head", "You've gone up to the bridge.");
                request.setAttribute("question", "Who are you?");
                break;
            case "Refuse to go up to the bridge":
                game.addDefeat();
                request.setAttribute("defeatText", "You didn’t attend the negotiations.");
                request.getRequestDispatcher("/defeat.jsp").forward(request, response);
                return;
            case "Tell the truth about yourself":
                game.addVictory();
                request.getRequestDispatcher("/victory.jsp").forward(request, response);
                return;
            case "Lie about yourself":
                game.addDefeat();
                request.setAttribute("defeatText", "Your lie has been revealed.");
                request.getRequestDispatcher("/defeat.jsp").forward(request, response);
                return;
            default:
                response.getWriter().println("Acción no reconocida.");
                return;
        }

        request.setAttribute("win", game.getCountVictory());
        request.setAttribute("defeat", game.getCountDefeat());
        request.setAttribute("full", game.getTotalGames());
        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }
}
