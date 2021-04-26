/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapp;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * FXML Controller class
 *
 * @author sjean
 */
public class FXMLDocumentController implements Initializable {

    
    private String selectedShape="LINE";
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
        // TODO
    }    

    @FXML
    private void setShape(ActionEvent event) {
        Button btn =(Button)event.getSource();
        
        switch(btn.getText()){
            case "Line": selectedShape="LINE";  break;
            case "Rectangle": selectedShape="RECT";  break;
            case "Circle": selectedShape="CIRCLE";  break;
            case "Eraser": selectedShape="ERASER";  break;
            
            
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
        if (selectedShape=="ERASER"){
            gc.setStroke(Color.WHITESMOKE);
            gc.setLineWidth(mSlider.getValue());
            gc.strokeRect(srtX, srtY, endX, endX);
            
        }
        else{   
        gc.setStroke(selectedColor);
        System.out.println(""+selectedColor);
        gc.setLineWidth(mSlider.getValue());
        switch(selectedShape){
            case    "LINE": gc.strokeLine(srtX, srtY, endX, endX);  break;
            case    "RECT": gc.strokeRect(srtX, srtY, endX, endX); break;
            case    "CIRCLE": gc.strokeOval(srtX, srtY, endX, endX);  break;
           
        }
        
        
        
        }
        
        
    }



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
              
    JFileChooser saveFile = new JFileChooser();
    saveFile.showSaveDialog(null);      

    

    }

    
    }
