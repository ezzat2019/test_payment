package com.example.testpayment.google_pay;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testpayment.R;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;

import java.util.Locale;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class GooglePayActivity extends AppCompatActivity {

    private PaymentsClient paymentsClient;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_pay);

        Wallet.WalletOptions options=new Wallet.WalletOptions.Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();
        paymentsClient=Wallet.getPaymentsClient(getApplicationContext(),options);

        IsReadyToPayRequest readyToPayRequest=
                null;
        try {
            readyToPayRequest = IsReadyToPayRequest.fromJson(baseConf().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Task<Boolean> task=paymentsClient.isReadyToPay(readyToPayRequest);
        try {
            baseConf().put("transactionInfo",getTransactionInfo("12"))
                    .put("merchantInfo",getMerchantInfo());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PaymentDataRequest request= null;
        try {
            request = PaymentDataRequest.fromJson(baseConf().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AutoResolveHelper.resolveTask(paymentsClient.loadPaymentData(request),this,20);
        task.addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful())
                {
                    try {
                        showButton(task.getResult());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    
                }
            }
        });






    }
    private  JSONObject baseConf() throws JSONException {
        return new JSONObject()
                .put("apiVersion",2)
                .put("apiVersionMinor",0)
                .put("allowedPaymentMethods",new JSONArray().put(getBaseCardPaymentMethod()));
    }

    private  JSONObject getBaseCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");
        cardPaymentMethod.put("tokenizationSpecification",getGatewayTokenizationSpecification());

        JSONObject parameters = new JSONObject();
        parameters.put("allowedAuthMethods", getAllowedCardAuthMethods());
        parameters.put("allowedCardNetworks", getAllowedCardNetworks());


        cardPaymentMethod.put("parameters", parameters);

        return cardPaymentMethod;
    }
    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
        return new JSONObject() {{
            put("type", "PAYMENT_GATEWAY");
            put("parameters", new JSONObject() {{
                put("gateway", "mpgs");
                put("gatewayMerchantId", "");
            }});
        }};
    }
    private static JSONObject getMerchantInfo() throws JSONException {
        return new JSONObject().put("merchantName", "wezz");
    }
    private static JSONObject getTransactionInfo(String price) throws JSONException {
        JSONObject transactionInfo = new JSONObject();
        transactionInfo.put("totalPrice", price);
        transactionInfo.put("totalPriceStatus", "FINAL");

        transactionInfo.put("currencyCode", "USD");


        return transactionInfo;
    }
    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY")
                .put("CRYPTOGRAM_3DS");
    }
    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("DISCOVER")
                .put("INTERAC")
                .put("JCB")
                .put("MASTERCARD")
                .put("VISA");
    }

    private void showButton(Boolean result) throws JSONException {
        if (result)
        {

        }

    }


}