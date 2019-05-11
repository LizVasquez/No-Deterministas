package com.example.nodeterministas.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nodeterministas.Models.ClaveRandom;
import com.example.nodeterministas.R;

import java.util.Locale;

public class Temporizador extends AppCompatActivity {


    private EditText mEditTextTime, mEditTextClave;
    private TextView mTextViewCountDown, TextViewClave;


    private Button mButtonSet;
    private Button mButtonStart;
    private Button mButtonConfirmClave;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    // long es un entero de 64 bits
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    public static final int NOTIFICATION_ID = 1;


    ClaveRandom claveRandom;


    private String numeroConfirmarClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        Context context = this;
        final SharedPreferences sharedPreferences = getSharedPreferences("BloqueoClaves", MODE_PRIVATE);


        mEditTextTime = findViewById(R.id.edit_text_inputtime);
        mEditTextClave = findViewById(R.id.edit_text_clave);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        TextViewClave = findViewById((R.id.text_clave));

        mButtonSet = findViewById(R.id.button_set);
        mButtonStart = findViewById(R.id.button_start_pause);
        mButtonConfirmClave = findViewById(R.id.button_comprobar_clave);



        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextTime.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(Temporizador.this, "Introduzca tiempo", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(Temporizador.this, "Debe ser un numero positivo", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                mEditTextTime.setText("");
            }
        });


        mButtonConfirmClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifica si la clave es correcta y manda a otrsa pantalla
                String clave = mEditTextClave.getText().toString();


                SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
                String claveRandom = sharpref.getString("MiClave", "No hay datos");
                TextViewClave.setText(clave);


                if (clave.equals(claveRandom)) {
                    Intent intent = new Intent(Temporizador.this, BienvenidosMenu.class);
                    startActivity(intent);
//                    pauseTimer();
//                    resetTimer();


                    sharpref = getPreferences(MODE_PRIVATE);
                    sharpref.edit().clear().commit();

                    Toast.makeText(Temporizador.this, "Clave correcta", Toast.LENGTH_SHORT).show();
                    onStop();
                } else {
                    Toast.makeText(Temporizador.this, "Clave Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonStart.setOnClickListener(new View.OnClickListener() {
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

    //Insertamos datos y lo que hace es un calculo para el temporizador
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
        //Le decimos quecambie al text view que tiene nuestro layout al formato de tiempo 00:00:00

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    //como se vera nuestra interfaz al realizar alguna tarea
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextTime.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonConfirmClave.setVisibility(View.VISIBLE);
            mButtonStart.setVisibility(View.INVISIBLE);
            mEditTextClave.setVisibility(View.VISIBLE);
            TextViewClave.setVisibility(View.INVISIBLE);
        } else {
            mEditTextTime.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mEditTextClave.setVisibility(View.INVISIBLE);
            mButtonConfirmClave.setVisibility(View.INVISIBLE);
            TextViewClave.setVisibility(View.INVISIBLE);

            if (mTimeLeftInMillis < 1000) {
                SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
                String clave = sharpref.getString("MiClave", null);

                if(clave !=null) {


                    mEditTextClave.setVisibility(View.VISIBLE);
                    mButtonConfirmClave.setVisibility(View.VISIBLE);
                    TextViewClave.setVisibility(View.VISIBLE);
                    mEditTextTime.setVisibility(View.INVISIBLE);
                    mButtonSet.setVisibility(View.INVISIBLE);
                    mButtonStart.setVisibility(View.INVISIBLE);
                    mostrarNotificacion();




                } else {
                    mEditTextClave.setVisibility(View.INVISIBLE);
                    mButtonConfirmClave.setVisibility(View.INVISIBLE);
                    TextViewClave.setVisibility(View.INVISIBLE);
                    mEditTextTime.setVisibility(View.VISIBLE);
                    mButtonSet.setVisibility(View.VISIBLE);
                    mButtonStart.setVisibility(View.VISIBLE);
                }}

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

                SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
                String clave = sharpref.getString("MiClave", "No hay dato");
                TextViewClave.setText(clave);
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


                        TextViewClave.setText(numeroConfirmarClave);
                        startTimer();

                        ClaveRandom claveRandom = new ClaveRandom();
                        numeroConfirmarClave = claveRandom.getNumeroClave();
                        TextViewClave.setText(numeroConfirmarClave);

                        SharedPreferences sharepref = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharepref.edit();
                        editor.putString("MiClave", TextViewClave.getText().toString());
                        editor.commit();


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

    // Muestra una notificación en el teléfono
    public void mostrarNotificacion() {
        // Es requerido crear un notification channel para nuestra app en Android 8 o superior
        createNotificationChannel();


        SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
        String clave = sharpref.getString("MiClave", "No hay dato");

        // Creamos un builder y seteamos los parametros que queremos mostrar en nuestra notificacion
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setContentTitle("Las Aplicacines ya fueron desbloqueadas");
        builder.setContentText("Tu clave es : "  + clave);

        // Seleccionamos el icono para mostrar en la notificacion
        builder.setSmallIcon(R.drawable.fondo);

        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_launcher_foreground);

        // Podemos mostrar un icono grande en la app
        builder.setLargeIcon(largeIcon);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        // A traves de un notification manager, mostramos nuestra notificacion
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(123456789, builder.build());
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Default";
            String description = "Descripcion del canal Defaul";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}