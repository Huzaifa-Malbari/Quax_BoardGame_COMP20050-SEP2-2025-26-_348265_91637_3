package hio.qss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Feature4_Colour_Text {

    @Test
    void setPlayerTurnTextTest() {

        QSSController qssController = new QSSController();

        assertEquals("Black's Turn", qssController.getTurnText());

        qssController.game.placeCell(false, 0, 0); // switches turn from Black to White
        assertEquals("White's Turn", qssController.getTurnText());
    }

}
