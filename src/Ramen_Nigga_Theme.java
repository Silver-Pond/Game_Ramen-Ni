import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Ramen_Nigga_Theme extends Soundtracks
{
    @Override
    void Audio()
    {
        try
        {
            audio = new File("soundtracks/Ramen_Nigga_Theme.wav");
            audioStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(-1);
        } catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        } catch (IOException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
    }
}
