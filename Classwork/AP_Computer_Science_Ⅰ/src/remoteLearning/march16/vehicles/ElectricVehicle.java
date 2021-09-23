package Classwork.AP_Computer_Science_Ⅰ.src.remoteLearning.march16.vehicles;

public interface ElectricVehicle extends Vehicle {
//	Drain an amount of power from a defined powerLevel field based on int parameter (i.e. subtract from int powerLevel)
	void usePower(int powerUsed);

//	Sets a defined powerLevel field to full capacity (however that is defined)
	void fullRecharge();
}
