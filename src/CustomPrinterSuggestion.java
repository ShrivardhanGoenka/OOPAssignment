public class CustomPrinterSuggestion extends ICustomPrinter<Suggestion>{
    public void print(Suggestion suggestion) {
        System.out.printf("Suggestion: %s\n", suggestion.getStringValue());
        System.out.printf("Submitted by: %s\n", suggestion.getSubmittedBy());
        System.out.printf("Submitted on: %s\n", suggestion.getSubmittedOn());
        if (suggestion.isProcessed()){
            System.out.printf("Reply: %s\n", suggestion.getReply());
            System.out.printf("Replied by: %s on: %s\n", suggestion.getRepliedBy(), suggestion.getRepliedOn());
            System.out.print("Approval Status: ");
            if (suggestion.getApprovalStatus() == 1) System.out.println("Approved");
            else if (suggestion.getApprovalStatus() == 2) System.out.println("Rejected");
        }
        else{
            System.out.println("Status: Pending");
        }
        System.out.printf("Last Updated on: %s\n", DBInterface.returnDateVal(suggestion.getUpdatedOn()));
        System.out.println("---------------------");
    }

}
