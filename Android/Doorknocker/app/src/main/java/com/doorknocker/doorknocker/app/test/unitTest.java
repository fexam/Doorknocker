package com.doorknocker.doorknocker.app.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.Spinner;

import com.doorknocker.doorknocker.app.MainActivity;
import com.doorknocker.doorknocker.app.R;
import com.robotium.solo.Solo;

/**
 * Created by nutjung on 4/28/2014.
 */
public class unitTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    public unitTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    private int numWing(String dormName,String floor){
        if(dormName.equalsIgnoreCase("Bray")||dormName.equalsIgnoreCase("Cary")
                ||dormName.equalsIgnoreCase("Crockett")||dormName.equalsIgnoreCase("Hall")
                ||dormName.equalsIgnoreCase("Nason")) {
            return 2;
        }
        if(dormName.equalsIgnoreCase("BARH A")&&floor.equalsIgnoreCase("4th floor")){
            return 2;
        }
        if(dormName.equalsIgnoreCase("BARH D")&&floor.equalsIgnoreCase("4th floor")){
            return 2;
        }
        if(dormName.equalsIgnoreCase("Barton")&&!floor.equalsIgnoreCase("1st floor")){
            return 2;
        }
        return 0;
    }

    public void testLogin() throws InterruptedException {

        String dormL[] ={"BARH A","BARH B","BARH C","BARH D","Barton",
                            "Bray","Cary","Crockett","Hall","Nason"};
        String floorL[] ={"1st floor","2nd floor","3rd floor","4th floor"};
        int floor[] = {4,3,3,4,4,3,3,3,3,3};
        EditText UserName = (EditText) solo.getView(R.id.UsernameEditText);
        solo.enterText(UserName,"hancom");
        EditText PassWord = (EditText) solo.getView(R.id.PasswordEditText);
        solo.enterText(PassWord,"test");
        solo.clickOnButton("Login");
        boolean spin = false;
        for(int i=0;i<dormL.length;i++){
            if(i!=0) {
                solo.pressSpinnerItem(0, 1);
            }
            for(int j=0;j<floor[i];j++) {
                if(j!=0){
                    solo.pressSpinnerItem(1,1);
                }
                int num = numWing(dormL[i],floorL[j]);
                for(int k=0;k<num;k++){
                    solo.clickOnRadioButton(k);
                    spin = solo.isSpinnerTextSelected(dormL[i])
                        && solo.isSpinnerTextSelected(floorL[j])
                        && solo.isRadioButtonChecked(k);
                    assertTrue("Dorm name", spin);
                }
                if(num==0){
                    spin = solo.isSpinnerTextSelected(dormL[i])
                            && solo.isSpinnerTextSelected(floorL[j]);
                    assertTrue("Dorm name", spin);
                }
            }
        }
        /*
        boolean found = solo.searchButton("A102")&&solo.searchButton("A104")&&solo.searchButton("A106");
        assertTrue("BOOOM",found);
        solo.clickOnButton("A102");
        boolean canT = solo.searchButton("Cancel") && solo.searchText("BARH A102");
        assertTrue("BOOOM",canT);
        solo.clickOnButton("Cancel");
        */
    }

    protected void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }

}
