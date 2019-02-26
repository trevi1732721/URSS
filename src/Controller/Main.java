package Controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class Main extends Application{
    public static void main(String[] args) {launch(args);}
    public void start(Stage stage1){
        Ellipse cible = new Ellipse(200,200,100,100);
        Rectangle tourCible = new Rectangle(50,50,310,310);
        tourCible.setOpacity(0);
        Ellipse cible2 = new Ellipse(500,200,100,100);
        final Group[] root = new Group[2];
         Controller controle= new Controller();
        root[0] = new Group(cible,tourCible);
        root[1] = new Group(cible2);


        stage1.setTitle("URSS");
        stage1.setHeight(500);
        stage1.setWidth(800);
        stage1.setResizable(true);

        Scene scene1 = new Scene(root[0]);
        stage1.setScene(scene1);
        stage1.show();

        tourCible.setOnMouseClicked(event -> {
            double positionX = event.getSceneX();
            double positionY = event.getSceneY();
            root[1].getChildren().add(controle.Visée(positionX+300,positionY+(controle.Gravité(10,10,0))));
            root[0].getChildren().setAll(cible, controle.Visée((positionX),positionY+(controle.Gravité(10,10,0))), root[1],tourCible);
            System.out.println("Hello");
        });
    }
}
