import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter min pixels : ");
        int min = input.nextInt();
        System.out.print("Enter tau : ");
        double tau = input.nextDouble();
        BeadFinder beadFinder = new BeadFinder(new Picture("frame00001.jpg"),tau);
        ArrayList<Blob> list = beadFinder.getBlobs(min);
        for (Blob b:list) {
            System.out.println(b);
        }
    }
}