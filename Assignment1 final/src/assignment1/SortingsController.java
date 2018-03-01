
package assignment1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SortingsController implements Initializable {
    private SortingStrategy sortingMethod; //take reference from interface
    private Model model; //reference from model
    private double viewHeight;
    private double viewWidth;
    private int arraySize;
    private int intArray[];
    
    @FXML
    private AnchorPane view;
    @FXML
    private Button sortButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button exitBtn;
    @FXML
    private Slider sliderBar;
    @FXML
    private Label arrayNumLabel;
    @FXML
    public  ComboBox sortComboBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
  
    //initialize stuff when program starts
    public void setData(){
        sortComboBox.getItems().clear();
        sortComboBox.getItems().addAll("SelectionSort","MergeSort");
        this.viewHeight = view.heightProperty().getValue();
        this.viewWidth = view.widthProperty().getValue();
       
        sliderBar.setMin(35);
        sliderBar.setMax(150);
        sliderBar.setShowTickLabels(true);  
        sliderBar.setShowTickMarks(true);
        sliderBar.setMajorTickUnit(10);
        sliderBar.setBlockIncrement(1);   
        
        //initialize values when program starts
        arraySize = (int) sliderBar.getValue();
        arrayNumLabel.setText(Integer.toString(arraySize));
        model = new Model(arraySize);
        intArray = model.getUnsortedList();
        setSortStrategy();
        sortComboBox.getSelectionModel().select(0);
        
        //draws rectangles slowly
        Thread printThread = new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> drawRectangles(intArray));
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                System.out.println("something went wrong");
            }
        });
        printThread.setDaemon(true);   //this thread stops when all normal threads stops running, it is low priority
        printThread.start();
    }
   @FXML
   public void arraySizeBar_ValueChanged(){
        arraySize = (int)sliderBar.getValue();
        arrayNumLabel.setText(Integer.toString(arraySize)); 
        reset(arraySize);
    }
   @FXML
   public void exit(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
   }
   @FXML
    public void sortBtn_Click(){
       Thread sortingThread = new Thread(() -> {
            sortingMethod.sortingMethod(intArray); //call respective sorts
        });
       sortingThread.start();
    }
    @FXML
    public void resetBtn_Click(){
      reset(arraySize);
    }
    
    public void reset(int size){
        model.setArraySize(size);
        arraySize=size;
        model.reset(arraySize);
        intArray = model.getUnsortedList(); 
    }

    public void drawRectangles(int arr[]){
       view.getChildren().clear();
       double paneWidth = view.widthProperty().getValue(), paneHeight = view.heightProperty().getValue(),recHeight, recWidth;
       
        for (int i =0;i<arraySize;i++){
            Rectangle temp = new Rectangle();
            recHeight = (paneHeight)*(double)arr[i]/arraySize;
            recWidth = paneWidth/arraySize;
            temp.setWidth(recWidth);
            temp.setHeight(recHeight);
            temp.setX(recWidth*i);
            temp.setY(paneHeight-recHeight);
            temp.setFill(Color.RED);  
            view.getChildren().add(temp);
        }
    }
    //select which sorting strategy to use
    public void setSortStrategy(){
        String sortSelect = (String) sortComboBox.getValue();
        if(sortSelect=="SelectionSort"){
            this.sortingMethod = new SelectionSort();
        }
        else{
            this.sortingMethod = new MergeSort();
        }
    }
}
