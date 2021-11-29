package tushargarg.com.expenditracker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import tushargarg.com.expenditracker.R;
import tushargarg.com.expenditracker.database.ExpenseDatabaseHelper;
import tushargarg.com.expenditracker.presenter.CategoryPresenter;
import tushargarg.com.expenditracker.view.AddCategoryView;

import static tushargarg.com.expenditracker.activity.MainActivity.ADD_NEW_CAT;


public class AddCategoryActivity extends FragmentActivity implements AddCategoryView {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.new_category);
  }

  public void addCategory(View view) {
    ExpenseDatabaseHelper expenseDatabaseHelper = new ExpenseDatabaseHelper(this);
    CategoryPresenter categoryPresenter = new CategoryPresenter(this, expenseDatabaseHelper);

    if(categoryPresenter.addCategory())
      Toast.makeText(this, getString(R.string.add_category_success), Toast.LENGTH_LONG).show();

    expenseDatabaseHelper.close();
    finishActivity(ADD_NEW_CAT);
  }

  @Override
  public String getCategory() {
    TextView categoryInput = (TextView) findViewById(R.id.category);
    return categoryInput.getText().toString();
  }

  @Override
  public void displayError() {
    TextView view = (TextView) this.findViewById(R.id.category);
    view.setError(this.getString(R.string.category_empty_error));
  }
}
