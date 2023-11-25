import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TXTFileReportDBWriter {
	public void writeToDatabase(Camp camp, HashMap<String, Boolean> filter, String userID) {
		String formattedText = getFormattedTextCampDetails(camp);
		ArrayList<Enquiry> enquiryList = new ArrayList<Enquiry> (camp.getCampEnquiries().values());
		ArrayList<Suggestion> suggestionList = new ArrayList<Suggestion> (camp.getCampSuggestions().values());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		String timeStamp = dateFormat.format(new Date());
			formattedText += filter.get("Attendee") ? getFormattedTextAttendee(camp.getAttendees()) : "";
			formattedText += filter.get("Committee") ? getFormattedTextCommittee(camp.getCommitteeMembers()) : "";
			formattedText += filter.get("Enquiry") ? getFormattedTextEnquiries(enquiryList) : "";
			formattedText += filter.get("Suggestion") ? getFormattedTextSuggestions(suggestionList) : "";
		    try(PrintWriter writer = new PrintWriter("LOGS/REPORT/" + userID + timeStamp + camp.getCampID() + ".txt")){
			writer.write(formattedText);
		    } catch(Exception e) {
			e.printStackTrace();
		    }
	}

	public String getFormattedTextCampDetails(Camp camp) { // refactored later this is copied from CustomPrinterCamp
		String text = "Camp Name: " + camp.getCampName();
		text += "\nCamp ID: " + camp.getCampID();
		text += "\nCamp Description: " + camp.getDescription();
		text += "\nCamp Dates: " + Parsers.datesToString(camp.getCampDates());
		text += "\nCamp Location: " + camp.getLocation();
		text += "\nCamp Registration Deadline: " + Parsers.dateToString(camp.getRegistrationDeadline());
		text += "\nCamp Total Slots: " + camp.getTotalSlots();
		text += "\nCamp Committee Slots: " + camp.getCampCommitteeSlots();
		text += "\nCamp Staff ID: " + camp.getStaffID();
		text += "\nCamp Visibility: " + camp.isVisible();
		text += "\n===================================================\n";
		return text;
	}

	public String getFormattedTextAttendee(ArrayList<String> studentList) {
		String text = "\nStudent List:";
		if (studentList.size() == 0) {
			text += "\nNo student has registered";
		}
		for (String student : studentList) {
			text += "\n" + student;
		}
		text += "\n===================================================\n";
		return text;
	}

	public String getFormattedTextCommittee(ArrayList<String> committeeList) {
		String text = "\nCamp Committee List:";
		if (committeeList.size() == 0) {
			text += "\nNo camp committee has registered";
		}
		for (String campCommittee : committeeList) {
			CampCommittee committeeObject = RegistryFactory.committeeRegistry.getEntry(campCommittee);
			text += "\n" + committeeObject.getUserID();
			text += ", Points = " + committeeObject.getPoint();
		}
		text += "\n===================================================\n";
		return text;
	}

	public String getFormattedTextEnquiries(ArrayList<Enquiry> enquiryList) {
		String text = "\nEnquiry List: \n";
		if (enquiryList.size() == 0) {
			text += "\nNo enquiry found";
		}
		for (Enquiry enquiry: enquiryList) {
			text += "Enquiry: " + enquiry.getStringValue();
			text += "\n" + "Submitted by: " + enquiry.getSubmittedBy();
			text += "\n" + "Submitted on: " + Parsers.dateToString(enquiry.getSubmittedOn());
			if (enquiry.isProcessed()) {
			    text += "\n" + "Reply: " + enquiry.getReply();
			    text += "\n" + "Replied by: " + enquiry.getRepliedBy() + " on: "+ Parsers.dateToString(enquiry.getRepliedOn());
			}
			text += "\n" + "Last Updated on: " + Parsers.dateToString(enquiry.getUpdatedOn());
			text += ("\n---------------------\n");
		}
		text += "\n===================================================\n";
		return text;
	}

	public String getFormattedTextSuggestions(ArrayList<Suggestion> suggestionList) {
		String text = "";
		if (suggestionList.size() == 0) {
			text += "\nNo suggestion found";
		}
		for (Suggestion suggestion: suggestionList) {
			text += "Suggestion: " + suggestion.getStringValue() + "\n";
			text += "Submitted by: " + suggestion.getSubmittedBy() + "\n";
			text += "Submitted on: " + suggestion.getSubmittedOn() + "\n";
			if (suggestion.isProcessed()){
			    text += "Reply: "  + suggestion.getReply() + "\n";
			    text += "Replied by: " + suggestion.getRepliedBy() + " on: " + suggestion.getRepliedOn() + "\n";
			    text += "Approval Status: ";
			    if (suggestion.getApprovalStatus() == 1) text += "Approved\n";
			    else if (suggestion.getApprovalStatus() == 2) text += "Rejected\n";
			}
			else{
			    text += "Status: Pending\n";
			}
			text += "Last Updated on: " + Parsers.dateToString(suggestion.getUpdatedOn());
			text += ("\n---------------------\n");
		}
		text += "\n===================================================\n";
		return text;
	}
}
