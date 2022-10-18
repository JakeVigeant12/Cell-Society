package cellsociety.parser;

import cellsociety.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class SimParser extends Parser {

  public Map<String, String> parseData(File simFile) throws FileNotFoundException {
    Map<String, String> simInfoMap = new HashMap<>();
    Scanner fileScanner = new Scanner(simFile); //TODO: Fix exception handling
    while(fileScanner.hasNextLine()) {
      String infoString = fileScanner.nextLine();
      if(infoString.startsWith("#")) {
        continue;
      } else {
        String[] infoArray = infoString.split("=");
        simInfoMap.put(infoArray[0], infoArray[1]);
      }
    }
    return simInfoMap;
  }
}
