package hunt.gilpix.wallpaper;

	import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
	 
	public class Nature extends Activity implements OnClickListener {
	    ImageView display;
	    int tophone;
	    ImageView back;
	    private Animation animation,animation1,animation2,animation3,animation4;
	    
	    int count=0;
	    int[] imageId={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
	    		R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,
	    		R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,
	    		R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,
	    		R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20,
	    		R.drawable.image21,R.drawable.image22,R.drawable.image23,R.drawable.image24,
	    		R.drawable.image25};  
	    		/*R.drawable.image26,R.drawable.image27,R.drawable.image28,
	    		R.drawable.image29,R.drawable.image30,R.drawable.image31,R.drawable.image32,
	    		R.drawable.image33,R.drawable.image34,R.drawable.image35,R.drawable.image36,
	    		R.drawable.image37,R.drawable.image38,R.drawable.image39,R.drawable.image40,
	    		R.drawable.image41,R.drawable.image42,R.drawable.image43,R.drawable.image44,
	    		R.drawable.image45};
	    		*/
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	 
	        // to our activity to cover the whole screen
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 
	        setContentView(R.layout.nature);
	        Button but = (Button) findViewById(R.id.setwallpaper);
	        Button prev = (Button) findViewById(R.id.button1);
	        Button next = (Button) findViewById(R.id.button2);
	        tophone = R.drawable.image1;
	        
	        final ImageView back = (ImageView) findViewById(R.id.image);  
		       
		      // back.setImageResource(R.drawable.image21);
		       animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.categories);
		       back.startAnimation(animation);
	        
	    
		       ((Button) findViewById(R.id.setwallpaper))
		        .setOnClickListener(new OnClickListener() {
		           
				    public void onClick(View v) {
				    	 back.setBackgroundResource(R.drawable.image4);
					       animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.categories);
					       back.startAnimation(animation);
				        // TODO Auto-generated method stub
				        Toast var;
				        

			            // to set a background we need to use bitmap
			            InputStream is = getResources().openRawResource(tophone);
			            // to phone is a variable that is updated everytime we click on an
			            // ImageView to that imageview resource and by clicking the button
			            // we set the phone background to that image.
			            Bitmap bm = BitmapFactory.decodeStream(is);
			            // decode inputstream is
			          /*  
			            wallpaperManager = WallpaperManager.getInstance(this);
			            wallpaperDrawable = wallpaperManager.getDrawable();
			            display.setImageURI(mUrls[1]);
			            */
			            try {
			                getApplicationContext().setWallpaper(bm);
			                // to set the wallpaper of the phone background we need to ask
			                // permission from the user so add permission of background from
			                // manifest file
			 
			            } catch (IOException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
			            }
			            var = Toast.makeText(Nature.this, "Home Screen changed",
			                    Toast.LENGTH_SHORT);
			 
			            var.show();
			 
				    }
		       
				    
		        });
		       
	 
		       
		       
		       
	        
	       
	        prev.setOnClickListener(this);
	        next.setOnClickListener(this);
	        display = (ImageView) findViewById(R.id.ivdisplay);
	    }
	 
	    
	    
	        @Override
	    public void onClick(View v) {
	        // TODO Auto-generated method stub
	        Toast var;
	        switch (v.getId()) {
	        case R.id.button1:
	        	if(count<=0)
	        		count=imageId.length-1;
	            display.setImageResource(imageId[count]);
	            
	            tophone = imageId[count];
	            count--;
	            break;
	            
	        case R.id.button2:
	        	count++;
	        	if(count>imageId.length-1)
	        		count=0;
	        	else if (count==imageId.length-1){
	            display.setImageResource(imageId[count]);
	            tophone = imageId[count];
	            count=0;
	            break;
	        	}
	        	 display.setImageResource(imageId[count]);
	            tophone = imageId[count];
	            //count++;
	            break;
	        
	        }
	    }
	 
	}