public class Main
{
    static Ramen_Nigga_Theme rnt = new Ramen_Nigga_Theme();
    static World01_Theme world01Theme = new World01_Theme();
    static World02_Theme world02Theme = new World02_Theme();
    static World03_Theme world03Theme = new World03_Theme();
    static  Health_Up_Sound hus = new Health_Up_Sound();
    static  Reverse_Health_Up_Sound suh = new Reverse_Health_Up_Sound();
    static Ramen_Attack_Sound ras = new Ramen_Attack_Sound();
    static Level_Finish_Sound lfs = new Level_Finish_Sound();
    static Ramen_Collection_Sound rsc = new Ramen_Collection_Sound();
    public static void main(String[] args)
    {
        Start_Panel startPanel = new Start_Panel();
        Start_Screen startScreen = new Start_Screen();
        startScreen.Screen(startPanel);
    }
}