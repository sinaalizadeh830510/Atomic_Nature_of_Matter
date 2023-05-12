import java.util.ArrayList;

public class Blob {
    ArrayList<Pixel> pixels ;

    public Blob() {
        pixels = new ArrayList<>();
    }
    public void add(int x, int y){
        pixels.add(new Pixel(x,y));
    }
    public int mass(){
        return pixels.size();
    }
    public double distanceTo(Blob blob){
        double powX = Math.pow(this.centerX()- blob.centerX(),2);
        double powY = Math.pow(this.centerY()- blob.centerY(),2);
        return Math.sqrt(powX+powY);
    }
    public float centerX(){
        float sumX=0;
        for (Pixel p:pixels) {
            sumX+=p.getX();
        }
        return sumX/mass();
    }
    public float centerY(){
        float sumY=0;
        for (Pixel p:pixels) {
            sumY+=p.getX();
        }
        return sumY/mass();
    }
    @Override
    public String toString() {
        return String.format("%d (%.4f,%.4f)",mass(),centerX(),centerY());
    }
}
