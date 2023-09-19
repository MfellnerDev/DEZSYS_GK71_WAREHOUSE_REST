package tradearea.warehouse;

import tradearea.model.Product;
import tradearea.model.WarehouseData;

import java.util.UUID;

public class WarehouseSimulation {
	
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

	public Product[] getFourProductData()	{
		Product[] products = new Product[4];
		products[0] = new Product(UUID.randomUUID(), "Frische Orangen", "FRUECHTE", 200, "1KG/Packung");
		products[1] = new Product(UUID.randomUUID(), "Vitaminsaft", "GETRAENKE", 2500, "1L/Packung");
		products[2] = new Product(UUID.randomUUID(), "Kartoffeln", "GEMUESE", 7300, "3KG/Packung");
		products[3] = new Product(UUID.randomUUID(), "Dosenbier", "ALKHOLISCHE GETRAENKE", 1000, "500ML/Packung");
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
