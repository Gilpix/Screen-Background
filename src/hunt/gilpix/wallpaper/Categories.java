package hunt.gilpix.wallpaper;

import java.io.File;





import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Categories extends Activity {

	ImageView image1;
	private Animation animation,animation1,animation2,animation3,animation4;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories); 
		
		
		// animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.picmov);
         image1 = (ImageView) findViewById(R.id.image);
      //   animation.setAnimationListener((AnimationListener) this);
	
        // back.setBackgroundResource(R.drawable.image42);
	       animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.categories);
	       image1.startAnimation(animation);
         
        
         
         
         
         
		 Button nat =(Button)findViewById(R.id.button1);
		     nat.setOnClickListener(ibLis);
		     
		     Button gallery =(Button)findViewById(R.id.gallery);
		     gallery.setOnClickListener(ibLis1);  
		     
		     Button camera =(Button)findViewById(R.id.camera);
		     camera.setOnClickListener(ibLis2);  
		
		   //----------ACTION BAR----------//
			    
			    ActionBar actionbar = getActionBar();
			   // actionbar.setDisplayUseLogoEnabled(false);//
		       
                  actionbar.hide();			   
                  // actionbar.setDisplayHomeAsUpEnabled(true);//use icon with back graphics
		        //actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
		       //use icon with back graphics
		        //Drawable d=getResources().getDrawable(R.drawable.log);  
		        //getActionBar().setBackgroundDrawable(d);
		        
		        //----------ACTION BAR----------//
			   
	}
		        
		        
		
	
	      private OnClickListener ibLis2=new OnClickListener(){

	          @Override
	          public void onClick(View v) {
	           
	            //START YOUR ACTIVITY HERE AS  
	             Intent intent = new Intent(Categories.this,Camera.class);
	             startActivity(intent);
	        }
	      };
	
				 private OnClickListener ibLis=new OnClickListener(){

				        @Override
				        public void onClick(View v) {
				           
				            //START YOUR ACTIVITY HERE AS  
				             Intent intent = new Intent(Categories.this,Nature.class);
				             startActivity(intent);
				        }
			   
			};
			 private OnClickListener ibLis1=new OnClickListener(){

			        @Override
			        public void onClick(View v) {
			           
			            //START YOUR ACTIVITY HERE AS  
			             Intent intent = new Intent(Categories.this,Gallery.class);
			             startActivity(intent);
			        }
		   
		};

			
			
			
			//CREATE FOLDER 
			String folder_main = "ASQWERRHHHHH";
	        File f = new File(Environment.getExternalStorageDirectory(),folder_main);{
	        if (!f.exists()) {
	            f.mkdirs();
	        }}
	        
	        /*  //create another folder inside
	         * 
	         File f1 = new File(Environment.getExternalStorageDirectory() + "/"
                + folder_main, "product1");
        if (!f1.exists()) {
            f1.mkdirs();
        }
	         */
			
			
			
		
}