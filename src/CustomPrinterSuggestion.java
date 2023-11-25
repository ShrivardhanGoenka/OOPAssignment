/**
 * The {@code CustomPrinterSuggestion} class provides a method to print suggestion details (suggestion message, submission information, reply information, status, date last updated) to the user.
 * It extends the {@code CustomPrinter} class with generic type {@code Suggestion}
 */
public class CustomPrinterSuggestion extends CustomPrinter<Suggestion> {
    /**
     * Prints details of a suggestion to the console.
     */
    public void print(Suggestion suggestion) {
        System.out.printf("Suggestion: %s\n", suggestion.getStringValue());
        System.out.printf("Submitted by: %s\n", suggestion.getSubmittedBy());
        System.out.printf("Submitted on: %s\n", Parsers.dateToString(suggestion.getSubmittedOn()));
        if (suggestion.isProcessed()){
            System.out.printf("Reply: %s\n", suggestion.getReply());
            System.out.printf("Replied by: %s on: %s\n", suggestion.getRepliedBy(), Parsers.dateToString(suggestion.getRepliedOn()));
            System.out.print("Approval Status: ");
            if (suggestion.getApprovalStatus() == 1) System.out.println("Approved");
            else if (suggestion.getApprovalStatus() == 2) System.out.println("Rejected");
        }
        else{
            System.out.println("Status: Pending");
        }
        System.out.printf("Last Updated on: %s\n", Parsers.dateToString(suggestion.getUpdatedOn()));
        System.out.println("---------------------");
    }

}
