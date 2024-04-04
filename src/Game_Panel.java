import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

public class Game_Panel extends JPanel
{
    static final int WIDTH = 900, HEIGHT = 600, UNIT_SIZE = 25;
    static int offset, cameraX, level = 1;
    static Ninja ninja;
    static Life playerLife = new Life();
    static Ramen_Score ramen_score = new Ramen_Score();
    static Pause_Menu pause_menu = new Pause_Menu();
    static Break_Screen break_screen = new Break_Screen();
    Timer timer = new Timer();
    static Time_Limit time_limit = new Time_Limit();
    ArrayList<Background_Clouds> background_clouds01s = new ArrayList<>();
    ArrayList<Background_Clouds02> background_clouds02s = new ArrayList<>();
    ArrayList<Background_Clouds03> background_clouds03s = new ArrayList<>();
    static ArrayList<Land> landTiles = new ArrayList<>();
    ArrayList<Ground> groundTiles = new ArrayList<>();
    ArrayList<Ocean> oceans = new ArrayList<>();
    ArrayList<Ramen_Icon> ramen_icons = new ArrayList<>();
    ArrayList<Life_Icon> lifeIcons = new ArrayList<>();
    ArrayList<DirtyBubble> dirtyBubbles = new ArrayList<>();
    ArrayList<Tomoe> tomoes = new ArrayList<>();
    ArrayList<Game_Over> game_overs = new ArrayList<>();
    ArrayList<Times_Up> times_ups = new ArrayList<>();
    ArrayList<Ramen> ramen = new ArrayList<>();
    ArrayList<RedCloud> redClouds = new ArrayList<>();
    ArrayList<GoldenCloud> goldenClouds = new ArrayList<>();
    ArrayList<BlueCloud> blueClouds = new ArrayList<>();
    static ArrayList<Normal_Block> normal_blocks = new ArrayList<>();
    ArrayList<Life_Block> life_blocks = new ArrayList<>();
    ArrayList<Drop_Block> drop_blocks = new ArrayList<>();
    ArrayList<Bounce_Block> bounce_blocks = new ArrayList<>();
    ArrayList<Crate> crates = new ArrayList<>();
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<Random_Box> random_boxes = new ArrayList<>();
    ArrayList<Shrub> shrubs = new ArrayList<>();
    ArrayList<Television> televisions = new ArrayList<>();
    ArrayList<Light_Post> light_posts = new ArrayList<>();
    ArrayList<Palm_Tree> palm_trees = new ArrayList<>();
    ArrayList<Tori> toris = new ArrayList<>();
    ArrayList<Sign> signs = new ArrayList<>();
    ArrayList<Onikasa> onikasa = new ArrayList<>();

