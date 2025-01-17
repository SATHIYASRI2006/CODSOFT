import java.util.Scanner;

public class currencyconverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double usdToInr = 82.0;
        double eurToInr = 88.0;
        double usdToEur = 0.93;
        double eurToUsd = 1.08;

        System.out.println("Welcome to the Currency Converter!");

        System.out.println("Available currencies:");
        System.out.println("1. USD (US Dollar)");
        System.out.println("2. INR (Indian Rupee)");
        System.out.println("3. EUR (Euro)");

        System.out.print("Select the base currency (1-3): ");
        int baseCurrency = scanner.nextInt();

        System.out.print("Select the target currency (1-3): ");
        int targetCurrency = scanner.nextInt();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double convertedAmount = 0;
        boolean validConversion = true;

        if (baseCurrency == 1 && targetCurrency == 2) { // USD to INR
            convertedAmount = amount * usdToInr;
        } else if (baseCurrency == 1 && targetCurrency == 3) { // USD to EUR
            convertedAmount = amount * usdToEur;
        } else if (baseCurrency == 2 && targetCurrency == 1) { // INR to USD
            convertedAmount = amount / usdToInr;
        } else if (baseCurrency == 2 && targetCurrency == 3) { // INR to EUR
            convertedAmount = (amount / usdToInr) * usdToEur;
        } else if (baseCurrency == 3 && targetCurrency == 1) { // EUR to USD
            convertedAmount = amount * eurToUsd;
        } else if (baseCurrency == 3 && targetCurrency == 2) { // EUR to INR
            convertedAmount = amount * eurToInr;
        } else if (baseCurrency == targetCurrency) { // Same currency
            convertedAmount = amount;
        } else {
            validConversion = false;
        }

        if (validConversion) {
            System.out.println("Converted amount: " + convertedAmount);
        } else {
            System.out.println("Invalid conversion selection.");
        }

        System.out.println("Thank you for using the Currency Converter!");
        scanner.close();
}
}