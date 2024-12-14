package org.example.swapi.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс Dictionary предоставляет функциональность для перевода запросов
 * с русского языка на английский, используя внутренний словарь.
 */
public class Dictionary {

    private Map<String, String> dictionary;

    /**
     * Конструктор инициализирует словарь переводов.
     * Включает данные для перевода персонажей, планет и кораблей.
     */
    public Dictionary() {
        dictionary = new HashMap<>();

        // Персонажи
        dictionary.put("Люк Скайуокер", "Luke Skywalker");
        dictionary.put("Дарт Вейдер", "Darth Vader");
        dictionary.put("Лея Органа", "Leia Organa");
        dictionary.put("Хан Соло", "Han Solo");
        dictionary.put("Чубакка", "Chewbacca");
        dictionary.put("Р2-Д2", "R2-D2");
        dictionary.put("С-3ПО", "C-3PO");
        dictionary.put("Йода", "Yoda");
        dictionary.put("Оби-Ван Кеноби", "Obi-Wan Kenobi");
        dictionary.put("Палпатин", "Palpatine");

        // Планеты
        dictionary.put("Татуин", "Tatooine");
        dictionary.put("Алдероан", "Alderaan");
        dictionary.put("Хот", "Hoth");
        dictionary.put("Дагоба", "Dagobah");
        dictionary.put("Эндор", "Endor");
        dictionary.put("Корусант", "Coruscant");
        dictionary.put("Набу", "Naboo");
        dictionary.put("Джакку", "Jakku");
        dictionary.put("Мустафар", "Mustafar");
        dictionary.put("Суллуст", "Sullust");

        // Корабли
        dictionary.put("Тысячелетний Сокол", "Millennium Falcon");
        dictionary.put("X-крыло", "X-Wing");
        dictionary.put("TIE-истребитель", "TIE Advanced x1");
        dictionary.put("Имперский шаттл", "Imperial Shuttle");
        dictionary.put("Раб 1", "Slave 1");
        dictionary.put("Звезда Смерти", "Death Star");
        dictionary.put("Транспорт Повстанцев", "Rebel Transport");
        dictionary.put("Десантный корабль класса Сентинел", "Sentinel-class landing craft");
        dictionary.put("Звёздный разрушитель", "Star Destroyer");
        dictionary.put("Палач", "Executor");
    }

    /**
     * Возвращает перевод для указанного ключа.
     *
     * @param key строка на русском или английском языке
     * @return перевод на английский язык, если ключ найден, или null, если перевода нет
     */
    public String getTranslation(String key) {
        if (dictionary.containsKey(key)) {
            return dictionary.get(key);
        }

        if (dictionary.containsValue(key)) {
            return key;
        }

        return null;
    }
}