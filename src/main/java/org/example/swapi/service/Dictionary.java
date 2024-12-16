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
        dictionary.put("люк скайуокер", "luke skywalker");
        dictionary.put("дарт вейдер", "darth vader");
        dictionary.put("лея органа", "leia organa");
        dictionary.put("хан соло", "han solo");
        dictionary.put("чубакка", "chewbacca");
        dictionary.put("р2-д2", "r2-d2");
        dictionary.put("с-3по", "c-3po");
        dictionary.put("йода", "yoda");
        dictionary.put("оби-ван кеноби", "obi-wan kenobi");
        dictionary.put("палпатин", "palpatine");

        // Планеты
        dictionary.put("татуин", "tatooine");
        dictionary.put("алдероан", "alderaan");
        dictionary.put("хот", "hoth");
        dictionary.put("дагоба", "dagobah");
        dictionary.put("эндор", "endor");
        dictionary.put("корусант", "coruscant");
        dictionary.put("набу", "naboo");
        dictionary.put("джакку", "jakku");
        dictionary.put("мустафар", "mustafar");
        dictionary.put("суллуст", "sullust");

        // Корабли
        dictionary.put("тысячелетний сокол", "millennium Falcon");
        dictionary.put("x-крыло", "x-wing");
        dictionary.put("tie-истребитель", "tie advanced x1");
        dictionary.put("имперский шаттл", "imperial shuttle");
        dictionary.put("раб 1", "slave 1");
        dictionary.put("звезда смерти", "death star");
        dictionary.put("транспорт повстанцев", "rebel transport");
        dictionary.put("десантный корабль класса сентинел", "sentinel-class landing craft");
        dictionary.put("звёздный разрушитель", "star destroyer");
        dictionary.put("палач", "executor");
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