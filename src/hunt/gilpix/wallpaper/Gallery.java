package hunt.gilpix.wallpaper;	

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle; 
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

		public class Gallery extends Activity {
			
		    private static final int SELECT_PICTURE = 1;		 
		    private String selectedImagePath;
		    private ImageView img;
		    int tophone;
		    int arr[];
		    Uri selectedImageUri;
		 
		    
		    AnimationDrawable AniFrame;

		    
		    
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        // to our activity to cover the whole screen
		        requestWindowFeature(Window.FEATURE_NO_TITLE);
		        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		        setContentView(R.layout.gallery);
		        
		      		   
		        
		        
		       
		        ImageView MyImageView = (ImageView)findViewById(R.id.myImageView);
		        MyImageView.setBackgroundResource(R.drawable.progress);
		        AniFrame = (AnimationDrawable) MyImageView.getBackground();
		        
		        
		      
		        img = (ImageView)findViewById(R.id.image);
		  
		        ((Button) findViewById(R.id.pick))
		                .setOnClickListener(new OnClickListener() {
		                    public void onClick(View arg0) {
		                        Intent intent = new Intent();
		                        intent.setType("image/*");
		                        intent.setAction(Intent.ACTION_GET_CONTENT);
		                     //  code for crop image
		                        intent.putExtra("crop", "true");
		                        intent.putExtra("aspectX", 3);
		                        intent.putExtra("aspectY", 4);
		                        intent.putExtra("outputX", 800);
		                        intent.putExtra("outputY", 700);

		                        try {

		                        intent.putExtra("return-data", true);
		                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
		                        } catch (ActivityNotFoundException e) {
		                        // Do nothing for now
		                        }
		                        }
		                        });
		                        


		                       
		                 
		        ((Button) findViewById(R.id.setimage))
                .setOnClickListener(new OnClickListener() {
                   
			    public void onClick(View v) {
			        // TODO Auto-generated method stub
			        Toast var;
			        if(selectedImageUri != null)
			        {
			        	 AniFrame.start();
			        		Bitmap image = null;							
								try {
									image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace(); 
								} catch (IOException e1) {
									// TODO Auto-generated catch block		
									e1.printStackTrace();
								}
							
			        		WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
			        		try {
								myWallpaperManager.setBitmap(image);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								var = Toast.makeText(Gallery.this, "Home Screen Not Changed",Toast.LENGTH_SHORT);
					               var.show();
								e.printStackTrace();
							}
			             
			        	var = Toast.makeText(Gallery.this, "Home Screen Changed",Toast.LENGTH_SHORT);
			               var.show();
			               AniFrame.stop();
			       }
			        else {
			        	var = Toast.makeText(Gallery.this, "Pick Image From Gallery",Toast.LENGTH_SHORT);
			               var.show();
			        }
			       
		        }});
		        };
            
		    
		 
		    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		        if (resultCode == RESULT_OK) {
		            if (requestCode == SELECT_PICTURE) { 
		                 selectedImageUri = data.getData();
		                selectedImagePath = getPath(selectedImageUri);
		                System.out.println("Image Path : " + selectedImagePath);
		                Uri tophone1 =data.getData();
		                img.setImageURI(selectedImageUri);
		             
		            }
		        }
		    }
		 
		    public String getPath(Uri uri) {
		        String[] projection = { MediaStore.Images.Media.DATA };
		        Cursor cursor = managedQuery(uri, projection, null, null, null);
		        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		        cursor.moveToFirst();
		        return cursor.getString(column_index);
		    }
		}