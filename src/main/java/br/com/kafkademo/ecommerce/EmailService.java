package br.com.kafkademo.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {

    // serviço para enviar um email assim que uma compra for realizada
    public static void main(String[] args) {
        var emailService = new EmailService();
        try (var service = new KafkaService(EmailService.class.getName(),
                "ECOMMERCE_SEND_EMAIL", emailService::parse,
                String.class)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("----------------------------------------");
        System.out.println("Send email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }

}
