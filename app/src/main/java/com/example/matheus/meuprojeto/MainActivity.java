package com.example.matheus.meuprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private LoginButton login;
    private CallbackManager callbackManager;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Button btnNovaTela = findViewById(R.id.button3);
        btnNovaTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastrarUsuario.class);
                startActivity(it);
            }
        });
    }
    private void initView(){
        login = findViewById(R.id.loginFacebook);
        textView = findViewById(R.id.textView);

        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        final String token = loginResult.getAccessToken().getToken();

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                getData(object);

                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields","id,email,birthday,name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code

                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }
                });
    }

    private void getData(JSONObject object) {
        try{
            textView.setText("true");
            textView.setText(" Nome = "+object.getString("name") + "nascimento = "+object.getString("birthday"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
