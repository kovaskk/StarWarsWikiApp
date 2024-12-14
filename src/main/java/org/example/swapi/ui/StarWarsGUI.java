package org.example.swapi.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.swapi.service.QuerySwitcher;
import org.example.swapi.service.SWAPIService;
import org.example.swapi.service.StarWarsAPI;
import org.example.swapi.service.Dictionary;

/**
 * Графический пользовательский интерфейс (GUI) для взаимодействия с Star Wars API.
 * Позволяет искать информацию о персонажах, планетах и кораблях.
 */
public class StarWarsGUI extends Application {

    private static final Logger logger = LogManager.getLogger(StarWarsGUI.class);

    private final SWAPIService swapiService = new SWAPIService(new StarWarsAPI(), new Dictionary());

    /**
     * Инициализирует и запускает интерфейс приложения.
     *
     * @param primaryStage главное окно JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            TextField searchField = new TextField();
            searchField.setPromptText("Введите запрос на русском или английском языке...");

            TextArea resultArea = new TextArea();
            resultArea.setEditable(false);
            resultArea.setWrapText(true);

            Button searchButton = new Button("Найти");
            Button helpButton = new Button("Помощь");
            Button queryListButton = new Button("Список запросов");


            searchButton.setOnAction(event -> {
                String query = searchField.getText();
                if (query.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Внимание", "Запрос не может быть пустым.");
                    return;
                }

                try {
                    String result = fetchResult(query);
                    resultArea.setText(result);
                } catch (IllegalArgumentException e) {
                    logger.error("Ошибка пользователя: {}", e.getMessage());
                    showAlert(Alert.AlertType.WARNING, "Ошибка", e.getMessage());
                } catch (Exception e) {
                    logger.error("Непредвиденная ошибка при выполнении запроса: {}", query, e);
                    showAlert(Alert.AlertType.ERROR, "Критическая ошибка", "Произошла непредвиденная ошибка. Попробуйте позже.");
                }
            });

            helpButton.setOnAction(event -> showHelpDialog());

            queryListButton.setOnAction(event -> showQueryListDialog());


            VBox root = new VBox(10, searchField, searchButton, resultArea, helpButton, queryListButton);
            root.setPadding(new Insets(15));
            root.setStyle("-fx-background-color: #2b2b2b;");

            searchField.setStyle("-fx-text-fill: white; -fx-background-color: #3c3f41; -fx-prompt-text-fill: gray;");
            resultArea.setStyle("-fx-text-fill: white; -fx-control-inner-background: #3c3f41;");
            searchButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            helpButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            queryListButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");


            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("StarWarsWiki");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            logger.fatal("Критическая ошибка при инициализации приложения.", e);
            showAlert(Alert.AlertType.ERROR, "Ошибка приложения", "Критическая ошибка инициализации приложения.");
        }
    }

    private final QuerySwitcher querySwitcher = new QuerySwitcher(swapiService);

    /**
     * Выполняет запрос и возвращает результат.
     *
     * @param query запрос пользователя
     * @return результат выполнения запроса в виде строки
     */
    private String fetchResult(String query) {
        return querySwitcher.handleQuery(query);
    }

    /**
     * Отображает справочную информацию о доступных запросах.
     */
    private void showHelpDialog() {
        try {
            Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
            helpAlert.setTitle("Справка");
            helpAlert.setHeaderText("Возможные запросы");
            helpAlert.setContentText("""
                    Введите название персонажа, планеты или корабля на русском или английском языке. Например:
                    - Люк Скайуокер - Luke Skywalker
                    - Tatooine - Tatooine
                    - Millennium Falcon - Millennium Falcon""");
            helpAlert.showAndWait();
        } catch (Exception e) {
            logger.error("Ошибка при отображении справки.", e);
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось показать справку.");
        }
    }

    /**
     * Отображает список доступных запросов, разделённых по категориям.
     */
    private void showQueryListDialog() {
        try {
            Alert queryListAlert = new Alert(Alert.AlertType.INFORMATION);
            queryListAlert.setTitle("Список возможных запросов");
            queryListAlert.setHeaderText("Доступные запросы:");
            queryListAlert.setContentText(
                    """
                            Персонажи:
                            - Люк Скайуокер - Luke Skywalker
                            - Дарт Вейдер - Darth Vader
                            - Лея Органа - Leia Organa
                            - Хан Соло - Han Solo
                            - Чубакка - Chewbacca
                            - Р2-Д2 - R2-D2
                            - С-3ПО - C-3PO
                            - Йода - Yoda
                            - Оби-Ван Кеноби - Obi-Wan Kenobi
                            - Палпатин - Palpatine
                            
                            Планеты:
                            - Татуин - Tatooine
                            - Алдероан - Alderaan
                            - Хот - Hoth
                            - Дагоба - Dagobah
                            - Эндор - Endor
                            - Корусант - Coruscant
                            - Набу - Naboo
                            - Джакку - Jakku
                            - Мустафар - Mustafar
                            - Суллуст - Sullust
                            
                            Корабли:
                            - Тысячелетний Сокол - Millennium Falcon
                            - X-крыло - X-Wing
                            - TIE-истребитель - TIE Advanced x1
                            - Имперский шаттл - Imperial Shuttle
                            - Звезда Смерти - Death Star
                            - Раб 1 - Slave 1
                            - Транспорт Повстанцев - Rebel Transport
                            - Десантный корабль класса Сентинел - Sentinel-class landing craft
                            - Звёздный разрушитель - Star Destroyer
                            - Палач - Executor
                            """);
            queryListAlert.showAndWait();
        } catch (Exception e) {
            logger.error("Ошибка при отображении списка запросов.", e);
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось показать список запросов.");
        }
    }

    /**
     * Показывает предупреждающее или информационное окно.
     *
     * @param type    тип окна (информация, предупреждение, ошибка)
     * @param title   заголовок окна
     * @param message текст сообщения
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Метод для запуска приложения.
     *
     * @param args аргументы командной строки
     */
    public static void startApplication(String[] args) {
        launch(args); // Вызов метода launch() для старта JavaFX-приложения
    }

}


