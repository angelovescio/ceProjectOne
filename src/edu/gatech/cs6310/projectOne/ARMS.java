package edu.gatech.cs6310.projectOne;

import java.util.List;
import java.util.UUID;

public class ARMS {
	private static Object locker = new Object();
	private Boolean AcquireLock(){
		return true;
	}
	private Boolean ReleaseLock(){
		return true;
	}
	private short CheckLock(){
		return 2;
	}
}
