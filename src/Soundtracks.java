import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

public abstract class Soundtracks
{
    Clip clip;
    File audio;
    AudioInputStream audioStream;
    abstract void Audio();
}
