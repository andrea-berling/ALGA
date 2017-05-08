package Graphics;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	Scene scene;
	Button play; //play the algorithm (select the speed in a menù)
	Button back; //back one step
	Button next; //next step
	Button pause; //pause the algorithm
	ArrayList<Comparable> input=new ArrayList<Comparable>();
	
	public static void main (String args[]){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		//set the window as primary stage
		window=primaryStage;
		window.setTitle("ALGA - ALGorithm Animation");
		window.setOnCloseRequest(e->{
			e.consume();
			closeProgram();	
		});
		
		TextArea inputtext=new TextArea();
		inputtext.setEditable(false);
		inputtext.setText("Data:\n");
		inputtext.setPrefSize(300, 300);
		inputtext.setMaxHeight(300);
		
		//Top Menu
		BorderPane topMenu=new BorderPane();
		//MenuBar
		MenuBar menu=this.setMenuBar(inputtext);
		//FlowBar
		HBox flowbar=this.setFlowControl();
		
		//Window's layout
		BorderPane layout = new BorderPane();
		
		//set topMenu
		topMenu.setLeft(menu);
		topMenu.setCenter(flowbar);
		
		layout.setTop(topMenu);
		layout.setLeft(inputtext);
		
		scene=new Scene(layout,1500,800);
		window.setScene(scene);
		window.show();
		
	}
	
	public MenuBar setMenuBar(TextArea inputtext){
			//File Menù
				Menu file=new Menu("File");
				MenuItem load = new MenuItem("Load Input...");
				file.getItems().add(load); //load input
				MenuItem settings=new MenuItem("Settings...");
				file.getItems().add(settings);
				MenuItem exit=new MenuItem("Exit");
				file.getItems().add(exit); //exit the program
				load.setOnAction(e->{
					input=inputSelector.display();
					for(int i=0;i<input.size();i++){
						inputtext.appendText(input.get(i)+"\n");
					}
					inputtext.appendText("\n\nInput Dimension: "+input.size());
				});
				settings.setOnAction(e->settingsWindow());
				exit.setOnAction(e->closeProgram());
				
				//Help Menù
				Menu help=new Menu("Help");
				MenuItem about = new MenuItem("About...");
				help.getItems().add(about); //load input
				MenuItem git=new MenuItem("Git...");
				help.getItems().add(git);
				about.setOnAction(e->About.display());
				
				 git.setOnAction(e->webPage.display("https://github.com/BaL97")	);
				 
				//main Menù Bar
				MenuBar menu=new MenuBar();	
				menu.getMenus().addAll(file,help);
		return menu;
	}
	
	private Object settingsWindow() {
		// TODO Auto-generated method stub
		return null;
	}
		
	private void closeProgram(){
		boolean x=ConfirmBox.display("Exit", "Are you sure?");
		if(x)
			window.close();
	}
	
	public HBox setFlowControl(){
		
		Image nextImg=new Image("img/next.png");		
		Image backImg=new Image("img/back.png");
		Image stopImg=new Image("img/stop.png");
		Image pauseImg=new Image("img/pause.png");
		Image playImg=new Image("img/play.png");
		
		Button back=new Button("",new ImageView(backImg));
		Button next=new Button("",new ImageView(nextImg));
		Button stop=new Button("", new ImageView(stopImg));
		Button play=new Button("",new ImageView(playImg));
		Button pause=new Button("", new ImageView(pauseImg));
		
		
		
		HBox menu=new HBox();
		menu.getChildren().addAll(back,stop,play,pause,next);
		return menu;
	}
}