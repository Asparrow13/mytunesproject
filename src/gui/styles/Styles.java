package gui.styles;

public class Styles {
    public static final String hover = "-fx-background-insets: 0,1,4,5,6;"
            + "-fx-font-alignment: center;"
            + "-fx-text-fill: #333333;"
            + "-fx-font: 20px Georgia;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
            ;
    public static final String idle= "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
            + "linear-gradient(#020b02, #3a3a3a), "
            + "linear-gradient(#b3b3b3 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
            + "linear-gradient(#304461 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
            + "-fx-background-insets: 0,1,4,5,6;"
            + "-fx-text-fill: #333333;"
            + "-fx-font: 20px Georgia;"
            + "-fx-font-alignment: center;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
            ;

    public static final String playIdle = "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
            + "linear-gradient(#020b02, #3a3a3a), "
            + "linear-gradient(#1c2331 0%, #c2c2c2 20%, #afafaf 80%, #1c2331 100%),"
            + "linear-gradient(#1c2331 0%, #dbdbdb 50%, #cacaca 51%, #1c2331 100%);"
            + "-fx-background-insets: 0,1,4,5,6;"
            + "-fx-font-alignment: center;"
            + "-fx-background-radius: 50em;"
            + "-fx-text-fill: #333333;"
            + "-fx-font: 25px Georgia;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
            ;
    public static final String playHover= "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
            + "linear-gradient(#020b02, #3a3a3a), "
            + "linear-gradient(#b3b3b3 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
            + "linear-gradient(#304461 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
            + "-fx-background-insets: 0,1,4,5,6;"
            + "-fx-text-fill: #333333;"
            + "-fx-background-radius: 50em;"
            + "-fx-font: 25px Georgia;"
            + "-fx-font-alignment: center;"
            + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
            ;

    public static final String label= "-fx-text-fill: white;"
            + "-fx-font: 25px Georgia;"
            ;

    public static final String viewStyle= "-fx-control-inner-background: #8897b3;"
            + "-fx-background-color: linear-gradient(to right, #212838, #c2e3fc);"
            + "-fx-text-fill: black; -fx-font-size: 20px;"
            ;

    public static final String viewStyleHeadingLabels = ("-fx-font-family: Harrington;" +
                "-fx-padding: 10px;" +
                "-fx-background-color: #1c2331;" +
                "-fx-background-radius: 25;" +
                "-fx-border-radius: 25; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 25px;" +
                "-fx-alignment: center;");

    // add a hover effect to the label to make it shine
    public static final String headingLabelsMouseOn = ("-fx-font-family: Harrington;" +
            "-fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);" +
            "-fx-padding: 10px;" +
            "-fx-background-color: black;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 25; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 2px;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 25px;" +
            "-fx-alignment: center;");

    public static final String headingLabelsMouseOff = ("-fx-font-family: Harrington;" +
            "-fx-padding: 10px;" +
            "-fx-background-color: #1c2331;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 25; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 2px;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 25px;" +
            "-fx-alignment: center;");
}
