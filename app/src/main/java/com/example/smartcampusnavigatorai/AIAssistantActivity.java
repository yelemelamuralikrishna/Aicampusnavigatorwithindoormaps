package com.example.smartcampusnavigatorai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Locale;

public class AIAssistantActivity extends AppCompatActivity {

    private static final int AUDIO_PERMISSION_REQUEST_CODE = 200;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private TextView tvAiResponse, tvListeningStatus;
    private FloatingActionButton fabMic;
    private boolean isListening = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_assistant);

        tvAiResponse = findViewById(R.id.tvAiResponse);
        tvListeningStatus = findViewById(R.id.tvListeningStatus);
        fabMic = findViewById(R.id.fabMic);

        initTextToSpeech();
        checkAudioPermission();

        fabMic.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                if (!isListening) {
                    startListening();
                } else {
                    stopListening();
                }
            } else {
                checkAudioPermission();
            }
        });
    }

    private void checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_PERMISSION_REQUEST_CODE);
        } else {
            initSpeechRecognizer();
        }
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
            }
        });
    }

    private void initSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                isListening = true;
                tvListeningStatus.setText("Listening...");
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                isListening = false;
                tvListeningStatus.setText("Tap to Speak");
            }

            @Override
            public void onError(int error) {
                isListening = false;
                tvListeningStatus.setText("Tap to Speak");
                String errorMessage = "Error recognizing speech: " + error;
                if (error == SpeechRecognizer.ERROR_NO_MATCH) {
                    errorMessage = "I didn't catch that. Please speak clearly into the microphone.";
                } else if (error == SpeechRecognizer.ERROR_AUDIO) {
                    errorMessage = "Audio recording error (check emulator mic settings).";
                }
                Toast.makeText(AIAssistantActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String query = matches.get(0);
                    handleAIQuery(query);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.startListening(intent);
    }

    private void stopListening() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
    }

    private void handleAIQuery(String query) {
        // Placeholder AI logic
        String response;
        query = query.toLowerCase();

        if (query.contains("library")) {
            response = "The library is located in the main block on the first floor. It is about a 5-minute walk.";
        } else if (query.contains("principal")) {
            response = "The Principal's office is in the administration block on the ground floor.";
        } else if (query.contains("washroom")) {
            response = "The nearest washroom is down the hall to your right.";
        } else {
            response = "I heard: " + query + ". I am still learning the campus layout.";
        }

        tvAiResponse.setText(response);
        textToSpeech.speak(response, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initSpeechRecognizer();
            } else {
                Toast.makeText(this, "Microphone permission is required for Voice Assistant", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
