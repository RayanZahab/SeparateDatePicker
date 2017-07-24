package com.example.rzahab.separateddatepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Author Rayan Al Zahab
 * git @RayanZahab
 * RayanZahab.com
 */
public class MainActivity extends AppCompatActivity {

    private Calendar calendar;
    Spinner year_spinner, month_spinner, day_spinner;
    AdapterView.OnItemSelectedListener fixDays = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setDays();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //Another interface callback
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();

        year_spinner = (Spinner) findViewById(R.id.year_spinner);
        month_spinner = (Spinner) findViewById(R.id.month_spinner);
        day_spinner = (Spinner) findViewById(R.id.day_spinner);

        year_spinner.setOnItemSelectedListener( fixDays );
        month_spinner.setOnItemSelectedListener( fixDays );

        //This option will only allow dates for DOB by min age of 10 and maximum 30
        //This is dynamic, it will always accept DOB for people aged between 10 and 30 depending on current year!
        populateYears(10, 30);

        /*
        You may also limit your entry by minYear and max Year using this function instead:
        populateYearsByRange(1980,2010)
         */
    }

    public void setDays()
    {
        int year = Integer.parseInt(year_spinner.getSelectedItem().toString());
        String month = month_spinner.getSelectedItem().toString();

        List<String> months = new ArrayList<>(Arrays.asList(getResources().getStringArray( R.array.months)));

        Calendar mycal = new GregorianCalendar(year ,months.indexOf(month), 1);

        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] days_array = new String[daysInMonth];

        for(int k = 0; k < daysInMonth; k++)
            days_array[k] = ""+ (k+1);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, days_array);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spinner.setAdapter(spinnerArrayAdapter);
    }



    public void populateDate(View v)
    {
        int year_selected = Integer.parseInt(year_spinner.getSelectedItem().toString());
        int day_selected = Integer.parseInt(day_spinner.getSelectedItem().toString());
        String month_selected = month_spinner.getSelectedItem().toString();

        TextView mytext = (TextView) findViewById(R.id.selectedDate);
        mytext.setText(day_selected + " / "+month_selected+" / "+year_selected);
    }

    public void populateYears(int minAge, int maxAge)
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        String[] years_array = new String[maxAge-minAge];

        for(int i=0; i < maxAge-minAge; i++)
            years_array[i] = ""+ (currentYear - minAge - i);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, years_array);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(spinnerArrayAdapter);

    }

    public void populateYearsByRange(int minYear, int maxYear)
    {

        String[] years_array = new String[maxYear-minYear];

        int count =0;
        for(int i=minYear; i <= maxYear; i++) {
            years_array[count] = "" + i;
            count++;
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, years_array);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(spinnerArrayAdapter);

    }
}
