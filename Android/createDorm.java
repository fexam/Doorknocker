package com.example.app;

import android.app.Activity;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by nutjung on 3/16/14.
 */
public class createDorm extends Dorm implements DormList {


    private String left ="left", right="right";

    public createDorm(String name,int f,int w,Activity m,LinearLayout l,ArrayList<Room> r)
    {
        super(name,f,w,m,l,r);
    }

    public void buildFreshmenHillFirstFloorWing1()
    {
        buildLayout();
        //Add room
        for(int i=101;i<=111;i+=2)
        {
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=102;i<=112;i+=2)
        {
            if(i!=106&&i!=108)
            {
                buildNormalRoom(i,right);
            }
            else if(i==106)
            {
                buildOpenRoom("Rest room",right,false);
            }
            else if(i==108)
            {
                buildOpenRoom("Rest room",right,true);
            }
        }
        buildSmallRoom(113,left,true);
        buildSmallRoom(115,left,false);
    }

    public void buildFreshmenHillFirstFloorWing2()
    {
        buildLayout();
        //Add room
        for(int i=117;i<=125;i+=2)
        {
            buildNormalRoom(i,left);
        }

        for(int i=130;i<133;i++)
        {
            buildEmptyRoom(left);
        }
        buildWalkWay();
        for(int i=114;i<=124;i+=2)
        {
            if(i!=120&&i!=122)
            {
                buildNormalRoom(i,right);
            }
            else if(i==120)
            {
                buildOpenRoom("Rest room",right,false);
            }
            else if(i==122)
            {
                buildOpenRoom("Rest room",right,true);
            }
        }
        buildEmptyRoom(right);
        buildNormalRoom(129,right);
    }

    public void buildFreshmenHillSecondThirdFloorWing1()
    {
        buildLayout();
        //Add room
        for(int i=1+(floor*100);i<=11+(floor*100);i+=2)
        {
            buildNormalRoom(i,left);
        }

        buildWalkWay();

        for(int i=2+(floor*100);i<=12+(floor*100);i+=2)
        {
            if(i!=6+(floor*100)&&i!=8+(floor*100))
            {
                buildNormalRoom(i,right);
            }
            else if(i==6+(floor*100))
            {
                buildOpenRoom("Rest room",right,false);
            }
            else if(i==8+(floor*100))
            {
                buildOpenRoom("Rest room",right,true);
            }
        }

        buildSmallRoom(13+(floor*100),left,true);
        buildSmallRoom(15+(floor*100),left,false);
    }

    public void buildFreshmenHillSecondThirdFloorWing2()
    {
        buildLayout();
        //Add room
        for(int i=17+(floor*100);i<=31+(floor*100);i+=2)
        {
            buildNormalRoom(i,left);
        }

        buildWalkWay();

        for(int i=14+(floor*100);i<=28+(floor*100);i+=2)
        {
            if(i!=20+(floor*100)&&i!=22+(floor*100))
            {
                buildNormalRoom(i,right);
            }
            else if(i==20+(floor*100))
            {
                buildOpenRoom("Rest room",right,false);
            }
            else if(i==22+(floor*100))
            {
                buildOpenRoom("Rest room",right,true);
            }
        }
    }

