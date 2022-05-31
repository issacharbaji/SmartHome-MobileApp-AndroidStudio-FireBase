package com.example.smarthome;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * One time initialization:
 */

public class TFLiteModel {

    private static final int OUTPUT_SIZE = 2;

    private static final String TAG = "TFLite";
    private static final String MODEL_PATH = "model.tflite";

    private final Map <Integer, String> mapLabels = new HashMap<>();

    private Interpreter tflite;

    private final Context context;

    public TFLiteModel(Context context) {
        this.context = context;
        mapLabels.put(0, "Off");
        mapLabels.put(1, "On");
    }

    /**
     * Load the TF Lite model and dictionary so that the client can start classifying text.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @WorkerThread
    public void load() {
        loadModel();
    }

    /**
     * Load TF Lite model.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @WorkerThread
    private synchronized void loadModel() {
        try {
            ByteBuffer buffer = loadModelFile(this.context.getAssets());
            tflite = new Interpreter(buffer);
            Log.v(TAG, "TFLite model loaded.");
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    /**
     * Free up resources as the client is no longer needed.
     */
    @WorkerThread
    public synchronized void unload() {
        tflite.close();
    }

    /**
     * Classify an input string and returns the classification results.
     */
    @WorkerThread
    public synchronized String classify(String input) {
        String[] features = input.split(" ");
        float[][] output = new float[1][OUTPUT_SIZE];
        float[] feature_values = new float[features.length];
        for (int i = 0; i < feature_values.length; i++) {
            feature_values[i] = Float.parseFloat(features[i]);
        }
        tflite.run(feature_values, output);
        // probability for each category
        float max = Float.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < output[0].length; i++) {
            if (output[0][i] > max) {
                index = i;
                max = output[0][i];
            }
        }
        return mapLabels.get(index);
    }

    /**
     * Load TF Lite model from assets.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static MappedByteBuffer loadModelFile(AssetManager assetManager) throws IOException {
        try (AssetFileDescriptor fileDescriptor = assetManager.openFd(MODEL_PATH);
             FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor())) {
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        }
    }
}
