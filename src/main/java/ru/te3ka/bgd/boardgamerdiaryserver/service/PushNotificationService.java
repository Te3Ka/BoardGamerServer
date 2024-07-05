package ru.te3ka.bgd.boardgamerdiaryserver.service;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.te3ka.bgd.boardgamerdiaryserver.model.ContactOneSignal;
import ru.te3ka.bgd.boardgamerdiaryserver.repository.ContactOneSignalRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Сервис для отправки push-уведомлений через OneSignal.
 *
 * Этот сервис отправляет push-уведомления в приложение через API OneSignal.
 */
@Service
public class PushNotificationService {
    private static final String REST_API_KEY = "ZmUzZWFkMTgtZjdiYy00NjEwLWJlZmUtM2RjMDVkZmMzNzM2";
    private static final String APP_ID = "eaebefa6-a7a5-43be-a8eb-7b4acac282b7";
    @Autowired
    private ContactOneSignalRepository contactOneSignalRepository;

    /**
     * Отправляет push-уведомление по приглашению на встречу.
     *
     * @param token Токен OneSignal для получения уведомлений.
     * @param title Заголовок уведомления.
     * @param body Текст уведомления.
     */
    public void sendPushNotificationToMeetingInvitation(String token, String title, String body) {
        try {
            HttpURLConnection connection = getHttpURLConnection();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("app_id", APP_ID);
            jsonObject.put("contents", new JSONObject().put("en", body));
            jsonObject.put("headings", new JSONObject().put("en", title));
            jsonObject.put("include_player_ids", new JSONArray().put(token.trim()));

            String jsonString = jsonObject.toString();
            System.out.println("Request JSON: " + jsonString);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonObject.toString().getBytes("UTF-8"));
            outputStream.close();

            int httpResponse = connection.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= 200 && httpResponse < 300) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                System.out.println("Response: " + jsonResponse);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                System.out.println("Error Response: " + jsonResponse);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Создает и настраивает соединение с API OneSignal.
     *
     * @return Конфигурированное {@link HttpURLConnection} для отправки запроса.
     * @throws IOException Если возникает ошибка при создании соединения.
     */
    private static @NotNull HttpURLConnection getHttpURLConnection() throws IOException {
        URL url = new URL("https://onesignal.com/api/v1/notifications");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Authorization", "Basic " + REST_API_KEY);
        connection.setRequestMethod("POST");
        return connection;
    }

    /**
     * Получает токен OneSignal по номеру телефона.
     *
     * @param phoneNumber Номер телефона.
     * @return Токен OneSignal, связанный с указанным номером телефона, или {@code null}, если токен не найден.
     */
    public String getTokenByPhoneNumber(String phoneNumber) {
        return contactOneSignalRepository.findByPhoneNumber(phoneNumber)
                .map(ContactOneSignal::getOneSignalToken)
                .orElse(null);
    }
}
