package com.example.communicationapp.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.SubmitUserParam;
import com.example.communicationapp.entity.SubmitUserResult;
import com.example.communicationapp.entity.User;
import com.example.communicationapp.http.UserService;
import com.example.communicationapp.util.DeviceUtil;
import com.example.communicationapp.util.HttpServiceCreator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {

    private EditText et_username;
    private EditText et_password;
    private Button btn_submit;
    final UserService userService = HttpServiceCreator.create(UserService.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container,false);

        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);
        btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = et_username.getText().toString();
                final String password = et_password.getText().toString();
                User user = new User(username, password);
                SubmitUserParam submitUserParam = new SubmitUserParam(user, DeviceUtil.getDevice(getContext()));
                userService.addUser(submitUserParam).enqueue(new Callback<SubmitUserResult>() {
                    @Override
                    public void onResponse(Call<SubmitUserResult> call, Response<SubmitUserResult> response) {
                        SubmitUserResult submitUserResult = response.body();
                        if(submitUserResult != null){
                            Toast.makeText(getContext(), submitUserResult.getMessage(), Toast.LENGTH_SHORT).show();

                            if(submitUserResult.getCode() == 1){
                                // 存储密码
                                SharedPreferences.Editor edit = getContext()
                                        .getSharedPreferences(getResources().getString(R.string.login_key), MODE_PRIVATE).edit();
                                edit.putString(getResources().getString(R.string.username_key), username);
                                edit.putString(getResources().getString(R.string.password_key), password);
                                edit.commit();

                                // 显示UserDetailFragment
                                Bundle args = new Bundle();
                                args.putString(getResources().getString(R.string.username_key), username);
                                UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(args);
                                if(!userDetailFragment.isAdded()){
                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.rl_fragment_container, userDetailFragment).commit();
                                }
                            }

                        }
                        else{
                            Toast.makeText(getContext(), "由于服务器原因，注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitUserResult> call, Throwable t) {
                        Toast.makeText(getContext(), "由于网络原因，注册失败 " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    public static RegisterFragment newInstance(Bundle args){
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.setArguments(args);
        return registerFragment;
    }
}
