import java.util.Vector;

public class GameFrame extends MyFrame {
	public void run() {
		GameWorld.Player=new Player(100,300,0,0);
		addKeyListener(GameWorld.Player);
		GameWorld.stage=1;
		while(true) {
			GameWorld.Player.x=100;
			GameWorld.Player.y=300;

			GameWorld.playerBullets=new Vector<PlayerBullet>();
			GameWorld.enemies=new Vector<Enemy>();
			GameWorld.enemies.add(new EnemyBase(100,50,GameWorld.stage,0));
			GameWorld.enterpressed=false;
			while(true) {
				clear();
				drawString("Stage = "+GameWorld.stage,
						300,50,15);
				drawString("Score ="+GameWorld.score,300,80,15);
				GameWorld.Player.draw(this);
				GameWorld.Player.move();
				movePlayerBullets();
				moveEnemies();
				checkPlayerAndEnemies();
				checkPlayerBulletsAndEnemies();
				if (GameWorld.enemies.size()==0) {
					setColor(0,0,0);
					drawString("クリア!",100,200,40);
					if (GameWorld.enterpressed) {
						GameWorld.stage++;
						break;
					}

				} else if (GameWorld.Player.y<0) {
					setColor(0,0,0);
					drawString("ゲームオーバー!",50,200,40);
					if (GameWorld.enterpressed) {
						GameWorld.stage=1;
						GameWorld.score=0;
						break;
					}

				}
				sleep(0.03);
		 }
		}
	}
		public void moveEnemies() {	 
			 for (int i=0 ; i<GameWorld.enemies.size();i++) {
				 Enemy e=GameWorld.enemies.get(i);
				 e.draw(this);
				 e.move();
			 }
			 
			 int i=0;
			 while (i<GameWorld.enemies.size()) {
				 Enemy e=GameWorld.enemies.get(i);
				 if (e.y>400) {
					 GameWorld.enemies.remove(i);
				 } else {
					 i++;
				 }
			 }
			
			 
		 }
	
		public void movePlayerBullets() {	
			 int i=0;
			 while (i<GameWorld.playerBullets.size()) {
				 PlayerBullet b=GameWorld.playerBullets.get(i);
				 b.draw(this);
				 b.move();
				 if (b.y<0) {
				 GameWorld.playerBullets.remove(i);
				 } else {
					 i++;
				 }
				 
			 }
			 
		 }
		public void checkPlayerAndEnemies() {
	        for (int i = 0; i < GameWorld.enemies.size(); i++) {
	            Enemy e = GameWorld.enemies.get(i);
	            if (checkHit(GameWorld.Player, e)) {
	                System.out.println("やられた!");
	                GameWorld.Player.y = -1000;
	            }
	        }
	    }
		
		public void checkPlayerBulletsAndEnemies() {
			int i=0;
			while (i<GameWorld.playerBullets.size()) {
				//プレイヤー弾一つ一つについて、変数に入れて繰り返し実行する
				PlayerBullet b=GameWorld.playerBullets.get(i);
				int j=0;
				int hits=0;
				while (j<GameWorld.enemies.size()) {
					//敵一つ一つについて、変数に入れて繰り返し実行する
					Enemy e=GameWorld.enemies.get(j);
					if (checkHit(e,b)) {
					///敵eとプレイヤー弾が衝突していたら「あたり」と表示	
						System.out.println("あたり");
						hits++;
						e.life--;
					} 
					if(e.life<=0) {
						GameWorld.score+=e.score;
						GameWorld.enemies.remove(j);
					}
					else {
						j++;
					}
				}
				if (hits>0) {
					GameWorld.playerBullets.remove(i);
				} else {
					
					 i++;
				}
				 
			}
			
			}
		  		
          
		  public boolean checkHit(Character a,Character b) {
		    	if (Math.abs(a.x-b.x)<=20 && Math.abs(a.y-b.y)<=20) {
		    		return true;
		    	
		    	} else {
		    		return false;
		    	}
		    }
}

