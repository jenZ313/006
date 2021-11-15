package BackEnd.Command;

import BackEnd.Write.Group.GroupWriter;
import BackEnd.Write.Group.WriteAnnouncement;

//String content, int group id -> SUCCESS/FAILED
public class createAnnouncementCommand extends Command {
    private final int groupID;
    private final String announcement;

    public createAnnouncementCommand(String announcement, int groupID) {
        this.announcement = announcement;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {
        GroupWriter groupWriter = new WriteAnnouncement(groupID, announcement);
        return groupWriter.set();
    }
}
