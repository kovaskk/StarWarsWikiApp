package org.example.swapi.dto;

/**
 * Объект передачи данных (DTO) для хранения информации о планетах.
 */
public class PlanetDTO {

    private String name;
    private String diameter;
    private String rotation_period;
    private String orbital_period;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String[] films;

    /**
     * Возвращает название планеты.
     *
     * @return название планеты
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название планеты.
     *
     * @param name название планеты
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает диаметр планеты.
     *
     * @return диаметр планеты
     */
    public String getDiameter() {
        return diameter;
    }

    /**
     * Устанавливает диаметр планеты.
     *
     * @param diameter диаметр планеты
     */
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    /**
     * Возвращает период вращения планеты.
     *
     * @return период вращения планеты
     */
    public String getRotationPeriod() {
        return rotation_period;
    }

    /**
     * Устанавливает период вращения планеты.
     *
     * @param rotation_period период вращения планеты
     */
    public void setRotationPeriod(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    /**
     * Возвращает орбитальный период планеты.
     *
     * @return орбитальный период планеты
     */
    public String getOrbitalPeriod() {
        return orbital_period;
    }

    /**
     * Устанавливает орбитальный период планеты.
     *
     * @param orbital_period орбитальный период планеты
     */
    public void setOrbitalPeriod(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    /**
     * Возвращает гравитацию на планете.
     *
     * @return гравитация на планете
     */
    public String getGravity() {
        return gravity;
    }

    /**
     * Устанавливает гравитацию на планете.
     *
     * @param gravity гравитация на планете
     */
    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    /**
     * Возвращает население планеты.
     *
     * @return население планеты
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Устанавливает население планеты.
     *
     * @param population население планеты
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Возвращает климат планеты.
     *
     * @return климат планеты
     */
    public String getClimate() {
        return climate;
    }

    /**
     * Устанавливает климат планеты.
     *
     * @param climate климат планеты
     */
    public void setClimate(String climate) {
        this.climate = climate;
    }

    /**
     * Возвращает тип рельефа планеты.
     *
     * @return рельеф планеты
     */
    public String getTerrain() {
        return terrain;
    }

    /**
     * Устанавливает тип рельефа планеты.
     *
     * @param terrain рельеф планеты
     */
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    /**
     * Возвращает массив фильмов, в которых упоминается планета.
     *
     * @return массив фильмов
     */
    public String[] getFilms() {
        return films;
    }

    /**
     * Устанавливает массив фильмов, в которых упоминается планета.
     *
     * @param films массив фильмов
     */
    public void setFilms(String[] films) {
        this.films = films;
    }
}
