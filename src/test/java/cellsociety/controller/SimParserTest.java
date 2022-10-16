package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimParserTest {
  SimParser simParser;

  @BeforeEach
  void setUp() {
    simParser = new SimParser();
  }

  @Test
  void parseValidSimFileTest() throws FileNotFoundException {
    Map<String, String> expected = new HashMap<>();
    expected.put("Type", "GameType");
    expected.put("Title", "SimulationTitle");
    expected.put("Author", "First Last");
    expected.put("Description", "A sentence describing how this game works");
    expected.put("InitialStates", "path_to/initial.csv");

    Map<String, String> actual = simParser.parseSimFile(new File("src/test/resources/validFile.sim"));

    Assertions.assertEquals(expected.get("Type"), actual.get("Type"));
    Assertions.assertEquals(expected.get("Title"), actual.get("Title"));
    Assertions.assertEquals(expected.get("Author"), actual.get("Author"));
    Assertions.assertEquals(expected.get("Description"), actual.get("Description"));
    Assertions.assertEquals(expected.get("InitialStates"), actual.get("InitialStates"));
  }

  @Test
  void parseValidSimFileWithCommentsTest() throws FileNotFoundException {
    Map<String, String> expected = new HashMap<>();
    expected.put("Type", "GameType");
    expected.put("Title", "SimulationTitle");
    expected.put("Author", "First Last");
    expected.put("Description", "A sentence describing how this game works");
    expected.put("InitialStates", "path_to/initial.csv");

    Map<String, String> actual = simParser.parseSimFile(new File("src/test/resources/validFileWithComments.sim"));

    Assertions.assertEquals(expected.get("Type"), actual.get("Type"));
    Assertions.assertEquals(expected.get("Title"), actual.get("Title"));
    Assertions.assertEquals(expected.get("Author"), actual.get("Author"));
    Assertions.assertEquals(expected.get("Description"), actual.get("Description"));
    Assertions.assertEquals(expected.get("InitialStates"), actual.get("InitialStates"));
    Assertions.assertNull(expected.get("#Comment"));
  }

}