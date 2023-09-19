# Protokoll - DEZSYS_GK71_WAREHOUSE_REST

**Autor:** Manuel Fellner

**Version:** 19.09.2023

---

In diesem Protokoll werde ich alle von mir gemachten Schritte für die jeweilige Laborübung mittels Sätzen, Code-Snippets und Screenshots festhalten.



# 1. Benötigte Attribute / Model-Klassen hinzufügen



### 1.1 Bestandsaufnahme: Was benötigen wir?

Um der Angabe Gerecht zu werden, benötigen wir noch folgende Komponenten:

- `Klasse`: Product
  
  - Beinhaltet alle Details über unsere Produkte
  
  -  `Attribute`:
    
    - productID : UUID
    
    - productName : String
    
    - productCategory : String (-> später vielleicht auf ENUM umstellen)
    
    - productQuantity : int
    
    - productUnit . String

- `Klasse`: ProductData
  
  - Beinhaltet ein Array aus Product-Objekten
  
  - `Attribute`:
    
    - products : Product[]

- `Attribute` für Klasse `WarehouseData`:
  
  - warehouseAddress : String
  
  - warehousePostalCode : int
  
  - warehouseCity : String
  
  - warehouseCountry : String



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
	public WarehouseData getData( String inID ) {
		
		WarehouseData data = new WarehouseData();
		// parse String into UUID object, we currently want a static id
		data.setWarehouseID(UUID.fromString("469d7240-b974-441d-9562-2c56a7b28767"));
		data.setWarehouseName( "Linz Bahnhof" );
		data.setWarehouseCity("Linz");
		data.setWarehouseAddress("WhoKnows Straße 12");
		data.setWarehouseCountry("Autria");
		data.setWarehousePostalCode(4000);

		return data;
		
	}
```

Nun haben wir die Methode mit den jeweiligen neuen Attributen erweitert.



Wenn wir jetzt die REST API neustarten und zu `http://localhost:8080/warehouse/001/xml` navigieren, sehen wir die folgende Veränderung der Ausgabe:

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



Wir können natürlich auch zu `http://localhost:8080/warehouse/001/data` navigieren, dann sehen wir die Ausgabe in JSON:

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

Jetzt fügen wir die benötigten Klassen hinzu.


