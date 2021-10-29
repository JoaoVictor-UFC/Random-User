package com.joao.victor.random.user.v1.utils;

import com.google.gson.Gson;
import com.joao.victor.random.user.v1.dtos.CreateUserRequest;
import com.joao.victor.random.user.v1.dtos.ImportRequest;
import com.joao.victor.random.user.v1.dtos.UserResponse;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public @Data class ImportDateRandomUser implements Serializable {

    static String url = "https://randomuser.me/api/";

    public static List<CreateUserRequest> importDateRandomUser(Long importNumber) throws Exception {

        String urlForRequest = url + "?results=" + importNumber;

        try {

            URL url = new URL(urlForRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + connection.getResponseCode());

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String jsonEmString = convertJsonString(response);
            Gson gson = new Gson();
            ImportRequest r = gson.fromJson(jsonEmString, ImportRequest.class);

            return r.getResults();

        }catch (Exception e){
            throw new Exception("ERRO: " + e);
        }
    }

    public static String convertJsonString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonString += resposta;
        }
        return jsonString;
    }
}
