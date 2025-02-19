import model.Traveler;
import model.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TravelerTest {

    @Test
    public void testTravelerHasOwnGame() {
        Traveler traveler = new Traveler("Alice");
        assertNotNull(traveler.getGame());

        traveler.getGame().addVictory();
        assertEquals(1, traveler.getGame().getCountVictory());

        Traveler traveler2 = new Traveler("Bob");
        assertEquals(0, traveler2.getGame().getCountVictory()); // Verifica que cada viajero tiene su propio juego
    }
}
