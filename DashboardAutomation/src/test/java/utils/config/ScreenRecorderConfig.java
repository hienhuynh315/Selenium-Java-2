package utils.config;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_QUICKTIME;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;
import org.monte.screenrecorder.ScreenRecorder;
import utils.helper.Logger;

public class ScreenRecorderConfig {
	private ScreenRecorder screenRecorder;

	public void startRecording(String testCaseName) {
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		try {
			screenRecorder = new ScreenRecorder(gc, null,
					new Format(MediaTypeKey, FormatKeys.MediaType.FILE,
							MimeTypeKey, MIME_QUICKTIME),
					new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO,
							EncodingKey, ENCODING_QUICKTIME_JPEG,
							CompressorNameKey, ENCODING_QUICKTIME_JPEG,
							DepthKey, 24, FrameRateKey, Rational.valueOf(15),
							QualityKey, 0.5f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO,
							EncodingKey, "black", FrameRateKey,
							Rational.valueOf(30)),
					null, new File(System.getProperty("user.dir")
							+ "\\screencasts\\" + testCaseName));
			screenRecorder.start();
			Logger.info("SEE THE RECORDED VIDEO AT THE LINK: "
					+ System.getProperty("user.dir") + "\\screencasts\\"
					+ testCaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopRecording() {
		try {
			screenRecorder.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
