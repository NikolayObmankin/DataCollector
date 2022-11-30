package metroData;

import com.google.gson.annotations.SerializedName;

public class Station implements Comparable<Station> {
    private final Line line;
    @SerializedName("name")
    private final String name;
    @SerializedName("depth")
    private Double depth;
    @SerializedName("date")
    private String openingDate;
    @SerializedName("hasConnected")
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
        String print =
                "name: '" + name + '\'' +
                "line: '" + line + '\'' +
                "date: '" + openingDate + '\'' +
                "depth: '" + depth + '\'' +
                "hasConnection: " + isConnected;
        if (openingDate == null) {
            print =
                "name: '" + name + '\'' +
                "line: '" + line + '\'' +
                "depth: '" + depth + '\'' +
                "hasConnection: " + isConnected;
        }
        if (depth == null) {
            print =
                "name: '" + name + '\'' +
                "line: '" + line + '\'' +
                "date: '" + openingDate + '\'' +
                "hasConnection: " + isConnected;
        }
        if (openingDate == null && depth == null) {
            print =
                "name: '" + name + '\'' +
                "line: '" + line + '\'' +
                "hasConnection: " + isConnected;
        }
        return print;
    }

}