import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


public class Main extends Application{

    public static void main(String[] args) {launch(args);}
    public void start(Stage stage1){
        Ellipse cible = new Ellipse(395,215,35,35);
        cible.setTranslateZ(400);
        Rectangle tourCible = new Rectangle(330,150,130,130);
        tourCible.setOpacity(0);
        tourCible.setTranslateZ(400);
        Ellipse cible2 = new Ellipse(150,150,35,35);
        cible2.setTranslateZ(400);
        Rectangle info = new Rectangle(-100,-100,908,800);
        info.setTranslateZ(400);
        info.setOpacity(0.5);
        Label urss = new Label("Ultimate Rifle Shooting Simulation");
        urss.setTranslateZ(400);
        urss.setTranslateY(80);
        urss.setTranslateX(295);
        urss.setScaleX(3);
        urss.setScaleY(3);


        final PhongMaterial ciel = new PhongMaterial();
        final PhongMaterial couleurTable = new PhongMaterial();
        couleurTable.setDiffuseColor(Color.SADDLEBROWN);
        couleurTable.setSpecularColor(Color.SADDLEBROWN);

        ciel.setDiffuseColor(Color.LIGHTBLUE);
        ciel.setSpecularColor(Color.GREEN);
        Box murGauche = new Box(20,500,2000);
        Box toit = new Box(800,10,2000);
        Box table = new Box(800,40,100);
        Box piedsChaiseGauche = new Box(20,125,100);
        Box piedsChaiseDroit = new Box(20,125,100);
        piedsChaiseGauche.setTranslateY(380);
        piedsChaiseGauche.setTranslateX(200);
        piedsChaiseGauche.setMaterial(couleurTable);
        piedsChaiseDroit.setTranslateY(380);
        piedsChaiseDroit.setTranslateX(585);
        piedsChaiseDroit.setMaterial(couleurTable);
        table.setMaterial(couleurTable);
        table.setTranslateX(200);
        table.setTranslateY(300);
        toit.setTranslateX(200);
        toit.setMaterial(ciel);
        murGauche.setTranslateX(180);
        murGauche.setTranslateY(200);
        murGauche.setTranslateZ(100);
        Box murDroit = new Box(20,500,2000);
        murDroit.setMaterial(ciel);
        murGauche.setMaterial(ciel);
        murDroit.setTranslateX(605);
        murDroit.setTranslateY(200);
        murDroit.setTranslateZ(100);
       /* PointLight light = new PointLight(Color.rgb(0,204,204));
        light.setOpacity(0.5);
        light.setTranslateZ(200);*/

        final Group[] root = new Group[2];
        Controller controle= new Controller();
        root[0] = new Group(murGauche,murDroit/*,light*/,toit,table,cible,piedsChaiseGauche,piedsChaiseDroit,tourCible/* cible2*/);
        root[1] = new Group(info,cible2,urss);
        Camera camera1 = new PerspectiveCamera();
        camera1.setTranslateZ(200);
        Camera camera2 = new PerspectiveCamera();
        camera2.setTranslateZ(200);


        stage1.setTitle("URSS");
        stage1.setHeight(500);
        stage1.setWidth(800);
        stage1.setResizable(true);
        Scene scene1 = new Scene(root[0],500,800, true, SceneAntialiasing.BALANCED);
        Scene scene2 = new Scene(root[1],500,800, true ,SceneAntialiasing.BALANCED);
        scene1.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.I){
                stage1.setScene(scene2);
            }
            else if(event.getCode() == KeyCode.getKeyCode("A")){
                controle.Visée(camera1);
            }
        });
        scene2.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.I){
                stage1.setScene(scene1);
                root[0].getChildren().setAll(murGauche/*,light*/,murDroit,toit,table,cible,piedsChaiseGauche,piedsChaiseDroit,tourCible);
            }
        });

        scene1.setFill(Color.DARKOLIVEGREEN);
        scene1.setCamera(camera1);
        scene2.setCamera(camera2);

        stage1.setScene(scene1);
        stage1.show();

        controle.Respiration(camera1);
        root[1].setTranslateZ(-400);

        tourCible.setOnMouseClicked(event -> {
             if (event.isAltDown()){
                 double positionX = event.getX();
                 double positionY = event.getY();
                 root[1].getChildren().add(controle.Visée(positionX-245,positionY-65+(controle.Gravité(10,10,500))));
                 root[0].getChildren().setAll(murGauche/*,light*/,murDroit,toit,table,cible,piedsChaiseGauche,piedsChaiseDroit, controle.Visée((positionX),positionY+(controle.Gravité(10,10,500))),tourCible);
             }
        });

      //  Timeline visée = new Timeline();
      //  visée.setCycleCount(Timeline.INDEFINITE);
      //  KeyValue kv = new KeyValue(controle.Visée(camera1));
      //  KeyFrame kf = new KeyFrame(Duration.seconds(3), kv);
      //  visée.getKeyFrames().add(kf);
      //  visée.play();
    }

}
