import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jakarta.servlet.ServletException;
import java.io.IOException;


import controller.ApiController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Traveler;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class ApiControllerTest {
    private ApiController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private Traveler traveler;
    private Game game;

    private Method doPostMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        servlet = new ApiController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        traveler = new Traveler("TestUser");
        game = traveler.getGame();

        // Inyectamos los objetos en los atributos privados del servlet
        // Para el atributo "traveler"
        Field travelerField = ApiController.class.getDeclaredField("traveler");
        travelerField.setAccessible(true);
        travelerField.set(servlet, traveler);

        // Para el atributo "game"
        Field gameField = ApiController.class.getDeclaredField("game");
        gameField.setAccessible(true);
        gameField.set(servlet, game);


        when(session.getAttribute("traveler")).thenReturn(traveler);

        doPostMethod = servlet.getClass().getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);

        doPostMethod.setAccessible(true); // Permitir acceso a métodos protected
    }

    @Test
    void testTravelerStartsWithZeroWinsAndDefeats() {
        assertEquals(0, game.getCountVictory());
        assertEquals(0, game.getCountDefeat());
    }

    @Test
    void testAcceptChallengeIncreasesVictoryCount() throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        when(request.getServletPath()).thenReturn("/continue");
        when(request.getParameter("btnradio")).thenReturn("Tell the truth about yourself");

        doPostMethod.invoke(servlet, request, response);

        assertEquals(1, game.getCountVictory());

        verify(request.getRequestDispatcher("/victory.jsp")).forward(request, response);

    }


    @Test
    void testRejectChallengeIncreasesDefeatCount() throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        when(request.getServletPath()).thenReturn("/continue");
        when(request.getParameter("btnradio")).thenReturn("Reject the challenge");

        doPostMethod.invoke(servlet, request, response);

        assertEquals(1, game.getCountDefeat());

        verify(request.getRequestDispatcher("/defeat.jsp")).forward(request, response);

    }

    @Test
    void testTravelerStoredInSessionOnStart() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(request.getServletPath()).thenReturn("/start");
        when(request.getParameter("traveler_name")).thenReturn("NewPlayer");

        //Method doPostMethod = ApiController.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);

        //servlet.doPost(request, response);
        doPostMethod.invoke(servlet, request, response);

        verify(session).setAttribute(eq("traveler"), any(Traveler.class));
    }
}
