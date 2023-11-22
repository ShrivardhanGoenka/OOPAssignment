import java.util.Date;
import java.util.Map;

public abstract class TXTDBCamp implements TXTDB<Camp>{
    Camp camp;
    public TXTDBCamp(Camp camp) {
        this.camp = camp;
    }
    @Override
    public String getFileName() {
        return "camp" + camp.getCampID() + ".txt";
    }

    @Override
    public String writeData() {
        String output = "";
        output += camp.getCampName() + "\n";
        String campdates = "";
        for (Date campdate : camp.getCampDates()) {
            campdates += DBInterface.returnDateVal(campdate) + ",";
        }
        if(!campdates.isEmpty())
            campdates = campdates.substring(0, campdates.length()-1);
        output += campdates + "\n";
        output += DBInterface.returnDateVal(camp.getRegistrationDeadline()) + "\n";
        output += camp.getFacultyOpenTo() + "\n";
        output += camp.getLocation() + "\n";
        output += camp.getTotalSlots() + "\n";
        output += camp.getCampCommitteeSlots() + "\n";
        output += camp.getDescription() + "\n";
        output += camp.getStaffID() + "\n";
        output += camp.isVisible()? "visible\n" : "notvisible\n";
        String attendeesString = "";
        for(String attendee: camp.getAttendees()){
            attendeesString += attendee + ",";
        }
        if(!attendeesString.isEmpty())
            attendeesString = attendeesString.substring(0, attendeesString.length()-1);
        output += attendeesString + "\n";
        String committeeMembersString = "";
        for(String committeeMember: camp.getCommitteeMembers()){
            committeeMembersString += committeeMember + ",";
        }
        if(!committeeMembersString.isEmpty())
            committeeMembersString = committeeMembersString.substring(0, committeeMembersString.length()-1);
        output += committeeMembersString + "\n";
        String campEnquiriesString = "";
        for (Map.Entry<Integer, Enquiry> i: camp.getCampEnquiries().entrySet() ) {
            campEnquiriesString += i.getKey() + ",";
        }
        if(!campEnquiriesString.isEmpty())
            campEnquiriesString = campEnquiriesString.substring(0, campEnquiriesString.length()-1);
        output += campEnquiriesString + "\n";
        String campSuggestionsString = "";
        for (Map.Entry<Integer, Suggestion> i: camp.getCampSuggestions().entrySet() ) {
            campSuggestionsString += i.getKey() + ",";
        }
        if(!campSuggestionsString.isEmpty())
            campSuggestionsString = campSuggestionsString.substring(0, campSuggestionsString.length()-1);
        output += campSuggestionsString + "\n";
        output += camp.getWithdrawnString() + "\n";
        return output;
    }


}
