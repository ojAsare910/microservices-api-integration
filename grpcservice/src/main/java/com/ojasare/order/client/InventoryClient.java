package com.ojasare.order.client;

import com.ojasare.order.service.OrderRequest;
import com.ojasare.order.service.OrderResponse;
import com.ojasare.order.service.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class InventoryClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);

        OrderRequest request = OrderRequest.newBuilder()
                .setOrderId("AMT4264")
                .setSkuCode("Latitude7400")
                .setQuantity(2)
                .setPrice(3500)
                .build();

        OrderResponse response = stub.createOrder(request);
        System.out.println("Order Id: " + response.getOrderId());
        System.out.println("Order Status: " + response.getStatus());

        channel.shutdown();
    }
}
