import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;

public class FirstScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);
		
		final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
		final Button secondBtn = (Button) findViewById(R.id.second);
		
		
		final Controller aController = (Controller) getApplicationContext();	
		
		ModelProducts productObject = null; 
		for(int i=1;i<=4;i++)
		{
			int price = 10+i;
			
			productObject = new ModelProducts("Product "+i,"Description "+i,price);
			
			aController.setProducts(productObject);
			
		}	
		int ProductsSize = aController.getProductsArraylistSize();
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		for(int j=0;j< ProductsSize;j++)
		{	
			String pName = aController.getProducts(j).getProductName();
			int pPrice   = aController.getProducts(j).getProductPrice();
			
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			TextView product = new TextView(this);
			product.setText(" "+pName+"    ");
			
			ll.addView(product);
			
			TextView price = new TextView(this);
			price.setText("  $"+pPrice+"     ");
			
			ll.addView(price);
			
			final Button btn = new Button(this);
				btn.setId(j+1);
				btn.setText("Add To Cart");				
				
				btn.setLayoutParams(params);
				
			    final int index = j;
			   
			    btn.setOnClickListener(new OnClickListener() {
			        public void onClick(View v) {
			        	
			            Log.i("TAG", "index :" + index);			            
			            ModelProducts tempProductObject = aController.getProducts(index);
			            
			            if(!aController.getCart().checkProductInCart(tempProductObject))
			            {
			            	btn.setText("Added");			            	
			            	aController.getCart().setProducts(tempProductObject);
			            	
			            	Toast.makeText(getApplicationContext(), "Now Cart size: "+aController.getCart().getCartSize(), 
			            			Toast.LENGTH_LONG).show();
			            }
			            else
			            {			            	 
			            	Toast.makeText(getApplicationContext(), "Product "+(index+1)+" already added in cart.", 
			            			Toast.LENGTH_LONG).show();
			            }	
			        }
			    });			    
			    
			    ll.addView(btn);		    
			     
			    lm.addView(ll);  
		}
				
		secondBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent i = new Intent(getBaseContext(), SecondScreen.class);
				startActivity(i);
			}
		});	
	}	
}
