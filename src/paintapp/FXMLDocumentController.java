/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapp;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * FXML Controller class
 *
 * @author sjean
 */
public class FXMLDocumentController implements Initializable {

    
    private String selectedShape="";
    private Color selectedColor=Color.BLACK;
    double srtX=0, srtY=0;
    double endX=0, endY=0;
    
    
    private Label label;
    @FXML
    private ColorPicker mColorPicker;
    private Group c;
    @FXML
    private Slider mSlider;
    @FXML
    private Canvas mCanvas;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem printFile;
    @FXML
    private MenuItem closeFile;
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
        
    }    

    @FXML
    private void setShape(ActionEvent event) {
        Button btn =(Button)event.getSource();
        
        switch(btn.getText()){
            case "Line": selectedShape="LINE";  break;
            case "Rectangle": selectedShape="RECT";  break;
            case "Circle": selectedShape="CIRCLE";  break;
            case "Eraser": selectedShape="ERASER";  break;
            case "Draw":  selectedShape="DRAW"; break;
            
            
            
        }
    }

    
   
    @FXML
    private void selectColor(ActionEvent event) {
        selectedColor = mColorPicker.getValue();
            }
    
 
    @FXML
    private void startShape(MouseEvent event) {
            srtX=event.getX();
            srtY=event.getY();
    
    }
    @FXML
    private void startDraw(MouseEvent event) {
        endX=event.getX();
        endY=event.getY();
        GraphicsContext gc= mCanvas.getGraphicsContext2D();
        gc.setStroke(selectedColor);
        System.out.println(""+selectedColor);
        gc.setLineWidth(mSlider.getValue());
        switch(selectedShape){
            case    "LINE": gc.strokeLine(srtX, srtY, endX, endY);  break;
            case    "RECT": gc.strokeRect(srtX, srtY, endX, endY); break;
            case    "CIRCLE":  gc.strokeOval(srtX, srtY, endX-srtX, endY-srtY);  break;
            case    "DRAW": {
                    if (selectedShape == "DRAW")
                    {    
                    mCanvas.setOnMouseDragged(e-> {
                    double size = mSlider.getValue()+10;
                    double x = e.getX() - size /2;
                    double y = e.getY() - size /2;
                    gc.setFill(selectedColor);
                    gc.fillRect(x, y, size, size);
                   });
                   }
            }        break;        
            case    "ERASER": {
                mCanvas.setOnMouseDragged(e-> {});
            gc.setStroke(Color.WHITESMOKE);
            gc.setLineWidth(mSlider.getValue());
            gc.strokeRect(srtX, srtY, endX, endY);
            
            
        }   break;
            }
           
        }
         //gc.setLineWidth(mSlider.getValue());
        
        
        
        
        
    



    @FXML
    private void openFile(ActionEvent event) {

        
    }

    @FXML
    private void printFile(ActionEvent event) {
        
        
        
    }

    @FXML
    private void closeFile(ActionEvent event) {
        
        System.exit(0);
    }

    @FXML
    private void saveFile(ActionEvent event) {
              
        try{
            WritableImage snapshot = mCanvas.snapshot(null, null);
            
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png",new File("paint.png"));
        }catch (Exception e){
            System.out.println("Failed to save image: " + e);
        }

    
    }

}
  