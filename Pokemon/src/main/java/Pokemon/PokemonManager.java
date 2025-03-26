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
    public String loadPokemonData(String filePath) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            return "Error: No se pudo encontrar el archivo CSV en resources.";
        }

        StringBuilder result = new StringBuilder();
        result.append("Archivo CSV encontrado, iniciando lectura...\n");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            String header = br.readLine(); // Saltar encabezado
            result.append("Encabezado del CSV: ").append(header).append("\n");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 9) {
                    result.append("Línea inválida (menos de 9 columnas), saltando...\n");
                    continue;
                }

                String name = normalizeString(data[0]);
                String type1 = normalizeString(data[2]);
                String type2 = normalizeString(data[3]);
                String abilities = normalizeString(data[7]); // Tomamos todas las habilidades

                // Aquí corregimos cómo se manejan las habilidades
                String[] abilityList = abilities.split(",");  // Si hay más de una habilidad, las separa
                StringBuilder abilityBuilder = new StringBuilder();

                for (String ability : abilityList) {
                    ability = ability.trim();  // Eliminar espacios adicionales
                    if (!ability.isEmpty()) {  // Si no está vacío, agregar al StringBuilder
                        abilityBuilder.append(ability).append(" ");  // Añadimos la habilidad
                    }
                }

                // Eliminamos el espacio extra al final
                String finalAbilities = abilityBuilder.toString().trim();

                // Creamos el objeto Pokémon con las habilidades correctamente asignadas
                Pokemon p = new Pokemon(name, type1, type2, finalAbilities);
                pokemonMap.put(name.toLowerCase(), p);
            }
            result.append("Pokémon cargados correctamente: ").append(pokemonMap.keySet()).append("\n");
        } catch (IOException e) {
            result.append("Error al leer el archivo CSV: ").append(e.getMessage()).append("\n");
        }
        return result.toString();
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
        ability = normalizeString(ability).replace("\"", ""); // Eliminar comillas y normalizar
        StringBuilder sb = new StringBuilder();

        for (Pokemon p : pokemonMap.values()) {
            // Eliminamos las comillas de la habilidad de cada Pokémon
            String[] abilities = p.getAbility().replace("\"", "").split(",");  // Dividimos las habilidades por coma

            for (String pokemonAbility : abilities) {
                pokemonAbility = normalizeString(pokemonAbility.trim());  // Normalizamos cada habilidad

                if (pokemonAbility.equalsIgnoreCase(ability)) {
                    sb.append(p).append("\n");
                    break;  // Si encontramos una coincidencia, terminamos la búsqueda
                }
            }
        }

        return sb.length() > 0 ? sb.toString() : "No se encontraron Pokémon con esta habilidad.";
    }

    // Método de normalización de texto para convertirlo a minúsculas
    private String normalizeString(String str) {
        return str == null ? "" : str.trim().toLowerCase();
    }
}
