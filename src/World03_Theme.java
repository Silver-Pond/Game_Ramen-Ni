import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class World03_Theme extends Soundtracks
{
    @Override
    void Audio()
    {
        try
        {
            audio = new File("soundtracks/Ramen_Nigga Theme 3.wav");
            audioStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((7.00f)*-1);
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
