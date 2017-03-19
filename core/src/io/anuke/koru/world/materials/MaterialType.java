package io.anuke.koru.world.materials;

import static io.anuke.koru.modules.World.tilesize;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import io.anuke.koru.graphics.KoruRenderable;
import io.anuke.koru.graphics.RenderPool;
import io.anuke.koru.modules.World;
import io.anuke.koru.world.Tile;
import io.anuke.ucore.spritesystem.RenderableList;

public abstract class MaterialType{
	public boolean tile, solid;
	public Color color;
	
	public MaterialType(boolean tile, boolean solid){
		this.tile = tile;
		this.solid = solid;
	}
	
	public MaterialType(){
		this(true, false);
	}
	
	protected static World world(){
		return World.instance();
	}
	
	/**Random numer, generated based on coordinates.*/
	protected int rand(int x, int y, int scl){
		int i = (x+y*x);
		MathUtils.random.setSeed(i);
	    return MathUtils.random(1, scl);
	}
	
	/**Centered tile posiiton.*/
	int tile(int i){
		return i * tilesize + tilesize / 2;
	}
	
	/**Uncentered tile position.*/
	int utile(int i){
		return i*tilesize;
	}
	
	/**Variant string.*/
	String variantString(int x, int y, Material material){
		return material.variants() > 1 ? (rand(x,y, material.variants()) + "") : "";
	}
	
	/**Variant number for this position.*/
	int variantNum(int x, int y, Material material){
		return rand(x,y, material.variants())-1;
	}
	
	/**Offset at a certain location.*/
	float variantOffset(int x, int y, Material material){
		return (material.offsets == null ? material.offset : material.offsets[variantNum(x,y, material)]);
	}
	
	/**Shortcut method.*/
	protected KoruRenderable sprite(String name){
		return RenderPool.get(name);
	}

	public boolean tile(){
		return tile;
	}

	public Color getColor(){
		return color;
	}

	public boolean solid(){
		return solid;
	}
	
	public abstract void draw(RenderableList group, Material material, Tile tile, int x, int y);
	
	public int size(){
		return 24;
	}

	public Rectangle getHitbox(int x, int y, Rectangle rectangle){
		return rectangle.set(x * tilesize, y * tilesize, tilesize, tilesize);
	}
}