package io.anuke.koru.entities;

import io.anuke.koru.components.DataComponent;
import io.anuke.koru.components.PositionComponent;
import io.anuke.koru.components.RenderComponent;
import io.anuke.koru.renderers.BlockAnimationRenderer;

public class BlockAnimation extends EntityType{

	@Override
	public ComponentList components(){
		return list(new PositionComponent(), new RenderComponent(new BlockAnimationRenderer()), new DataComponent());
	}

}
