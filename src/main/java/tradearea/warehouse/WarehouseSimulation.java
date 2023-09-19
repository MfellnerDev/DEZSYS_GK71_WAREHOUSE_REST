package tradearea.warehouse;

import tradearea.model.Product;
import tradearea.model.WarehouseData;

import java.util.UUID;

public class WarehouseSimulation {

	String[] productNames = {
			"Milch",
			"Eier",
			"Brot",
			"Reis",
			"Nudeln",
			"Kartoffeln",
			"Tomaten",
			"Bananen",
			"Äpfel",
			"Hühnchen",
			"Rindfleisch",
			"Lachs",
			"Butter",
			"Käse",
			"Joghurt"
	};

	String[] productCategories = {
			"Milchprodukte",
			"Eier",
			"Brot und Backwaren",
			"Getreide und Reis",
			"Nudeln",
			"Gemüse",
			"Obst",
			"Obst",
			"Obst",
			"Fleisch",
			"Fleisch",
			"Fisch",
			"Milchprodukte",
			"Milchprodukte",
			"Milchprodukte"
	};

	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		double rounded = Math.round(number * 100.0) / 100.0; 
		return rounded;
		
	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		Long rounded = Math.round(number); 
		return rounded.intValue();

	}

	private int[] getFourRandomNumbers()	{
		if (this.productNames.length == this.productCategories.length)	{
			int firstRandomNumber = getRandomInt(0, this.productNames.length-1);
			int secondRandomNumber = getRandomInt(0, this.productNames.length-1);
			int thirdRandomNumber = getRandomInt(0, this.productNames.length-1);
			int fourthRandomNumber = getRandomInt(0, this.productNames.length-1);
			return new int[]{firstRandomNumber, secondRandomNumber, thirdRandomNumber, fourthRandomNumber};
		}
		return new int[]{0, 0, 0, 0};
	}

	private Product[] getFourProductData()	{
		int[] randomNumbers = getFourRandomNumbers();
		Product[] products = new Product[4];
		products[0] = new Product(UUID.randomUUID(), this.productNames[randomNumbers[0]], this.productCategories[randomNumbers[0]], getRandomInt(75, 200), "1KG/Packung");
		products[1] = new Product(UUID.randomUUID(), this.productNames[randomNumbers[1]], this.productCategories[randomNumbers[1]], getRandomInt(1000, 2500), "1L/Packung");
		products[2] = new Product(UUID.randomUUID(), this.productNames[randomNumbers[2]], this.productCategories[randomNumbers[2]], getRandomInt(2000, 7300), "3KG/Packung");
		products[3] = new Product(UUID.randomUUID(), this.productNames[randomNumbers[3]], this.productCategories[randomNumbers[3]], getRandomInt(100, 1000), "500ML/Packung");
		return products;
	}
	
	public WarehouseData getData(UUID inID) {
		
		WarehouseData data = new WarehouseData();
		// parse String into UUID object, we currently want a static id
		data.setWarehouseID(inID);
		data.setWarehouseName( "Linz Bahnhof" );
		data.setWarehouseCity("Linz");
		data.setWarehouseAddress("WhoKnows Straße 12");
		data.setWarehouseCountry("Autria");
		data.setWarehousePostalCode(4000);
		data.setProductData(getFourProductData());

		return data;
	}

}
