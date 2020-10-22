package main.java.quinzical.utility;

/**
 * StringSpeaker is a utility helper class that uses the festival
 * text to speech service to produce audible dialogue
 */
public class StringSpeaker {
    private double voiceSpeed = 1;

    public void setSpeed(double voiceSpeed) {
        this.voiceSpeed = 2-voiceSpeed;
        if (this.voiceSpeed == 0.0) {
            this.voiceSpeed = this.voiceSpeed + 0.1;
        }
    }

    public double getVoiceSpeed() {
        return this.voiceSpeed;
    }

    /**
     * Generates a scm String with the input String spokenString to be spoken
     * This scm String is piped into festival tts where it is read as an scm file
     * @param spokenString
     * @throws Exception
     */
    public void speakString(String spokenString) throws Exception{
        // Cleaning the string by deleting quotation marks that may interfere when read
        spokenString = spokenString.replaceAll("'", "").
                replaceAll("\"", "");
        /*
         * The scm String would look like this:
         * "(Parameter.set 'Duration_Stretch [voiceSpeed])(SayText "[spokenString]")"
         */
        String scm = "\"(Parameter.set 'Duration_Stretch " + this.voiceSpeed + ")" +
                "(SayText \\\"" + spokenString + "\\\")\"";
        String command = "echo " + scm + " | festival -b --pipe";
        ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
        builder.start();

    }
}