    public void buildBlitmanFirstFloorWing1(){
        buildLayout();
        for(int i=1301;i<=1325;i+=2){
            if(i!=1301&&i!=1315&&i!=1317&&i!=1319){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=1302;i<=1322;i+=2){
            if(i!=1302){
                buildNormalRoom(i,right);
            }else{
                buildEmptyRoom(right);
            }
        }

    }

    public void buildBlitmanFirstFloorWing2(){
        buildLayout();
        for(int i=1409;i<=1421;i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=1410;i<=1422;i+=2){
            if(i!=1410){
                buildNormalRoom(i,right);
            }else{
                buildEmptyRoom(right);
            }
        }
    }

    public void buildBlitmanOtherFloorWing1(){
        buildLayout();
        for(int i=301+(floor*1000);i<=329+(floor*1000);i+=2){
            if(i!=301+(floor*1000)){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=302+(floor*1000);i<=324+(floor*1000);i+=2){
            buildNormalRoom(i,right);
        }
    }

    public void buildBlitmanOtherFloorWing2(){
        buildLayout();
        for(int i=409+(floor*1000);i<=421+(floor*1000);i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=410+(floor*1000);i<=422+(floor*1000);i+=2){
            if(i!=410+(floor*1000)){
                buildNormalRoom(i,right);
            }else{
                buildEmptyRoom(right);
            }
        }
    }

    public void buildBARHAFirstFloor(){
        buildLayout();
        for(int i=102;i<=112;i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=101;i<=111;i+=2){
            buildEmptyRoom(right);
        }
    }
    public void buildBARHASecondFloor(){
        buildLayout();
        for(int i=202;i<=212;i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=201;i<=211;i+=2){
            if(i<=207){
                buildNormalRoom(i,right);
            }else{
                buildEmptyRoom(right);
            }
        }
    }
    public void buildBARHAThirdFloor(){
        buildLayout();
        for(int i=302;i<=310;i+=2){
            if(i==306){
                buildEmptyRoom(left);
            }else if(i==308){
                buildNormalRoom(306,left);
            }else{
                buildNormalRoom(i,left);
            }
        }
        buildWalkWay();
        for(int i=301;i<=311;i+=2){
            if(i==309){
                buildNormalRoom(311,right);
            }else if(i==311){
                buildEmptyRoom(right);
            }else{
                buildNormalRoom(i,right);
            }
        }
    }
    public void buildBARHAForthFloorWing1(){
        buildLayout();
        for(int i=402;i<=412;i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
        for(int i=401;i<=411;i+=2){
            if(i==409||i==411){
                buildEmptyRoom(right);
            }else{
                buildNormalRoom(i,right);
            }
        }
    }
    public void buildBARHAForthFloorWing2(){
        buildLayout();
        for(int i=413;i<=423;i+=2){
            buildNormalRoom(i,left);
        }
        buildWalkWay();
    }
    public void buildBARHBFirstFloor(){
        buildLayout();
        for(int i=102;i<=112;i+=2){
            if(i==106||i==112){
                buildEmptyRoom(left);
            }else if(i==108){
                buildNormalRoom(106,left);
            }else if(i==110){
                buildNormalRoom(108,left);
            }else{
                buildNormalRoom(i,left);
            }
        }
        buildWalkWay();
        for(int i=101;i<=111;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHBSecondFloor(){
        buildLayout();
        for(int i=202;i<=212;i+=2){
            if(i<=208){
                buildNormalRoom(i,left);
            }else {
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=201;i<=211;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHBThirdFloor(){
        buildLayout();
        for(int i=302;i<=312;i+=2){
            if(i<=308){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=301;i<=311;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHCFirstFloor(){
        buildLayout();
        for(int i=101;i<=111;i+=2){
            if(i==105||i==109||i==111){
                buildEmptyRoom(left);
            }else if(i==107){
                buildNormalRoom(105,left);
            }else{
                buildNormalRoom(i,left);
            }
        }
        buildWalkWay();
        for(int i=102;i<=112;i+=2){
            if(i==106){
                buildEmptyRoom(right);
            }else if(i>=108){
                buildNormalRoom(i-2,right);
            }else{
                buildNormalRoom(i,right);
            }
        }
    }
    public void buildBARHCSecondFloor(){
        buildLayout();
        for(int i=201;i<=211;i+=2){
            if(i<=207){
                buildNormalRoom(i,left);
            }else {
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=202;i<=212;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHCThirdFloor(){
        buildLayout();
        for(int i=301;i<=311;i+=2){
            if(i<=307){
                buildNormalRoom(i,left);
            }else {
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=302;i<=312;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHDFirstFloor(){
        buildLayout();
        for(int i=101;i<=111;i+=2){
            if(i<=103){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=102;i<=112;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHDSecondFloor(){
        buildLayout();
        for(int i=201;i<=211;i+=2){
            if(i<=207){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=202;i<=222;i+=2){
            buildNormalRoom(i,right);
        }
    }
    public void buildBARHDThirdFloor(){
        buildLayout();
        for(int i=301;i<=311;i+=2){
            if(i==309){
                buildNormalRoom(311,left);
            }else if(i==311){
                buildEmptyRoom(left);
            }else{
                buildNormalRoom(i,left);
            }
        }
        buildWalkWay();
        for(int i=302;i<=312;i+=2){
            if(i==306){
                buildEmptyRoom(right);
            }else if(i>=308){
                buildNormalRoom(i-2,right);
            }else{
                buildNormalRoom(i,right);
            }
        }
    }
    public void buildBARHDForthFloorWing1(){
        buildLayout();
        for(int i=401;i<=411;i+=2){
            if(i<=407){
                buildNormalRoom(i,left);
            }else{
                buildEmptyRoom(left);
            }
        }
        buildWalkWay();
        for(int i=402;i<=412;i+=2){
            buildNormalRoom(i,left);
        }
    }
    public void buildBARHDForthFloorWing2(){
        buildLayout();
        buildWalkWay();
        for(int i=414;i<=424;i+=2){
            buildNormalRoom(i,right);
        }
    }

    public void buildQuadNormalFirstSecondFloor(){
        buildLayout();
        buildNormalRoom(2+(floor*1000),left);
        buildSmallEmptyRoom(left);
        buildNormalRoom(3+(floor*1000),left);
        buildWalkWay();
        buildNormalRoom(1+(floor*1000),right);
        buildSmallEmptyRoom(right);
        buildNormalRoom(4+(floor*1000),right);
    }

    public void buildQuadHuntIISecondFloor(){
        buildLayout();
        buildEmptyRoom(left);
        buildSmallEmptyRoom(left);
        buildNormalRoom(2001,left);
        buildWalkWay();
        buildSmallRoom(2005,right,true);
        buildSmallRoom(2004,right,true);
        buildSmallEmptyRoom(right);
        buildSmallRoom(3003,right,false);
        buildSmallRoom(3002,right,false);
    }
    public void buildQuadChurchIIIFirstFloor(){
        buildLayout();
        buildNormalRoom(1003,left);
        buildSmallEmptyRoom(left);
        buildEmptyRoom(left);
        buildWalkWay();
        buildNormalRoom(1002,right);
        buildSmallEmptyRoom(right);
        buildNormalRoom(1001,right);
    }
    public void buildQuadChurchIIISecondFloor(){
        buildLayout();
        buildNormalRoom(2004,left);
        buildSmallEmptyRoom(left);
        buildNormalRoom(2001,left);
        buildWalkWay();
        buildNormalRoom(2003,right);
        buildSmallEmptyRoom(right);
        buildNormalRoom(2002,right);
    }
    public void buildQuadRoeblingFirstFloor(){
        buildLayout();
        buildEmptyRoom(left);
        buildSmallEmptyRoom(left);
        buildNormalRoom(1001,left);
        buildWalkWay();
        buildNormalRoom(1004,right);
        buildSmallRoom(1003,right,true);
        buildNormalRoom(1002,right);
    }
    public void buildQuadRoeblingSecondFloor(){
        buildLayout();
        buildNormalRoom(2005,left);
        buildSmallEmptyRoom(left);
        buildNormalRoom(2001,left);
        buildWalkWay();
        buildNormalRoom(2004,right);
        buildSmallRoom(2003,right,true);
        buildNormalRoom(2002,right);
    }

    public boolean isFreshmenHill()
    {
        if(this.dorm.equalsIgnoreCase("Bray")||this.dorm.equalsIgnoreCase("Hall")
                ||this.dorm.equalsIgnoreCase("Cary")||this.dorm.equalsIgnoreCase("Nason")
                ||this.dorm.equalsIgnoreCase("Crockett")){
            return true;
        }
        return false;
    }

    @Override
    public void Create(boolean ro,boolean flip) {
        this.rotate=ro;
        if(flip){
            left ="right";
            right ="left";
        }else{
            left="left";
            right="right";
        }

        ll.removeAllViews();
        if(isFreshmenHill()){
            if(floor==1){
                if(wing==1){
                    buildFreshmenHillFirstFloorWing1();
                }else if(wing==2){
                    buildFreshmenHillFirstFloorWing2();
                }
            }else{
                if(wing==1){
                    buildFreshmenHillSecondThirdFloorWing1();
                }else if(wing==2){
                    buildFreshmenHillSecondThirdFloorWing2();
                }
            }
        }else if(dorm.equalsIgnoreCase("Blitman")){
            if(floor==1){
                if(wing==1){
                    buildBlitmanFirstFloorWing1();
                }else {
                    buildBlitmanFirstFloorWing2();
                }
            }else {
                if(wing==1){
                    buildBlitmanOtherFloorWing1();
                }else{
                    buildBlitmanOtherFloorWing2();
                }
            }
        }else if(dorm.equalsIgnoreCase("BARHA")){
            if(floor==1){
                buildBARHAFirstFloor();
            }else if(floor==2){
                buildBARHASecondFloor();
            }else if(floor==3){
                buildBARHAThirdFloor();
            }else if(floor==4){
                if(wing==1){
                    buildBARHAForthFloorWing1();
                }else{
                    buildBARHAForthFloorWing2();
                }
            }
        }else if(dorm.equalsIgnoreCase("BARHB")){
            if(floor==1){
                buildBARHBFirstFloor();
            }else if(floor==2){
                buildBARHBSecondFloor();
            }else if(floor==3){
                buildBARHBThirdFloor();
            }
        }else if(dorm.equalsIgnoreCase("BARHC")){
            if(floor==1){
                buildBARHCFirstFloor();
            }else if(floor==2){
                buildBARHCSecondFloor();
            }else if(floor==3){
                buildBARHCThirdFloor();
            }
        }else if(dorm.equalsIgnoreCase("BARHD")){
            if(floor==1){
                buildBARHDFirstFloor();
            }else if(floor==2){
                buildBARHDSecondFloor();
            }else if(floor==3){
                buildBARHDThirdFloor();
            }else if(floor==4){
                if(wing==1) {
                    buildBARHDForthFloorWing1();
                }else{
                    buildBARHDForthFloorWing2();
                }
            }
        }else{
            //Quad case
            if(floor==1||floor==2){
                if(dorm.equalsIgnoreCase("Quad Roebling")||dorm.equalsIgnoreCase("Quad HuntII")||
                        dorm.equalsIgnoreCase(("Quad ChurchIII"))){
                    if(dorm.equalsIgnoreCase("Quad HuntII")){
                        buildQuadHuntIISecondFloor();
                    }else if(dorm.equalsIgnoreCase("Quad Roebling")){
                        if(floor==1){
                            buildQuadRoeblingFirstFloor();
                        }else{
                            buildQuadRoeblingSecondFloor();
                        }
                    }else if(dorm.equalsIgnoreCase("Quad ChurchIII")){
                        if(floor==1){
                            buildQuadChurchIIIFirstFloor();
                        }else{
                            buildQuadChurchIIISecondFloor();
                        }
                    }
                }else{
                    buildQuadNormalFirstSecondFloor();
                }
            }else if(floor==3){
                //not yet implemented
            }
        }
    }
}
