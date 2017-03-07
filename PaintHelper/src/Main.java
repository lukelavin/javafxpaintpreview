import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by lukel on 3/6/2017.
 */
public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Scene scene = new Scene(new Group(), 1593, 893);

        Pane pane = new Pane();

        Scanner input = new Scanner(new File("paints.txt"));
        int count = 0;
        while(input.hasNextLine())
        {
            String line = input.nextLine();

            String colorName = line.substring(0, line.indexOf("The color"));
            System.out.println(colorName);

            String hex = line.substring(line.indexOf("#") + 1, line.indexOf("#") + 1 + 6);
            System.out.println(Arrays.toString(hexToRGB(hex)));

            int[] rgb = hexToRGB(hex);
            Rectangle rectangle = new Rectangle(229, 43, Color.rgb(rgb[0], rgb[1], rgb[2]));
            rectangle.setTranslateX(count % 7 * 229);
            rectangle.setTranslateY(count / 7 * 43);
            pane.getChildren().add(rectangle);

            Label name = new Label(colorName);
            if(average(rgb) < 128)
                name.setTextFill(Color.WHITE);
            name.setTranslateX(count % 7 * 229);
            name.setTranslateY(count / 7 * 43);
            pane.getChildren().add(name);

            count++;
        }
        System.out.println(count);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(pane);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Paint Tester");
        stage.setResizable(false);
    }

    private int[] hexToRGB(String hex)
    {
        int[] ints = new int[3];
        String[] conversions = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

        ints[0] = indexOf(conversions, (hex.substring(0, 1))) * 16 + indexOf(conversions, hex.substring(1, 2));
        ints[1] = indexOf(conversions, hex.substring(2, 3)) * 16 + indexOf(conversions, hex.substring(3, 4));
        ints[2] = indexOf(conversions, hex.substring(4, 5)) * 16 + indexOf(conversions, hex.substring(5, 6));
        return ints;
    }

    private int indexOf(String[] arr, String str)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(str.equals(arr[i]))
                return i;
        }

        return -1;
    }

    private int average(int[] arr)
    {
        int sum = 0;
        for(int num : arr)
            sum += num;

        return sum / arr.length;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
