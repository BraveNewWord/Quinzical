package quinzical;

public class StringSpeaker {

    private double voiceSpeed = 1;

    public void setSpeed(double voiceSpeed) {
        this.voiceSpeed = 2-voiceSpeed;
        if (this.voiceSpeed == 0.0) {
            this.voiceSpeed = this.voiceSpeed + 0.2;
        }
    }

    public void speakString(String spokenString) throws Exception{
        // Cleaning the string by escaping quotation marks that may interfere when read
        spokenString = spokenString.replaceAll("'", "\\\\'").
                replaceAll("\"", "\\\\\"");
        // The scm String would look like this:
        // (Parameter.set 'Duration_Stretch [])
        // (SayText "[]")
        String scm = "\"(Parameter.set 'Duration_Stretch " + this.voiceSpeed + ")" +
                "(SayText \\\"" + spokenString + "\\\")\"";

        String command = "echo " + scm + " | festival -b --pipe";
        ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
        builder.start();
    }
}
