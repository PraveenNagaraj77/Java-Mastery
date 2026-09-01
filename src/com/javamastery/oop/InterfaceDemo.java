package com.javamastery.oop;

public class InterfaceDemo {
    interface NotificationService{
        void send(String message);
    }

    static class EmailNotification implements NotificationService{
        public void send(String message){
            System.out.println("Sending Email "+message);
        }
    }

    static class SmsNotification implements NotificationService{
        public void send(String message){
            System.out.println("Sending SMS : "+message);
        }
    }

    static class PushNotification implements NotificationService{
        public void send(String message){
            System.out.println("Sending Push Notifications : "+message);
        }
    }

    static class WhatsAppNotification
            implements NotificationService {

        @Override
        public void send(String message) {

            System.out.println(
                    "Sending WhatsApp: " + message
            );
        }
    }

    public static void main(String[] args) {
        NotificationService notification;

        notification = new EmailNotification();
        notification.send("Your Order has been Shipped");

        notification = new SmsNotification();
        notification.send("Your OTP is 482931");

        notification = new PushNotification();
        notification.send("You have a New Message");


        notification =
                new WhatsAppNotification();

        notification.send(
                "Your order is ready"
        );

    }



}

