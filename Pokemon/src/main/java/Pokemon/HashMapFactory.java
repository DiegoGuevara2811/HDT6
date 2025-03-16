package Pokemon;

import java.util.HashMap;
import java.util.Map;

public class HashMapFactory implements MapFactory {
    public Map<String, Pokemon> createMap() {
        return new HashMap<>();
    }
}
