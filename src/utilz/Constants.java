package utilz;

public class Constants {
    public static class playerConstants{
        public static final int IDLEFRONT=0;
        public static final int IDLEBACK=3;
        public static final int LEFTDASH=1;
        public static final int RIGHTDASH=2;

        public static int GetSpriteAmount(int player_action){
            switch (player_action){
                case IDLEFRONT:
                    return 12;
                case IDLEBACK:
                    return 6;
                case LEFTDASH:
                    return 6;
                case RIGHTDASH:
                    return 6;
                default:
                    return 0;
            }
        }

    }
    public static class tileConstants{
        public static final int GRASS_TILE=0;
        public static final int FLOOR_TILE=1;
        public static final int WALL_TILE=2;
    }
}
