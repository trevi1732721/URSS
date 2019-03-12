
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Camera;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.awt.*;

public class Controller {
    public Ellipse Visée(Double posX, Double posY){
        Ellipse balle = new Ellipse(posX,posY,2,2);
        balle.setFill(Color.WHITE);
        balle.setStroke(Color.BLACK);
        balle.setTranslateZ(400);
        return balle;
    };
    public Double Gravité(double poudreNoir,double masseBalle ,double distance ){
        double E = poudreNoir*(1/*constante*/);
        double v = E*masseBalle;
        double T = (distance/v);
        double g = 2;
        double deltaY = T+((g*(T*T))/2) ;//delta Y = vi*temps+((a*temps^2)/2)
        return deltaY;
    };
    public void Respiration(Camera camera){
        TranslateTransition bouge = new TranslateTransition(Duration.seconds(1.5),camera);
        bouge.setByY(-10);
        bouge.setAutoReverse(true);
        bouge.setCycleCount(Timeline.INDEFINITE);
        bouge.play();
    }
    public void Visée(Camera centreVisée){
        TranslateTransition bouge = new TranslateTransition(Duration.seconds(1.5),centreVisée);
        bouge.setToY(MouseInfo.getPointerInfo().getLocation().getY());
        bouge.setToX(MouseInfo.getPointerInfo().getLocation().getX());
        bouge.setCycleCount(Timeline.INDEFINITE);
        bouge.play();
    }
}