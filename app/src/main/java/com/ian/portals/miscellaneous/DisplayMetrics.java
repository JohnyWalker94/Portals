package com.ian.portals.miscellaneous;

import android.content.res.Resources;

import com.ian.portals.models.Point;

/**
 * Created by Ian.
 */

public class DisplayMetrics
{
    //widthTilesCount/heightTilesCount: the number of tiles to be drawn horizontally/vertically
    private int widthTilesCount;
    private int heightTilesCount;

    //offsetX/offsetY: the calculated space from the edge of the screen to the map
    private int offsetX;
    private int offsetY;

    //tileWidth/tileHeight: the width & height of each tile
    private int tileWidth;
    private int tileHeight;


    /**
     * @param widthTilesCount Number of tiles to be drawn horizontally
     * @param heightTilesCount Number of tiles to be drawn vertically
     */
    public DisplayMetrics(int widthTilesCount, int heightTilesCount)
    {
        setWidthTilesCount(widthTilesCount);
        setHeightTilesCount(heightTilesCount);

        setOffsetX(2);
        setOffsetY(2);

        setTileWidth(50);
        setTileWidth(50);
    }

    public int getTileWidth()
    {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth)
    {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight()
    {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight)
    {
        this.tileHeight = tileHeight;
    }

    public int getWidthTilesCount()
    {
        return widthTilesCount;
    }

    public void setWidthTilesCount(int widthTilesCount)
    {
        this.widthTilesCount = widthTilesCount;
    }

    public int getHeightTilesCount()
    {
        return heightTilesCount;
    }

    public void setHeightTilesCount(int heightTilesCount)
    {
        this.heightTilesCount = heightTilesCount;
    }

    public int getOffsetX()
    {
        return offsetX;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public int getOffsetY()
    {
        return offsetY;
    }

    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }

    public int getDisplayWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getDisplayHeight()
    {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * @return Returns a 2D-map of the coordinates where the tiles will be drawn
     */
    @SuppressWarnings("RedundantCast")
    public Point[][] getTileCoordinates()
    {
        Point[][] mTileCoordinates = new Point[getWidthTilesCount()][getHeightTilesCount()];

        int width, height;
        int tileCount;
        int displayWidth, displayHeight;

        displayWidth = getDisplayWidth();
        displayHeight = getDisplayHeight();

        setTileWidth((int) ((displayWidth - getWidthTilesCount() + 1) / getWidthTilesCount()));
        setTileHeight((int) ((displayHeight - getHeightTilesCount() + 1) / getHeightTilesCount()));

        setOffsetX((displayWidth - (getTileWidth() * getWidthTilesCount())) / 2);
        setOffsetY((displayHeight - (getTileHeight() * getHeightTilesCount())) / 2);

        width = getOffsetX();
        height = getOffsetY();

        tileCount = 1;

        for(int i=0; i<getWidthTilesCount(); i++)
        {
            for(int j=0; j<getHeightTilesCount(); j++)
            {
                mTileCoordinates[i][j] = new Point();
                mTileCoordinates[i][j].setDimensions(width, height);

                height = height + getTileHeight();
            }
            width = width + getTileWidth() + 1;
            height = getOffsetY();
        }

        for(int j=getHeightTilesCount(); j>0; j--)
        {
            for(int i=0; i<getWidthTilesCount(); i++)
            {
                if(j%2 == 0)
                {
                    mTileCoordinates[i][j-1].setIndex(tileCount);
                }
                else
                {
                    mTileCoordinates[getWidthTilesCount()-i-1][j-1].setIndex(tileCount);
                }
                tileCount++;
            }
        }

        return mTileCoordinates;
    }
}
