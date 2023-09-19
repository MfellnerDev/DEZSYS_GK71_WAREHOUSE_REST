# Protokoll - DEZSYS_GK71_WAREHOUSE_REST

**Autor:** Manuel Fellner

**Version:** 19.09.2023

---

In diesem Protokoll werde ich alle von mir gemachten Schritte für die jeweilige Laborübung mittels Sätzen, Code-Snippets und Screenshots festhalten.



## 1. Benötigte Attribute / Model-Klassen hinzufügen



### 1.1 Bestandsaufnahme: Was benötigen wir?

Um der Angabe Gerecht zu werden, benötigen wir noch folgende Komponenten:

- `Klasse`: Product
  
  - Beinhaltet alle Details über unsere Produkte
  
  -  `Attribute`:
    
    - productID : UUID
    
    - productName : String
    
    - productCategory : String (-> später vielleicht auf ENUM umstellen)
    
    - productQuantity : int
    
    - productUnit : String (-> später vielleicht auf ENUM umstellen)

- `Attribute` für Klasse `WarehouseData`:
  
  - warehouseAddress : String
  
  - warehousePostalCode : int
  
  - warehouseCity : String
  
  - warehouseCountry : String
  
  - productData : Product[]



### 1.2 Hinzufügen der Attribute für die Klasse WarehouseData

Wir fügen jetzt folgendermaßen einfach die benötigten Attribute zur Klasse hinzu:

```java
	private String warehouseAddress;

	private int warehousePostalCode;

	private String warehouseCity;

	private String warehouseCountry;
```

- Nachdem wir die Attribute verändert haben, lassen wir uns mittels IntellIJ "Generate" Feature Getter- sowie Setter-Methoden für die neuen Attribute generieren

- Ebenso passen wir die `toString()` methode der Klasse an - dies machen wir ebenso mit dem "Generate" Feature.



Da wir jetzt neue Attribute haben, wäre es sinnvoll, die Generator-Methode für das Warehouse zu verändern:

```java
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
```

Nun haben wir die Methode mit den jeweiligen neuen Attributen erweitert.



Wenn wir jetzt die REST API neustarten und zu `http://localhost:8080/warehouse/469d7240-b974-441d-9562-2c56a7b28767/xml` navigieren, sehen wir die folgende Veränderung der Ausgabe:

```xml
<WarehouseData>
<warehouseID>469d7240-b974-441d-9562-2c56a7b28767</warehouseID>
<warehouseName>Linz Bahnhof</warehouseName>
<warehouseAddress>WhoKnows Straße 12</warehouseAddress>
<warehousePostalCode>4000</warehousePostalCode>
<warehouseCity>Linz</warehouseCity>
<warehouseCountry>Autria</warehouseCountry>
<timestamp>2023-09-19 15:35:46.685</timestamp>
</WarehouseData>
```

- Wie man hier sehen kann, hat das Hinzufügen neuer Attribute funktioniert! Diese sind jetzt nämlich hier in XML sichtbar.



Wir können natürlich auch zu `http://localhost:8080/warehouse/469d7240-b974-441d-9562-2c56a7b28767/data` navigieren, dann sehen wir die Ausgabe in JSON:

```json
{
"warehouseID":"469d7240-b974-441d-9562-2c56a7b28767",
"warehouseName":"Linz Bahnhof",
"warehouseAddress":"WhoKnows Straße 12",
"warehousePostalCode":4000,
"warehouseCity":"Linz",
"warehouseCountry":"Autria",
"timestamp":"2023-09-19 15:40:54.164"
}
```



### 1.3 Hinzufügen von benötigten Klassen

Jetzt fügen wir die benötigte Klasse hinzu.



wir haben jetzt eine Klasse namens `Product` mit den folgenden Attributen:

```java
    private UUID productID;

    private String productName;

    private String productCategory;

    private int productQuantity;

    private String productUnit;
```



## 2. Produkt-Objekte generieren

Jetzt haben wir die Grundstruktur - also die Klassen.

Im nächsten Schritt müssen wir noch vier Realitätsnahe Fake-Produkte für unser Warenhaus generieren, dies machen wir folgendermaßen:

