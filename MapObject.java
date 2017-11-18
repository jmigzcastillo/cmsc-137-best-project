public abstract MapObject{
	protected int x, y;
	protected float velX = 0, velY = 0;


	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public float getVelX(){
		return this.velX;
	}

	public float getVelY(){
		return this.velY;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
}