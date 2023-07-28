module com.example.conversordemonedaalurachallengesone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.conversordemonedaalurachallengesone to javafx.fxml;
    exports com.example.conversordemonedaalurachallengesone;
    exports com.example.conversordemonedaalurachallengesone.controllers;
    opens com.example.conversordemonedaalurachallengesone.controllers to javafx.fxml;
}