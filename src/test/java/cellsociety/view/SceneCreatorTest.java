package cellsociety.view;

import javafx.scene.Scene;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SceneCreatorTest{

    double size = 500;

    SceneCreator tester = new SceneCreator(500);

    @Test
    void testSceneCreator(){
        assertEquals(size, tester.mySize);
    }
    
}