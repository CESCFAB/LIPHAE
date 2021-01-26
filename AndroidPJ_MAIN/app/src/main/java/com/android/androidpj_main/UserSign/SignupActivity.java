package com.android.androidpj_main.UserSign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.androidpj_main.Activity.PurchaseActivity;
import com.android.androidpj_main.Activity.RegisterAddress;
import com.android.androidpj_main.NetworkTask.CartNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

public class SignupActivity extends Activity {

    String macIP = ShareVar.macIP;

    EditText signupEmail, signupPw, signupPwConfirm, signupName;
    EditText telNo1, telNo2, telNo3;

    TextView signupErrorEmail;
    TextView signupErrorPwCheck;

    Button btnGotoGender;

    //아이디 이메일 양싱.
    private String emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    int check1 = 0;
    int check2 = 0;


    String resultEmail;
    String resultPw;
    String resultName;
    String resultTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Resource 이어주기
        signupEmail = findViewById(R.id.signup_email);
        signupPw = findViewById(R.id.signup_password);
        signupPwConfirm = findViewById(R.id.signup_password_confirm);
        signupName = findViewById(R.id.signup_name);

        telNo1 = findViewById(R.id.telno1);
        telNo2 = findViewById(R.id.telno2);
        telNo3 = findViewById(R.id.telno3);

        signupErrorEmail = findViewById(R.id.signup_error_email);
        signupErrorPwCheck = findViewById(R.id.signup_error_pwConfirm);


        // Textwatcher
        signupEmail.addTextChangedListener(emailCheck);
        // pwCheck
        signupPwConfirm.addTextChangedListener(pwCheck);

        // 카드번호 4개씩 자동이동
        telNo1.addTextChangedListener(addTextChangedListener);
        telNo2.addTextChangedListener(addTextChangedListener);
        telNo3.addTextChangedListener(addTextChangedListener);


