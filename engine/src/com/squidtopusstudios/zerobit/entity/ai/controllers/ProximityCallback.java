package com.squidtopusstudios.zerobit.entity.ai.controllers;

import com.badlogic.gdx.physics.box2d.Body;

import java.util.Vector;

/** The callback object used by a proximity to report the owner's neighbor.
 *
	 * @param <T> Type of vector, either 2D or 3D, implementing the {@link Vector} interface
	 *
	 * @author davebaol */
	public interface ProximityCallback<T extends Vector<T>> {

		/** The callback method used to report a neighbor.
		 * @param neighbour the reported neighbor.
		 * @return {@code true} if the given neighbor is valid; {@code false} otherwise. */
		public boolean reportNeighbourBody(Body neighbour);

	}