package com.example.communicationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.SubmitUserResult;
import com.example.communicationapp.entity.User;
import com.example.communicationapp.http.UserService;
import com.example.communicationapp.service.GetPositionService;
import com.example.communicationapp.util.HttpServiceCreator;
import com.example.communicationapp.util.StringUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, GetPositionService.class);
        startService(intent);

        Fragment fragment = null;
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.login_key), Context.MODE_PRIVATE);
        if(sharedPreferences != null){
            String username = sharedPreferences.getString(getResources().getString(R.string.username_key), "");
            if(!StringUtils.isEmpty(username)){
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.username_key), username);
                fragment = UserDetailFragment.newInstance(bundle);
            }
        }
        else{
            fragment = RegisterFragment.newInstance(null);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment != null && !fragment.isAdded()){
            fragmentTransaction.add(R.id.rl_fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}