        //
        btnGotoGender = findViewById(R.id.btn_goto_gender);
        btnGotoGender.setOnClickListener(finalRegisterListener);



    }

    /**
     * email 형식 체크
     */
    TextWatcher emailCheck = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String sid = signupEmail.getText().toString().trim();

            if(sid.length() == 0){   //null값일때, 메세지 표시안하도록

                signupErrorEmail.setText("");

            }else {


                if (sid.matches(emailValidation) && s.length() > 0) {

                    signupErrorEmail.setText("올바른 이메일 형식입니다.");//글씨.

                    signupEmail.setBackgroundResource(R.drawable.et_black); //박스.


                    String strColor = "#0000FF";

                    signupErrorEmail.setTextColor(Color.parseColor(strColor));

                    //     inputEmail.setBackgroundResource(R.drawable.edt_bg_selelctor);

                    //     btnReset.setBackgroundColor(Color.parseColor("#007ed6"));
                    check1 = 1;

                } else {

                    signupErrorEmail.setText("올바른 이메일 형식이 아닙니다."); //글씨.

                    String strColor2 = "#FF0000"; //색깔.

                    signupErrorEmail.setTextColor(Color.parseColor(strColor2)); //글씨색깔.

                    signupEmail.setBackgroundResource(R.drawable.et_red); //박스


                    //      inputEmail.setBackgroundResource(R.drawable.edt_bg_wrong_validate);

                    //     btnReset.setBackgroundColor(Color.parseColor("#c0c0c0"));

                    check1 = 0;
                }

            }

        }
    };

    /**
     * 비밀번호 확인 체크
     */
    TextWatcher pwCheck = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String pwCheck = signupPwConfirm.getText().toString().trim();

            if(pwCheck.length() == 0){   //null값일때, 메세지 표시안하도록

                signupErrorPwCheck.setText("");

            }else {


                if (pwCheck.equals(signupPw.getText().toString().trim())) {

                    signupErrorPwCheck.setText("비밀번호가 같습니다");//글씨.

                    signupPwConfirm.setBackgroundResource(R.drawable.et_black); //박스.


                    String strColor = "#0000FF";

                    signupErrorPwCheck.setTextColor(Color.parseColor(strColor));

                    //     inputEmail.setBackgroundResource(R.drawable.edt_bg_selelctor);

                    //     btnReset.setBackgroundColor(Color.parseColor("#007ed6"));

                    check2 = 1;

                } else {

                    signupErrorPwCheck.setText("비밀번호가 다릅니다"); //글씨.

                    String strColor2 = "#FF0000"; //색깔.

                    signupErrorPwCheck.setTextColor(Color.parseColor(strColor2)); //글씨색깔.

                    signupPwConfirm.setBackgroundResource(R.drawable.et_red); //박스


                    //      inputEmail.setBackgroundResource(R.drawable.edt_bg_wrong_validate);

                    //     btnReset.setBackgroundColor(Color.parseColor("#c0c0c0"));

                    check2 = 0;

                }

            }

        }
    };

    /**
     * 텍스트 웟쳐
     */
    TextWatcher addTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (telNo1.length() == 3) {               // edit1 맥스값을 3이라고 가정했을때
                telNo2.requestFocus();             // 두번째로 포커스가 넘어간다
            }
            if (telNo2.length() == 4) {
                telNo3.requestFocus();
            }


        }



        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener finalRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String telNo = telNo1.getText().toString() + telNo2.getText().toString() + telNo3.getText().toString();

            if (signupEmail.getText().toString().trim().length() == 0 || check1 != 1){
                Toast.makeText(SignupActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                signupEmail.requestFocus();
                signupEmail.setCursorVisible(true);
            }else if (signupPw.getText().toString().trim().length() == 0){
                Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                signupPw.requestFocus();
                signupPw.setCursorVisible(true);
            }else if (signupPwConfirm.getText().toString().trim().length() == 0 || check2 != 1){
                Toast.makeText(SignupActivity.this, "비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                signupPwConfirm.requestFocus();
                signupPwConfirm.setCursorVisible(true);
            }else if (signupName.getText().toString().trim().length() == 0){
                Toast.makeText(SignupActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                signupName.requestFocus();
                signupName.setCursorVisible(true);
            }else if (telNo1.getText().toString().trim().length() < 3 || telNo2.getText().toString().trim().length() < 4 || telNo3.getText().toString().trim().length() < 4){
                Toast.makeText(SignupActivity.this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            }else{

                // 회원가입 정보 텍스트박스
                 resultEmail = String.valueOf(signupEmail.getText());
                 resultPw = String.valueOf(signupPw.getText());
                 resultName = String.valueOf(signupName.getText());
                 resultTel = telNo;

                if (userinfoInsert().equals("1")){
                    Toast.makeText(SignupActivity.this, "USER INFO 입력 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, SignupActivity_2.class);
                    startActivity(intent);
                    finish();
                }

            }




        }
    };

    /**
     * user 에 INSERT DATA
     */
    private String userinfoInsert(){
        String result = null;

        String urlAddrInsert = "http://" + macIP + ":8080/JSP/userinfo_insert_1.jsp?userEmail=" + resultEmail + "&userPw=" + resultPw + "&userName="+ resultName + "&userTel="+ resultTel;

        try {
            ///////////////////////////////////////////////////////////////////////////////////////
            // Date : 2020.01.11
            //
            // Description:
            //  - 입력하고 리턴값을 받음
            //
            ///////////////////////////////////////////////////////////////////////////////////////
            CartNetworkTask networkTask = new CartNetworkTask(SignupActivity.this, urlAddrInsert, "insert");
            ///////////////////////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////////////////////
            // Date : 2020.12.24
            //
            // Description:
            //  - 입력 결과 값을 받기 위해 Object로 return후에 String으로 변환 하여 사용
            //
            ///////////////////////////////////////////////////////////////////////////////////////
            Object obj = networkTask.execute().get();
            result = (String) obj;
            ///////////////////////////////////////////////////////////////////////////////////////

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }



} // ----