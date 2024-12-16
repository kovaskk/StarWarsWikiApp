package org.example.swapi.service;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.swapi.dto.CharactersDTO;
import org.example.swapi.dto.PlanetDTO;
import org.example.swapi.dto.StarshipsDTO;
import org.example.swapi.exceptions.NotFoundException;
import java.util.stream.StreamSupport;

/**
 * Сервис для работы с SWAPI, реализующий методы для получения информации
 * о персонажах, планетах и космических кораблях и распределния её в DTO-классы.
 */
public class SWAPIService {

    private static final Logger logger = LogManager.getLogger(SWAPIService.class);

    private final StarWarsAPI api;
    private final Dictionary dictionary;

    /**
     * Конструктор сервиса SWAPIService.
     *
     * @param api объект для выполнения запросов к API
     * @param dictionary словарь возможных запросов
     */
    public SWAPIService(StarWarsAPI api, Dictionary dictionary) {
        this.api = api;
        this.dictionary = dictionary;
    }

    /**
     * Переводит запрос с русского на английский язык с использованием словаря.
     *
     * @param query переданный запрос
     * @return строка перевода запроса на английский
     * @throws IllegalArgumentException если запрос отсутствует в словаре
     */
    public String translateToEnglish(String query) throws IllegalArgumentException{
        String translation = dictionary.getTranslation(query.toLowerCase());
        if (translation == null) {
            logger.error("Запрос не найден в словаре: {}", query);
            throw new IllegalArgumentException("Запрос \"" + query + "\" не найден в словаре. Проверьте правильность ввода.");
        }
        logger.info("Переводим запрос: {} -> {}", query, translation);
        return translation;
    }

    /**
     * Извлекает название объекта (персонажа, планеты или фильма) из URL.
     * Функционал реализован для внутренних запросов
     *
     * @param url URL объекта в API
     * @return название объекта
     * @throws NotFoundException если название объекта не найдено
     */
    public String getNameFromUrl(String url) throws NotFoundException {
        logger.debug("Получаем название из URL: {}", url);
        JsonObject response = api.innerRequest(url);
        if (response.has("title")) {
            return response.get("title").getAsString(); // Для фильмов
        } else if (response.has("name")) {
            return response.get("name").getAsString(); // Для планет и других объектов
        }
        logger.error("Не удалось получить название из URL: {}", url);
        throw new NotFoundException("Не удалось получить название объекта по URL: " + url);
    }

    /**
     * Получает информацию о персонаже по запросу и формирует объект класса Characters DTO,
     * включая в него необходимые параметры через сеттеры
     *
     * @param query персонаж на русском или английском языке
     * @return объект CharactersDTO с данными о персонаже
     * @throws NotFoundException если персонаж не найден
     */
    public CharactersDTO getPerson(String query) throws NotFoundException {
        query = translateToEnglish(query);
        logger.info("Ищем персонажа: {}", query);
        JsonObject response = api.getBuilder("people", query);
        if (response.getAsJsonArray("results").isEmpty()) {
            logger.warn("Персонаж не найден: {}", query);
            throw new NotFoundException("Персонаж не найден.");
        }

        JsonObject personJson = response.getAsJsonArray("results").get(0).getAsJsonObject();
        CharactersDTO person = new CharactersDTO();
        person.setName(personJson.get("name").getAsString());
        person.setHomeworld(personJson.get("homeworld").getAsString());
        person.setBirthYear(personJson.get("birth_year").getAsString());
        person.setGender(personJson.get("gender").getAsString());

        String[] films = StreamSupport.stream(personJson.getAsJsonArray("films").spliterator(), false)
                .map(filmUrl -> {
                    try {
                        return getNameFromUrl(filmUrl.getAsString());
                    } catch (Exception e) {
                        logger.error("Не удалось получить название фильма по URL: {}", filmUrl);
                        return "Unknown Film";
                    }
                })
                .toArray(String[]::new);
        person.setFilms(films);
        logger.info("Персонаж успешно найден: {}", person.getName());
        return person;
    }

    /**
     * Получает информацию о планете по её названию и формирует объект класса Planet DTO,
     * включая в него необходимые параметры через сеттеры
     *
     * @param query название планеты на русском или английском языке
     * @return объект PlanetDTO с данными о планете
     * @throws NotFoundException если планета не найдена
     */
    public PlanetDTO getPlanet(String query) throws NotFoundException {
        query = translateToEnglish(query);
        logger.info("Ищем планету: {}", query);
        JsonObject response = api.getBuilder("planets", query);
        if (response.getAsJsonArray("results").isEmpty()) {
            logger.warn("Планета не найдена: {}", query);
            throw new NotFoundException("Планета не найдена.");
        }

        JsonObject planetJson = response.getAsJsonArray("results").get(0).getAsJsonObject();
        PlanetDTO planet = new PlanetDTO();
        planet.setName(planetJson.get("name").getAsString());
        planet.setDiameter(planetJson.get("diameter").getAsString());
        planet.setRotationPeriod(planetJson.get("rotation_period").getAsString());
        planet.setOrbitalPeriod(planetJson.get("orbital_period").getAsString());
        planet.setGravity(planetJson.get("gravity").getAsString());
        planet.setPopulation(planetJson.get("population").getAsString());
        planet.setClimate(planetJson.get("climate").getAsString());
        planet.setTerrain(planetJson.get("terrain").getAsString());

        String[] films = StreamSupport.stream(planetJson.getAsJsonArray("films").spliterator(), false)
                .map(filmUrl -> {
                    try {
                        return getNameFromUrl(filmUrl.getAsString()); // Получаем название фильма по URL
                    } catch (Exception e) {
                        logger.error("Не удалось получить название фильма по URL: {}", filmUrl);
                        return "Фильм не найден!";
                    }
                })
                .toArray(String[]::new);
        planet.setFilms(films);
        logger.info("Планета успешно найдена: {}", planet.getName());
        return planet;
    }

    /**
     * Получает информацию о космическом корабле по его названию и формирует объект класса Starships DTO,
     * включая в него необходимые параметры через сеттеры
     *
     * @param query название корабля на русском или английском языке
     * @return объект StarshipsDTO с данными о корабле
     * @throws NotFoundException если корабль не найден
     */
    public StarshipsDTO getStarship(String query) throws NotFoundException {
        query = translateToEnglish(query);
        logger.info("Ищем корабль: {}", query);
        JsonObject response = api.getBuilder("starships", query);
        if (response.getAsJsonArray("results").isEmpty()) {
            logger.warn("Корабль не найден: {}", query);
            throw new NotFoundException("Корабль не найден.");
        }

        JsonObject starshipJson = response.getAsJsonArray("results").get(0).getAsJsonObject();
        StarshipsDTO starship = new StarshipsDTO();
        starship.setName(starshipJson.get("name").getAsString());
        starship.setModel(starshipJson.get("model").getAsString());
        starship.setStarshipClass(starshipJson.get("starship_class").getAsString());
        starship.setManufacturer(starshipJson.get("manufacturer").getAsString());
        starship.setCrew(starshipJson.get("crew").getAsString());
        starship.setPassengers(starshipJson.get("passengers").getAsString());
        starship.setHyperdriveRating(starshipJson.get("hyperdrive_rating").getAsString());
        starship.setCargoCapacity(starshipJson.get("cargo_capacity").getAsString());
        starship.setCostInCredits(starshipJson.get("cost_in_credits").getAsString());

        logger.info("Корабль успешно найден: {}", starship.getName());
        return starship;
    }
}
