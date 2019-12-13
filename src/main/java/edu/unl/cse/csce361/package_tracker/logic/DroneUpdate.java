package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;

public class DroneUpdate implements Runnable {
	private Thread t;
	private static final logicFacade logic = logicFacade.getInstance();
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
	private String threadName;

	public DroneUpdate(String name) {
		threadName = name;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void run() {
		logic.setDispatchingPackage(BACKEND_FACADE.getDispatchingPackage());
		try {
			boolean running = true;
			while (running) {
				int packageWaiting = logic.getDispatchingPackage().size();
				System.out.println(packageWaiting);
				for (int i = 0; i < packageWaiting; i++) {
					boolean drone1Match = logic.getDispatchingPackage().get(i).getTrackingNumber()
							.equals(logic.getDrone().get(0).getTrackingNumber());
					boolean drone2Match = logic.getDispatchingPackage().get(i).getTrackingNumber()
							.equals(logic.getDrone().get(1).getTrackingNumber());
					if (!drone1Match || !drone2Match) {
						logic.dispatchingPackages("Flying" + i,
								logic.getDispatchingPackage().get(i).getTrackingNumber());
						System.out.println("haha");
					}
				}
				if (packageWaiting == 0) {
					running = false;
				}
				Thread.sleep(10000);
				ShippingLogic.dispatchingPackage.clear();
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");

		}
	}
}
