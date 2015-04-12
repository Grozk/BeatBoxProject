package fr.gro.beatboxproject.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.beatboxproject.R;

import fr.gro.beatboxproject.business.Player;
import fr.gro.beatboxproject.business.Recorder;
import fr.gro.beatboxproject.objects.StateEnum;

public class MainActivity extends Activity implements OnClickListener,
		OnLongClickListener {

	/**
	 * Button 1
	 */
	Recorder r1 = null;
	Player p1 = null;
	StateEnum stateB1 = null;

	/**
	 * Button 2
	 */
	Recorder r2 = null;
	Player p2 = null;
	StateEnum stateB2 = null;

	/**
	 * Button 3
	 */
	Recorder r3 = null;
	Player p3 = null;
	StateEnum stateB3 = null;

	/**
	 * Button 4
	 */
	Recorder r4 = null;
	Player p4 = null;
	StateEnum stateB4 = null;

	/**
	 * Button rec
	 */
	Recorder rec = null;
	Player lec = null;
	StateEnum stateRecLec = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		b1.setOnLongClickListener(this);

		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(this);
		b2.setOnLongClickListener(this);

		Button b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(this);
		b3.setOnLongClickListener(this);

		Button b4 = (Button) findViewById(R.id.button4);
		b4.setOnClickListener(this);
		b4.setOnLongClickListener(this);

		Button recLec = (Button) findViewById(R.id.buttonRec);
		recLec.setOnClickListener(this);
		recLec.setOnLongClickListener(this);

		r1 = new Recorder(null, "button1");
		p1 = new Player(null, r1.getFilenameRecord());
		stateB1 = StateEnum.RECORD;

		r2 = new Recorder(null, "button2");
		p2 = new Player(null, r2.getFilenameRecord());
		stateB2 = StateEnum.RECORD;

		r3 = new Recorder(null, "button3");
		p3 = new Player(null, r3.getFilenameRecord());
		stateB3 = StateEnum.RECORD;

		r4 = new Recorder(null, "button4");
		p4 = new Player(null, r4.getFilenameRecord());
		stateB4 = StateEnum.RECORD;

		rec = new Recorder(null, "YourSound");
		lec = new Player(null, rec.getFilenameRecord());
		stateRecLec = StateEnum.RECORD;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.button1:
			stateB1 = nextStep(r1, p1, stateB1);
			break;
		case R.id.button2:
			stateB2 = nextStep(r2, p2, stateB2);
			break;
		case R.id.button3:
			stateB3 = nextStep(r3, p3, stateB3);
			break;
		case R.id.button4:
			stateB4 = nextStep(r4, p4, stateB4);
			break;
		case R.id.buttonRec:
			stateRecLec = nextStep(rec, lec, stateRecLec);
			break;
		default:
			break;

		}

	}

	@Override
	public boolean onLongClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.button1:
			Toast.makeText(this, "reset btt 1", Toast.LENGTH_SHORT).show();
			stateB1 = StateEnum.RECORD;
			break;
		case R.id.button2:
			Toast.makeText(this, "reset btt 2", Toast.LENGTH_SHORT).show();
			stateB2 = StateEnum.RECORD;
			break;
		case R.id.button3:
			Toast.makeText(this, "reset btt 3", Toast.LENGTH_SHORT).show();
			stateB3 = StateEnum.RECORD;
			break;
		case R.id.button4:
			Toast.makeText(this, "reset btt 4", Toast.LENGTH_SHORT).show();
			stateB4 = StateEnum.RECORD;
			break;
		case R.id.buttonRec:
			Toast.makeText(this, "reset btt rec", Toast.LENGTH_SHORT).show();
			stateRecLec = StateEnum.RECORD;
			break;
		default:
			break;

		}
		return true;
	}

	private StateEnum nextStep(Recorder record, Player pla, StateEnum currState) {

		switch (currState) {
		case RECORD:
			if (!record.isRecording()) {
				Toast.makeText(this, "start record", Toast.LENGTH_SHORT).show();
				record.start();
			} else {
				Toast.makeText(this, "stop record", Toast.LENGTH_SHORT).show();
				currState = StateEnum.PLAY;
				record.stop();
			}
			break;
		case PLAY:
			if (!pla.isStarted()) {
				Toast.makeText(this, "start playing", Toast.LENGTH_SHORT)
						.show();
				pla.start();
			} else {
				Toast.makeText(this, "stop playing", Toast.LENGTH_SHORT).show();
				pla.stop();
			}
			break;
		default:
			break;
		}

		return currState;
	}


	@Override
	public void onResume(){
		super.onResume();
	}
	@Override
	public void onStop(){
		super.onStop();
		this.r1.stop();
		this.p1.stop();
		this.r2.stop();
		this.p2.stop();
		this.r3.stop();
		this.p3.stop();
		this.r4.stop();
		this.p4.stop();
		this.rec.stop();
		this.lec.stop();
	}
	@Override
	 public void onPause(){
		super.onPause();
		this.r1.stop();
		this.p1.stop();
		this.r2.stop();
		this.p2.stop();
		this.r3.stop();
		this.p3.stop();
		this.r4.stop();
		this.p4.stop();
		this.rec.stop();
		this.lec.stop();
	}

}
