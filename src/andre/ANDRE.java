/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package andre;

import java.awt.Desktop;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author DELL
 */
public class ANDRE extends Application {

    List<File> mylist = new ArrayList<File>();
    String destiny;
    ArrayList<String> checks = new ArrayList<String>();

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("ANDRE");


        final FileChooser fileChooser = new FileChooser();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        Text scenetitle = new Text("Choose picutres for resizing");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);



        final TextField userTextField = new TextField();
        grid.add(userTextField, 0, 1);

        Button one = new Button();
        one.setText("Choose picture(s)");

        EventHandler hand = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                configureFileChooser(fileChooser);
                List<File> list =
                        fileChooser.showOpenMultipleDialog(primaryStage);
                if (list != null) {
                    String title = "";
                    for (File file : list) {
                        mylist.add(file);
                        title += file.getAbsolutePath();


                    }
                    userTextField.setText(title);
                }
            }
        };

        one.setOnAction(hand);

        grid.add(one, 1, 1);

        Text or = new Text("OR");
    
        grid.add(or, 0, 2);


        final TextField multi = new TextField();
        grid.add(multi, 0, 4);

        final Button two = new Button();
        two.setText("Choose folder");
        two.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                final DirectoryChooser directoryChooser =
                        new DirectoryChooser();
                final File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);
                if (selectedDirectory != null) {
                    mylist = new ArrayList<>(Arrays.asList(selectedDirectory.listFiles()));

                    two.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });
        grid.add(two, 1, 4);

        final TextField desField = new TextField();
        grid.add(desField, 0, 5);



        Button des = new Button();
        des.setText("Choose destination folder: (containing your drawables folders)");

        des.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                final DirectoryChooser directoryChooser =
                        new DirectoryChooser();
                final File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);
                if (selectedDirectory != null) {
                    destiny = selectedDirectory.getAbsolutePath();
                    desField.setText(destiny);
                }

            }
        });
        grid.add(des, 1, 5);


        final CheckBox cb1 = new CheckBox("ldpi");
        cb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //To change body of generated methods, choose Tools | Templates.
                if (t1) {
                    checks.add(cb1.getText());
                } else {
                    checks.remove(cb1.getText());
                }

            }
        });
        grid.add(cb1, 1, 7);

        final CheckBox cb2 = new CheckBox("mdpi");
        cb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //To change body of generated methods, choose Tools | Templates.
                if (t1) {
                    checks.add(cb2.getText());
                } else {
                    checks.remove(cb2.getText());
                }

            }
        });
        grid.add(cb2, 1, 8);

        final CheckBox cb3 = new CheckBox("hdpi");
        cb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //To change body of generated methods, choose Tools | Templates.
                if (t1) {
                    checks.add(cb3.getText());
                } else {
                    checks.remove(cb3.getText());
                }

            }
        });
        grid.add(cb3, 1, 9);
        final CheckBox cb4 = new CheckBox("xhdpi");
        cb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //To change body of generated methods, choose Tools | Templates.
                if (t1) {
                    checks.add(cb4.getText());
                } else {
                    checks.remove(cb4.getText());
                }

            }
        });
        grid.add(cb4, 1, 10);
        final CheckBox cb5 = new CheckBox("xxhdpi");
        cb5.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //To change body of generated methods, choose Tools | Templates.
                if (t1) {
                    checks.add(cb5.getText());
                } else {
                    checks.remove(cb5.getText());
                }

            }
        });
        grid.add(cb5, 1, 11);
        Button btn = new Button("Resize");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (File f : mylist) {

                    resize(f, destiny, checks);
                }
                userTextField.setText("");
                multi.setText("");
                desField.setText("");
                /*Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Sucessful!");
                
                 alert.setContentText("You have sucessfully resized your pictures!");

                 alert.showAndWait();*/
            }
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 12);

        final Text sucess = new Text("");
        sucess.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(sucess, 0, 12, 2, 12);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (File f : mylist) {

                    resize(f, destiny, checks);
                }
                userTextField.setText("");
                multi.setText("");
                desField.setText("");
                sucess.setText("You have sucessfully resized your pictures!\nFind them in your destination folder\n"
                        + destiny + "\nEnter more to resize ");
                try {
                    Desktop.getDesktop().open(new File(destiny));
                    /*Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Sucessful!");
                   
                            alert.setContentText("You have sucessfully resized your pictures!");

                            alert.showAndWait();*/
                } catch (IOException ex) {
                    Logger.getLogger(ANDRE.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    private static void resize(File f, String destiny, ArrayList<String> a) {
        String s = f.getPath();
        OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
        int index2;
        switch (ostype) {
            case Windows: {
                index2 = s.lastIndexOf("\\");
                break;
            }
            default: {
                index2 = s.lastIndexOf("/");
                break;
            }

        }


        int index = s.lastIndexOf(".");
        String ext = s.substring(index + 1);
        String filename = s.substring(index2);
        try {
            BufferedImage originalImage = ImageIO.read(f);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            
            if(a.contains("ldpi")){
            BufferedImage resizeImageL = resizeImage(originalImage, type, 36, 36);

            ImageIO.write(resizeImageL, ext, new FileOutputStream(destiny + "\\drawable-ldpi" + filename));
            }
            if(a.contains("mdpi")){
            BufferedImage resizeImageM = resizeImage(originalImage, type, 48, 48);
            ImageIO.write(resizeImageM, ext, new FileOutputStream(destiny + "\\drawable-mdpi" + filename));
            }
            
            if(a.contains("hdpi")){
            BufferedImage resizeImageH = resizeImage(originalImage, type, 72, 72);
            ImageIO.write(resizeImageH, ext, new FileOutputStream(destiny + "\\drawable-hdpi" + filename));
            }
            
            if(a.contains("xhdpi")){
            BufferedImage resizeImageX = resizeImage(originalImage, type, 96, 96);
            ImageIO.write(resizeImageX, ext, new FileOutputStream(destiny + "\\drawable-xhdpi" + filename));
            }
            
            if(a.contains("xxhdpi")){
            BufferedImage resizeImageXX = resizeImage(originalImage, type, 144, 144);
            ImageIO.write(resizeImageXX, ext, new FileOutputStream(destiny + "\\drawable-xxhdpi" + filename));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int height, int width) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();


        return resizedImage;

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choose Picture(s)");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                 new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("WebP", "*.webp"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg"),
               
                
                new FileChooser.ExtensionFilter("BMP", "*.bmp"));
               
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
