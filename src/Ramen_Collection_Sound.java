import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Ramen_Collection_Sound extends Soundtracks
{
    @Override
    void Audio()
    {
        try
        {
            audio = new File("soundtracks/Ramen_Collection_Effect.wav");
            audioStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        } catch (IOException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
    }
}
