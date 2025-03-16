package Pokemon;

import java.io.*;
import java.util.*;

public class PokemonManager {
    private final Map<String, Pokemon> pokemonMap;
    private final List<Pokemon> userCollection;

    public PokemonManager(MapFactory factory) {
        this.pokemonMap = factory.createMap();
        this.userCollection = new ArrayList<>();
    }

    // Cargar Pokémon desde resources usando getResourceAsStream
    public void loadPokemonData(String filePath) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            System.out.println("Error: No se pudo encontrar el archivo CSV en resources.");
            return;
        }

        System.out.println("Archivo CSV encontrado, iniciando lectura...");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            String header = br.readLine(); // Saltar encabezado
            System.out.println("Encabezado del CSV: " + header);

            while ((line = br.readLine()) != null) {
                System.out.println("Leyendo línea: " + line); // Depuración
                String[] data = line.split(",");

                if (data.length < 9) {
                    System.out.println("Línea inválida (menos de 9 columnas), saltando...");
                    continue;
                }

                String name = normalizeString(data[0]);
                String type1 = normalizeString(data[2]);
                String type2 = normalizeString(data[3]);
                String abilities = normalizeString(data[7].split(",")[0]);

                Pokemon p = new Pokemon(name, type1, type2, abilities);
                pokemonMap.put(name.toLowerCase(), p);
            }
            System.out.println("Pokémon cargados correctamente: " + pokemonMap.keySet());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    // Agregar Pokémon a la colección del usuario con manejo de mayúsculas y espacios
    public String addPokemonToUserCollection(String name) {
        name = normalizeString(name);
        if (!pokemonMap.containsKey(name)) {
            return "Error: Pokémon no encontrado.";
        }
        for (Pokemon p : userCollection) {
            if (p.name.equalsIgnoreCase(name)) {
                return "Error: Pokémon ya está en la colección.";
            }
        }
        userCollection.add(pokemonMap.get(name));
        return "Pokémon agregado con éxito.";
    }

    // Obtener la información de un Pokémon por su nombre con manejo de casos
    public String getPokemonInfo(String name) {
        name = normalizeString(name);
        if (!pokemonMap.containsKey(name)) {
            return "Error: Pokémon no encontrado.";
        }
        return pokemonMap.get(name).toString();
    }

    // Obtener la colección del usuario ordenada por tipo
    public String getUserCollectionSortedByType() {
        userCollection.sort(Comparator.comparing(p -> p.type1));
        StringBuilder sb = new StringBuilder();
        for (Pokemon p : userCollection) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    // Obtener todos los Pokémon ordenados por tipo
    public String getAllPokemonSortedByType() {
        List<Pokemon> sortedList = new ArrayList<>(pokemonMap.values());
        sortedList.sort(Comparator.comparing(p -> p.type1));
        StringBuilder sb = new StringBuilder();
        for (Pokemon p : sortedList) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    // Obtener Pokémon por habilidad con manejo de mayúsculas y espacios
    public String getPokemonByAbility(String ability) {
        ability = normalizeString(ability);
        StringBuilder sb = new StringBuilder();
        for (Pokemon p : pokemonMap.values()) {
            if (p.ability.equalsIgnoreCase(ability)) {
                sb.append(p).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "No se encontraron Pokémon con esta habilidad.";
    }

    // Normalizar strings eliminando espacios adicionales y convirtiendo a minúsculas
    private String normalizeString(String str) {
        return str == null ? "" : str.trim().toLowerCase();
    }
}
