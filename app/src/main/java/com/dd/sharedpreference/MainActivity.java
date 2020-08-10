package com.dd.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_result;
    private EditText edt_id, edt_pwd;
    private Button btn_save;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);
        edt_id = findViewById(R.id.edt_id);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_save = findViewById(R.id.btn_save);

        //getSharedPreferences("파일이름",'모드')
        //모드 => 0 (읽기,쓰기가능)
        //모드 => MODE_PRIVATE (이 앱에서만 사용가능)
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        //버튼 이벤트
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Editor를 preferences에 쓰겠다고 연결
                SharedPreferences.Editor editor = preferences.edit();
                //putString(KEY,VALUE)
                editor.putString("userid",edt_id.getText().toString());
                editor.putString("userpwd",edt_pwd.getText().toString());
                //항상 commit & apply 를 해주어야 저장이 된다.
                editor.commit();
                //메소드 호출
                getPreferences();
            }
        });
    }
    //Preferences에서 꺼내오는 메소드
    private void getPreferences(){
        //getString(KEY,KEY값이 없을때 대체)
        tv_result.setText("USERID = " + preferences.getString("userid","") + " \n USERPWD = " + preferences.getString("userpwd",""));
    }
}