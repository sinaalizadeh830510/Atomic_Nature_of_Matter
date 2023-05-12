// Name        : Tyler Fricks
// Email       : tyler.fricks@gmail.com
// Description :

import java.util.ArrayList;
import java.util.Scanner;

public class BeadTracker {
    private Picture picture1;
    private Picture picture2;
    private int tau;
    private int min;
    private double delta;
    public BeadTracker(Picture picture1, Picture picture2 , int tau , int min , double delta) {
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.tau = tau;
        this.min = min;
        this.delta = delta;
    }
    public void tracker(){
        BeadFinder beadFinder1 = new BeadFinder(picture1, tau);
        BeadFinder beadFinder2 = new BeadFinder(picture2, tau);

        ArrayList<Blob> blobs1 = beadFinder1.getBlobs(min);

        ArrayList<Blob> blobs2 = beadFinder2.getBlobs(min);

        for(int i = 0; i < blobs2.size(); i++){
            double least = Double.MAX_VALUE;
            for(int j = 0; j < blobs1.size(); j++){
                double distance = blobs2.get(i).distanceTo(blobs1.get(j));
                if (distance <= delta && distance < least){
                    least = distance;
                }
            }
            if(least < Double.MAX_VALUE)
                System.out.printf("%.4f\n", least);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double delta = 25;
        System.out.print("Enter min pixel : ");
        int min = input.nextInt();
        System.out.print("Enter tau : ");
        int tau = input.nextInt();
        input.nextLine();
        System.out.print("Enter name picture 1 : ");
        String namePicture1 = input.nextLine();
        System.out.print("Enter name picture 2 : ");
        String namePicture2 = input.nextLine();
        BeadTracker beadFinder = new BeadTracker(new Picture(namePicture1)
                ,new Picture(namePicture2) , tau , min , delta);
        beadFinder.tracker();
    }
}