    Game_Panel()
    {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setLayout(null);
        this.setBackground(new Color(60,50,80));

            ninja = new Ninja(100,200,this);

            reset();

            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    time_limit.Tick();
                    repaint();
                }
            },1000L);

            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    deathByTime();
                    if(ninja.key1stAttack){ninja.actionSet1st();}
                    if(ninja.key2ndAttack){ninja.actionSet2nd();}
                    if(ninja.keyBackwards && ninja.key2ndAttack){ninja.actionSet3rd();}
                    if(Crate.keyCrate){ninja.actionPush();}
                    ninja.Set(); ninja.goldenCloudAction(); ninja.blueCloudAction();
                    ninja.crateAction(); ninja.dropAction(); ninja.offscreenEnemies();
                    for(Background_Clouds background_clouds: background_clouds01s){background_clouds.Set(cameraX);}
                    for(Background_Clouds02 background_clouds02: background_clouds02s){background_clouds02.Set(cameraX);}
                    for(Background_Clouds03 background_clouds03: background_clouds03s){background_clouds03.Set(cameraX);}
                    for(Shrub shrub: shrubs){shrub.Set(cameraX);}
                    for(Television television: televisions){television.Set(cameraX);}
                    for(Light_Post light_post: light_posts){light_post.Set(cameraX);}
                    for(Palm_Tree palm_tree: palm_trees){palm_tree.Set(cameraX);}
                    for(Land land: landTiles){land.Set(cameraX);}
                    for(Ground ground: groundTiles){ground.Set(cameraX);}
                    for(Sign sign: signs){sign.Set(cameraX);}
                    for(Tori tori: toris){tori.Set(cameraX);}
                    for(RedCloud redCloud: redClouds){redCloud.Set(cameraX);}
                    for(GoldenCloud goldenCloud: goldenClouds){goldenCloud.Set(cameraX);}
                    for(BlueCloud blueCloud: blueClouds){blueCloud.Set(cameraX);}
                    for(Normal_Block normal_block: normal_blocks){normal_block.Set(cameraX);}
                    for(Drop_Block drop_block: drop_blocks){drop_block.Set(cameraX);}
                    for(Bounce_Block bounce_block: bounce_blocks){bounce_block.Set(cameraX);}
                    for(Life_Block life_block: life_blocks){life_block.Set(cameraX);}
                    for(Crate crate: crates){crate.Set(cameraX); crate.collision();}
                    for(Box box: boxes){box.Set(cameraX);}
                    for(Random_Box random_box: random_boxes){random_box.Set(cameraX);}
                    for(Ramen ramen1: ramen){ramen1.Set(cameraX);}
                    for(DirtyBubble dirtyBubble: dirtyBubbles){dirtyBubble.Set(cameraX);}
                    for(Tomoe tomoe: tomoes){tomoe.Set(cameraX); tomoe.collision();}
                    for(Onikasa onikasa1: onikasa){onikasa1.Set(cameraX);}
                    repaint();
                }
            };
            timer.schedule(task,0L,17);

            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    ninja.dirtyBubbleAnimation();
                    ninja.dirtyBubbleMovement();
                    ninja.tomoeAnimation();
                    ninja.tomoeMovement();
                    ninja.onikasaAnimation();
                    repaint();
                }
            }, 100L);
    }
    protected void soundtracks()
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                Main.world01Theme.Audio();
            }
            //Level 2
            case 2 ->
            {
                Main.world02Theme.Audio();
            }
            //Level 3
            case 3 ->
            {
                Main.world03Theme.Audio();
            }
            //Level 4
            case 4 ->
            {

            }
            //Level 5
            case 5 -> {}
            //Level 6
            case 6 -> {}
            //Level 7
            case 7 -> {}
        }
    }
    protected void makeBackground(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for(int x = 525; x < 13000; x += 1050)
                {
                    background_clouds01s.add(new Background_Clouds(offset + x,0,524,262));
                }
                for(int x = 0; x < 13000; x += 1050)
                {
                    background_clouds01s.add(new Background_Clouds(offset + x,50,524,262));
                }
            }
            //Level 2
            case 2 ->
            {
                this.setBackground(new Color(40,80,80));

                for(int x = 0; x < 32000; x += 600)
                {
                    for(int y = 0; y < HEIGHT; y += 200)
                    {
                        background_clouds02s.add(new Background_Clouds02(offset + x,y,300,200));
                    }
                }
                for(int x = 300; x < 32000; x += 600)
                {
                    for(int y = 50; y < HEIGHT; y += 200)
                    {
                        background_clouds02s.add(new Background_Clouds02(offset + x, y, 300, 200));
                    }
                }
            }
            //Level 3
            case 3 ->
            {
                this.setBackground(new Color(60,90,120));

                for(int x = 0; x < 3100; x += 800)
                {
                    background_clouds03s.add(new Background_Clouds03(offset + x, 0,400,363));
                }
                for(int x = 400; x < 3100; x += 800)
                {
                    background_clouds03s.add(new Background_Clouds03(offset + x, 50,400,363));
                }

                for(int x = 6000; x < 12100; x += 1050)
                {
                    background_clouds01s.add(new Background_Clouds(offset + x,0,524,262));
                }
                for(int x = 6525; x < 12100; x += 1050)
                {
                    background_clouds01s.add(new Background_Clouds(offset + x,50,524,262));
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeOcean()
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for (int i = 0; i < WIDTH/UNIT_SIZE; i++)
                {
                    for (int a = 525; a < Game_Panel.HEIGHT; a += UNIT_SIZE)
                    {
                        oceans.add(new Ocean(i * UNIT_SIZE, a, UNIT_SIZE, UNIT_SIZE));
                    }
                }
            }
            //Level 2
            case 2 ->
            {
                for(int i = 0; i < WIDTH/UNIT_SIZE; i++)
                {
                    for(int a = 525; a < HEIGHT; a += UNIT_SIZE)
                    {
                        oceans.add(new Ocean(i * UNIT_SIZE,a,UNIT_SIZE,UNIT_SIZE));
                    }
                }
            }
            //Level 3
            case 3 ->
            {
                for(int i = 0; i < WIDTH/UNIT_SIZE; i++)
                {
                    for(int a = 525; a < HEIGHT; a += UNIT_SIZE)
                    {
                        oceans.add(new Ocean(i * UNIT_SIZE,a,UNIT_SIZE,UNIT_SIZE));
                    }
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->
            {
                for(int i = 0; i < WIDTH/UNIT_SIZE; i++)
                {
                    for(int a = 525; a < HEIGHT; a += UNIT_SIZE)
                    {
                        oceans.add(new Ocean(i * UNIT_SIZE,a,UNIT_SIZE,UNIT_SIZE));
                    }
                }
            }
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeLandTiles(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for (int i = 0; i < 400 / Game_Panel.UNIT_SIZE; i++)
                {
                    landTiles.add(new Land(offset + (i * Game_Panel.UNIT_SIZE), 500, Game_Panel.UNIT_SIZE, Game_Panel.UNIT_SIZE));
                }
                for (int i = 750; i < 1700; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i, 500, Game_Panel.UNIT_SIZE, Game_Panel.UNIT_SIZE));
                }
                for (int i = 3000; i < Game_Panel.WIDTH * 4; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i, 500, Game_Panel.UNIT_SIZE, Game_Panel.UNIT_SIZE));
                }
                for (int i = 6175; i < 7500; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i, 475, Game_Panel.UNIT_SIZE, Game_Panel.UNIT_SIZE));
                }
                for (int i = 12600; i < 13175; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i, 500, Game_Panel.UNIT_SIZE, Game_Panel.UNIT_SIZE));
                }
            }
            //Level 2
            case 2 ->
            {
                for(int i = 0; i < 1200; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 1500; i < 2500; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 2700; i < 2800; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 3000; i < 3100; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 3300; i < 3400; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 3600; i < 3700; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 3900; i < 4000; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 4200; i < 4625; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 6700; i < 7900; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 8600; i < 9600; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,325,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 10300; i < 11300; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 11500; i < 12000; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 12200; i < 12625; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,450,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 12700; i < 13125; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,400,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 13200; i < 13625; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,350,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 13200; i < 13625; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,350,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 13700; i < 14025; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,200,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 15150; i < 16050; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,475,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 16950; i < 17975; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,400,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 18100; i < 19000; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 18100; i < 19400; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 20200; i < 21000; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,500,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
            }
            //Level 3
            case 3 ->
            {
                for(int i = 0; i < 900; i += Game_Panel.UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i,375,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int i = 2300; i < 4300 ; i += UNIT_SIZE)
                {
                    landTiles.add(new Land(offset + i, 475, UNIT_SIZE, UNIT_SIZE));
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeShrubs(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for(int v = 825; v < 1700; v += 350)
                {
                    shrubs.add(new Shrub(offset + v,460,75,43));
                }
                for(int v = 3125; v < 3600; v += 300)
                {
                    shrubs.add(new Shrub(offset + v,460,75,43));
                }
                for(int v = 6250; v < 7400; v += 300)
                {
                    shrubs.add(new Shrub(offset + v,435,75,43));
                }
            }
            //Level 2
            case 2 ->
            {
                for(int v = 18250; v < 19400; v += 300)
                {
                    shrubs.add(new Shrub(offset + v,260,75,43));
                }
            }
            //Level 3
            case 3 ->
            {
                for(int v = 2525; v < 4200; v += 275)
                {
                    shrubs.add(new Shrub(offset + v,435,75,43));
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeTelevision(int offset)
    {
        switch (level)
        {
            //Level 2
            case 2 ->
            {
                for(int x = 150; x < 1200; x += 300)
                {
                    televisions.add(new Television(offset + x,450,61,50));
                }
                for(int x = 8750; x < 9600; x += 300)
                {
                    televisions.add(new Television(offset + x,275,61,50));
                }
                for(int x = 13850; x < 14025; x += 300)
                {
                    televisions.add(new Television(offset + x,150,61,50));
                }
                for(int x = 17100; x < 17975; x += 200)
                {
                    televisions.add(new Television(offset + x,350,61,50));
                }
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeLightPosts(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for(int v = 775; v < 1700; v += 350)
                {
                    light_posts.add(new Light_Post(offset + v,400,41,100));
                }
                light_posts.add(new Light_Post(offset + 2050,325,41,100));

                light_posts.add(new Light_Post(offset + 2450,250,41,100));

                for(int v = 3075; v < 3600; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,400,41,100));
                }
                for(int v = 4650; v < 4975; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,75,41,100));
                }

                light_posts.add(new Light_Post(offset + 5075,200,41,100));

                for(int v = 6200; v < 6900; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,375,41,100));
                }
                for(int v = 8175; v < 9025; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,175,41,100));
                }
                for(int v = 11100; v < 11800; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,300,41,100));
                }
                for(int v = 11200; v < 11950; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,125,41,100));
                }
                for(int v = 12350; v < 12425; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,200,41,100));
                }
            }
            //Level 2
            case 2 ->
            {
                for(int v = 100; v < 1200; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,400,41,100));
                }
                for(int v = 1600; v < 2500 ; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,200,41,100));
                }
                for(int v = 2725; v < 4000; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,200,41,100));
                }
                for(int v = 4300; v < 4600; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,200,41,100));
                }
                for(int v = 6700; v < 7900; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,400,41,100));
                }
                for(int v = 8700; v < 9600; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,225,41,100));
                }
                for(int v = 11600; v < 12000; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,400,41,100));
                }
                for(int v = 12300; v < 12625; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,350,41,100));
                }
                for(int v = 12800; v < 13125; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,300,41,100));
                }
                for(int v = 13300; v < 13625; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,250,41,100));
                }
                for(int v = 13800; v < 14000; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,100,41,100));
                }
                for(int v = 15250; v < 16050; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,375,41,100));
                }
                for(int v = 17050; v < 17975; v += 200)
                {
                    light_posts.add(new Light_Post(offset + v,300,41,100));
                }
                for(int v = 18200; v < 19400; v += 300)
                {
                    light_posts.add(new Light_Post(offset + v,200,41,100));
                }
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makePalmTrees(int offset)
    {
        switch (level)
        {
            //Level 3
            case 3 ->
            {
                for(int x = 100; x < 900; x += 275)
                {
                    palm_trees.add(new Palm_Tree(offset + x,250,93,125));
                }
                for(int x = 2450; x < 4200; x += 275)
                {
                    palm_trees.add(new Palm_Tree(offset + x,350,93,125));
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeUndergroundTitles(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for (int i = 6175; i < 7500; i += UNIT_SIZE)
                {
                    groundTiles.add(new Ground(offset + i, 500, UNIT_SIZE, UNIT_SIZE));
                }
            }
            //Level 2
            case 2 ->
            {
                for(int i = 1500; i < 2500 ; i += UNIT_SIZE)
                {
                    for(int p = 325; p < 525; p += UNIT_SIZE)
                    {
                        groundTiles.add(new Ground(offset + i, p, UNIT_SIZE, UNIT_SIZE));
                    }
                }
                for(int i = 15150; i < 16050; i += UNIT_SIZE)
                {
                    groundTiles.add(new Ground(offset + i, 500,UNIT_SIZE,UNIT_SIZE));
                }
                for(int i = 19000; i < 19400; i += UNIT_SIZE)
                {
                    for(int p = 325; p < 525; p += UNIT_SIZE)
                    {
                        groundTiles.add(new Ground(offset + i, p, UNIT_SIZE, UNIT_SIZE));
                    }
                }
            }
            //Level 3
            case 3 ->
            {
                for(int i = 0; i < 900 ; i += UNIT_SIZE)
                {
                    for(int p = 400; p < 525; p += UNIT_SIZE)
                    {
                        groundTiles.add(new Ground(offset + i, p, UNIT_SIZE, UNIT_SIZE));
                    }
                }
                for(int i = 2300; i < 4300 ; i += UNIT_SIZE)
                {
                    groundTiles.add(new Ground(offset + i, 500, UNIT_SIZE, UNIT_SIZE));
                }
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeRegBlocks(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for(int x = 1900; x < 2300; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,425,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 2250; x < 2650; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,350,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 4150; x < 5350; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 4300; x < 4350; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 250; y < 300; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 4475; x < 5075; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 175; y < 225; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 5275; x < 5325; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,100,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 5500; x < 5550; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,225,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 8000; x < 9025; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 275; y < 325; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                normal_blocks.add(new Normal_Block(offset + 9025,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));

                int slant = 325;

                for(int x = 9050; x < 9650; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,slant,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    if(slant <= 450){slant += Game_Panel.UNIT_SIZE;}
                }

                slant = 325;

                for(int x = 9025; x < 9650; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,slant,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    if(slant <= 475){slant += Game_Panel.UNIT_SIZE;}
                }

                for(int x = 9200; x < 10000; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 200; y < 325; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 9800; x < 10000; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 325; y < 525; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 10200; x < 10250; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 350; y < 400; y += Game_Panel.UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 10525; x < 10625; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,400,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int x = 10800; x < 10875; x += UNIT_SIZE)
                {
                    for(int y = 350; y < 400; y += UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 11000; x < 11900; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,250,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
                for(int x = 11050; x < 11850; x += Game_Panel.UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,225,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }

                for(int x = 10800; x < 12100; x += Game_Panel.UNIT_SIZE)
                {
                    for(int y = 400; y < 450; y += UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 12025; x < 12100; x += UNIT_SIZE)
                {
                    for(int y = 350; y < 400; y += UNIT_SIZE)
                    {
                        normal_blocks.add(new Normal_Block(offset + x,y,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                    }
                }

                for(int x = 12300; x < 12425; x += UNIT_SIZE)
                {
                    normal_blocks.add(new Normal_Block(offset + x,300,Game_Panel.UNIT_SIZE,Game_Panel.UNIT_SIZE));
                }
            }
            //Level 2
            case 2 ->
            {
                /*
                    No blocks in this level.
                    Just air and chill man.
                */
            }
            //Level 3
            case 3 ->
            {
                normal_blocks.add(new Normal_Block(offset + 1000,375,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                normal_blocks.add(new Normal_Block(offset + 1300,325,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                normal_blocks.add(new Normal_Block(offset + 1600,425,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                normal_blocks.add(new Normal_Block(offset + 4400,425,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                normal_blocks.add(new Normal_Block(offset + 4700,400,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                normal_blocks.add(new Normal_Block(offset + 5000,325,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeDropBlock(int offset)
    {
        switch (level)
        {
            //Level 2
            case 2 ->
            {
                for(int x = 14200; x < 15050; x += 50)
                {
                    drop_blocks.add(new Drop_Block(offset + x,300,50,25));
                }
                drop_blocks.add(new Drop_Block(offset + 16200,450,50,25));
                drop_blocks.add(new Drop_Block(offset + 16475,425,50,25));
                drop_blocks.add(new Drop_Block(offset + 16750,400,50,25));
                drop_blocks.add(new Drop_Block(offset + 19600,250,50,25));
                drop_blocks.add(new Drop_Block(offset + 19800,300,50,25));
                drop_blocks.add(new Drop_Block(offset + 20000,350,50,25));
            }
            //Level 3
            case 3 -> {}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeBouceBlocks(int offset)
    {
        switch (level)
        {
            //Level 3
            case 3 ->
            {
                bounce_blocks.add(new Bounce_Block(offset + 1800,325,Game_Panel.UNIT_SIZE*4,Game_Panel.UNIT_SIZE));
                bounce_blocks.add(new Bounce_Block(offset + 5400,500,Game_Panel.UNIT_SIZE*2,Game_Panel.UNIT_SIZE));
                bounce_blocks.add(new Bounce_Block(offset + 5800,475,Game_Panel.UNIT_SIZE*2,Game_Panel.UNIT_SIZE));
                bounce_blocks.add(new Bounce_Block(offset + 6200,500,Game_Panel.UNIT_SIZE*2,Game_Panel.UNIT_SIZE));
                bounce_blocks.add(new Bounce_Block(offset + 6600,435,Game_Panel.UNIT_SIZE*2,Game_Panel.UNIT_SIZE));
            }
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeRamen(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                for(int v = 925; v < 1700; v += 350)
                {
                    ramen.add(new Ramen(offset + v,450,50,50));
                }
                for(int v = 3025; v < 3600; v += 300)
                {
                    ramen.add(new Ramen(offset + v,450,50,50));
                }
            }
            //Level 2
            case 2 ->
            {
                for(int v = 1650; v < 2500; v += 200)
                {
                    ramen.add(new Ramen(offset + v,250,50,50));
                }
                for(int v = 4900; v < 6600; v += 200)
                {
                    ramen.add(new Ramen(offset + v,160,50,50));
                }
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeCrates(int offset)
    {
        switch (level)
        {
            //Level 2
            case 2 ->
            {
                crates.add(new Crate(offset + 13425,325,60,54));
            }
            //Level 3
            case 3 -> {}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeBox(int offset)
    {
        switch (level)
        {
            //Level 2
            case 2 ->
            {
                for(int x = 7100; x < 7250; x += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + x,375,50,50));
                }
                for(int x = 7150; x < 7300; x += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + x,325,50,50));
                }
                for(int x = 7200; x < 7300; x += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + x,275,50,50));
                }
                for(int y = 400; y < 500; y += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + 10500,y,50,50));
                }
                for(int x = 10625; x < 10725; x += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + x,300,50,50));
                }
                for(int y = 400; y < 500; y += (UNIT_SIZE*2))
                {
                    boxes.add(new Box(offset + 10850,y,50,50));
                }
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    void makeRandoBox(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                random_boxes.add(new Random_Box(offset + 6650, 325, 50, 50));
                random_boxes.add(new Random_Box(offset + 8625, 125, 50, 50));
            }
            //Level 2
            case 2 ->
            {
                random_boxes.add(new Random_Box(offset + 10675,175,50,50));
                random_boxes.add(new Random_Box(offset + 10725,300,50,50));
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeLifeBlocks(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                life_blocks.add(new Life_Block(offset + 10250,350,50,50));
            }
            //Level 2
            case 2 ->
            {
                life_blocks.add(new Life_Block(offset + 7250,225,50,50));
            }
            //Level 3
            case 3 ->
            {

            }
            //Level 4
            case 4 ->
            {

            }
            //Level 5
            case 5 ->
            {

            }
            //Level 6
            case 6 ->
            {

            }
            //Level 7
            case 7 ->
            {

            }
        }
    }
    protected void makeDirtyBubble(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                dirtyBubbles.add(new DirtyBubble(offset + 2250,375,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 2400,300,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 5025,125,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 5025,250,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 8975,225,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 9550,425,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 9925,150,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 8416,225,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 11800,200,50,50));
            }
            //Level 2
            case 2 ->
            {
                dirtyBubbles.add(new DirtyBubble(offset + 12575,400,50,50));
                dirtyBubbles.add(new DirtyBubble(offset + 13075,350,50,50));
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeTomoe(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                tomoes.add(new Tomoe(offset + 7150,425,50,50));
                tomoes.add(new Tomoe(offset + 11450,350,50,50));
            }
            //Level 2
            case 2 ->
            {
                tomoes.add(new Tomoe(offset + 7500,425,50,50));
                tomoes.add(new Tomoe(offset + 15750,425,50,50));
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeOnikasa(int offset)
    {
        switch (level)
        {
            case 3 ->
            {
                onikasa.add(new Onikasa(offset + 800, 225,105,150));
                onikasa.add(new Onikasa(offset + 3200, 325,105,150));
            }
        }
    }
    protected void makeTorii(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->{toris.add(new Tori(offset + 9575,375,79,100));}
            //Level 2
            case 2 ->{toris.add(new Tori(offset + 18800,400,79,100));}
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeRedClouds(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                redClouds.add(new RedCloud(offset + 400,425,100,50));
                redClouds.add(new RedCloud(offset + 650,350,100,50));
                redClouds.add(new RedCloud(offset + 5850,350,100,50));
                redClouds.add(new RedCloud(offset + 6075,425,100,50));
            }
            //Level 2
            case 2 ->
            {
                redClouds.add(new RedCloud(offset + 8050,425,100,50));
                redClouds.add(new RedCloud(offset + 8350,350,100,50));
                redClouds.add(new RedCloud(offset + 9850,350,100,50));
                redClouds.add(new RedCloud(offset + 10150,425,100,50));
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeGoldenClouds(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->
            {
                goldenClouds.add(new GoldenCloud(offset + 3750,425,150,67));
                goldenClouds.add(new GoldenCloud(offset + 7700,400,150,67));
            }
            //Level 2
            case 2 ->
            {
                goldenClouds.add(new GoldenCloud(offset + 1250,425,150,67));
            }
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeBlueCloud(int offset)
    {
        switch (level)
        {
            //Level 2
            case 2 ->
            {
                blueClouds.add(new BlueCloud(offset + 4800,300,91,50));
            }
            //Level 3
            case 3 -> {}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeRamenIcon()
    {
        switch (level)
        {
            case 1 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 2 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 3 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 4 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 5 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 6 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
            case 7 ->{ramen_icons.add(new Ramen_Icon(25,25,50,50));}
        }
    }
    protected void makeLifeIcon()
    {
        switch (level)
        {
            case 1 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 2 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 3 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 4 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 5 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 6 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
            case 7 ->{lifeIcons.add(new Life_Icon(825,25,50,50));}
        }
    }
    protected void makeSign(int offset)
    {
        switch (level)
        {
            //Level 1
            case 1 ->{signs.add(new Sign(offset + 12650,450,56,50));}
            //Level 2
            case 2 ->{signs.add(new Sign(offset + 20250,450,56,50));}
            //Level 3
            case 3 ->{}
            //Level 4
            case 4 ->{}
            //Level 5
            case 5 ->{}
            //Level 6
            case 6 ->{}
            //Level 7
            case 7 ->{}
        }
    }
    protected void makeTimesUp(int offset)
    {
        switch (level)
        {
            case 1 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 2 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 3 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 4 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 5 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 6 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
            case 7 ->{times_ups.add(new Times_Up(offset + 623,150,275,275));}
        }
    }
    protected void makeGameOver()
    {
        switch (level)
        {
            case 1 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 2 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 3 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 4 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 5 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 6 ->{game_overs.add(new Game_Over(200,150,500,300));}
            case 7 ->{game_overs.add(new Game_Over(200,150,500,300));}
        }
    }
    //Drawing Method
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
        if(Start_Panel.paused){Game_Panel.pause_menu.draw(g2D);}
        if(break_screen.b_screen == true){break_screen.draw(g2D);}
    }
    //Drawing Method
    protected void draw(Graphics2D g2D)
    {
        /*for(int i = 0; i < WIDTH/UNIT_SIZE; i++)
        {
             g2D.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,HEIGHT);
             g2D.drawLine(0,i*UNIT_SIZE,WIDTH,i*UNIT_SIZE);
        }*/
        for(Background_Clouds cloudsTypeA: background_clouds01s){cloudsTypeA.draw(g2D);}
        for(Background_Clouds02 background_clouds02: background_clouds02s){background_clouds02.draw(g2D);}
        for(Background_Clouds03 background_clouds03: background_clouds03s){background_clouds03.draw(g2D);}
        for(Shrub shrub: shrubs){shrub.draw(g2D);}
        for(Television television: televisions){television.draw(g2D);}
        for(Light_Post light_post: light_posts){light_post.draw(g2D);}
        for(Palm_Tree palm_tree: palm_trees){palm_tree.draw(g2D);}
        for(Land land: landTiles){land.draw(g2D);}
        for(Ground ground: groundTiles){ground.draw(g2D);}
        for(Ocean ocean: oceans){ocean.draw(g2D);}
        for(Life_Icon life_icon: lifeIcons){life_icon.draw(g2D);}
        for(Ramen_Icon ramen_icon: ramen_icons){ramen_icon.draw(g2D);}
        Game_Panel.playerLife.draw(g2D);
        Game_Panel.ramen_score.draw(g2D);
        time_limit.draw(g2D);
        for(Sign sign: signs){sign.draw(g2D);}
        for(Tori tori: toris){tori.draw(g2D);}
        for(RedCloud redCloud: redClouds){redCloud.drawCloud(g2D);}
        for(GoldenCloud goldenCloud: goldenClouds){goldenCloud.drawCloud(g2D);}
        for(BlueCloud blueCloud: blueClouds){blueCloud.drawCloud(g2D);}
        for(Normal_Block normal_block: normal_blocks){normal_block.drawBlock(g2D);}
        for(Drop_Block drop_block: drop_blocks){drop_block.drawBlock(g2D);}
        for(Bounce_Block bounce_block: bounce_blocks){bounce_block.drawBlock(g2D);}
        for(Life_Block life_block: life_blocks){life_block.drawBlock(g2D);}
        for(Crate crate: crates){crate.drawBlock(g2D);}
        for(Box box: boxes){box.drawBlock(g2D);}
        for(Random_Box random_box: random_boxes){random_box.drawBlock(g2D);}
        for(Ramen ramen1: ramen){ramen1.draw(g2D);}
        for(DirtyBubble dirtyBubble: dirtyBubbles){dirtyBubble.drawBubble(g2D);}
        for(Onikasa onikasa1: onikasa){onikasa1.drawBubble(g2D);}
        for(Tomoe tomoe: tomoes){tomoe.drawBubble(g2D);}
        Game_Panel.ninja.draw(g2D);
        for(Game_Over game_over: game_overs){game_over.draw(g2D);}
        for(Times_Up times_up: times_ups){times_up.draw(g2D);}
    }
    void reset()
    {
        if(Game_Panel.playerLife.life > 0)
        {
            Game_Panel.ninja.keyCamera = false;
            Game_Panel.ninja.x = 100;
            Game_Panel.ninja.y = 300;
            Game_Panel.cameraX = 290;
            Game_Panel.offset = -290;
            Game_Panel.ninja.xSpeed = 0;
            Game_Panel.ninja.ySpeed = 0;

            if(Main.rsc.clip != null)
            {
                Main.rsc.clip.stop(); Main.rsc.clip.close();
            }
            if(Main.hus.clip != null)
            {
                Main.hus.clip.stop(); Main.hus.clip.close();
            }
            if(Main.ras.clip != null)
            {
                Main.ras.clip.stop(); Main.ras.clip.close();
            }
            if(Main.lfs.clip != null)
            {
                Main.lfs.clip.stop(); Main.lfs.clip.close();
            }
            if(Main.world01Theme.clip != null)
            {
                Main.world01Theme.clip.stop();
                Main.world01Theme.clip.close();
            }
            if(Main.world02Theme.clip != null)
            {
                Main.world02Theme.clip.stop();
                Main.world02Theme.clip.close();
            }
            if(Main.world03Theme.clip != null)
            {
                Main.world03Theme.clip.stop();
                Main.world03Theme.clip.close();
            }

            background_clouds01s.clear(); background_clouds02s.clear(); background_clouds03s.clear();
            lifeIcons.clear(); ramen_icons.clear();
            oceans.clear(); landTiles.clear(); groundTiles.clear();
            shrubs.clear(); televisions.clear(); light_posts.clear(); palm_trees.clear();
            signs.clear(); toris.clear(); ramen.clear();
            redClouds.clear(); goldenClouds.clear(); blueClouds.clear();
            normal_blocks.clear(); drop_blocks.clear(); bounce_blocks.clear(); life_blocks.clear();
            crates.clear(); boxes.clear(); random_boxes.clear();
            dirtyBubbles.clear(); tomoes.clear(); onikasa.clear();

            makeBackground(offset); makeOcean();
            makeLandTiles(offset); makeUndergroundTitles(offset);
            makeLifeIcon(); makeRamenIcon();
            makeShrubs(offset); makeTelevision(offset); makeRamen(offset); makeLightPosts(offset);
            makeSign(offset); makeTorii(offset); makePalmTrees(offset);
            makeRedClouds(offset); makeGoldenClouds(offset); makeBlueCloud(offset);
            makeRegBlocks(offset); makeDropBlock(offset); makeBouceBlocks(offset); makeLifeBlocks(offset);
            makeCrates(offset); makeBox(offset); makeRandoBox(offset);
            makeDirtyBubble(offset); makeTomoe(offset); makeOnikasa(offset);
            soundtracks();

            Game_Panel.playerLife.life--;
        }
        Game_Panel.playerLife.newLife--;

        if(Game_Panel.playerLife.newLife == -2)
        {
            makeGameOver();

            TimerTask end = new TimerTask()
            {
                @Override
                public void run()
                {
                    System.exit(0);
                }
            };
            long delay = 2000L;
            timer.schedule(end,delay);
        }
    }
    void deathByTime()
    {
        if(time_limit.time == 0)
        {
            Game_Panel.ninja.keyDead = true;
            Game_Panel.ninja.keyRight = false;
            Game_Panel.ninja.keyLeft = false;
            Game_Panel.ninja.keyJump = false;
            Game_Panel.ninja.key1stAttack = false;
            ninja.key2ndAttack = false;

            makeTimesUp(offset);

            TimerTask end = new TimerTask()
            {
                @Override
                public void run()
                {
                    System.exit(0);
                }
            };
            long delay = 2000L;
            timer.schedule(end,delay);
        }
    }
    void keyPressed(KeyEvent e)
    {
        if(e.getKeyChar() == 'd' && !Start_Panel.paused && !ninja.keyDead && !ninja.keyBlueCloud){ninja.keyRight = true;}
        if(e.getKeyChar() == 'a' && !Start_Panel.paused && !ninja.keyDead && !ninja.keyBlueCloud){ninja.keyLeft = true;}
        if(e.getKeyChar() == '2' && !Start_Panel.paused && !ninja.keyDead){ninja.keyJump = true;}
        if(e.getKeyChar() == '6' && !Start_Panel.paused && !ninja.keyDead)
        {
            ninja.key2ndAttack = true;

            TimerTask attack2 = new TimerTask()
            {
                @Override
                public void run()
                {
                    ninja.key2ndAttack = false;
                }
            };
            long delay = 250L;
            timer.schedule(attack2,delay);
        }
        if(e.getKeyChar() == '4' && !Start_Panel.paused && !ninja.keyDead)
        {
            ninja.key1stAttack = true;

            TimerTask attack3 = new TimerTask()
            {
                @Override
                public void run()
                {
                    ninja.key1stAttack = false;
                }
            };
            long delay = 250L;
            timer.schedule(attack3,delay);
        }
        if(e.getKeyChar() == '\n' && !ninja.keyDead)
        {
            Start_Panel.paused = true;

            if(Start_Panel.paused)
            {
                try
                {
                    timer.wait();
                } catch (InterruptedException ex)
                {
                    throw new RuntimeException(ex);
                }
                pause_menu.start();
                pause_menu.Menu(0,0,WIDTH,HEIGHT);
            }
        }
        if(e.getKeyChar() == '\b')
        {
            Start_Panel.paused = false;

            if(!Start_Panel.paused)
            {
                timer.notify();
            }
        }
    }
    void keyReleased(KeyEvent e)
    {
        if(e.getKeyChar() == 'd'){ninja.keyRight = false;}
        if(e.getKeyChar() == 'a'){ninja.keyLeft = false;}
        if(e.getKeyChar() == '2'){ninja.keyJump = false;}
        if(e.getKeyChar() == '6'){ninja.key2ndAttack = false;}
        if(e.getKeyChar() == '4'){ninja.key1stAttack = false;}
    }
}
