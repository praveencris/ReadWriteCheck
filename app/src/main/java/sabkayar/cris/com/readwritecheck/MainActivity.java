package sabkayar.cris.com.readwritecheck;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String READ_WRITE_PREFERENCE = "read_write_preference";
    private TextView mSharedPrefTextView, mInternalTextView, mAndroidIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPrefTextView = findViewById(R.id.shared_pref_text_view);
        mInternalTextView = findViewById(R.id.internal_text_view);
        mAndroidIdTextView = findViewById(R.id.android_id_text_view);

        String fileName = "myFile.txt";
        String fileContent = "Hello World!";

        /*writeFile(fileName, fileContent);
        writePreference();*/

        mSharedPrefTextView.setText(readPreference());
        mInternalTextView.setText(readFile(fileName));
        mAndroidIdTextView.setText(getAndroidID());
    }

    private String readPreference() {
        //Get SharedPreference data
        SharedPreferences pref = getSharedPreferences(READ_WRITE_PREFERENCE, MODE_PRIVATE);
        return pref.getString("name", "Value Not Available");
    }

    private void writePreference() {
        //Create SharedPreference and store key value pair
        SharedPreferences pref = getSharedPreferences(READ_WRITE_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "Praveen Kumar");
        editor.apply();
    }

    /**
     * It is used to write file inside internal storage
     *
     * @param fileName    Name of the file
     * @param fileContent Content to be written inside file
     */
    private void writeFile(String fileName, String fileContent) {
        //Write file inside internal storage
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It is used to read content of file
     *
     * @param fileName Name of the file to be read
     * @return Content of file
     */
    private String readFile(String fileName) {
        //Read file from internal storage
        FileInputStream inputStream;
        String fileContent = "Nothing Available";
        try {
            inputStream = openFileInput(fileName);
            StringBuilder buffer = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                buffer.append((char) read);
            }
            inputStream.close();
            fileContent = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * It is used for getting ANDROID_ID
     *
     * @return return ANDROID_ID
     */
    private String getAndroidID() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
