package metroData;

public class Station implements Comparable<Station>
{
    private Line line;
    private String name;
    private String depth;
    private String openingDate;
    private boolean isConnected;

    public Station(String name, Line line)
    {
        this.name = name;
        this.line = line;
    }


    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public Line getLine()
    {
        return line;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public int compareTo(Station station)
    {
        int lineComparison = line.compareTo(station.getLine());
        if(lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString() {
        return "Станция {" +
                "" + line +
                ", название станции: '" + name + '\'' +
                ", глубина: '" + depth + '\'' +
                ", дата открытия: '" + openingDate + '\'' +
                ", наличие пересадки: " + isConnected +
                '}';
    }

}