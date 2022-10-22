package cellsociety;

import java.util.HashMap;

public record simParameters(HashMap<String, Integer> specificValues, SimType simType) {

}
