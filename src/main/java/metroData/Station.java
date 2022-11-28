package metroData;

public class Station implements Comparable<Station> {
    private final Line line;
    private final String name;
    private Double depth;
    private String openingDate;
    private boolean isConnected;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public Double getDepth() {
        return depth;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    @Override
    public int compareTo(Station station) {
        int lineComparison = line.compareTo(station.getLine());
        if (lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString() {
        String print = line +
                ", станция: '" + name + '\'' +
                ", глубина: '" + depth + '\'' +
                ", дата открытия: '" + openingDate + '\'' +
                ", наличие пересадки: " + isConnected;
        if (openingDate == null) {
            print = line +
                    ", станция: '" + name + '\'' +
                    ", глубина: '" + depth + '\'' +
                    ", наличие пересадки: " + isConnected;
        }
        return print;
    }

}