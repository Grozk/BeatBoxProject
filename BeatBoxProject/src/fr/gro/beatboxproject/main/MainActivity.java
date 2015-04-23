package fr.gro.beatboxproject.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import fr.grzk.beatboxproject.R;

import fr.gro.beatboxproject.business.Player;
import fr.gro.beatboxproject.business.Recorder;
import fr.gro.beatboxproject.objects.StateEnum;

import static android.graphics.Color.RED;

public class MainActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    public static final String SOUND_BUTTON_1 = "SOUND_button1";
    public static final String SOUND_BUTTON_2 = "SOUND_button2";
    public static final String SOUND_BUTTON_3 = "SOUND_button3";
    public static final String SOUND_BUTTON_4 = "SOUND_button4";
    public static final String YOUR_SOUND = "FINAL_SOUND";

    /**
     * Button 1
     */
    Recorder r1 = null;
    Player p1 = null;
    StateEnum stateB1 = StateEnum.RECORD;
    Button b1 = null;

    /**
     * Button 2
     */
    Recorder r2 = null;
    Player p2 = null;
    StateEnum stateB2 = StateEnum.RECORD;
    Button b2 = null;

    /**
     * Button 3
     */
    Recorder r3 = null;
    Player p3 = null;
    StateEnum stateB3 = StateEnum.RECORD;
    Button b3 = null;

    /**
     * Button 4
     */
    Recorder r4 = null;
    Player p4 = null;
    StateEnum stateB4 = StateEnum.RECORD;
    Button b4 = null;

    /**
     * Button rec
     */
    Recorder rec = null;
    Player lec = null;
    StateEnum stateRecLec = StateEnum.RECORD;
    Button recLec = null;

    /**
     * Button save final sound
     */
    ImageButton buttonSaveLoad = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        recLec = (Button) findViewById(R.id.buttonRec);
        buttonSaveLoad = (ImageButton) findViewById(R.id.buttonSaveLoad);

        initListener();

        r1 = new Recorder(null, SOUND_BUTTON_1);
        r2 = new Recorder(null, SOUND_BUTTON_2);
        r3 = new Recorder(null, SOUND_BUTTON_3);
        r4 = new Recorder(null, SOUND_BUTTON_4);
        /*use of current date*/
        rec = new Recorder(null, YOUR_SOUND);

        p1 = new Player(null, r1.getFilenameRecord());
        p2 = new Player(null, r2.getFilenameRecord());
        p3 = new Player(null, r3.getFilenameRecord());
        p4 = new Player(null, r4.getFilenameRecord());
        lec = new Player(null, rec.getFilenameRecord());

    }

    /**
     * Init listener
     */
    private void initListener() {
        b1.setOnClickListener(this);
        b1.setOnLongClickListener(this);

        b2.setOnClickListener(this);
        b2.setOnLongClickListener(this);

        b3.setOnClickListener(this);
        b3.setOnLongClickListener(this);

        b4.setOnClickListener(this);
        b4.setOnLongClickListener(this);

        recLec.setOnClickListener(this);
        recLec.setOnLongClickListener(this);

        buttonSaveLoad.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.menu_action_credit);

                EditText editText = new EditText(getApplicationContext());
                editText.setText(R.string.menu_action_credit_text);
                editText.setEnabled(false);
                builder.setView(editText);

                // create alert dialog
                AlertDialog alertDialog = builder.create();

                // show it
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.button1:
                stateB1 = nextStep(r1, p1, stateB1, b1);
                break;
            case R.id.button2:
                stateB2 = nextStep(r2, p2, stateB2, b2);
                break;
            case R.id.button3:
                stateB3 = nextStep(r3, p3, stateB3, b3);
                break;
            case R.id.button4:
                stateB4 = nextStep(r4, p4, stateB4, b4);
                break;
            case R.id.buttonRec:
                stateRecLec = nextStep(rec, lec, stateRecLec, recLec);
                break;
            case R.id.buttonSaveLoad:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.questionSave);
                builder.setCancelable(false)
                        .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!rec.copie()) {
                                    Toast.makeText(getApplicationContext(), R.string.failed, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = builder.create();

                // show it
                alertDialog.show();
                break;
            default:
                break;

        }

    }

    @Override
    public boolean onLongClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.button1:
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                stateB1 = StateEnum.RECORD;
                b1.setText(R.string.readyRecord);
                break;
            case R.id.button2:
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                stateB2 = StateEnum.RECORD;
                b2.setText(R.string.readyRecord);
                break;
            case R.id.button3:
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                stateB3 = StateEnum.RECORD;
                b3.setText(R.string.readyRecord);
                break;
            case R.id.button4:
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                stateB4 = StateEnum.RECORD;
                b4.setText(R.string.readyRecord);
                break;
            case R.id.buttonRec:
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                stateRecLec = StateEnum.RECORD;
                recLec.setText(R.string.readyRecord);
                break;
            default:
                break;

        }
        return true;
    }

    /**
     * Evaluate the next step of the button
     * !isRecording ? recording : stop recording
     * !isPlaying ? playing : stop playing
     * <p/>
     * Change the appearance of the button :
     * text color to RED if button is use, white if
     *
     * @param recorder  : current recorder
     * @param player    : current player
     * @param currState : current state
     * @param button    : current button
     * @return new current state
     */
    private StateEnum nextStep(Recorder recorder, Player player, StateEnum currState, Button button) {

        switch (currState) {
            case RECORD:
                if (!recorder.isRecording()) {
                    Toast.makeText(this, R.string.startRecording, Toast.LENGTH_SHORT).show();
                    recorder.start();
                    button.setText(R.string.recording);
                    changeStyleToButtonUsed(button);
                } else {
                    Toast.makeText(this, R.string.stopRecording, Toast.LENGTH_SHORT).show();
                    currState = StateEnum.PLAY;
                    recorder.stop();
                    button.setText(R.string.readyPlay);
                    changeStyleToButtonNotUsed(button);
                }
                break;
            case PLAY:
                if (!player.isStarted()) {
                    Toast.makeText(this, R.string.startPlaying, Toast.LENGTH_SHORT)
                            .show();
                    player.start();
                    button.setText(R.string.playing);
                    changeStyleToButtonUsed(button);
                } else {
                    Toast.makeText(this, R.string.stopPlaying, Toast.LENGTH_SHORT).show();
                    player.stop();
                    button.setText(R.string.readyPlay);
                    changeStyleToButtonNotUsed(button);
                }
                break;
            default:
                break;
        }

        return currState;
    }

    /**
     * Change button textColor to RED
     *
     * @param button :
     */
    private void changeStyleToButtonUsed(Button button) {
        button.setTextColor(RED);
    }

    /**
     * Change button textColor to WHITE
     *
     * @param button :
     */
    private void changeStyleToButtonNotUsed(Button button) {
        button.setTextColor(Color.BLACK);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
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
    public void onPause() {
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
