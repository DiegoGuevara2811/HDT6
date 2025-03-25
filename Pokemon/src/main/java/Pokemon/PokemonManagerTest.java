package Pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PokemonManagerTest {
    private PokemonManager manager;

    @BeforeEach
    void setUp() {
        manager = new PokemonManager(new HashMapFactory());
        manager.loadPokemonData("pokemon_data_pokeapi.csv");
    }

    @Test
    void testAddPokemonToUserCollection() {
        // Agregar un Pokémon existente
        String result1 = manager.addPokemonToUserCollection("Pikachu");
        assertEquals("Pokémon agregado con éxito.", result1);

        // Intentar agregarlo de nuevo
        String result2 = manager.addPokemonToUserCollection("Pikachu");
        assertEquals("Error: Pokémon ya está en la colección.", result2);
    }

    @Test
    void testAddPokemonToUserCollection_NotFound() {
        // Intentar agregar un Pokémon que no existe
        String result = manager.addPokemonToUserCollection("NoExiste");
        assertEquals("Error: Pokémon no encontrado.", result);
    }

    @Test
    void testGetPokemonInfo() {
        // Buscar un Pokémon existente
        String result = manager.getPokemonInfo("Pikachu");
        assertTrue(result.contains("Pikachu"));
    }

    @Test
    void testGetPokemonInfo_NotFound() {
        // Buscar un Pokémon que no existe
        String result = manager.getPokemonInfo("NoExiste");
        assertEquals("Error: Pokémon no encontrado.", result);
    }

    @Test
    void testGetUserCollectionSortedByType() {
        manager.addPokemonToUserCollection("Pikachu");
        manager.addPokemonToUserCollection("Charmander");
        String sortedCollection = manager.getUserCollectionSortedByType();
        assertTrue(sortedCollection.contains("Charmander") && sortedCollection.contains("Pikachu"));
    }

    @Test
    void testGetAllPokemonSortedByType() {
        String sortedAll = manager.getAllPokemonSortedByType();
        assertFalse(sortedAll.isEmpty());
    }
