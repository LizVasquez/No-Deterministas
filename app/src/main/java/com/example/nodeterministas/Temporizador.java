package com.example.nodeterministas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Temporizador extends AppCompatActivity {

//    private String Clave = "123";

    private EditText mEditTextTime;
    private EditText mEditTextClave;
    private TextView mTextViewCountDown;


    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonConfirmarClave;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;


    Random random;
    int ClaveAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        mEditTextTime = findViewById(R.id.edit_text_inputtime);
        mEditTextClave = findViewById(R.id.edit_text_clave);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonConfirmarClave = findViewById(R.id.button_comprobar_clave);


        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = mEditTextTime.getText().toString();
                if (time.length() == 0) {
                    Toast.makeText(Temporizador.this, "Introduzca tiempo", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(time) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(Temporizador.this, "Debe ser un numero positivo", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                mEditTextTime.setText("");
            }
        });

        mButtonConfirmarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifica si la clave es correcta y manda a otrsa pantalla
                String clave = mEditTextClave.getText().toString();
                if (clave.equals("123")) {
                    Intent intent = new Intent(Temporizador.this, BienvenidosMenu.class);
                    pauseTimer();
                    resetTimer();
                    startActivity(intent);
                    Toast.makeText(Temporizador.this, "Clave correcta", Toast.LENGTH_SHORT).show();
                    onStop();
                } else {
                    Toast.makeText(Temporizador.this, "Clave Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mostrarDialog(v);

                }

        });

    }

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }




    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextTime.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonConfirmarClave.setVisibility(View.VISIBLE);
            mButtonStartPause.setVisibility(View.INVISIBLE);
            mEditTextClave.setVisibility(View.VISIBLE);
        } else {
            mEditTextTime.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mEditTextClave.setVisibility(View.INVISIBLE);
            mButtonConfirmarClave.setVisibility(View.INVISIBLE);

            if (mTimeLeftInMillis < 1000) {
                mEditTextClave.setVisibility(View.VISIBLE);
                mButtonConfirmarClave.setVisibility(View.VISIBLE);
                mEditTextClave.setVisibility(View.VISIBLE);

                //revisar
                mButtonStartPause.setVisibility(View.VISIBLE);
                ;
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateWatchInterface();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }

    public void mostrarDialog(View view) {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this); // Es necesario crear un "builder" para agregarle opciones
        alertDialogBuilder.setTitle("Estas seguro de bloquear las aplicaciones?")

                .setCancelable(false)

        .setMessage("Las aplicaciones seleccionadas se bloquearan cuando el tiempo escogido concluya")


        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Temporizador.this, "Aceptaste", Toast.LENGTH_SHORT).show();
                startTimer();
            }
        })
                .setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        alertDialogBuilder.create().show();
    }





}