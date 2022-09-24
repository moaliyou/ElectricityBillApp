package com.example.electricitybillapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricitybillapp.classes.ElectricityBill;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout lastMonthReadingInputLayout, currentMonthReadingInputLayout,
            rateChargeInputLayout, kilowattInputLayout, billAmountInputLayout;

    private Button payBillButton;
    private ElectricityBill bill;
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (!getLastMonthReadingInputLayoutText().isEmpty() && !getCurrentMonthReadingInputLayoutText().isEmpty() && !getRateChargeInputLayoutText().isEmpty()) {
                bill.setLastMonthReading(Long.parseLong(getLastMonthReadingInputLayoutText()));
                bill.setCurrentMonthReading(Long.parseLong(getCurrentMonthReadingInputLayoutText()));
                bill.setRateCharge(Double.parseDouble(getRateChargeInputLayoutText()));

                setKilowattInputLayout(String.valueOf(bill.getKilowatt()));
                setBillAmountInputLayout(String.valueOf(bill.getBillAmount()));

            } else {
                setKilowattInputLayout(getResources().getString(R.string.kilowatt_text_string));
                setBillAmountInputLayout(getResources().getString(R.string.kilowatt_text_string));
            }

            payBillButton.setEnabled(!getLastMonthReadingInputLayoutText().isEmpty() && !getCurrentMonthReadingInputLayoutText().isEmpty() && !getRateChargeInputLayoutText().isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        eventHandler();
        clearInputData();

        Log.i("Test", String.format("Resutl: %b", !getLastMonthReadingInputLayoutText().isEmpty() && !getCurrentMonthReadingInputLayoutText().isEmpty() && !getRateChargeInputLayoutText().isEmpty()));

    }

    public String getLastMonthReadingInputLayoutText() {
        return Objects.requireNonNull(lastMonthReadingInputLayout.getEditText()).getText().toString();
    }

    public String getCurrentMonthReadingInputLayoutText() {
        return Objects.requireNonNull(currentMonthReadingInputLayout.getEditText()).getText().toString();
    }

    public String getRateChargeInputLayoutText() {
        return Objects.requireNonNull(rateChargeInputLayout.getEditText()).getText().toString();
    }

    public void setKilowattInputLayout(String kilowattInputLayoutText) {
        Objects.requireNonNull(this.kilowattInputLayout.getEditText()).setText(kilowattInputLayoutText);
    }

    public void setBillAmountInputLayout(String billAmountInputLayoutText) {
        Objects.requireNonNull(this.billAmountInputLayout.getEditText()).setText(billAmountInputLayoutText);
    }

    public void setLastMonthReadingInputLayout(String lastMonthReadingInputLayoutText) {
        Objects.requireNonNull(this.lastMonthReadingInputLayout.getEditText()).setText(lastMonthReadingInputLayoutText);
    }

    public void setCurrentMonthReadingInputLayout(String currentMonthReadingInputLayoutText) {
        Objects.requireNonNull(this.currentMonthReadingInputLayout.getEditText()).setText(currentMonthReadingInputLayoutText);
    }

    public void setRateChargeInputLayout(String rateChargeInputLayoutText) {
        Objects.requireNonNull(this.rateChargeInputLayout.getEditText()).setText(rateChargeInputLayoutText);
    }

    private void eventHandler() {
        payBillButton.setOnClickListener(this);
        Objects.requireNonNull(lastMonthReadingInputLayout.getEditText()).addTextChangedListener(watcher);
        Objects.requireNonNull(currentMonthReadingInputLayout.getEditText()).addTextChangedListener(watcher);
        Objects.requireNonNull(rateChargeInputLayout.getEditText()).addTextChangedListener(watcher);
    }

    private void clearInputData() {
        setLastMonthReadingInputLayout(null);
        setCurrentMonthReadingInputLayout(null);
        setRateChargeInputLayout(null);
        setKilowattInputLayout(getResources().getString(R.string.kilowatt_text_string));
        setBillAmountInputLayout(getResources().getString(R.string.bill_amount_text_string));
    }

    private void initializeViews() {
        lastMonthReadingInputLayout = findViewById(R.id.lastMonthReadingInputLayout);
        currentMonthReadingInputLayout = findViewById(R.id.currentMonthReadingInputLayout);
        rateChargeInputLayout = findViewById(R.id.rateChargeInputLayout);
        kilowattInputLayout = findViewById(R.id.kilowattInputLayout);
        billAmountInputLayout = findViewById(R.id.billAmountInputLayout);
        payBillButton = findViewById(R.id.payBillButton);
        bill = new ElectricityBill();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.payBillButton) {
            Snackbar.make(v, "Paid :)", Snackbar.LENGTH_LONG).show();
        }
    }
}