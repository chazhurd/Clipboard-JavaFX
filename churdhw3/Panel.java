/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package churdhw3;



import java.util.Optional;
import java.util.Random;
import javafx.geometry.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author vanil
 */
public class Panel extends Region {
    Subpanel sp;
    Region mReg;
    Boolean mSeen = false;
    Canvas mCan = new Canvas(200,340);
    Text mText = new Text();
    Clipboard mCB = Clipboard.getSystemClipboard();
    ClipboardContent mClip = new ClipboardContent();
    ContextMenu mRCM = new ContextMenu();
    
    
    private void onRC(MouseEvent MouseEvent) {
     //set items
     MouseButton check = MouseEvent.getButton();
     if(check.equals(MouseButton.SECONDARY) && mSeen == false){
     MenuItem changeText = new MenuItem("Chage Text");
     MenuItem changeColor = new MenuItem("Change Color");
     MenuItem copySettings = new MenuItem("Copy Settings");
     MenuItem pasteSettings = new MenuItem("Paste Settings");
     //set events
     changeText.setOnAction(Event->onText(MouseEvent));
     changeColor.setOnAction(Event->onColor());
     copySettings.setOnAction(Event->onCopy());
     pasteSettings.setOnAction(Event->onPaste());    
     mRCM.getItems().addAll(changeText, changeColor, copySettings, pasteSettings);
     mRCM.show(this,MouseEvent.getScreenX(), MouseEvent.getScreenY());
     mSeen = true;
        }else if(check.equals(MouseButton.SECONDARY)) {
     mRCM.show(this,MouseEvent.getScreenX(), MouseEvent.getScreenY());
    }
    }
    private void onText(MouseEvent MouseEvent) {
        TextInputDialog tID = new TextInputDialog();
        tID.setTitle("Input text");
        tID.setContentText("Enter a string: ");
        Optional<String> result = tID.showAndWait();
        this.setText(result.get());          
    }

    private void onColor() {
        Random r = new Random();
        float red = r.nextFloat();
        float green = r.nextFloat();
        float blue = r.nextFloat();
        Color c = Color.color(red, green, blue);
        sp.setFill(c);
    }

    private void onCopy() {
        Color c = (Color)sp.getFill();
        double r = c.getRed();
        double g = c.getGreen();
        double b = c.getBlue();
        String colors = (" " + r + " " + g + " " + b);
        System.out.print("/n " + colors);
        String temp = mText.getText() + " ###" + colors;
        mClip.putString(temp);
        mCB.setContent(mClip);
    }

    private void onPaste() {
        String[] colors = mCB.getString().split(" ");
        int index = 0;
        String t = new String("");
        for(int i=0;i<colors.length;i++){
            if(colors[i].equals("###")){
                index = i;
                break;
            }
            t += colors[i] + " ";
        }
        float r = Float.parseFloat(colors[++index]);
        float g = Float.parseFloat(colors[++index]);
        float b = Float.parseFloat(colors[++index]);
        
        Color col = Color.color(r,g,b);
        mText.setText(t);
        sp.setFill(col);
        
    }

    private void onRightClickMenu() {
        mRCM.hide();
    }
    
    
    static class Subpanel extends Rectangle{
        Subpanel(double width, double height){
            super(width,height);
        }
        @Override 
        public double minWidth(double height){
            return 10.0;
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }
    
    Panel(){
    sp = new Subpanel(20,20);
    this.onColor();
    sp.widthProperty().bind(this.widthProperty());
    sp.heightProperty().bind(this.heightProperty());
    mText.setText("Hello Moto");
    
    sp.setOnMousePressed(MouseEvent->onRC(MouseEvent));
    mRCM.setOnAction(ActionEvent->onRightClickMenu());
    this.setPrefSize(20000, 34000);
    this.getChildren().addAll(sp,mText);
}
    public void layoutChildren(){
        
        mText.relocate(this.getWidth()/2 - 25, this.getHeight()/2-10);
        this.layoutInArea(sp,0,0,this.getWidth(), this.getHeight(), 0, HPos.CENTER, VPos.CENTER);
        
    }
    public void setText(String s){
        mText.setText(s);
    }
    public void setCOlor(Color c){
        sp.setFill(c);
    }
 
}
    
