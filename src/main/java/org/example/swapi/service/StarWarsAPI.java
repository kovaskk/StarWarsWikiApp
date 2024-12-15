package org.example.swapi.service;

import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.swapi.exceptions.ApiException;
import org.example.swapi.exceptions.InvalidResponseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Предоставляет методы для взаимодействия с Star Wars API.
 */
public class StarWarsAPI {

    private static final Logger logger = LogManager.getLogger(StarWarsAPI.class);

    /**
     * Выполняет запрос к API для получения данных по указанному адресу с поисковым запросом.
     *
     * @param path путь до категории (например, "people", "planets" и т.д.)
     * @param searchQuery поисковый запрос для API
     * @return объект JsonObject, содержащий ответ сервера
     * @throws ApiException если произошла ошибка во время запроса или кодирования
     */
    public JsonObject getBuilder(String path, String searchQuery) throws ApiException {
        logger.info("Запуск метода getBuilder с path: {} и searchQuery: {}", path, searchQuery);

        HttpGet httpGet;

        try {
            String encodedQuery = encodeQuery(searchQuery.toLowerCase());
            httpGet = new HttpGet("https://swapi.dev/api/" + path + "/?search=" + encodedQuery);
            logger.debug("Закодированный запрос: {}", encodedQuery);
            return getRequest(httpGet);
        } catch (UnsupportedEncodingException e) {
            logger.error("Ошибка кодирования для запроса: {}", searchQuery);
            throw new ApiException("Ошибка кодирования запроса: " + searchQuery, e);
        }
    }

    /**
     * Отправляет HTTP GET запрос на указанный URI и обрабатывает ответ API.
     *
     * @param getRequest объект HttpGet, представляющий запрос
     * @return объект JsonObject, содержащий ответ API*
     * @throws ApiException если возникает ошибка на стороне API при выполнении запроса
     */
    public JsonObject getRequest(HttpGet getRequest) throws ApiException {
        logger.info("Запуск метода getRequest с URI: {}", getRequest.getURI());

        HttpClient httpClient = HttpClientBuilder.create().build();
        getRequest.addHeader("accept", "application/json");

        HttpResponse response;
        try {
            response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.debug("Статус HTTP ответа: {}", statusCode);

            if (statusCode != 200) {
                logger.error("API вернул не 200 статус: {}", statusCode);
                throw new InvalidResponseException("Ошибка выполнения запроса: Код ответа " + statusCode);
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JsonObject jsonObject = deserialize(stringBuilder.toString());
            bufferedReader.close();

            if (jsonObject.has("results") && jsonObject.getAsJsonArray("results").isEmpty()) {
                logger.warn("Пустой результат для запроса: {}", getRequest.getURI());
            }

            logger.info("Метод getRequest успешно завершён.");
            return jsonObject;
        } catch (Exception e) {
            logger.error("Неожиданная ошибка во время запроса: {}", getRequest.getURI());
            throw new ApiException("Неожиданная ошибка при выполнении запроса: " + getRequest.getURI(), e);
        }
    }

    /**
     * Десериализует JSON строку в объект типа JsonObject.
     *
     * @param json JSON строка для десериализации
     * @return объект JsonObject, представляющий входящую строку
     * @throws ApiException если не удаётся десериализовать JSON
     */
    public JsonObject deserialize(String json) throws ApiException {
        logger.info("Запуск метода deserialize.");
        try {
            return new com.google.gson.JsonParser().parse(json).getAsJsonObject();
        } catch (ApiException e) {
            logger.error("Ошибка при десериализации JSON: {}", json);
            throw new ApiException("Ошибка десериализации JSON: " + json, e);
        }
    }

    /**
     * Кодирует строку запроса для использования в URL.
     *
     * @param query строка запроса для кодирования
     * @return закодированная в кодировке UTF-8 строка запроса
     * @throws UnsupportedEncodingException если кодировка не поддерживается
     */
    private String encodeQuery(String query) throws UnsupportedEncodingException {
        logger.info("Запуск метода encodeQuery с запросом: {}", query);
        return URLEncoder.encode(query, "UTF-8");
    }

    /**
     * Выполняет внутренний запрос по указанному URI и обрабатывает ответ.
     *
     * @param uri URI для выполнения запроса
     * @return объект типа JsonObject, содержащий ответ API
     * @throws InvalidResponseException если произошла ошибка во время выполнения внутреннего запроса
     */
    public JsonObject innerRequest(String uri) throws InvalidResponseException{
        logger.info("Запуск метода innerRequest с URI: {}", uri);
        try {
            HttpGet httpGet = new HttpGet(uri);
            return getRequest(httpGet);
        } catch (InvalidResponseException e) {
            logger.error("Ошибка при внутреннем запросе для URI: {}", uri);
            throw new InvalidResponseException("Ошибка выполнения внутреннего запроса: " + uri);
        }
    }
}
