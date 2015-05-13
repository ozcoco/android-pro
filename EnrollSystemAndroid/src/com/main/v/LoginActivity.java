package com.main.v;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.config.api.API;
import com.util.network.NetworkUtil;

public class LoginActivity extends BasicActivity {

	private String admin = "XLAdministrator";
	
	private String teacher = "XLEnrollPerson";
	
	private EditText edt_userName = null;
	
	private EditText edt_userPassword = null;
	
	private TextView btn_login = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//不显示标题栏
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉信息栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_login);
		//设置标题栏颜色
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.loginBgColor));		
		//设置标题
		getActionBar().setTitle("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t登录");
		//初始化各个控件
		init();

	}

	
	private void init()
	{
		//实例化各个控件
		instanceWeiget();
		//设置各个控件的事件监听器
		setWeigetEvent();
		//设置各个控件的数据
		setWeigetData();
		
	}

	
	private void setWeigetData() {
	
	
	}


	private void setWeigetEvent() {
	
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new Thread(LoginActivity.this).start();
				
				Toast.makeText(getApplicationContext(), "start thread...", Toast.LENGTH_LONG).show();
				
			}
		});
	
	}


	private void instanceWeiget() {
		
		this.btn_login = (TextView) findViewById(R.id.btn_login);
		
		this.edt_userName = (EditText) findViewById(R.id.edt_userName);
		
		this.edt_userPassword = (EditText) findViewById(R.id.edt_userPassword);
		
	}

	
	@Override
	public void run() {
		
		
		String url = API.login.login_api(
				this.edt_userName.getText().toString(), 
				this.edt_userPassword.getText().toString());
		
		Log.i("url", url);
		
		String result = NetworkUtil.getResponseData(url);
		
		Log.i("result", result);
		
		if(result.equals(admin))
		{
			
			startActivity(new Intent(LoginActivity.this,ManageMenuActivity.class));
			
		}
		
		
		if(result.equals(teacher))
		{
		
			startActivity(new Intent(LoginActivity.this,TeacherMenuActivity.class));
			
			
		}
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		
		if (id == R.id.action_settings) {

			AlertDialog.Builder builder = new Builder(this);
			
			builder.setTitle("设置IP");
			
			View view =	getLayoutInflater().inflate(R.layout.dialog_set_ip, null);
			
			TextView  prompt = (TextView) view.findViewById(R.id.txt_showIP);
			
			final EditText  ip = (EditText) view.findViewById(R.id.edt_ip);
			
			prompt.setText("当前IP:"+API.getBasic_Url().
							replace("http://", "").
							replace(":8080/EnrollSystem/", ""));
			
			builder.setView(view);
				
			builder.setPositiveButton("确认",  
	                new DialogInterface.OnClickListener() {  
	                    public void onClick(DialogInterface dialog, int whichButton) { 
	                    	
	                    	if(ip.getText().toString()!=null&&
	                    			!ip.getText().toString().equals(""))
	                    	{
	                    		
	                    		API.setBasic_Url(ip.getText().toString());  
	                    	
	                    	}
	                    }  
	                });  
			
	        builder.setNegativeButton("取消",null);  			
			
			builder.show();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
