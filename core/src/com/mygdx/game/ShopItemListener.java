package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShopItemListener extends ClickListener {
	
	private ShopItemSlot ref;
	private Label label = new Label("Test", BaseGame.labelStyle);
	private boolean clicked = false;
	
	public ShopItemListener(ShopItemSlot slot) {
		this.ref = slot;
		BaseScreen.uiStage.addActor(label);
		label.setFontScale(1.0f);
		label.setColor(Color.GOLD);
		label.setVisible(false);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if(LevelScreen.wastelander.getInventory().canAfford(ref.getCanPrice(), ref.getBottlePrice(), ref.getPaperPrice(), ref.getWoodPrice()) && !ref.playerHasEffect()) {
			ref.activateEffect();
			ref.buyItem();
			LevelScreen.wastelander.getInventory().deduct(ref.getCanPrice(), ref.getBottlePrice(), ref.getPaperPrice(), ref.getWoodPrice());
		}
		clicked = true;
	}
	
	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		if(!ref.bought()) {
			label.setX(ref.getParent().getX() + ref.getX());
			label.setY(ref.getParent().getY() + ref.getHeight());
			label.setFontScale(1.0f);
			label.setText(ref.getCanPrice() + " cans \n" + ref.getBottlePrice() + " bottles\n" + ref.getPaperPrice() + " paper\n" + ref.getWoodPrice() + " wood");
			label.setVisible(true);
		}
	}
	
	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		if(!clicked) {
			label.setVisible(false);
		} else {
			clicked = false;
		}
	}
	
}
