package week8ap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 5: The Streaming Plan Renewal Reminder
 * 
 * Task:
 * A video streaming service offers three subscription plans: Basic, Standard, and Premium.
 * Each plan stays valid for a different number of days from its start date. For every subscriber,
 * calculate the date on which the plan must be renewed and display it.
 * 
 * Input:
 * The first line contains an integer N, the number of subscribers. Each of the next N lines contains
 * PlanType Name StartDate, where StartDate is in the format YYYY-MM-DD:
 * - BASIC Name StartDate
 * - STANDARD Name StartDate
 * - PREMIUM Name StartDate
 * 
 * Output:
 * For each subscriber, display Name: RenewalDate, where RenewalDate is formatted as YYYY-MM-DD.
 * 
 * Business Rules:
 * - Basic plan: valid for 30 days.
 * - Standard plan: valid for 90 days.
 * - Premium plan: valid for 365 days.
 * - The renewal date is the start date plus the plan's validity in days.
 * 
 * Constraints:
 * - 1 <= N <= 100
 * - Name is a single word
 * - StartDate is a valid date
 * 
 * Instructions:
 * Consider how to model different plans that share a common operation (finding the renewal date)
 * but have different validity periods. Focus on how the system can process a list of subscribers
 * without explicit type checking. You may use java.time.LocalDate and its plusDays() method;
 * note that the sample includes a leap year (2024) and a change of year.
 */
abstract class SubscriptionPlan {
    protected final String planType;
    protected final String subscriberName;
    protected final LocalDate startDate;

    public SubscriptionPlan(String planType, String subscriberName, LocalDate startDate) {
        this.planType = planType;
        this.subscriberName = subscriberName;
        this.startDate = startDate;
    }

    public String getPlanType() {
        return planType;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    // Abstract method to calculate the renewal date polymorphically
    public abstract LocalDate calculateRenewalDate();
}

class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String subscriberName, LocalDate startDate) {
        super("BASIC", subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        // Valid for 30 days
        return startDate.plusDays(30);
    }
}

class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String subscriberName, LocalDate startDate) {
        super("STANDARD", subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        // Valid for 90 days
        return startDate.plusDays(90);
    }
}

class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String subscriberName, LocalDate startDate) {
        super("PREMIUM", subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        // Valid for 365 days
        return startDate.plusDays(365);
    }
}

class RenewalReminderSystem {
    private final List<SubscriptionPlan> subscriptions = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addSubscription(SubscriptionPlan subscription) {
        if (subscription != null) {
            subscriptions.add(subscription);
        }
    }

    // Process all subscribers uniformly without checking concrete plan types
    public void processAllRenewals() {
        for (SubscriptionPlan plan : subscriptions) {
            LocalDate renewalDate = plan.calculateRenewalDate();
            System.out.printf("%s: %s%n", plan.getSubscriberName(), renewalDate.format(FORMATTER));
        }
    }
}

public class TheStreamingPlanRenewalReminder {
    public static SubscriptionPlan createPlan(String planType, String name, LocalDate startDate) {
        if ("BASIC".equalsIgnoreCase(planType)) {
            return new BasicPlan(name, startDate);
        } else if ("STANDARD".equalsIgnoreCase(planType)) {
            return new StandardPlan(name, startDate);
        } else if ("PREMIUM".equalsIgnoreCase(planType)) {
            return new PremiumPlan(name, startDate);
        }
        throw new IllegalArgumentException("Unknown plan type: " + planType);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            RenewalReminderSystem system = new RenewalReminderSystem();
            for (int i = 0; i < n; i++) {
                String planType = sc.next();
                String name = sc.next();
                String dateStr = sc.next();
                LocalDate startDate = LocalDate.parse(dateStr);
                system.addSubscription(createPlan(planType, name, startDate));
            }
            system.processAllRenewals();
        } else {
            // Default sample demo if no input piped
            RenewalReminderSystem system = new RenewalReminderSystem();
            system.addSubscription(createPlan("BASIC", "Asha", LocalDate.parse("2024-01-15")));
            system.addSubscription(createPlan("STANDARD", "Ravi", LocalDate.parse("2024-02-01")));
            system.addSubscription(createPlan("PREMIUM", "Neha", LocalDate.parse("2024-03-10")));
            system.addSubscription(createPlan("BASIC", "Kiran", LocalDate.parse("2024-12-20")));
            system.processAllRenewals();
        }
        sc.close();
    }
}
