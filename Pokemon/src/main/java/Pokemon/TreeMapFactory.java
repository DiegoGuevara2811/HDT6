package Pokemon;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapFactory implements MapFactory {
    public Map<String, Pokemon> createMap() {
        return new TreeMap<>();
    }
}
