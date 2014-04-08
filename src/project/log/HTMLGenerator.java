package project.log;

/**
 * Created by d.zhukov on 03.04.14.
 */
public class HTMLGenerator {

    public static String GetHead()
    {

        return "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"table1\" style=\"position:absolute; overflow:hidden; left:40px; top:30px; width:500px; height:1000px; z-index:0\">\n" +
                "<div class=\"wpmd\">\n" +
                "<div><TABLE bgcolor=\"#FFFFFF\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#808080\">\n";

    }

    public static String GetTail()
    {
        return "</TABLE>\n" +
                "</div>\n" +
                "</div></div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }

    public static String GetTableRow(String text1, String text2, String text3)
    {
        text1 = text1.replaceAll("\n", "<br>");
        text2 = text2.replaceAll("\n", "<br>");
        text3 = text3.replaceAll("\n", "<br>");


        return "<TR valign=top>\n" +
                "<TD width=200><div class=\"wpmd\">\n" +
                "<div>" + text1 + "</div>\n" +
                "</div>\n" +
                "</TD>\n" +
                "<TD width=300><div class=\"wpmd\">\n" +
                "<div>" + text2 + "</div>\n" +
                "</div>\n" +
                "</TD>\n" +
                "<TD width=300><div class=\"wpmd\">\n" +
                "<div>" + text3 + "</div>\n" +
                "</div>\n" +
                "</TD>\n" +
                "</TR>\n";
    }

}
