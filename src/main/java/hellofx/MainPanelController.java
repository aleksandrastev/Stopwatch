package hellofx;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainPanelController {

	@FXML
	private Text hours;

	@FXML
	private Text seconds;

	@FXML
	private Text minutes;

	@FXML
	private Button startButton;

	@FXML
	private Button stopButton;

	@FXML
	private Button resetButton;

	Thread thrd;
	boolean state = true;

	static int ms = 0;
	static int s = 0;
	static int m = 0;
	static int h = 0;

	@FXML
	void start(ActionEvent event) {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(100));
		tr1.setToX(0);
		tr1.setToY(200);
		tr1.setNode(startButton);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(100));
		tr2.setFromX(0);
		tr2.setFromY(200);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(stopButton);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.setOnFinished(e -> {
			try {
				System.out.println("Start countdown...");
				startCountDown();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		pt.play();

	}

	@FXML
	void stop(ActionEvent event) {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(100));
		tr1.setToX(0);
		tr1.setToY(200);
		tr1.setNode(stopButton);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(100));
		tr2.setFromX(0);
		tr2.setFromY(200);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(startButton);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.setOnFinished(e -> {
			try {
				System.out.println("Stopped");
				state = false;

			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		pt.play();

	}

	@FXML
	void reset(ActionEvent event) {
		if(state = true)
			stop(event);
		ms = 0;
		s = 0;
		m = 0;
		h = 0;
		seconds.setText("00");
		minutes.setText("00");
		hours.setText("00");
		System.out.println("Reset");
	}

	void startCountDown() {
		state = true;
		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				while (state) {
					try {
						Thread.sleep(1);

						if (ms > 550) {
							ms = 0;
							s++;
						}
						if (s > 59) {
							s = 0;
							m++;
						}
						if (m > 59) {
							ms = 0;
							s = 0;
							m = 0;
							h++;
						}
						ms++;

						if (s < 10) {
							seconds.setText("0" + s);
						} else
							seconds.setText("" + s);
						if (m < 10) {
							minutes.setText("0" + m);
						} else
							minutes.setText("" + m);
						if (h < 10) {
							hours.setText("0" + h);
						} else
							hours.setText("" + h);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thrd.start();
	}

}
