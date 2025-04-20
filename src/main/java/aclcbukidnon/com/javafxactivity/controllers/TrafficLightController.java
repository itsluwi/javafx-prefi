package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLightController {

    private enum TrafficLightColor {
        STOP,
        HOLD,
        GO,
    }

    private TrafficLightColor currentColor = TrafficLightColor.STOP;

    @FXML
    private Circle stopLight;

    @FXML
    private Circle holdLight;

    @FXML
    private Circle goLight;

    private Timeline timeline;

    @FXML
    public void initialize() {
        startTimerForCurrentColor();
        updateLights();
    }

    private void startTimerForCurrentColor() {
        if (timeline != null) {
            timeline.stop();
        }

        Duration duration = switch (currentColor) {
            case STOP, GO -> Duration.minutes(1);
            default -> Duration.seconds(3);
        };

        timeline = new Timeline(
                new KeyFrame(duration, e -> {
                    changeToNextColor();
                    updateLights();
                    startTimerForCurrentColor();
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void changeToNextColor() {
        switch (currentColor) {
            case STOP:
                currentColor = TrafficLightColor.HOLD;
                break;
            case HOLD:
                currentColor = TrafficLightColor.GO;
                break;
            case GO:
                currentColor = TrafficLightColor.STOP;
                break;
        }
    }

    private void updateLights() {
        stopLight.setFill(currentColor == TrafficLightColor.STOP ? Color.RED : Color.web("#575757"));
        holdLight.setFill(currentColor == TrafficLightColor.HOLD ? Color.YELLOW : Color.web("#575757"));
        goLight.setFill(currentColor == TrafficLightColor.GO ? Color.GREEN : Color.web("#575757"));
    }
}
