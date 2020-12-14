package subway.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import subway.util.Constants;

public class SectionRepository {

    private final Map<Line, List<Station>> sectionMap = new HashMap<>();

    public SectionRepository() {
    }

    public void addStationList(Line sectionTitle, List<Station> stations) {
        this.sectionMap.put(sectionTitle, stations);
    }

    public void addNewLine(Line sectionTitle, Station startStationInLine,
        Station endStationInLine) {
        List<Station> startToEndStation = new ArrayList<>();
        startToEndStation.add(startStationInLine);
        startToEndStation.add(endStationInLine);
        this.addStationList(sectionTitle, startToEndStation);
    }

    public void addSection(Line sectionTitle, Station station, int index) {
        List<Station> stations = this.sectionMap.get(sectionTitle);
        stations.add(index - Constants.INDEX_ARRANGE_INT, station);
        this.addStationList(sectionTitle, stations);
    }

    public int getSize(Line sectionTitle) {
        List<Station> stations = this.sectionMap.get(sectionTitle);
        return stations.size();
    }

    public List<Station> getStationListInLine(Line sectionTitle) {
        return this.sectionMap.get(sectionTitle);
    }

    public void deleteLine(Line sectionTitle) {
        this.sectionMap.remove(sectionTitle);
    }

    public void deleteSection(Line sectionTitle, Station station) {
        List<Station> stations = this.sectionMap.get(sectionTitle);
        stations.removeIf(element -> element == station);
        this.sectionMap.put(sectionTitle, stations);
    }

    public Set<String> getSetStations() {
        Set<String> foundSet = new HashSet<>();
        for (Line sectionTitle : this.sectionMap.keySet()) {
            for (Station station : this.sectionMap.get(sectionTitle)) {
                foundSet.add(station.getName());
            }
        }
        return foundSet;
    }

    public Map<String, List<String>> findAll() {
        Map<String, List<String>> wholeSubwayMap = new HashMap<>();
        for (Line sectionTitle : sectionMap.keySet()) {
            List<String> stations = new ArrayList<>();
            for (Station station : sectionMap.get(sectionTitle)) {
                stations.add(station.getName());
            }
            wholeSubwayMap.put(sectionTitle.getName(), stations);
        }
        return wholeSubwayMap;
    }
}
