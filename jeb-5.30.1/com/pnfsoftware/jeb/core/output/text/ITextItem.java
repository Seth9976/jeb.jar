package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.core.output.IItem;

public interface ITextItem extends IItem {
   int getOffset();

   int getLength();

   int getOffsetEnd();

   ILine getLine();

   String getText();
}
