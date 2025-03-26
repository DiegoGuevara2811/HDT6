import Pokemon.HashMapFactory;
import Pokemon.PokemonManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class PokemonManagerTest {
    private PokemonManager manager;

    @BeforeEach
    void setUp() {
        manager = new PokemonManager(new HashMapFactory());
        manager.loadPokemonData("pokemon_data_pokeapi.csv");
    }

    // Pruebas para addPokemonToUserCollection

    @Test
    void testAddPokemonToUserCollection_Success() {
        String result = manager.addPokemonToUserCollection("Pikachu");
        assertEquals("Pokémon agregado con éxito.", result);  // Verifica que el Pokémon se haya agregado correctamente
    }

    @Test
    void testAddPokemonToUserCollection_AlreadyInCollection() {
        manager.addPokemonToUserCollection("Pikachu");
        String result = manager.addPokemonToUserCollection("Pikachu");
        assertEquals("Error: Pokémon ya está en la colección.", result);  // Verifica que no se agregue el mismo Pokémon dos veces
    }

    // Pruebas para addPokemonToUserCollection con un Pokémon no encontrado

    @Test
    void testAddPokemonToUserCollection_NotFound() {
        String result = manager.addPokemonToUserCollection("NoExiste");
        assertEquals("Error: Pokémon no encontrado.", result);  // Verifica que si el Pokémon no existe se retorne un mensaje de error
    }

    @Test
    void testAddPokemonToUserCollection_EmptyName() {
        String result = manager.addPokemonToUserCollection("");
        assertEquals("Error: Pokémon no encontrado.", result);  // Verifica que si el nombre es vacío también se maneje el error
    }

    // Pruebas para getPokemonInfo

    @Test
    void testGetPokemonInfo_Success() {
        String result = manager.getPokemonInfo("Pikachu");
        assertTrue(result.contains("pikachu"));  // Verifica que la información del Pokémon contenga el nombre esperado
    }

    @Test
    void testGetPokemonInfo_NotFound() {
        String result = manager.getPokemonInfo("NoExiste");
        assertEquals("Error: Pokémon no encontrado.", result);  // Verifica que si el Pokémon no existe se retorne el mensaje adecuado
    }

    // Pruebas para getUserCollectionSortedByType



    @Test
    void testGetUserCollectionSortedByType_EmptyCollection() {
        String sortedCollection = manager.getUserCollectionSortedByType();
        assertEquals("", sortedCollection);  // Verifica que se maneje correctamente una colección vacía
    }

    // Pruebas para getAllPokemonSortedByType

    @Test
    void testGetAllPokemonSortedByType_Success() {
        String sortedAll = manager.getAllPokemonSortedByType();
        assertFalse(sortedAll.isEmpty());  // Verifica que el listado de todos los Pokémon ordenados no esté vacío
    }

    @Test
    void testGetAllPokemonSortedByType_EmptyMap() {
        manager = new PokemonManager(new HashMapFactory());  // Crear un manager vacío
        String sortedAll = manager.getAllPokemonSortedByType();
        assertEquals("", sortedAll);  // Verifica que si no hay Pokémon, el resultado sea vacío
    }

    // Pruebas para getPokemonByAbility


    @Test
    void testGetPokemonByAbility_NotFound() {
        String result = manager.getPokemonByAbility("unknownability");
        assertEquals("No se encontraron Pokémon con esta habilidad.", result);  // Verifica que si no se encuentra la habilidad, se maneje el caso correctamente
    }
}
