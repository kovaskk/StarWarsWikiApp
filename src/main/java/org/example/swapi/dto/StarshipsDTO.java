package org.example.swapi.dto;

/**
 * Объект передачи данных (DTO) для хранения информации о космических кораблях.
 */
public class StarshipsDTO {

    private String name;
    private String model;
    private String starship_class;
    private String manufacturer;
    private String crew;
    private String passengers;
    private String hyperdrive_rating;
    private String cargo_capacity;
    private String cost_in_credits;

    /**
     * Возвращает название корабля.
     *
     * @return название корабля
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название корабля.
     *
     * @param name название корабля
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает модель корабля.
     *
     * @return модель корабля
     */
    public String getModel() {
        return model;
    }

    /**
     * Устанавливает модель корабля.
     *
     * @param model модель корабля
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Возвращает класс корабля.
     *
     * @return класс корабля
     */
    public String getStarshipClass() {
        return starship_class;
    }

    /**
     * Устанавливает класс корабля.
     *
     * @param starship_class класс корабля
     */
    public void setStarshipClass(String starship_class) {
        this.starship_class = starship_class;
    }

    /**
     * Возвращает производителя корабля.
     *
     * @return производитель корабля
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Устанавливает производителя корабля.
     *
     * @param manufacturer производитель корабля
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Возвращает экипаж корабля.
     *
     * @return экипаж корабля
     */
    public String getCrew() {
        return crew;
    }

    /**
     * Устанавливает экипаж корабля.
     *
     * @param crew экипаж корабля
     */
    public void setCrew(String crew) {
        this.crew = crew;
    }

    /**
     * Возвращает количество пассажиров, которых может перевезти корабль.
     *
     * @return количество пассажиров
     */
    public String getPassengers() {
        return passengers;
    }

    /**
     * Устанавливает количество пассажиров, которых может перевезти корабль.
     *
     * @param passengers количество пассажиров
     */
    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    /**
     * Возвращает рейтинг гипердвигателя корабля.
     *
     * @return рейтинг гипердвигателя
     */
    public String getHyperdriveRating() {
        return hyperdrive_rating;
    }

    /**
     * Устанавливает рейтинг гипердвигателя корабля.
     *
     * @param hyperdrive_rating рейтинг гипердвигателя
     */
    public void setHyperdriveRating(String hyperdrive_rating) {
        this.hyperdrive_rating = hyperdrive_rating;
    }

    /**
     * Возвращает грузоподъёмность корабля.
     *
     * @return грузоподъёмность корабля
     */
    public String getCargoCapacity() {
        return cargo_capacity;
    }

    /**
     * Устанавливает грузоподъёмность корабля.
     *
     * @param cargo_capacity грузоподъёмность корабля
     */
    public void setCargoCapacity(String cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    /**
     * Возвращает стоимость корабля в кредитах.
     *
     * @return стоимость корабля
     */
    public String getCostInCredits() {
        return cost_in_credits;
    }

    /**
     * Устанавливает стоимость корабля в кредитах.
     *
     * @param cost_in_credits стоимость корабля
     */
    public void setCostInCredits(String cost_in_credits) {
        this.cost_in_credits = cost_in_credits;
    }
}
