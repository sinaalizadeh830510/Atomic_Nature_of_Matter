import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Avogadro {
    public static void main(String[] args) {
        final double P_RADIUS = .5 * 10e-6;
        final double T_KELVIN = 297;
        final double VISCOSITY_WATER = 9.135e-4;
        final double GAS_CONSTANT = 8.31446;
        final double PIXELS_METERS = 0.175e-6;

        ArrayList<Double> list_radius = getDisplacements();
        double sum = 0;
        for (Double listRadius : list_radius) {
            sum += Math.pow(listRadius * PIXELS_METERS, 2);
        }

        double D = sum/(2* list_radius.size());
        double boltzmann = (6*Math.PI*D*VISCOSITY_WATER*P_RADIUS)/T_KELVIN;
        double avogadro = GAS_CONSTANT/boltzmann;
        System.out.printf("Boltzmann = %.4e\n", boltzmann);
        System.out.printf("Avogadro = %.4e\n", avogadro);
    }
    public static ArrayList<Double> getDisplacements() {
        ArrayList<Double> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("displacements-run_1.txt"));
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.length()>0)
                    list.add(Double.parseDouble(s));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
