package Pokemon;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapFactory implements MapFactory {
    public Map<String, Pokemon> createMap() {
        return new LinkedHashMap<>();
    }
}
