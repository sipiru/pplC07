package ppl.sipiru4.model;


public class DaftarDisetujuiItemP {

    public final String title;
    public final String statusClosed;

    // the text for the ListView item title
    public DaftarDisetujuiItemP(String title, String statusClosed) {

        this.title = title;
        this.statusClosed = statusClosed;
    }

    public String getTitle() {
        return this.title;

    }
    public String getStatusClosed() {
        return this.statusClosed;

    }

    public String setTitle() {
        return title;
    }
    public String setStatusClosed() {
        return statusClosed;
    }
}
