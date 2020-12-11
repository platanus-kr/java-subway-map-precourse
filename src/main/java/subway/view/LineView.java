package subway.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import subway.Subway;
import subway.domain.Line;
import subway.domain.Station;
import subway.util.Constants;
import subway.util.MessageUtils;

public class LineView {

    public static String menuSelector(Scanner userInput) {
        String input = userInput.next();
        String thisMenuState = Constants.LINE_MENU_STATE;
        if (input.equals("1")) {
            insertLine(userInput);
        }
        if (input.equals("2")) {
            deleteLine(userInput);
        }
        if (input.equals("3")) {
            showLines();
        }
        if (input.toLowerCase().equals("b")) {
            thisMenuState = Constants.MAIN_MENU_STATE;
        }
        if (!(input.equals("1") || input.equals("2") || input.equals("3")
            || input.toLowerCase().equals("b"))) {
            MessageUtils.printError(Constants.INVALID_STRING_OUTPUT_COMMENT);
        }
        return thisMenuState;
    }

    private static boolean insertLine(Scanner userInput) {
        MessageUtils.printAnnouncement(Constants.ADD_LINE_NAME_INPUT_COMMENT);
        String lineName = userInput.next();
        if (isExistLineName(lineName)) {
            MessageUtils.printError(Constants.EXIST_LINE_OUTPUT_COMMENT);
            return false;
        }
        Subway.lines.addLine(new Line(lineName));
        Line newLine = Subway.lines.findByName(lineName);
        List<Station> p2pStations = p2pStation(userInput);
        Subway.Map.addLine(newLine, p2pStations.get(0), p2pStations.get(1));
        MessageUtils.printInfo(Constants.ADD_LINE_OUTPUT_COMMENT);
        return true;
    }

    private static List<Station> p2pStation(Scanner userInput) {
        MessageUtils.printAnnouncement(Constants.ADD_LINE_START_STATION_NAME_INPUT_COMMENT);
        String startStationInLineName = userInput.next();
        MessageUtils.printAnnouncement(Constants.ADD_LINE_END_STATION_NAME_INPUT_COMMENT);
        String endStationInLineName = userInput.next();
        List<Station> stationList = new ArrayList<>();
        stationList.add(Subway.stations.findByName(startStationInLineName));
        stationList.add(Subway.stations.findByName(endStationInLineName));
        return stationList;
    }

    private static boolean isExistLineName(String lineName) {
        if (Subway.lines.findByName(lineName) == null) {
            return false;
        }
        return true;
    }

    private static boolean deleteLine(Scanner userInput) {
        MessageUtils.printAnnouncement(Constants.DELETE_LINE_END_STATION_NAME_INPUT_COMMENT);
        String targetLineName = userInput.next();
        if (!isExistLineName(targetLineName)) {
            MessageUtils.printError(Constants.NO_EXIST_LINE_OUTPUT_COMMENT);
            return false;
        }
        Subway.Map.deleteLine(Subway.lines.findByName(targetLineName));
        Subway.lines.deleteLineByName("2호선");
        MessageUtils.printInfo(Constants.DELETE_LINE_OUTPUT_COMMENT);
        return true;
    }

    private static void showLines() {
        MessageUtils.printAnnouncement(Constants.TITLE_WHOLE_LINE_TEXT);
        for (Object line : Subway.lines.findAll()) {
            MessageUtils.printInfo((String) line);
        }
    }


}
