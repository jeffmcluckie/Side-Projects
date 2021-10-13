package com.techelevator;

import com.techelevator.view.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS =
											"Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase Menu";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN_SALES_REPORT = "";
	private static final String[] MAIN_MENU_OPTIONS =
			{ MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
					MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN_SALES_REPORT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION =
																"Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS =
			{ PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT,
					PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	// instantiate the Menu class, PurchaseMenu class, and

	private Menu menu;
	public PurchaseMenu purchaseMenu = new PurchaseMenu();
	public VirtualCashier virtualCashier = new VirtualCashier();
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {

		// create a new instance of the menu class and vending machine
		// 				to utilize in the run method

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.run();

	}

	public void run() {

		virtualCashier.createStock();
		SalesReport.initializeSalesMap(virtualCashier);

		while (true) {

			// show the main menu initially
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {

				case MAIN_MENU_OPTION_DISPLAY_ITEMS:

					// use formatted method to display items
					Item.printAllItems(virtualCashier);
					break;

				case MAIN_MENU_OPTION_PURCHASE:

					// boolean 'loop' causes user to stay in purchase menu
					// 		until they choose to finish transaction

					boolean loop = true;

					while (loop) {

						// uses same method as initially provided to display purchase menu
						choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

						if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

							// redirects to feedMoney method in instance of PurchaseMenu
							purchaseMenu.feedMoney(virtualCashier);

						}
						if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

							// redirects to selectProduct method in instance of PurchaseMenu
							purchaseMenu.selectProduct(virtualCashier);

						}
						if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

							// redirects to checkOut method in instance of VirtualCashier,
							//   then sets loop to false to return user to Main Menu

							virtualCashier.checkOut();
							loop = false;

						}
					}
					break;

				case MAIN_MENU_OPTION_EXIT:

					// if user chooses to exit, add the ending log entry and exit the program
					WriteToLog.endLogEntry();
					System.exit(0);

				case MAIN_MENU_OPTION_HIDDEN_SALES_REPORT:

					// if the hidden menu option is chosen, add the ending log entry,
					//      generate the Sales Report, and exit the program

					WriteToLog.endLogEntry();
					SalesReport.generateSalesReport(virtualCashier);
					System.exit(0);

			}
		}
	}
}
