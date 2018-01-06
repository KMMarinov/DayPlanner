package com.kalinmarinov.dayplanner.views.containers.calendar;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public final class GridPosition {

    private final int positionX;
    private final int positionY;

    private GridPosition(final int positionX, final int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public static GridPosition of(final int positionX, final int positionY) {
        return new GridPosition(positionX, positionY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GridPosition that = (GridPosition) o;

        if (positionX != that.positionX) {
            return false;
        }
        return positionY == that.positionY;
    }

    @Override
    public int hashCode() {
        int result = positionX;
        result = 31 * result + positionY;
        return result;
    }
}
