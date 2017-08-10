import controller.ControllerException;
import controller.GameController;
import view.UI;

public class launcher {
	
	public static void main(String args[]) 
	{
		UI view = new UI();
		GameController controller;
		try {
			controller = new GameController(view);
			controller.startView();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
	
}
//controller maken
//ui maken
//facade maken

//controller krijgt ui en facade mee
//vanuit controller wordt alles beheerst
//bij invoer in ui => controller ontvangt gegevens => geeft die door aan facade 
	//=> facade roept klassen en fucnties aan die nodig zijn om functies uit te voeren
//GameBoardPanel is observer van de klasse waar we schepen aanmaken en op de juiste coordinaten zetten 
	//(zo krijg je realtime updates in je ui scherm)

//unlocking dice still neds to be done
//implement, states and turns - state turn (foreach player turn) start with 1 loop add more after score system
//implement categories and scoring system - enum categories
