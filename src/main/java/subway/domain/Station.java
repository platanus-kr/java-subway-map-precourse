package subway.domain;

public class Station {

    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Station) {
            Station station = (Station) obj;
            if (this.name.equals(station.name)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
