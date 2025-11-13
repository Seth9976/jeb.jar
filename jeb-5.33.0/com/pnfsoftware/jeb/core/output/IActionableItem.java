package com.pnfsoftware.jeb.core.output;

public interface IActionableItem {
   long ITEM_TYPE_MASK = -72057594037927936L;
   long ITEM_TYPE_IDENTITY = -144115188075855872L;
   int ROLE_MASTER = 1;
   int HAS_RELATED_ITEMS = 2;

   long getItemId();

   int getItemFlags();
}
