package com.squidtopusstudios.zerobit.util.loaders;

import com.uwsoft.editor.renderer.data.*;

import java.util.ArrayList;

/**
 * Extended Composite VO that allows recursive unpacking of composites
 */
public class CompositeVOEx extends CompositeVO {

    public CompositeVOEx(CompositeVO vo) {
        if (vo == null) return;
        update(vo);
    }

    public ArrayList<MainItemVO> getAllItems(boolean unpackComposites) {
        ArrayList<MainItemVO> itemsList = new ArrayList<>();
        itemsList = getAllItemsRecursive(itemsList, this, unpackComposites);
        return itemsList;
    }
    @Override
    public ArrayList<MainItemVO> getAllItems() {
        return getAllItems(true);
    }

    private ArrayList<MainItemVO> getAllItemsRecursive(ArrayList<MainItemVO> itemsList, CompositeVO compositeVo, boolean unpackComposites) {
        for(MainItemVO vo: compositeVo.sButtons) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sCheckBoxes) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sImage9patchs) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sImages) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sLabels) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sLights) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sParticleEffects) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sSelectBoxes) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sSpineAnimations) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sSpriteAnimations) {
            itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sSpriterAnimations) {
        	itemsList.add(vo);
        }
        for(MainItemVO vo: compositeVo.sTextBox) {
            itemsList.add(vo);
        }
        for(CompositeItemVO vo: compositeVo.sComposites) {
            if (unpackComposites) {
                itemsList = getAllItemsRecursive(itemsList, vo.composite);
            }
            itemsList.add(vo);
        }

        return itemsList;
    }

    private ArrayList<MainItemVO> getAllItemsRecursive(ArrayList<MainItemVO> itemsList, CompositeVO compositeVo) {
        return getAllItemsRecursive(itemsList, compositeVo, true);
    }
}