```java
	public Product[] getFourProductData()	{
		Product[] products = new Product[4];
		products[0] = new Product(UUID.randomUUID(), "Frische Orangen", "FRUECHTE", 200, "1KG/Packung");
		products[1] = new Product(UUID.randomUUID(), "Vitaminsaft", "GETRAENKE", 2500, "1L/Packung");
		products[2] = new Product(UUID.randomUUID(), "Kartoffeln", "GEMUESE", 7300, "3KG/Packung");
		products[3] = new Product(UUID.randomUUID(), "Dosenbier", "ALKHOLISCHE GETRAENKE", 1000, "500ML/Packung");
		return products;
	}
```

- Diese Methode ist für den Anfang nichts anderes als eine Methode mit statischen Werten (ausgenommen der `productID`)

- Wie vorgegeben fügt diese Methode genau 4 Objekte in ein Array und gibt dieses zurück.



Wir müssen jetzt noch die `getData()` Methode für die Simulation folgendermaßen abändern:

```java
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
```

- Hier fügen wir alle in der obrigen Methode erstellen Produkt-Objekte zu unserem WarehouseData Objekt hinzu



## 3. Ergebnis

#### 3.1 JSON-Ansicht



Navigieren wir zu `http://localhost:8080/warehouse/469d7240-b974-441d-9562-2c56a7b28767/data`, sehen wir die folgende JSON-Ansicht:

```json
{"warehouseID":"469d7240-b974-441d-9562-2c56a7b28767","warehouseName":"Linz Bahnhof","warehouseAddress":"WhoKnows Straße 12","warehousePostalCode":4000,"warehouseCity":"Linz","warehouseCountry":"Autria","timestamp":"2023-09-19 16:09:05.532","productData":[{"productID":"53dbb174-01eb-43df-9092-49a613e49529","productName":"Frische Orangen","productCategory":"FRUECHTE","productQuantity":200,"productUnit":"1KG/Packung"},{"productID":"d8369b85-e681-436d-9451-f41b90174f34","productName":"Vitaminsaft","productCategory":"GETRAENKE","productQuantity":2500,"productUnit":"1L/Packung"},{"productID":"170ef4cd-13d3-41a9-a09e-53015742809d","productName":"Kartoffeln","productCategory":"GEMUESE","productQuantity":7300,"productUnit":"3KG/Packung"},{"productID":"a5e9824f-5a87-4e3f-b72d-500f722b6c2c","productName":"Dosenbier","productCategory":"ALKHOLISCHE GETRAENKE","productQuantity":1000,"productUnit":"500ML/Packung"}]}
```



#### 3.2 XML-Ansicht

Navigieren wir zu `http://localhost:8080/warehouse/469d7240-b974-441d-9562-2c56a7b28767/xml`, sehen wir folgende XML-Ansicht:

```xml
<WarehouseData>
<warehouseID>469d7240-b974-441d-9562-2c56a7b28767</warehouseID>
<warehouseName>Linz Bahnhof</warehouseName>
<warehouseAddress>WhoKnows Straße 12</warehouseAddress>
<warehousePostalCode>4000</warehousePostalCode>
<warehouseCity>Linz</warehouseCity>
<warehouseCountry>Autria</warehouseCountry>
<timestamp>2023-09-19 16:09:43.951</timestamp>
<productData>
<productData>
<productID>9cceba01-662c-474c-91f4-7aa41c338cd3</productID>
<productName>Frische Orangen</productName>
<productCategory>FRUECHTE</productCategory>
<productQuantity>200</productQuantity>
<productUnit>1KG/Packung</productUnit>
</productData>
<productData>
<productID>d89e80ca-ff44-4f5a-bdfa-183e77209211</productID>
<productName>Vitaminsaft</productName>
<productCategory>GETRAENKE</productCategory>
<productQuantity>2500</productQuantity>
<productUnit>1L/Packung</productUnit>
</productData>
<productData>
<productID>74ee2df1-29c6-43ad-b7c0-7cc155135213</productID>
<productName>Kartoffeln</productName>
<productCategory>GEMUESE</productCategory>
<productQuantity>7300</productQuantity>
<productUnit>3KG/Packung</productUnit>
</productData>
<productData>
<productID>b3e72e4e-7b04-4620-98fb-8213d51eab92</productID>
<productName>Dosenbier</productName>
<productCategory>ALKHOLISCHE GETRAENKE</productCategory>
<productQuantity>1000</productQuantity>
<productUnit>500ML/Packung</productUnit>
</productData>
</productData>
</WarehouseData>
```
