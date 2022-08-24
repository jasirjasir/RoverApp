package com.schneewittchen.rosandroid.utility;

import java.util.ArrayList;

/**
 * Constants of the project that are globally reachable
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 31.01.20
 * @updated on 31.01.20
 * @modified by
 */
public class Constants {

    /**
     * Name of the room database on disk
     */
    public static final String DB_NAME = "config_database";

    public static final String VIEW_FORMAT = ".widgets.%s.%sView";

    public static final String VIEWHOLDER_FORMAT = ".widgets.%s.%sDetailVH";

    public static final String ENTITY_FORMAT = ".widgets.%s.%sEntity";

    public static final String DETAIL_LAYOUT_FORMAT = "widget_detail_%s";

    public static final String WIDGET_NAMING = "%s #%d";

    public static final String LOGTAG = "ros-android";

    public static String MASTER_IP = "";

    public static String API_SERVER_IP = "10.42.0.188";

    public static boolean IS_MASTER_CONNECTED = false;

    public static final String API_PORT = "5001";


    public static final ArrayList<String> LOCATION_LIST = new ArrayList<String>() {{
        add("Current location");
        add("IOT lab");
        add("Robotics lab");
        add("Pantry");
        add("Bay");
        add("Entrance");
    }};
}
