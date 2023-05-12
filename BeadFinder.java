import java.awt.*;
import java.util.ArrayList;

public class BeadFinder {
    private Picture picture;
    private double tau;
    private boolean[][] visited;
    private ArrayList<Blob> beads;

    public BeadFinder(Picture picture, double tau) {
        this.picture = convertGrayscale(picture);
        this.tau = tau;
        visited = new boolean[picture.width()][picture.height()];
        beads = new ArrayList<>();
        find();
    }

    public ArrayList<Blob> getBlobs(int min) {
        ArrayList<Blob> list = new ArrayList<>();
        for (Blob b : beads) {
            if (b.pixels.size() > min)
                list.add(b);
        }
        return list;
    }

    public void find() {

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                if (picture.get(i,j).getRed() >= tau) {
                    beads.add(new Blob());
                    findRecursive(beads.get(beads.size() - 1), i, j);
                }
            }
        }
    }

    public void findRecursive(Blob blob, int i, int j) {
        if (i < 0 || j < 0 || i >= picture.width() || j >= picture.height() || visited[i][j])
            return;
        if (picture.get(i,j).getRed() < tau) {
            visited[i][j] = true;
            return;
        }
        visited[i][j] = true;
        blob.add(i, j);
        findRecursive(blob, i + 1, j);
        findRecursive(blob, i - 1, j);
        findRecursive(blob, i, j + 1);
        findRecursive(blob, i, j - 1);
    }

    public Picture convertGrayscale(Picture picture) {
        Picture grayscale = new Picture(picture.width(), picture.height());
        for (int col = 0; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                Color color = picture.get(col, row);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int y = (int) (Math.round(0.299*r + 0.587*g + 0.114*b));
                Color gray = new Color(y, y, y);
                grayscale.set(col, row, gray);
            }
        }
        return grayscale;
    }
}
