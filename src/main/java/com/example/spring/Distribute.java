package com.example.spring;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Distribute {

	public static List<Account> splitAmountByType(String type, List<Account> accounts, double amount) {
		switch (type) {
		case "EqualSplit":
			return equalSplit(accounts, amount);
		case "WeightedSplit":
			return weightedSplit(accounts, amount);
		default:
			return equalSplit(accounts, amount);
		}
	}

	public static double totalAmountDue(List<Account> accounts) {
		return accounts.stream().reduce(0.0, (intialAmount, account) -> intialAmount + account.getAmountDue(),
				Double::sum);
	}

	public static List<Account> filterAccountBasedOnCondition(List<Account> accounts, Predicate<Account> condition) {
		return accounts.stream().filter(condition).collect(Collectors.toList());
	}

	public static List<Account> getRemainingAccounts(List<Account> accounts) {
		return filterAccountBasedOnCondition(accounts, account -> (account.getAmountDue() > account.getAmount()));
	}

	public static List<Account> equalSplit(List<Account> accounts, double amount) {
		int remainingAccounts = getRemainingAccounts(accounts).size();
		double splitAmount = round(amount / remainingAccounts);

		for (Account account : accounts) {
			double diffAmount = round(account.getAmountDue() - account.getAmount());
			if (diffAmount > 0) {
				// split the remaining pennies
				if (splitAmount == 0 && amount > 0) {
					double distributeAmount = round(amount + account.getAmount());
					if (distributeAmount < account.getAmountDue()) {
						account.setAmount(distributeAmount);
						amount = 0;
						break;
					}
				} else {
					// deriving the splitAmount again based on the AmountDue
					double derivedSplitAmount = ((splitAmount > diffAmount) ? diffAmount : splitAmount);
					double distributeAmount = round(account.getAmount() + derivedSplitAmount);
					if (round(amount - derivedSplitAmount) >= 0) {
						account.setAmount(distributeAmount);
						amount = round(amount - derivedSplitAmount);
						if (amount == 0)
							break;
					}
				}
			}
		}

		if (amount > 0) {
			return equalSplit(accounts, amount);
		}
		{
			return accounts;
		}
	}

	public static List<Account> weightedSplit(List<Account> accounts, double amount) {
		double total = totalAmountDue(accounts);
		double distributedAmount = 0.0;
		for (int i = 0; i < accounts.size(); i++) {
			double percent = round(accounts.get(i).getAmountDue() / total);
			double splitAmount = round(percent * amount);
			distributedAmount = round(distributedAmount + splitAmount);
			accounts.get(i).setAmount(splitAmount);
		}
		return accounts;
	}

	public static double round(double value) {
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
