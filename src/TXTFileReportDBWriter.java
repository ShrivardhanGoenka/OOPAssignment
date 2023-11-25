import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The {@code TXTFileReportDBWriter} class is for writing and formatting camp reports to text files.
 * It includes methods for formatting camp details, attendee lists, committee lists, enquiry lists, and suggestion lists.
 */
public class TXTFileReportDBWriter {
    	/**
	* Writes a formatted camp report to a text file based on specified filters.
	*
	* @param camp              The camp to generate the report
	* @param filter            {@code HashMap} containing filters indicating to include or not include each attributes in the report
	* @param userID            The userID of camp commmittee or staff generating the report.
	*/
	public void writeToDatabase(Camp camp, HashMap<String, Boolean> filter, String userID) {
		String formattedText = getFormattedTextCampDetails(camp);
		ArrayList<Enquiry> enquiryList = new ArrayList<Enquiry> (camp.getCampEnquiries().values());
		ArrayList<Suggestion> suggestionList = new ArrayList<Suggestion> (camp.getCampSuggestions().values());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_G_HH:mm:ss_z");
		String timeStamp = dateFormat.format(new Date());
		if (filter.containsKey("Attendee"))
			formattedText += filter.get("Attendee") ? getFormattedTextAttendee(camp.getAttendees()) : "";
		if (filter.containsKey("Committee"))
			formattedText += filter.get("Committee") ? getFormattedTextCommittee(camp.getCommitteeMembers()) : "";
		if (filter.containsKey("Enquiry"))
			formattedText += filter.get("Enquiry") ? getFormattedTextEnquiries(enquiryList) : "";
		if (filter.containsKey("Suggestion"))
			formattedText += filter.get("Suggestion") ? getFormattedTextSuggestions(suggestionList) : "";

		String filename = "REPORT/" + userID + "_" + timeStamp + "_camp" + camp.getCampID() + ".txt";
		System.out.printf("Report printed to %s\n", filename);
		try(PrintWriter writer = new PrintWriter(filename)) {
			writer.write(formattedText);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
    	/**
     	* Gets the formatted text for camp details.
     	*
     	* @param camp                      The camp for which the details are formatted
     	* @return a formatted string containing camp details
     	*/
	public String getFormattedTextCampDetails(Camp camp) {
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
		text += "\nCamp Faculty Opens to: " + (camp.getFacultyOpenTo().equals("*") ? "All" : camp.getFacultyOpenTo());
		text += "\n===================================================\n";
		return text;
	}
	/**
	* Gets the formatted text for the attendee list.
	*
	* @param studentList                   The list of students attending the camp
	* @return A formatted string containing the attendee list
	*/
	public String getFormattedTextAttendee(ArrayList<String> studentList) {
		String text = "\nStudent List:";
		if (studentList.size() == 0) {
			text += "\nNo student has registered";
		}

		Collections.sort(studentList);
		
		for (String student : studentList) {
			text += "\n" + student;
		}
		text += "\n===================================================\n";
		return text;
	}

	/**
	* Gets the formatted text for the camp committee list.
	*
	* @param committeeList                  The list of committee members associated with the camp
	* @return  A formatted string containing the committee list
	*/
	public String getFormattedTextCommittee(ArrayList<String> committeeList) {
		String text = "\nCamp Committee List:";
		if (committeeList.size() == 0) {
			text += "\nNo camp committee has registered";
		}
		Collections.sort(committeeList);
		for (String campCommittee : committeeList) {
			CampCommittee committeeObject = RegistryFactory.committeeRegistry.getEntry(campCommittee);
			text += "\n" + committeeObject.getUserID();
			text += ", Points = " + committeeObject.getPoint();
		}
		text += "\n===================================================\n";
		return text;
	}

	/**
	* Gets the formatted text for the enquiry list.
	*
	* @param enquiryList                   The list of enquiries associated with the camp
	* @return a formatted string containing the enquiry list
	*/
	public String getFormattedTextEnquiries(ArrayList<Enquiry> enquiryList) {
		String text = "\nEnquiry List: \n\n";
		if (enquiryList.size() == 0) {
			text += "\nNo enquiry found";
		}
		SortableEnquiryCommittee sortable = new SortableEnquiryCommittee();
		System.out.println("Select sortable attribute for Enquiry:");
		sortable.runMenu(enquiryList);
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

	/**
	* Gets the formatted text for the suggestion list.
	*
	* @param suggestionList                The list of suggestions associated with the camp
	* @return A formatted string containing the suggestion list
	*/
	public String getFormattedTextSuggestions(ArrayList<Suggestion> suggestionList) {
		String text = "\nSuggestion List: \n\n";
		if (suggestionList.size() == 0) {
			text += "\nNo suggestion found";
		}
		SortableSuggestionStaff sortable = new SortableSuggestionStaff();
		System.out.println("Select sortable attribute for Suggestion:");
		sortable.runMenu(suggestionList);
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
