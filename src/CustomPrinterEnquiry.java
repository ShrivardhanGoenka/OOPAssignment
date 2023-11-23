
/**
 * This class provides a method to print enquiry details (enquiry message, submission detail, reply detail, date of last updated) to the user.
 */
public class CustomPrinterEnquiry extends CustomPrinter<Enquiry> {

    /**
     * Prints details of an enquiry to the console.
     */
    public void print(Enquiry enquiry){
        System.out.printf("Enquiry: %s\n", enquiry.getStringValue());
        System.out.printf("Submitted by: %s\n", enquiry.getSubmittedBy());
        System.out.printf("Submitted on: %s\n", Parsers.dateToString(enquiry.getSubmittedOn()));
        if (enquiry.isProcessed()) {
            System.out.printf("Reply: %s\n", enquiry.getReply());
            System.out.printf("Replied by: %s on: %s\n", enquiry.getRepliedBy(), Parsers.dateToString(enquiry.getRepliedOn()));
        }
        System.out.printf("Last Updated on: %s\n", Parsers.dateToString(enquiry.getUpdatedOn()));
        System.out.println("---------------------");
    }
}
