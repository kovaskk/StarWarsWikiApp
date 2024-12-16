package org.example.swapi.dto;

/**
 * Объект передачи данных (DTO) для хранения информации о персонажах.
 */
public class CharactersDTO {
    private String name;
    private String birth_year;
    private String homeworld;
    private String gender;
    private String[] films;

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя персонажа.
     *
     * @param name имя персонажа
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает название родной планеты  персонажа.
     *
     * @return родной мир персонажа
     */
    public String getHomeworld() {
        return homeworld;
    }

    /**
     * Устанавливает название родной планеты персонажа.
     *
     * @param homeworld родной мир персонажа
     */
    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    /**
     * Возвращает год рождения персонажа.
     *
     * @return год рождения персонажа
     */
    public String getBirthYear() {
        return birth_year;
    }

    /**
     * Устанавливает год рождения персонажа.
     *
     * @param birth_year год рождения персонажа
     */
    public void setBirthYear(String birth_year) {
        this.birth_year = birth_year;
    }

    /**
     * Возвращает пол персонажа.
     *
     * @return пол персонажа
     */
    public String getGender() {
        return gender;
    }

    /**
     * Устанавливает пол персонажа.
     *
     * @param gender пол персонажа
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Возвращает массив фильмов, в которых упоминается персонаж.
     *
     * @return массив фильмов
     */
    public String[] getFilms() {
        return films;
    }

    /**
     * Устанавливает массив фильмов, в которых упоминается персонаж.
     *
     * @param films массив фильмов
     */
    public void setFilms(String[] films) {
        this.films = films;
    }
}
