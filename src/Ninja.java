import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Ninja
{
    boolean keyForwards = true, keyBackwards, keyLeft, keyRight, key2ndAttack, key1stAttack, keyJump, keyDead, keyCamera, keyBlueCloud, keyTomoeDead;
    Image murasaki = new ImageIcon("images/kuroimaru san.png").getImage();
    Image murasaki_dead = new ImageIcon("images/kuroimaru san dead.png").getImage();
    Image murasaki_running = new ImageIcon("images/kuroimaru san running.png").getImage();
    Image murasaki_running_backwards = new ImageIcon("images/kuroimaru san running backwards.png").getImage();
    Image murasaki_backing = new ImageIcon("images/kuroimaru san backwards.png").getImage();
    Image murasaki_jumping = new ImageIcon("images/kuroimaru san jumping.png").getImage();
    Image murasaki_jumping_backwards = new ImageIcon("images/kuroimaru san jumping backwards.png").getImage();
    Image murasaki_1stform = new ImageIcon("images/kuroihana 3rd form.png").getImage();
    Image murasaki_2ndform = new ImageIcon("images/kuroihana 2nd form.png").getImage();
    Image murasaki_3rdform = new ImageIcon("images/kuroihana 2nd form backwards.png").getImage();
    Image murasaki_pushing = new ImageIcon("images/kuroimaru_pushing.png").getImage();
    Image murasaki_pushing_reverse = new ImageIcon("images/kuroimaru_pushing_reverse.png").getImage();
    Game_Panel gamePanel;
    Rectangle hitBox;
    Timer timer = new Timer();
    int x,y,width,height;
    double xSpeed,ySpeed;
    Ninja(int x, int y, Game_Panel gamePanel)
    {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        width = this.murasaki.getWidth(null);
        height = this.murasaki.getHeight(null);
        hitBox = new Rectangle(x,y,width,height);
    }
    protected void sizeReset()
    {
        width = this.murasaki.getWidth(null);
        height = this.murasaki.getHeight(null);
        hitBox.width = width;
        hitBox.height = height;
    }

    protected void Set()
    {
        if(keyLeft && keyRight || !(keyLeft) && !(keyRight)){xSpeed *= 0.5;}
        else if(keyRight && !(keyLeft)){xSpeed++;}
        else if(keyLeft && !(keyRight)){xSpeed--;}
        //slide prevention.
        if(xSpeed > 0 && xSpeed < 0.75){xSpeed = 0;}
        else if (xSpeed < 0 && xSpeed > -0.75){xSpeed = 0;}
        //Jumping code.
        if(keyJump)
        {
            hitBox.y ++;
            landTilesJump();
            redCloudJump(); goldenCloudJump(); blueCloudJump();
            regBlockJump(); lifeBlockJump();
            crateJump(); boxJump();
            dropBlockJump();
            hitBox.y --;
        }
        ySpeed += 0.3;
        //speed limiter.
        if(xSpeed > 5){xSpeed = 5;}
        else if (xSpeed < -5){xSpeed = -5;}
        //x collision checker.
        hitBox.x += xSpeed;
        landTilesXCollisionCheck(); groundTilesXCollision();
        redCloudXCollisionCheck(); goldenCloudXCollisionCheck(); blueCloudXCollisionCheck();
        regBlockXCollisionCheck(); dropBlockXCollisionCheck(); bounceBlockXCollision(); lifeBlockXCollisionCheck();
        crateXCollisionCheck(); boxXCollisionCheck(); randoBoxXCollisionCheck();
        dirtyBubbleXCollisionCheck();
        tomoeXCollisionCheck();
        //y collision checker.
        hitBox.y += ySpeed;
        landTilesYCollisionCheck(); groundTilesYCollision();
        redCloudYCollisionCheck(); goldenCloudYCollisionCheck(); blueCloudYCollisionCheck();
        regBlockYCollisionCheck(); dropBlockYCollisionCheck(); bounceBlockYCollision(); lifeBlockYCollisionCheck();
        crateYCollisionCheck(); boxYCollisionCheck(); randoBoxYCollisionCheck();
        dirtyBubbleYCollisionCheck();
        tomoeYCollisionCheck();

        signCollisionCheck();
        toriiCollisionCheck();
        ramenObtain();

        if(x <= 280 || keyBackwards || keyCamera){x += xSpeed;}
        else if(!keyCamera){gamePanel.cameraX -= xSpeed;}
        //Code to stop player from going off-screen backwards.
        if(x <= 1){xSpeed = 1;}

        y += ySpeed;

        hitBox.x = x;
        hitBox.y = y;
        //Level change code.
        if(x > 900)
        {
            Main.lfs.Audio();

            if(Game_Panel.level != 2)
            {
                Game_Panel.level += 1;
            } else if(Game_Panel.level == 2)
            {
                Game_Panel.level += 2;
            }
            gamePanel.reset();
            gamePanel.time_limit.seconds = 59;
            gamePanel.time_limit.minutes = 30;
            gamePanel.time_limit.time = 1860;
        }
        //Death code.
        if(y > 700)
        {
            keyDead = true;
            keyRight = false;
            keyLeft = false;
            keyJump = false;
            key1stAttack = false;
            key2ndAttack = false;

            gamePanel.reset();
            gamePanel.ramen_score.score = 0;

            TimerTask dead = new TimerTask()
            {
                @Override
                public void run()
                {
                    keyDead = false;
                }
            };
            long death = 100L;
            timer.schedule(dead,death);
        }
    }
    protected void actionSet1st()
    {
        if(key1stAttack && !key2ndAttack || key1stAttack && keyRight && !key2ndAttack && !key2ndAttack || key1stAttack && keyLeft && !key2ndAttack )
        {
            width = this.murasaki_1stform.getWidth(null);
            height = this.murasaki_1stform.getHeight(null);
            hitBox.width = width;
            hitBox.height = height;

            for(int n = 0; n < gamePanel.dirtyBubbles.size(); n++)
            {
                DirtyBubble dirtyBubble = new DirtyBubble(gamePanel.dirtyBubbles.get(n).x,gamePanel.dirtyBubbles.get(n).y,gamePanel.dirtyBubbles.get(n).width,gamePanel.dirtyBubbles.get(n).height);
                if(hitBox.intersects(dirtyBubble.hitBox))
                {
                    Main.ras.Audio();
                    gamePanel.dirtyBubbles.remove(n);
                }
            }
            for(int n = 0; n < gamePanel.life_blocks.size(); n++)
            {
                if(hitBox.intersects(gamePanel.life_blocks.get(n).hitBox) && key1stAttack)
                {
                    if(gamePanel.playerLife.life != gamePanel.playerLife.MAXLIFE)
                    {
                        Main.hus.Audio();
                        gamePanel.playerLife.life += 1;
                        gamePanel.playerLife.newLife += 1;
                    }
                    gamePanel.life_blocks.remove(gamePanel.life_blocks.get(n));
                }
            }
            for(int n = 0; n < gamePanel.crates.size(); n++)
            {
                if(hitBox.intersects(gamePanel.crates.get(n).hitBox) && key1stAttack)
                {
                    Crate.keyCrate = false;
                }
            }
            for(int n = 0; n < gamePanel.tomoes.size(); n++)
            {
                if(hitBox.intersects(gamePanel.tomoes.get(n).hitBox))
                {
                    Main.ras.Audio();
                    keyTomoeDead = true;
                    gamePanel.tomoes.remove(n);
                }
            }
            for(int x = 0; x < gamePanel.random_boxes.size(); x++)
            {
                if(hitBox.intersects(gamePanel.random_boxes.get(x).hitBox))
                {
                    gamePanel.random_boxes.get(x).attacked = true;
                    gamePanel.random_boxes.get(x).RandomPrize();
                    gamePanel.random_boxes.remove(x);
                }
            }
            ramenObtain();
        }
        sizeReset();
    }
    protected void actionSet2nd()
    {
        if((key2ndAttack && !key1stAttack && !keyBackwards) || (key2ndAttack && keyRight && !key1stAttack))
        {
            width = this.murasaki_2ndform.getWidth(null);
            height = this.murasaki_2ndform.getHeight(null);
            hitBox.width = width;
            hitBox.height = height;

            hitBox.y ++;

            for(Land land: gamePanel.landTiles)
            {
                if(hitBox.intersects(land.hitBox)) {ySpeed = -5;}
            }
            for(RedCloud redCloud: gamePanel.redClouds)
            {
                if(hitBox.intersects(redCloud.hitBox)) {ySpeed = -5;}
            }
            for(GoldenCloud goldenCloud: gamePanel.goldenClouds)
            {
                if(hitBox.intersects(goldenCloud.hitBox)) {ySpeed = -5;}
            }
            for(BlueCloud blueCloud: gamePanel.blueClouds)
            {
                if(blueCloud.hitBox.intersects(hitBox)) {ySpeed = -5;}
            }
            for(Normal_Block normal_block: gamePanel.normal_blocks)
            {
                if(hitBox.intersects(normal_block.hitBox)) {ySpeed = -5;}
            }
            for(Drop_Block drop_block: gamePanel.drop_blocks)
            {
                if(hitBox.intersects(drop_block.hitBox)) {ySpeed = -5;}
            }
            for(Bounce_Block bounce_block: gamePanel.bounce_blocks)
            {
                if(hitBox.intersects(bounce_block.hitBox)) {ySpeed -= 5;}
            }
            for(int n = 0; n < gamePanel.dirtyBubbles.size(); n++)
            {
                DirtyBubble dirtyBubble = new DirtyBubble(gamePanel.dirtyBubbles.get(n).x,gamePanel.dirtyBubbles.get(n).y,gamePanel.dirtyBubbles.get(n).width,gamePanel.dirtyBubbles.get(n).height);
                if(hitBox.intersects(dirtyBubble.hitBox))
                {
                    Main.ras.Audio();
                    gamePanel.dirtyBubbles.remove(n);
                }
            }
            for(int n = 0; n < gamePanel.life_blocks.size(); n++)
            {
                if(hitBox.intersects(gamePanel.life_blocks.get(n).hitBox) && key2ndAttack)
                {
                    if(gamePanel.playerLife.life != gamePanel.playerLife.MAXLIFE)
                    {
                        Main.hus.Audio();
                        gamePanel.playerLife.life += 1;
                        gamePanel.playerLife.newLife += 1;
                    }
                    gamePanel.life_blocks.remove(gamePanel.life_blocks.get(n));
                }
            }
            for(Box box: gamePanel.boxes)
            {
                if(box.hitBox.intersects(hitBox)){ySpeed = -5;}
            }
            for(int n = 0; n < gamePanel.tomoes.size(); n++)
            {
                if(hitBox.intersects(gamePanel.tomoes.get(n).hitBox))
                {
                    Main.ras.Audio();
                    keyTomoeDead = true;
                    gamePanel.tomoes.remove(n);
                }
            }
            for(int x = 0; x < gamePanel.random_boxes.size(); x++)
            {
                if(hitBox.intersects(gamePanel.random_boxes.get(x).hitBox))
                {
                    gamePanel.random_boxes.get(x).attacked = true;
                    gamePanel.random_boxes.get(x).RandomPrize();
                    gamePanel.random_boxes.remove(x);
                }
            }
            ramenObtain();
            hitBox.y --;
        }
        ySpeed += 0.3;

        sizeReset();
    }
    protected void actionSet3rd()
    {
        if((key2ndAttack && !key1stAttack && keyBackwards))
        {
            this.x = x-2;
            width = this.murasaki_3rdform.getWidth(null);
            height = this.murasaki_3rdform.getHeight(null);
            hitBox.width = width;
            hitBox.height = height;

            hitBox.y ++;

            for(Land land: gamePanel.landTiles)
            {
                if(hitBox.intersects(land.hitBox)) {ySpeed = -5; /*xSpeed += (-25);*/}
            }
            for(RedCloud redCloud: gamePanel.redClouds)
            {
                if(hitBox.intersects(redCloud.hitBox)) {ySpeed = -5;}
            }
            for(GoldenCloud goldenCloud: gamePanel.goldenClouds)
            {
                if(hitBox.intersects(goldenCloud.hitBox)) {ySpeed = -5;}
            }
            for(BlueCloud blueCloud: gamePanel.blueClouds)
            {
                if(blueCloud.hitBox.intersects(hitBox)) {ySpeed = -5;}
            }
            for(Normal_Block normal_block: gamePanel.normal_blocks)
            {
                if(hitBox.intersects(normal_block.hitBox)) {ySpeed = -5;}
            }
            for(Drop_Block drop_block: gamePanel.drop_blocks)
            {
                if(hitBox.intersects(drop_block.hitBox)) {ySpeed = -5;}
            }
            for(Bounce_Block bounce_block: gamePanel.bounce_blocks)
            {
                if(hitBox.intersects(bounce_block.hitBox)) {ySpeed -= 5;}
            }
            for(int n = 0; n < gamePanel.dirtyBubbles.size(); n++)
            {
                DirtyBubble dirtyBubble = new DirtyBubble(gamePanel.dirtyBubbles.get(n).x,gamePanel.dirtyBubbles.get(n).y,gamePanel.dirtyBubbles.get(n).width,gamePanel.dirtyBubbles.get(n).height);
                if(hitBox.intersects(dirtyBubble.hitBox))
                {
                    Main.ras.Audio();
                    gamePanel.dirtyBubbles.remove(n);
                }
            }
            for(int n = 0; n < gamePanel.life_blocks.size(); n++)
            {
                if(hitBox.intersects(gamePanel.life_blocks.get(n).hitBox) && key2ndAttack)
                {
                    if(gamePanel.playerLife.life != gamePanel.playerLife.MAXLIFE)
                    {
                        Main.hus.Audio();
                        gamePanel.playerLife.life += 1;
                        gamePanel.playerLife.newLife += 1;
                    }
                    gamePanel.life_blocks.remove(gamePanel.life_blocks.get(n));
                }
            }
            for(Box box: gamePanel.boxes)
            {
                if(box.hitBox.intersects(hitBox)){ySpeed = -5;}
            }
            for(int n = 0; n < gamePanel.tomoes.size(); n++)
            {
                if(hitBox.intersects(gamePanel.tomoes.get(n).hitBox))
                {
                    Main.ras.Audio();
                    keyTomoeDead = true;
                    gamePanel.tomoes.remove(n);
                }
            }
            for(int x = 0; x < gamePanel.random_boxes.size(); x++)
            {
                if(hitBox.intersects(gamePanel.random_boxes.get(x).hitBox))
                {
                    gamePanel.random_boxes.get(x).attacked = true;
                    gamePanel.random_boxes.get(x).RandomPrize();
                    gamePanel.random_boxes.remove(x);
                }
            }
            ramenObtain();
            hitBox.y --;
        }
        ySpeed += 0.3;

        if(x <= 1){xSpeed = 4;}

        sizeReset();
    }
    protected void actionPush()
    {
        if(Crate.keyCrate && keyForwards)
        {
            width = this.murasaki_pushing.getWidth(null);
            height = this.murasaki_pushing.getHeight(null);
            hitBox.width = width;
            hitBox.height = height;
        } else if(Crate.keyCrate && keyBackwards)
        {
            width = this.murasaki_pushing_reverse.getWidth(null);
            height = this.murasaki_pushing_reverse.getHeight(null);
            hitBox.width = width;
            hitBox.height = height;
        }
    }
    protected void dyingAction()
    {
        keyDead = true;
        keyRight = false;
        keyLeft = false;
        keyJump = false;
        key1stAttack = false;
        key2ndAttack = false;

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if(keyDead)
                {
                    ySpeed = -8;
                    gamePanel.reset();
                    gamePanel.ramen_score.score = 0;
                }
            }
        };
        long delay = 1000L;
        timer.schedule(task,delay);

        TimerTask dead = new TimerTask()
        {
            @Override
            public void run()
            {
                keyDead = false;
            }
        };
        long death = 1000L;
        timer.schedule(dead,death);
    }
    void landTilesJump()
    {
        for(Land land: gamePanel.landTiles)
        {
            if(land.hitBox.intersects(hitBox)){ySpeed = -8;}
        }
    }
    void redCloudJump()
    {
            for(RedCloud redCloud: gamePanel.redClouds)
            {
                if(redCloud.hitBox.intersects(hitBox)) {ySpeed = -8;}
            }
    }
    void goldenCloudJump()
    {
        for(GoldenCloud goldenCloud: gamePanel.goldenClouds)
        {
            if(goldenCloud.hitBox.intersects(hitBox)) {ySpeed = -8;}
        }
    }
    void blueCloudJump()
    {
        for(BlueCloud blueCloud: gamePanel.blueClouds)
        {
            if(blueCloud.hitBox.intersects(hitBox)) {ySpeed = -8;}
        }
    }
    void regBlockJump()
    {
        for(Normal_Block normal_block: gamePanel.normal_blocks)
        {
            if(normal_block.hitBox.intersects(hitBox)) {ySpeed = -8;}
        }
    }
    void lifeBlockJump()
    {
        for(Life_Block life_block: gamePanel.life_blocks)
        {
            if (life_block.hitBox.intersects(hitBox)) {ySpeed = -8;}
        }
    }
    void crateJump()
    {
        for(Crate crate: gamePanel.crates)
        {
            if(crate.hitBox.intersects(hitBox)) {ySpeed = -8;}
        }
    }
    void boxJump()
    {
        for(Box box: gamePanel.boxes)
        {
            if(box.hitBox.intersects(hitBox)){ySpeed = -8;}
        }
    }
    void dropBlockJump()
    {
        for(Drop_Block drop_block: gamePanel.drop_blocks)
        {
            if (drop_block.hitBox.intersects(hitBox))
            {
                ySpeed = -8;
            }
        }
    }
    void signCollisionCheck()
    {
        for(int z = 0; z < gamePanel.signs.size(); z++)
        {
            int xDiff = gamePanel.signs.get(z).x - this.x;

            if(xDiff <= 110)
            {
                keyCamera = true;
                x += xSpeed;
            }
        }
    }
    void dropAction()
    {
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                for(int drop = 0; drop < gamePanel.drop_blocks.size(); drop++)
                {
                    float xiff = gamePanel.drop_blocks.get(drop).x - x
                            ,yiff = gamePanel.drop_blocks.get(drop).y - y;

                    if((xiff < 25 && xiff > -25) && (yiff < 50.5 && yiff > -1.5))
                    {
                        gamePanel.drop_blocks.get(drop).stepped_on = true;
                        gamePanel.drop_blocks.get(drop).velocity += 0.3;

                        if(gamePanel.drop_blocks.get(drop).stepped_on == true)
                        {
                            gamePanel.drop_blocks.get(drop).hitBox.y += gamePanel.drop_blocks.get(drop).velocity;
                        }
                        gamePanel.drop_blocks.get(drop).y = gamePanel.drop_blocks.get(drop).hitBox.y;
                    }
                    if(!(xiff < 25 && xiff > -25) && (yiff < 50.5 && yiff > -1.5))
                    {
                        gamePanel.drop_blocks.get(drop).stepped_on = false;
                    }
                    gamePanel.drop_blocks.get(drop).hitBox.y = gamePanel.drop_blocks.get(drop).y;
                    //System.out.println("x: "+xiff+" || y: "+yiff);
                }
            }
        }, 13000L);
    }
    void crateAction()
    {
        for(int i = 0; i < gamePanel.crates.size(); i++)
        {
            float xdiff = gamePanel.crates.get(i).x - this.x
                    , ydiff = gamePanel.crates.get(i).y - this.y;

            gamePanel.crates.get(i).y += gamePanel.crates.get(i).y_velocity;

            if((xdiff >= 40 && xdiff <= 60.50 && ydiff == 0.0) || (xdiff >= -68.2 && xdiff <= -40 && ydiff == 0.0))
            {
                Crate.keyCrate = true;
            } else
            {
                Crate.keyCrate = false;
            }
            //System.out.println("Crate Key: "+keyCrate);
        }
    }
    void goldenCloudAction()
    {
        for(int i = 0; i < gamePanel.goldenClouds.size(); i++)
        {
            float yDiff = gamePanel.goldenClouds.get(i).y - this.y
                    , xDiff = gamePanel.goldenClouds.get(i).x - this.x;

            if(yDiff <= 75.2 && yDiff >= 49.25 && xDiff <= -20.3 && xDiff >= -75.3 && gamePanel.goldenClouds.get(i).golden_note < 125)
            {
                gamePanel.goldenClouds.get(i).y -= gamePanel.goldenClouds.get(i).golden_velocity;
                gamePanel.goldenClouds.get(i).golden_note++;
            }
            if(gamePanel.goldenClouds.get(i).golden_note == 125 && xDiff <= -140 && xDiff >= -500 || gamePanel.goldenClouds.get(i).golden_note == 125 && keyDead == true)
            {
                gamePanel.goldenClouds.get(i).golden_note = 0;
            }
            gamePanel.goldenClouds.get(i).hitBox.y = gamePanel.goldenClouds.get(i).y;
            //System.out.println("x: "+xDiff+" | y: "+yDiff);
        }
    }
    void blueCloudAction()
    {
        for(int i = 0; i < gamePanel.blueClouds.size(); i++)
        {
            float yistance = gamePanel.blueClouds.get(i).y - this.y
                    , xistance = gamePanel.goldenClouds.get(i).x - this.x;

            if((xistance >= -5200.0 && xistance <= -3500) && (yistance >= 49.5 && yistance < 200) && (gamePanel.blueClouds.get(i).note < 1600))
            {
                gamePanel.blueClouds.get(i).startX += gamePanel.blueClouds.get(i).cloudspeed;
                xSpeed += gamePanel.blueClouds.get(i).cloudspeed;
                gamePanel.blueClouds.get(i).note++;
                keyBlueCloud = true;
            }
            if(gamePanel.blueClouds.get(i).note == 1600)
            {
                keyBlueCloud = false;
            }
            if((gamePanel.blueClouds.get(i).note == 1600 && xistance >= -5220.25 &&  xistance <= -5320.45) || (gamePanel.blueClouds.get(i).note == 1600 && keyDead == true))
            {
                gamePanel.blueClouds.get(i).note = 0;
            }
            gamePanel.blueClouds.get(i).hitBox.x = gamePanel.blueClouds.get(i).startX;
            //System.out.println("xistance: "+xistance+" yistance: "+yistance+" note: "+gamePanel.blueClouds.get(i).note+" key: "+keyBlueCloud);
        }
    }
    void dirtyBubbleAnimation()
    {
        javax.swing.Timer bubble_animation = new javax.swing.Timer(110, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int c = 0; c < gamePanel.dirtyBubbles.size(); c++)
                {
                    gamePanel.dirtyBubbles.get(c).bubble_animation++;

                    if(gamePanel.dirtyBubbles.get(c).bubble_animation <= 5)
                    {
                        gamePanel.dirtyBubbles.get(c).open = false;
                        gamePanel.dirtyBubbles.get(c).closed = true;
                    } else if(gamePanel.dirtyBubbles.get(c).bubble_animation < 10)
                    {
                        gamePanel.dirtyBubbles.get(c).closed = false;
                        gamePanel.dirtyBubbles.get(c).open = true;
                    }
                    if(gamePanel.dirtyBubbles.get(c).bubble_animation == 10)
                    {
                        gamePanel.dirtyBubbles.get(c).bubble_animation = 0;
                    }
                    //System.out.println("Animation: "+gamePanel.dirtyBubbles.get(c).bubble_animation);
                }
            }
        }); bubble_animation.start();
    }
    void tomoeAnimation()
    {
        javax.swing.Timer tomoe_animation = new javax.swing.Timer(70, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int c = 0; c < gamePanel.tomoes.size(); c++)
                {
                    gamePanel.tomoes.get(c).distance = gamePanel.tomoes.get(c).x - gamePanel.ninja.x;

                    if(gamePanel.tomoes.get(c).distance >= 0)
                    {
                        if(gamePanel.tomoes.get(c).distance <= 400)
                        {
                            gamePanel.tomoes.get(c).high = false;
                            gamePanel.tomoes.get(c).mid = false;
                            gamePanel.tomoes.get(c).low = false;
                            gamePanel.tomoes.get(c).mad = true;
                        }
                        else
                        {
                            gamePanel.tomoes.get(c).mad = false;
                            gamePanel.tomoes.get(c).tomoe_animation++;

                            if(gamePanel.tomoes.get(c).tomoe_animation <= 3)
                            {
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = false;
                                gamePanel.tomoes.get(c).low = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 6)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 9)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).mid = false;
                                gamePanel.tomoes.get(c).high = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 11)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = true;
                            }
                            if(gamePanel.tomoes.get(c).tomoe_animation == 13)
                            {
                                gamePanel.tomoes.get(c).tomoe_animation = 0;
                            }
                        }
                    } else if(gamePanel.tomoes.get(c).distance < 0)
                    {
                        if(gamePanel.tomoes.get(c).distance <= 400)
                        {
                            gamePanel.tomoes.get(c).high = false;
                            gamePanel.tomoes.get(c).mid = false;
                            gamePanel.tomoes.get(c).low = false;
                            gamePanel.tomoes.get(c).mad = true;
                        }
                        else
                        {
                            gamePanel.tomoes.get(c).mad = false;
                            gamePanel.tomoes.get(c).tomoe_animation++;

                            if(gamePanel.tomoes.get(c).tomoe_animation <= 3)
                            {
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = false;
                                gamePanel.tomoes.get(c).low = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 6)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 9)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).mid = false;
                                gamePanel.tomoes.get(c).high = true;
                            } else if(gamePanel.tomoes.get(c).tomoe_animation <= 11)
                            {
                                gamePanel.tomoes.get(c).low = false;
                                gamePanel.tomoes.get(c).high = false;
                                gamePanel.tomoes.get(c).mid = true;
                            }
                            if(gamePanel.tomoes.get(c).tomoe_animation == 13)
                            {
                                gamePanel.tomoes.get(c).tomoe_animation = 0;
                            }
                        }
                    }
                    //System.out.println("Distance: "+gamePanel.tomoes.get(c).distance);
                }
            }
        });tomoe_animation.start();
    }
    void tomoeMovement()
    {
        javax.swing.Timer tomoe_pace = new javax.swing.Timer(15, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int c = 0; c < gamePanel.tomoes.size(); c++)
                {
                    float positionx = gamePanel.tomoes.get(c).x
                            , ydiff = gamePanel.tomoes.get(c).y - y;

                    if(gamePanel.tomoes.get(c).distance > 0 && gamePanel.tomoes.get(c).mad && ydiff <= 125.50 && ydiff >= 0.0)
                    {
                        gamePanel.tomoes.get(c).velocity += 0.5;

                        if(gamePanel.tomoes.get(c).velocity > 6.5)
                        {
                            gamePanel.tomoes.get(c).velocity = 6.5;
                        }
                        gamePanel.tomoes.get(c).startX -= gamePanel.tomoes.get(c).velocity;

                        if(hitBox.intersects(gamePanel.tomoes.get(c).hitBox))
                        {
                            gamePanel.tomoes.get(c).velocity = 0;
                        }
                    }else if(gamePanel.tomoes.get(c).distance < 0 && gamePanel.tomoes.get(c).mad && ydiff <= 125.50 && ydiff >= 0.0)
                    {
                        gamePanel.tomoes.get(c).velocity += 0.5;

                        if(gamePanel.tomoes.get(c).velocity > 3.5)
                        {
                            gamePanel.tomoes.get(c).velocity = 3.5;
                        }
                        gamePanel.tomoes.get(c).startX += gamePanel.tomoes.get(c).velocity;

                        if(hitBox.intersects(gamePanel.tomoes.get(c).hitBox))
                        {
                            gamePanel.tomoes.get(c).velocity = 0;
                        }
                    }
                    gamePanel.tomoes.get(c).hitBox.x = gamePanel.tomoes.get(c).x;
                    System.out.println("Velocity: "+gamePanel.tomoes.get(c).velocity);
                }
            }
        }); tomoe_pace.start();
    }
    void dirtyBubbleMovement()
    {
        javax.swing.Timer bubble_pace = new javax.swing.Timer(15, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int c = 0; c < gamePanel.dirtyBubbles.size(); c++)
                {
                    if(gamePanel.dirtyBubbles.get(c).bubble_tracker < 150 && gamePanel.dirtyBubbles.get(c).bubble_note > 0)
                    {
                        gamePanel.dirtyBubbles.get(c).startX -= gamePanel.dirtyBubbles.get(c).bubble_velocity;
                        gamePanel.dirtyBubbles.get(c).bubble_tracker++; gamePanel.dirtyBubbles.get(c).bubble_note--;
                    }
                    if (gamePanel.dirtyBubbles.get(c).bubble_tracker == 150 && gamePanel.dirtyBubbles.get(c).bubble_note != 150)
                    {
                        gamePanel.dirtyBubbles.get(c).startX += gamePanel.dirtyBubbles.get(c).bubble_velocity;
                        gamePanel.dirtyBubbles.get(c).bubble_note++;
                    }
                    if(gamePanel.dirtyBubbles.get(c).bubble_tracker == 150 && gamePanel.dirtyBubbles.get(c).bubble_note == 150)
                    {
                        gamePanel.dirtyBubbles.get(c).bubble_tracker = 0;
                    }
                    gamePanel.dirtyBubbles.get(c).hitBox.x = gamePanel.dirtyBubbles.get(c).x;
                    //System.out.println("Tracker: "+gamePanel.dirtyBubbles.get(c).bubble_tracker+": Note: "+gamePanel.dirtyBubbles.get(c).bubble_note);
                }
            }
        }); bubble_pace.start();
    }
    void landTilesXCollisionCheck()
    {
        for(Land land: gamePanel.landTiles)
        {
            if(hitBox.intersects(land.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!land.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void groundTilesXCollision()
    {
        for(Ground ground: gamePanel.groundTiles)
        {
            if(hitBox.intersects(ground.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!ground.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void dirtyBubbleXCollisionCheck()
    {
        for(DirtyBubble dirtyBubble: gamePanel.dirtyBubbles)
        {
            if(hitBox.intersects(dirtyBubble.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.x -= xSpeed;

                while(!dirtyBubble.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;

                dyingAction();
            }
        }
    }
    void tomoeXCollisionCheck()
    {
        for(Tomoe tomoe: gamePanel.tomoes)
        {
            if(hitBox.intersects(tomoe.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.x -= xSpeed;

                while(!tomoe.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;

                dyingAction();
            }
        }
    }
    void ramenObtain()
    {
        for(int r = 0; r < gamePanel.ramen.size(); r++)
        {
            if(hitBox.intersects(gamePanel.ramen.get(r).hitBox))
            {
                Main.rsc.Audio();
                gamePanel.ramen.remove(r);
                if(gamePanel.ramen_score.score != 100)
                {
                    gamePanel.ramen_score.score++;
                    if(gamePanel.ramen_score.score == 10 || gamePanel.ramen_score.score == 20)
                    {
                        Main.hus.Audio();
                        gamePanel.playerLife.life += 1;
                        gamePanel.playerLife.newLife += 1;
                    }
                }
            }
        }
    }
    void toriiCollisionCheck()
    {
        for(int a = 0; a < gamePanel.toris.size(); a++)
        {
            if(hitBox.intersects(gamePanel.toris.get(a).hitBox))
            {
                Main.lfs.Audio();
                gamePanel.level += 2;
                gamePanel.reset();
                gamePanel.time_limit.seconds = 59;
                gamePanel.time_limit.minutes = 30;
                gamePanel.time_limit.time = 1860;
            }
        }
    }
    void regBlockXCollisionCheck()
    {
        for(Normal_Block normal_block: gamePanel.normal_blocks)
        {
            if(hitBox.intersects(normal_block.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!normal_block.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void bounceBlockXCollision()
    {
        for(Bounce_Block bounce_block: gamePanel.bounce_blocks)
        {
            if(hitBox.intersects(bounce_block.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!bounce_block.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void lifeBlockXCollisionCheck()
    {
        for(Life_Block life_block: gamePanel.life_blocks)
        {
            if(hitBox.intersects(life_block.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.x -= xSpeed;

                while(!life_block.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void crateXCollisionCheck()
    {
        if(keyForwards)
        {
            for(int i = 0; i < gamePanel.crates.size(); i++)
            {
                gamePanel.crates.get(i).x_velocity *= 0.5; gamePanel.crates.get(i).x_velocity += 0.3;
                gamePanel.crates.get(i).startX += gamePanel.crates.get(i).x_velocity;

                if(hitBox.intersects(gamePanel.crates.get(i).hitBox))
                {
                    hitBox.x -= xSpeed;
                    gamePanel.crates.get(i).x_velocity += 2;

                    while(!gamePanel.crates.get(i).hitBox.intersects(hitBox))

                        hitBox.x += Math.signum(xSpeed);
                    hitBox.x -= Math.signum(xSpeed);
                    gamePanel.cameraX += x - hitBox.x;
                    xSpeed = 0;
                    hitBox.x = x;
                }
                gamePanel.crates.get(i).x = gamePanel.crates.get(i).hitBox.x;
                gamePanel.crates.get(i).hitBox.x = gamePanel.crates.get(i).x;
            }
        } else if(keyBackwards)
        {
            for(Crate crate: gamePanel.crates)
            {
                if (hitBox.intersects(crate.hitBox))
                {
                    hitBox.x -= xSpeed;

                    while (!crate.hitBox.intersects(hitBox))

                        hitBox.x += Math.signum(xSpeed);
                    hitBox.x -= Math.signum(xSpeed);
                    gamePanel.cameraX += x - hitBox.x;
                    xSpeed = 0;
                    hitBox.x = x;
                }
            }
        }
    }
    void boxXCollisionCheck()
    {
        for(Box box: gamePanel.boxes)
        {
            if(hitBox.intersects(box.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.x -= xSpeed;

                while(!box.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void randoBoxXCollisionCheck()
    {
        for(Random_Box random_box: gamePanel.random_boxes)
        {
            if(hitBox.intersects(random_box.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.x -= xSpeed;

                while(!random_box.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void dropBlockXCollisionCheck()
    {
        for(Drop_Block drop_block: gamePanel.drop_blocks)
        {
            if(hitBox.intersects(drop_block.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!drop_block.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void redCloudXCollisionCheck()
    {
        for(RedCloud redCloud: gamePanel.redClouds)
        {
            if(hitBox.intersects(redCloud.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!redCloud.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void goldenCloudXCollisionCheck()
    {
        for(GoldenCloud goldenCloud: gamePanel.goldenClouds)
        {
            if(hitBox.intersects(goldenCloud.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!goldenCloud.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void blueCloudXCollisionCheck()
    {
        for(BlueCloud blueCloud: gamePanel.blueClouds)
        {
            if(hitBox.intersects(blueCloud.hitBox))
            {
                hitBox.x -= xSpeed;

                while(!blueCloud.hitBox.intersects(hitBox))

                    hitBox.x += Math.signum(xSpeed);
                hitBox.x -= Math.signum(xSpeed);
                gamePanel.cameraX += x - hitBox.x;
                xSpeed = 0;
                hitBox.x = x;
            }
        }
    }
    void landTilesYCollisionCheck()
    {
        for(Land land: gamePanel.landTiles)
        {
            if(hitBox.intersects(land.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!land.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void groundTilesYCollision()
    {
        for(Ground ground: gamePanel.groundTiles)
        {
            if(hitBox.intersects(ground.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!ground.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void dirtyBubbleYCollisionCheck()
    {
        hitBox.y += ySpeed;

        for(DirtyBubble dirtyBubble: gamePanel.dirtyBubbles)
        {
            if(hitBox.intersects(dirtyBubble.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.y -= ySpeed;

                while(!dirtyBubble.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;

                dyingAction();
            }
        }
    }
    void tomoeYCollisionCheck()
    {
        for(Tomoe tomoe: gamePanel.tomoes)
        {
            if(hitBox.intersects(tomoe.hitBox) && !key1stAttack && !key2ndAttack)
            {
                hitBox.y -= ySpeed;

                while(!tomoe.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                gamePanel.cameraX += y - hitBox.y;
                ySpeed = 0;
                y = hitBox.y;

                dyingAction();
            }
        }
    }
    void regBlockYCollisionCheck()
    {
        for(Normal_Block normal_block: gamePanel.normal_blocks)
        {
            if(hitBox.intersects(normal_block.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!normal_block.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void bounceBlockYCollision()
    {
        for(Bounce_Block bounce_block: gamePanel.bounce_blocks)
        {
            if(hitBox.intersects(bounce_block.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!bounce_block.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed -= 15;
                y = hitBox.y;
            }
        }
    }
    void lifeBlockYCollisionCheck()
    {
        for(Life_Block life_block: gamePanel.life_blocks)
        {
            if(hitBox.intersects(life_block.hitBox) && !key2ndAttack)
            {
                hitBox.y -= ySpeed;

                while(!life_block.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void crateYCollisionCheck()
    {
        for(Crate crate: gamePanel.crates)
        {
            if(hitBox.intersects(crate.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!crate.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void boxYCollisionCheck()
    {
        for(Box box: gamePanel.boxes)
        {
            if(hitBox.intersects(box.hitBox) && !key2ndAttack)
            {
                hitBox.y -= ySpeed;

                while(!box.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void randoBoxYCollisionCheck()
    {
        for(Random_Box random_box: gamePanel.random_boxes)
        {
            if(hitBox.intersects(random_box.hitBox) && !key2ndAttack)
            {
                hitBox.y -= ySpeed;

                while(!random_box.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void dropBlockYCollisionCheck()
    {
        for(Drop_Block drop_block: gamePanel.drop_blocks)
        {
            if(hitBox.intersects(drop_block.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!drop_block.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void redCloudYCollisionCheck()
    {
        for(RedCloud redCloud: gamePanel.redClouds)
        {
            if(hitBox.intersects(redCloud.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!redCloud.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void goldenCloudYCollisionCheck()
    {
        for(GoldenCloud goldenCloud: gamePanel.goldenClouds)
        {
            if(hitBox.intersects(goldenCloud.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!goldenCloud.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    void blueCloudYCollisionCheck()
    {
        for(BlueCloud blueCloud: gamePanel.blueClouds)
        {
            if(hitBox.intersects(blueCloud.hitBox))
            {
                hitBox.y -= ySpeed;

                while(!blueCloud.hitBox.intersects(hitBox))

                    hitBox.y += Math.signum(ySpeed);
                hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;
            }
        }
    }
    //Draws image.
    protected void draw(Graphics2D g2D)
    {
        if((keyLeft && keyRight && keyForwards && !Crate.keyCrate) || (key1stAttack && key2ndAttack) || (!(keyLeft) && !(keyRight) && !(keyJump) && !(key2ndAttack) && !(key1stAttack) && !(keyDead)  && !(Crate.keyCrate) && keyForwards))
        {
            g2D.drawImage(murasaki,x,y,null);
        }
        else if ((keyLeft && keyRight && keyBackwards && !Crate.keyCrate) || (!(keyLeft) && !(keyRight) && !(keyJump) && !(key2ndAttack) && !(key1stAttack) && !(keyDead) && !(Crate.keyCrate) && keyBackwards))
        {
            g2D.drawImage(murasaki_backing,x,y,null);
        }
        else if(keyRight && !(keyLeft) && !(Crate.keyCrate) && !(key2ndAttack) && !(key1stAttack) && !(keyDead))
        {
            keyBackwards = false;
            keyForwards = true;
            g2D.drawImage(murasaki_running,x,y,null);
        }
        else if(keyLeft && !(keyRight) && !(Crate.keyCrate) && !(key2ndAttack) && !(key1stAttack) && !(keyDead))
        {
            keyForwards = false;
            keyBackwards = true;
            g2D.drawImage(murasaki_running_backwards,x,y,null);
        }
        if(keyJump && keyForwards && !(Crate.keyCrate) && !(keyRight) && !(keyLeft) && !(key2ndAttack) && !(key1stAttack) && !(keyDead))
        {
            g2D.drawImage(murasaki_jumping,x,y,null);
        }
        else if (keyJump && keyBackwards && !(Crate.keyCrate) && !(keyRight) && !(keyLeft) && !(key2ndAttack) && !(key1stAttack) && !(keyDead))
        {
            g2D.drawImage(murasaki_jumping_backwards,x,y,null);
        }
        if(key1stAttack && keyForwards && !(Crate.keyCrate) && !key2ndAttack)
        {
            g2D.drawImage(murasaki_1stform,x,y,null);
        }
        else if (key1stAttack && !(Crate.keyCrate) && keyBackwards && !key2ndAttack)
        {
            g2D.drawImage(murasaki_backing,x,y,null);
        }
        if(key2ndAttack && !(Crate.keyCrate) && keyForwards && !key1stAttack)
        {
            g2D.drawImage(murasaki_2ndform,x,y,null);
        }
        else if (key2ndAttack && !(Crate.keyCrate) && keyBackwards && !key1stAttack)
        {
            g2D.drawImage(murasaki_3rdform,x,y,null);
        }
        if(Crate.keyCrate && keyRight)
        {
            g2D.drawImage(murasaki_pushing,x,y,null);
        }
        if(Crate.keyCrate && keyForwards && !keyRight)
        {
            g2D.drawImage(murasaki,x,y,null);
        }
        if(Crate.keyCrate && keyBackwards && keyLeft)
        {
            g2D.drawImage(murasaki_running_backwards,x,y,null);
        }
        if(Crate.keyCrate && keyBackwards && !keyLeft)
        {
            g2D.drawImage(murasaki_backing,x,y,null);
        }
        if(keyDead)
        {
            g2D.drawImage(murasaki_dead,x,y,null);
        }
    }
}
