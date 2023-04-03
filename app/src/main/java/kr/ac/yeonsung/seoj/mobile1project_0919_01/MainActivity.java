package kr.ac.yeonsung.seoj.mobile1project_0919_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear1; //배경색을 변경해야하므로
    Button btnBg, btnChange; //참조변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear1 = findViewById(R.id.linear1);
        btnBg = findViewById(R.id.btn_bg);
        btnChange = findViewById(R.id.btn_change);
        registerForContextMenu(btnBg); //컨텍스트 메뉴가 나타나도록 등록
        registerForContextMenu(btnChange);

        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        //윈도우 매니저 관리자 객체가 필요해서 생성함
        String msg = "너비: " + display.getWidth() + ", 높이: " + display.getHeight();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo); //액티비티에 컨텍스트메뉴가 생성될 때 쓰는 메소드
        //(컨텍스트 메뉴 객체 참조값을 받는 매개변수, 롱클릭 때 참조값을 받는 매개변수,
        // 컨텍스트 메뉴의 정보를 전달받는 매개변수)

        MenuInflater inflater = getMenuInflater(); //메뉴 인플래이터 참조변수 선언

        if(v == btnBg){ //만약에 뷰 객체 참조값이 btnBg와 같다면,
            menu.setHeaderTitle("배경색 변경");
            inflater.inflate(R.menu.menu1, menu); //menu1에 저장한 3색 모두 menu에 등록됨
        }else if(v == btnChange){
            menu.setHeaderTitle("버튼 변경");
            inflater.inflate(R.menu.menu2, menu); //menu2라는 파일이 나타나야함
        }
    }
    //컨텍스트메뉴의 아이템이 선택되는 메소드(이벤트 처리를 위한 메소드) : onContextItemSelected
    //항목이 변경되면 호출되는 onCreateSelected
    //배경색 3개, 버튼변경 2개. 총 5개 구별해야함
    //그렇다면 onContextItemSelected가 언제 호출되는지를 알아야함
    //아이템의 선택이 변경 되었을때 호출이 됨
    //지금 선택한 아이템 값을 반환받을 수 있음 52
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) { //선택된 항목에 대한 아이디를 반환 받을 수 있음
            case R.id.bg_linear_green: //각각의 다른 처리를 위해 case문 사용
                linear1.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.bg_linear_blue:
                linear1.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.bg_linear_purple:
                linear1.setBackgroundColor(Color.rgb(230,230,250));
                return true;
            case R.id.change_rotation:
                btnBg.setRotation(60);
                return true;
            case R.id.change_size:
                btnBg.setScaleX(2);
                btnBg.setScaleY(1.5f);
                return true;

        }
        return false; //혹시라도 스위치케이스가 제대로 실행이 안됐을 경우면 false로 반환
    }
}