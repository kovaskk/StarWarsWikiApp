package org.example.swapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.swapi.dto.CharactersDTO;
import org.example.swapi.dto.PlanetDTO;
import org.example.swapi.dto.StarshipsDTO;
import org.example.swapi.exceptions.NotFoundException;

/**
 * Класс для обработки запросов и переключения между различными категориями (персонажи, планеты, корабли).
 */
public class QuerySwitcher {

    private static final Logger logger = LogManager.getLogger(QuerySwitcher.class);

    private final SWAPIService swapiService;

    /**
     * Конструктор для инициализации QuerySwitcher.
     *
     * @param swapiService сервис для выполнения запросов к Star Wars API
     */
    public QuerySwitcher(SWAPIService swapiService) {
        this.swapiService = swapiService;
    }

    /**
     * Обработка запроса, определение его принадлежности к одной из категорий: персонажи, планеты или корабли.*
     *
     * @param query запрос на русском или английском языке
     * @return Информация об объекте (персонаж, планета или корабль)
     * @throws IllegalArgumentException если запрос не соответствует ни одной из категорий
     */
    public String handleQuery(String query) throws IllegalArgumentException {
        String translatedQuery = swapiService.translateToEnglish(query);

        try {
            CharactersDTO character = swapiService.getPerson(translatedQuery);
            return "Имя: " + character.getName() + "\n" +
                    "Родной мир: " + swapiService.getNameFromUrl(character.getHomeworld()) + "\n" +
                    "Год рождения: " + character.getBirth_year() + "\n" +
                    "Пол: " + character.getGender() + "\n" +
                    "Фильмы: " + String.join(", ", character.getFilms());
        } catch (NotFoundException e) {
            logger.debug("Запрос не является запросом персонажа: {}", translatedQuery, e);
        }

        try {
            PlanetDTO planet = swapiService.getPlanet(translatedQuery);
            return "Название: " + planet.getName() + "\n" +
                    "Диаметр: " + planet.getDiameter() + "\n" +
                    "Период вращения: " + planet.getRotationPeriod() + "\n" +
                    "Период орбиты: " + planet.getOrbitalPeriod() + "\n" +
                    "Гравитация: " + planet.getGravity() + "\n" +
                    "Население: " + planet.getPopulation() + "\n" +
                    "Климат: " + planet.getClimate() + "\n" +
                    "Рельеф: " + planet.getTerrain() + "\n" +
                    "Фильмы: " + String.join(", ", planet.getFilms());
        } catch (NotFoundException e) {
            logger.debug("Запрос не является запросом планеты: {}", translatedQuery, e);
        }

        try {
            StarshipsDTO starship = swapiService.getStarship(translatedQuery);
            return "Название: " + starship.getName() + "\n" +
                    "Модель: " + starship.getModel() + "\n" +
                    "Класс: " + starship.getStarshipClass() + "\n" +
                    "Производитель: " + starship.getManufacturer() + "\n" +
                    "Экипаж: " + starship.getCrew() + "\n" +
                    "Пассажиры: " + starship.getPassengers() + "\n" +
                    "Гипердрайв: " + starship.getHyperdriveRating() + "\n" +
                    "Грузоподъемность: " + starship.getCargoCapacity() + "\n" +
                    "Стоимость: " + starship.getCostInCredits() + "\n";
        } catch (NotFoundException e) {
            logger.debug("Запрос не является запросом корабля: {}", translatedQuery, e);
        }

        logger.warn("Запрос не соответствует ни одной известной категории: {}", query);
        throw new IllegalArgumentException("Введенный запрос не соответствует ни одной категории (персонажи, планеты, корабли). Проверьте ввод.");
    }
}
