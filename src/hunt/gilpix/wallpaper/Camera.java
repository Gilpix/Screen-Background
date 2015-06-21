package hunt.gilpix.wallpaper;

import java.io.IOException;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;




	public class Camera extends Activity implements OnClickListener {
		 
	    final int CAMERA_CAPTURE = 1;
	    final int CROP_PIC = 2;
	    private Uri picUri;
	    Bitmap setphoto;
	 
	    private Animation animation,animation1,animation2,animation3,animation4;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.camera);
	 
	        Button captureBtn = (Button) findViewById(R.id.camera);  
	        captureBtn.setOnClickListener(this);
	        
	        ActionBar actionbar = getActionBar();
			   actionbar.hide();			   
	        
	       final ImageView back = (ImageView) findViewById(R.id.back);  
	       
	      // back.setImageResource(R.drawable.image21);
	       animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
	       back.startAnimation(animation);
	     
	  
	    ((Button) findViewById(R.id.setimage))
        .setOnClickListener(new OnClickListener() {
           
		    public void onClick(View v) {
		    	
		    	 back.setBackgroundResource(R.drawable.image4);
			       animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
			       back.startAnimation(animation);
		        // TODO Auto-generated method stub
		        Toast var;
		        if(setphoto != null) 
		        { 
		        		Bitmap image = setphoto;							
							
		        		WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
		        		try {
							myWallpaperManager.setBitmap(image);
						} catch (IOException e) {
							// TODO Auto-generated catch block 
							var = Toast.makeText(Camera.this, "Home Screen Not Changed",Toast.LENGTH_SHORT);
				               var.show();
							e.printStackTrace();
						}
		             
		        	var = Toast.makeText(Camera.this, "Home Screen Changed",Toast.LENGTH_SHORT);
		               var.show();
		       }
		        else {
		        	var = Toast.makeText(Camera.this, "Pick Image From Gallery",Toast.LENGTH_SHORT);
		               var.show();
		        }
		       
	        }});
	        };
 
	        
	        
	        
	        
	        
	        
	        
	        
	        
		
		
			
		
	    
	    
	    
	    
	    
	    
	    
	    
	    public void onClick(View v) {
	        if (v.getId() == R.id.camera) {
	            try {
	                // use standard intent to capture an image
	                Intent captureIntent = new Intent(
	                        MediaStore.ACTION_IMAGE_CAPTURE);
	                // we will handle the returned data in onActivityResult
	                startActivityForResult(captureIntent, CAMERA_CAPTURE);
	            } catch (ActivityNotFoundException anfe) {
	               // Toast toast = Toast.makeText(this, "This device doesn't support the crop action!",
	                 //       Toast.LENGTH_SHORT);
	                //toast.show();
	            }
	        } }
	       
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == CAMERA_CAPTURE) {
	                // get the Uri for the captured image
	                picUri = data.getData();

	                Intent intent = new Intent("com.android.camera.action.CROP");
	                intent.setType("image/*");
	                List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
	                int size = list.size();
	                if (size != 0) {            
	                	performCrop();
	                	//Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();
	                    }
	                else
	                {
	                	Bundle extras = data.getExtras();
		                // get the cropped bitmap
		                Bitmap thePic = extras.getParcelable("data");
		                setphoto = thePic;
		                ImageView picView = (ImageView) findViewById(R.id.image);
		                picView.setImageBitmap(thePic);
	                }
	                    
	                
	                }
	            // user is returning from cropping the image
	            else if (requestCode == CROP_PIC) {
	                // get the returned data
	                Bundle extras = data.getExtras();
	                // get the cropped bitmap
	                Bitmap thePic = extras.getParcelable("data");
	                setphoto = thePic;
	                ImageView picView = (ImageView) findViewById(R.id.image);
	                picView.setImageBitmap(thePic);
	            }
	        }
	    }
	 
	    /**
	     * this function does the crop operation.
	     */
	    private void performCrop() { 
	        // take care of exceptions
	        try {
	            // call the standard crop action intent (the user device may not
	            // support it)
	            Intent cropIntent = new Intent("com.android.camera.action.CROP");
	            // indicate image type and Uri
	            cropIntent.setDataAndType(picUri, "image/*");
	            // set crop properties
	            cropIntent.putExtra("crop", "true");
	            // indicate aspect of desired crop
	            cropIntent.putExtra("aspectX", 3);
	            cropIntent.putExtra("aspectY", 4);
	            // indicate output X and Y
	            cropIntent.putExtra("outputX", 800);
	            cropIntent.putExtra("outputY", 800);
	            // retrieve data on return
	            cropIntent.putExtra("return-data", true);
	            // start the activity - we handle returning in onActivityResult
	            startActivityForResult(cropIntent, CROP_PIC);
	        }
	        // respond to users whose devices do not support the crop action
	        catch (ActivityNotFoundException anfe) {
	            Toast toast = Toast
	                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
	            toast.show();
	        }
	    }
	}