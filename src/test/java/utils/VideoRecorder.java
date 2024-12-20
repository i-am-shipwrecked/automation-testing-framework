package utils;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder {
    private boolean enableRecording;
    private ScreenRecorder screenRecorder;
    private File videoFile;

    public VideoRecorder() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/test.properties")) {
            properties.load(fis);
            enableRecording = Boolean.parseBoolean(properties.getProperty("enable_video_recording", "true"));
        } catch (IOException e) {
            e.printStackTrace();
            enableRecording = true;
        }
    }

    public void startRecording() throws IOException, AWTException {
        File file = new File("logs/videos");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        this.screenRecorder = new ScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file);
        ;
        this.screenRecorder.start();
    }

    public File getVideoFile() {
        return videoFile;
    }

    public void stopRecording() throws IOException {
        this.screenRecorder.stop();
        if (!screenRecorder.getCreatedMovieFiles().isEmpty()) {
            videoFile = screenRecorder.getCreatedMovieFiles().get(0);
        }
    }

    public boolean isRecordingEnabled() {
        return enableRecording;
    }

}


