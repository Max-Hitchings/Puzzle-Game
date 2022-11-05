package Levels;

import utils.Constants;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class JsonLevel {

        //    Currently unused
        public int[] dimensions;
        public int[] playerSpawn;
        public String layout;


        public JsonLevel(int[] dimensions, String layout, int[] playerSpawn) {
            this.layout = layout;
            this.dimensions = dimensions;
            this.playerSpawn = playerSpawn;
        }

        public Level createLevel() throws IOException {

            BufferedReader bufReader = new BufferedReader(new StringReader(layout));
            Constants.Grid.TYPES[][] encodedLayoutData = new Constants.Grid.TYPES[9][16];


            String line="";
            int i = 0;
            while( (line=bufReader.readLine()) != null )
            {
                char[] charLine = line.toCharArray();
                int j = 0;
//                System.out.println(j);
                for (char c : charLine) {
                        encodedLayoutData[i][j] = Constants.MapBlocks.GetBlockType(c);
                        j++;

                }
                i++;
            }
//            return new Level(new Constants.Grid.TYPES[1][1], new Point(), new Point());

            return new Level(encodedLayoutData, new Point(playerSpawn[0], playerSpawn[1]), new Point(dimensions[0], dimensions[1]));
        }


}
