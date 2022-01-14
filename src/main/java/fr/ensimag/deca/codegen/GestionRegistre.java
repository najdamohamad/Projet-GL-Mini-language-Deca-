package fr.ensimag.deca.codegen;

import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import java.util.concurrent.*;
public class GestionRegistre{

    public boolean tableRegistre[];

    public int GB;
    public int LB;

    public int getGB(){return GB;}
    public int getLB() {return LB;}

    public void incrGB(){GB++; }
    public void incrLB(){LB++; }

    public boolean[] getTableRegistre() {
        return this.tableRegistre;
    }

    public void occupeRegistre(int i){
        this.tableRegistre[i] = true;
    }

    public void libereRegistre(int i){
        this.tableRegistre[i] = false;
    }

    public boolean registreEstOccupee (int i){
        return this.tableRegistre[i];
    }

    public int getPremierRegistreLibre(){
        int i = 2;
        while(i<16 && registreEstOccupee(i)){
            i++;
        }
        return i;
    }

    public int getRandomRegistre(){
        return ThreadLocalRandom.current().nextInt(2, 16);
    }

    public int getRandomRegistre(int i){
        int res = i;
        while (res == i){
            res = ThreadLocalRandom.current().nextInt(2, 16);
        }
        return res;
    }
    public boolean tousLesRegistresSontOccuppee(){
        return (this.getPremierRegistreLibre()==16);
    }

    public GestionRegistre(){
        this.GB = 0;
        this.LB = 0;
        this.tableRegistre = new boolean[16];
        for (int i = 0; i<16; i++){
            //case non occupées
            tableRegistre[i] = false;
        }
    }


}
