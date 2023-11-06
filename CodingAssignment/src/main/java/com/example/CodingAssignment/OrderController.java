package com.example.CodingAssignment;

import com.example.CodingAssignment.entity.Consumer;
import com.example.CodingAssignment.entity.Merchant;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OrderController {
    private static final String SCALAPAY_API_BASE_URL = "https://integration.api.scalapay.com/v2";
    private static final String SCALAPAY_API_KEY = "qhtfs87hjnc12kkos"; // _API_KEY

    @PostMapping("/create-order")
    public String createOrder(@org.springframework.web.bind.annotation.RequestBody Order order) {

        try {
            OkHttpClient client = new OkHttpClient();

            // Chuyển đổi đối tượng đặt hàng thành JSON sử dụng một thư viện JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);

            // Tạo một request body với nội dung JSON
            RequestBody requestBody = RequestBody.create(orderJson, MediaType.parse("application/json"));

            // Tạo một yêu cầu POST đến API của Scalapay
            Request request = new Request.Builder()
                    .url(SCALAPAY_API_BASE_URL + "/orders")
                    .addHeader("Authorization", "Bearer " + SCALAPAY_API_KEY)
                    .post(requestBody)
                    .build();

            // Gửi yêu cầu và lấy phản hồi từ API
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Lấy URL thanh toán từ phản hồi
                String responseBody = response.body().string();
                return responseBody;
            } else {
                return "Lỗi khi tạo đơn hàng";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi tạo đơn hàng";
        }
    }
}
